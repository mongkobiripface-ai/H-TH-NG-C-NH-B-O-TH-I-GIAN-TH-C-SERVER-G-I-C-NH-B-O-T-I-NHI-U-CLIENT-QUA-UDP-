import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Weather Alert Client với Admin Login trong cùng một file
 */
public class WeatherAlertClient extends JFrame {
    // ================== Login Admin ==================
    static class AdminLogin extends JFrame {
        private JTextField userField = new JTextField();
        private JPasswordField passField = new JPasswordField();
        private JButton loginBtn = new JButton("Login");

        private final String ADMIN_USER = "admin";
        private final String ADMIN_PASS = "123456";

        public AdminLogin() {
            setTitle("Admin Login");
            setSize(300, 180);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout(10, 10));

            JPanel fields = new JPanel(new GridLayout(2, 2, 5, 5));
            fields.add(new JLabel("Username: "));
            fields.add(userField);
            fields.add(new JLabel("Password: "));
            fields.add(passField);
            add(fields, BorderLayout.CENTER);

            JPanel bottom = new JPanel();
            bottom.add(loginBtn);
            add(bottom, BorderLayout.SOUTH);

            loginBtn.addActionListener(e -> checkLogin());
            passField.addActionListener(e -> checkLogin());
        }

        private void checkLogin() {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (ADMIN_USER.equals(user) && ADMIN_PASS.equals(pass)) {
                SwingUtilities.invokeLater(() -> new WeatherAlertClient().setVisible(true));
                dispose(); // đóng login
            } else {
                JOptionPane.showMessageDialog(this,
                        "Sai tài khoản hoặc mật khẩu",
                        "Lỗi đăng nhập",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ================== Weather Alert Client ==================
    private DefaultTableModel model = new DefaultTableModel(
            new String[]{"Thời gian", "Thành phố", "Thông điệp"}, 0);
    private JTable table = new JTable(model);
    private JTextField searchField = new JTextField();

    private JButton startBtn, stopBtn;
    private volatile boolean running = false;
    private MulticastSocket socket;
    private Thread clientThread;
    private List<String[]> allData = new ArrayList<>();

    public WeatherAlertClient() {
        setTitle("Weather Alert Client");
        setSize(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Giao diện bảng
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
table.setFillsViewportHeight(true);
        table.setSelectionBackground(new Color(135, 206, 250));
        table.setGridColor(Color.LIGHT_GRAY);

        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String msg = table.getValueAt(row, 2).toString().toLowerCase();
                if (msg.contains("nguy hiểm") || msg.contains("bão") || msg.contains("mưa lớn") ||
                        msg.contains("mưa to") || msg.contains("lũ")) {
                    c.setBackground(new Color(255, 228, 225)); // hồng nhạt
                } else {
                    c.setBackground(isSelected ? new Color(135, 206, 250) : Color.WHITE);
                }
                return c;
            }
        });

        // Thanh tìm kiếm
        JPanel top = new JPanel(new BorderLayout(5, 5));
        top.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel searchLabel = new JLabel("Tìm thành phố: ");
        searchLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        top.add(searchLabel, BorderLayout.WEST);
        top.add(searchField, BorderLayout.CENTER);

        // Nút Start/Stop
        startBtn = new JButton("Start Client");
        startBtn.setBackground(new Color(60, 179, 113));
        startBtn.setForeground(Color.WHITE);
        stopBtn = new JButton("Stop Client");
        stopBtn.setBackground(new Color(220, 20, 60));
        stopBtn.setForeground(Color.WHITE);
        stopBtn.setEnabled(false);

        JPanel bottom = new JPanel();
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.add(startBtn);
        bottom.add(stopBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Tìm kiếm
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterData(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterData(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterData(); }
        });

        startBtn.addActionListener(e -> startClient());
        stopBtn.addActionListener(e -> stopClient());
    }

    private void startClient() {
if (running) return;
        running = true;
        allData.clear();
        model.setRowCount(0);

        clientThread = new Thread(() -> {
            try {
                socket = new MulticastSocket(4446);
                InetAddress group = InetAddress.getByName("239.255.0.1");
                socket.joinGroup(group);

                byte[] buf = new byte[1024];
                while (running) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    String city = extractCity(msg);
                    String[] row = {LocalTime.now().withNano(0).toString(), city, msg};
                    allData.add(row);

                    if (isImportant(msg)) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(this,
                                    "Cảnh báo quan trọng tại " + city + ":\n" + msg,
                                    "⚠️ Cảnh báo quan trọng", JOptionPane.WARNING_MESSAGE);
                            Toolkit.getDefaultToolkit().beep();
                        });
                    }

                    SwingUtilities.invokeLater(this::filterData);
                }
                socket.leaveGroup(group);
                socket.close();
            } catch (Exception e) {
                if (running) e.printStackTrace();
            }
        });
        clientThread.start();
        startBtn.setEnabled(false);
        stopBtn.setEnabled(true);
    }

    private void stopClient() {
        running = false;
        try {
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (Exception ignored) {}
        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
    }

    private void filterData() {
        String key = searchField.getText().trim().toLowerCase();
        model.setRowCount(0);
        for (String[] row : allData) {
            if (key.isEmpty() || row[1].toLowerCase().contains(key)) {
                model.addRow(row);
            }
        }
    }

    private String extractCity(String msg) {
        try {
            if (msg.contains("tại")) {
                return msg.split("tại")[1].split("\\|")[0].trim();
            }
        } catch (Exception ignored) {}
        return "Unknown";
    }

    private boolean isImportant(String msg) {
        String lower = msg.toLowerCase();
        return lower.contains("nguy hiểm")
                || lower.contains("bão")
                || lower.contains("mưa lớn")
                || lower.contains("mưa to")
                || lower.contains("lũ");
    }

    // ================== Chạy chương trình ==================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLogin().setVisible(true));
    }
}