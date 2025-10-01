# 🎓 Faculty of Information Technology (DaiNam University)  
## 🛡 HỆ THỐNG CẢNH BÁO THỜI GIAN THỰC  
(Server gửi cảnh báo tới nhiều Client qua UDP)  

**AIoTLab – Faculty of Information Technology – DaiNam University**  

---

## 1. Giới thiệu hệ thống
Hệ thống cảnh báo thời gian thực (**Real-time Alert System**) là một giải pháp công nghệ giúp **truyền thông tin khẩn cấp** hoặc **cập nhật nhanh chóng** từ một nguồn trung tâm (**Server**) đến nhiều thiết bị nhận (**Client**) trong mạng.

Ứng dụng thực tế:
- **Cảnh báo an ninh**: phát hiện xâm nhập, cháy nổ, trộm cắp, bạo loạn.
- **Cảnh báo thiên tai**: lũ lụt, bão, cháy rừng, giúp cơ quan chức năng phản ứng kịp thời.
- **Giám sát hệ thống**: theo dõi máy chủ, dịch vụ, thiết bị trong môi trường mạng.
- **Ứng dụng khác**: trò chơi trực tuyến, đồng bộ dữ liệu, thông báo nhanh.

**Đặc điểm nổi bật:**
- Nhận thông tin **tức thì**, giảm thiểu thời gian phản hồi khi có sự cố.
- Hỗ trợ **nhiều client cùng lúc** nhờ multicast UDP.
- Tiết kiệm băng thông, CPU nhờ cơ chế truyền nhẹ.
- Giao diện **GUI trực quan** bằng Java Swing.

---

## 🔧 2. Công nghệ sử dụng

### 2.1. Ngôn ngữ lập trình
- **Java**: hỗ trợ UDP socket và GUI đa nền tảng.

### 2.2. Giao thức
- **UDP (User Datagram Protocol)**  
  - Truyền dữ liệu nhanh, không yêu cầu kết nối lâu dài.  
  - Thích hợp cho cảnh báo thời gian thực.

### 2.3. Thư viện / API
- **Java Swing**: xây dựng giao diện đồ họa (Server, Client).
- **java.net.MulticastSocket / DatagramPacket**: gửi và nhận dữ liệu qua UDP.

### 2.4. Công cụ hỗ trợ
- **IDE**: IntelliJ IDEA / Eclipse / NetBeans / VS Code.  
- **Git & GitHub**: lưu trữ code, báo cáo, quản lý phiên bản.  
- **JDK**: Java Development Kit 8 trở lên.  

---

## 🚀 3. Hình ảnh các chức năng

### Hình 1: Giao diện đăng nhập quản trị (Client - Admin Login)  
*(Client phải đăng nhập bằng `admin/123456`)*

### Hình 2: Giao diện Client hiển thị cảnh báo theo thời gian thực  
- Có nút **Start / Stop Client** để bật/tắt nhận dữ liệu.  
- Hiển thị bảng cảnh báo với màu sắc theo mức độ.

### Hình 3: Cảnh báo quan trọng hiển thị popup  
- Khi có mức độ **HIGH** hoặc **CRITICAL**, client sẽ đổi màu nổi bật và hiển thị popup.

### Hình 4: Giao diện Server (SecurityAlertServer.java)  
- Bảng hiển thị cảnh báo đã gửi.  
- Panel bên phải hiển thị **log server**.  
- Các nút: **Start / Stop / Thêm ngẫu nhiên / Nhập tay**.  

---

## ⚙️ 4. Hướng dẫn cài đặt và sử dụng

### Yêu cầu hệ thống
- **Java JDK**: 8 trở lên  
- **Hệ điều hành**: Windows / Linux / macOS  
- **RAM**: ≥ 512 MB  
- **Dung lượng mã nguồn**: ~10 MB  

### Cài đặt
**Bước 1. Kiểm tra Java**
```bash
java -version
javac -version
