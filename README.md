<h2 align="center">
    <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
    🎓 Faculty of Information Technology (DaiNam University)
    </a>
</h2>
<h2 align="center">
    HỆ THỐNG CẢNH BÁO THỜI GIAN THỰC (SERVER GỬI CẢNH BÁO TỚI NHIỀU CLIENT QUA UDP)
</h2>
<div align="center">
    <p align="center">
        <img alt="AIoTLab Logo" width="170" src="https://github.com/user-attachments/assets/711a2cd8-7eb4-4dae-9d90-12c0a0a208a2" />
        <img alt="AIoTLab Logo" width="180" src="https://github.com/user-attachments/assets/dc2ef2b8-9a70-4cfa-9b4b-f6c2f25f1660" />
        <img alt="DaiNam University Logo" width="200" src="https://github.com/user-attachments/assets/77fe0fd1-2e55-4032-be3c-b1a705a1b574" />
    </p>

[![AIoTLab](https://img.shields.io/badge/AIoTLab-green?style=for-the-badge)](https://www.facebook.com/DNUAIoTLab)
[![Faculty of Information Technology](https://img.shields.io/badge/Faculty%20of%20Information%20Technology-blue?style=for-the-badge)](https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin)
[![DaiNam University](https://img.shields.io/badge/DaiNam%20University-orange?style=for-the-badge)](https://dainam.edu.vn)

</div>


## 📖 1. Giới thiệu hệ thống

🖥️ Server

Server có nhiệm vụ phát sinh hoặc thu thập cảnh báo an ninh (cháy nổ, xâm nhập, sự cố kỹ thuật, …) và gửi đến các client qua multicast.

📌 Chức năng:

🚨 Phát cảnh báo: Gửi thông tin cảnh báo an ninh (thời gian, mức độ, loại, khu vực, chi tiết).

🗂️ Quản lý log: Ghi lại lịch sử cảnh báo vào file hoặc hiển thị trên giao diện log.

⚠️ Xử lý lỗi: Hiển thị & lưu lỗi kết nối, gửi/nhận dữ liệu.

🖥️ Giao diện GUI: Quản lý server, theo dõi danh sách cảnh báo đã gửi.

💻 Client

Client tham gia nhóm multicast để nhận dữ liệu và hiển thị cảnh báo trực quan trên GUI.

📌 Chức năng:

🌐 Kết nối multicast: Tham gia nhóm 230.0.0.1:4446 để nhận dữ liệu từ server.

📊 Hiển thị cảnh báo: Trình bày trong bảng (JTable) kèm màu sắc & biểu tượng cho từng mức độ:

LOW 🟢 | MEDIUM 🟡 | HIGH 🟠 | CRITICAL 🔴 | EMERGENCY 🚨

🔔 Thông báo tức thì: Cảnh báo nghiêm trọng (CRITICAL, EMERGENCY) sẽ phát âm thanh và popup cảnh báo.

📑 Lưu trữ log: Ghi nhật ký hoạt động đầy đủ với thời gian.

🔍 Tìm kiếm / lọc: Nhập từ khóa để lọc nhanh trong bảng cảnh báo.

🧪 Chế độ test: Có sẵn nút Test Alert để giả lập cảnh báo khi chưa kết nối server.

🌐 Hệ thống chung

📡 UDP Multicast: Giao tiếp qua nhóm 230.0.0.1:4446 bằng MulticastSocket.

📝 Định dạng dữ liệu: time | level | type | area | detail.

🎨 Biểu tượng trực quan: Icon riêng cho từng mức cảnh báo (vòng tròn màu, tam giác cảnh báo).

💾 Lưu file log: Ghi toàn bộ cảnh báo kèm timestamp.

⚡ Xử lý lỗi: Hệ thống báo lỗi rõ ràng khi có sự cố mạng/kết nối.




## 🔧 2. Công nghệ sử dụng


☕ Java: Ngôn ngữ chính để phát triển hệ thống.

📡 UDP Multicast:

Sử dụng MulticastSocket, DatagramPacket, InetAddress để gửi và nhận dữ liệu.

Cho phép server gửi cảnh báo đồng thời đến nhiều client trong nhóm multicast.

🖥️ Java Swing:

Tạo giao diện đồ họa trực quan (GUI) cho Server và Client.

Thành phần sử dụng: JFrame, JTable, JButton, JTextArea, JScrollPane.

📑 DefaultTableModel: Quản lý dữ liệu cảnh báo và hiển thị trong bảng.

📁 File I/O: Ghi lại lịch sử cảnh báo vào file weather_alerts.log.

⏱️ Timer & TimerTask: Tạo và gửi dữ liệu cảnh báo định kỳ từ Server.

🔄 Đa luồng (Thread):

Xử lý lắng nghe dữ liệu từ server ở client.

Đảm bảo giao diện GUI luôn phản hồi mượt mà.

🎨 Xử lý sự kiện GUI: Điều khiển nút Start/Stop server, Join/Leave multicast client.



## 🚀 3. Hình ảnh các chức năng

<p align="center">
  <img src="images/hinh1.png" alt="Hình 1" width="600"/>
</p>
<p align="center">
  <em>Hình 1: Giao diện đăng nhập hệ thống</em>
</p>

<p align="center">
  <img src="images/hinh2.png" alt="Hình 2" width="800"/>
</p>
<p align="center">
  <em>Hình 2: Giao diện Client hiển thị cảnh báo an ninh</em>
</p>

<p align="center">
  <img src="images/hinh3.png" alt="Hình 3" width="800"/>
</p>
<p align="center">
  <em>Hình 3: Giao diện Server hiển thị log cảnh báo và điều khiển</em>
</p>

<p align="center">
  <img src="images/hinh4.png" alt="Hình 4" width="700"/>
</p>
<p align="center">
  <em>Hình 4: Giao diện cảnh báo nghiêm trọng (popup Client)</em>
</p>


## 📝 4. Hướng dẫn cài đặt và sử dụng

### 🔧 Yêu cầu hệ thống

- **Java Development Kit (JDK)**: Phiên bản 8 trở lên
- **Hệ điều hành**: Windows, macOS, hoặc Linux
- **Môi trường phát triển**: IDE (IntelliJ IDEA, Eclipse, VS Code) hoặc terminal/command prompt
- **Bộ nhớ**: Tối thiểu 512MB RAM
- **Dung lượng**: Khoảng 10MB cho mã nguồn và file thực thi
- **Tệp cấu hình**: File config.properties chứa API key và URL của OpenWeather API.




## 📦 Cài đặt và triển khai

#### Bước 1: Chuẩn bị môi trường
1. **Kiểm tra Java**: Mở terminal/command prompt và chạy:
   ```bash
   java -version
   javac -version
   ```

Đảm bảo cả hai lệnh đều hiển thị phiên bản Java 8 trở lên.

2. **Tải mã nguồn**: Sao chép thư mục `BTL` chứa các file:
- `AlertServer.java`
- `AlertServerGUI.java`
- `AlertClientGUI.java`
- `AlertClientGUI2.java`
- `AlertClientGUI3.java`
- `Config.java`
- `config.properties (cần cấu hình WEATHER_API_KEY và WEATHER_API_URL).`



Cấu hình file config.properties:
- `WEATHER_API_KEY=your_openweather_api_key`
- `WEATHER_API_URL=http://api.openweathermap.org/data/2.5/forecast`
- `DEFAULT_CITY=Hanoi,vn`


#### Bước 2: Biên dịch mã nguồn

1. **Mở terminal** và điều hướng đến thư mục chứa mã nguồn
2. **Biên dịch các file Java**:
   ```bash
   javac Alert/*.java
   ```
   Hoặc biên dịch từng file riêng lẻ:
   ```bash
   javac Alert/AlertServer.java
   javac Alert/AlertServerGUI.java
   javac Alert/AlertClientGUI.java
   javac Alert/AlertClientGUI2.java
   javac Alert/AlertClientGUI3.java
   javac Alert/Config.java
   ```

3. **Kiểm tra kết quả**: Nếu biên dịch thành công, sẽ tạo ra các file `.class` tương ứng.



#### Bước 3: Chạy ứng dụng

**Khởi động Server:**
```bash
java Alert.AlertServerGUI
```
- Giao diện server sẽ hiển thị.
- Nhập tên thành phố (ví dụ: Hanoi,vn) và nhấn "Start Server".
- Server sẽ gửi cảnh báo đến nhóm multicast 239.255.0.1:4446 mỗi 5 phút.

**Khởi động Client:**
```bash
java Alert.AlertClientGUI
java Alert.AlertClientGUI2
java Alert.AlertClientGUI3
```

- Mở terminal mới cho mỗi client.
- Client tự động tham gia nhóm multicast và hiển thị các cảnh báo thời tiết.

### 🚀 Sử dụng ứng dụng

1.**Server:**

- Nhập tên thành phố vào ô nhập liệu.
- Chọn loại cảnh báo, mức độ nghiêm trọng, khoảng thời gian, nội dung tùy chỉnh.
- Nhấn "Khởi động Server" để bắt đầu gửi cảnh báo.
- Nhấn "Gửi Cảnh báo Thủ công" để gửi thủ công đến cổng được chọn.
- Nhấn "Gửi đến Tất cả Client" để gửi đến tất cả client cùng lúc.
- Nhấn "Dừng Server" để dừng.
- Log cảnh báo được hiển thị trên GUI và lưu vào file weather_alerts.log.
- Lịch sử gửi cảnh báo được hiển thị trong vùng "Lịch sử Gửi Cảnh báo".


2.**Client:**

- Tự động nhận và hiển thị các cảnh báo thời tiết từ server.
- Nhấn "Dừng ứng dụng" để ngắt kết nối và thoát.
- Các cảnh báo được lưu vào file weather_alerts.log (hoặc weather_alerts2.log, weather_alerts3.log cho client khác).

## 📚 5. Thông tin liên hệ
Họ tên: Lê Đức Khánh Long.  
Lớp: CNTT 16-03.  
Email: khanhlong12c@gmail.com

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
