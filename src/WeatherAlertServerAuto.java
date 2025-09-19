import javax.swing.*;
import java.net.*;

public class WeatherAlertServerAuto extends JFrame {
    private JTextArea log = new JTextArea();
    private JButton start = new JButton("Start"), stop = new JButton("Stop");
    private volatile boolean running = false;
    private MulticastSocket socket;
    private Thread sendThread;

    public WeatherAlertServerAuto() {
        setTitle("Weather Alert Server");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        log.setEditable(false);
        add(new JScrollPane(log), "Center");

        JPanel panel = new JPanel();
        panel.add(start);
        panel.add(stop);
        add(panel, "South");

        stop.setEnabled(false);
        start.addActionListener(e -> startServer());
        stop.addActionListener(e -> stopServer());
    }

    private void startServer() {
        try {
            socket = new MulticastSocket();
            socket.setTimeToLive(1);
            InetAddress group = InetAddress.getByName("239.255.0.1");
            running = true;
            start.setEnabled(false);
            stop.setEnabled(true);
            log("Server started, sending alerts every 5s...");

            sendThread = new Thread(() -> {
                String[] alerts = {
                        "mưa nhẹ tại Danang | 26.1°C | gió 2.4 km/h | 0.6 mm",
                        "mưa to tại Hanoi | 29.5°C | gió 4.2 km/h | 2.1 mm",
                        "trời nắng tại HCM | 32°C | gió 1.2 km/h | 0 mm"
                };
                int i = 0;
                while (running) {
                    try {
                        String msg = alerts[i % alerts.length];
                        byte[] buf = msg.getBytes("UTF-8");
                        socket.send(new DatagramPacket(buf, buf.length, group, 4446));
                        log("Sent: " + msg);
                        i++;
                        Thread.sleep(5000);
                    } catch (Exception ex) {
                        if (running) log("Error: " + ex);
                    }
                }
            });
            sendThread.start();
        } catch (Exception e) {
            log("Start error: " + e);
        }
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> log.append(msg + "\n"));
    }

    private void stopServer() {
        running = false;
        start.setEnabled(true);
        stop.setEnabled(false);
        try {
            if (socket != null && !socket.isClosed()) socket.close();
            log("Server stopped");
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WeatherAlertServerAuto().setVisible(true));
    }
}