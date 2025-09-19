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


## 1. Giới thiệu hệ thống



Hệ thống cảnh báo thời gian thực (Real-time Alert System) là một giải pháp công nghệ giúp truyền thông tin khẩn cấp hoặc cập nhật nhanh chóng từ một nguồn trung tâm (server) đến nhiều thiết bị nhận (client) trong mạng.

Hệ thống này được thiết kế để:

Cảnh báo thiên tai: như lũ lụt, bão, cháy rừng, giúp người dân và cơ quan chức năng phản ứng kịp thời.

Giám sát hệ thống: theo dõi máy chủ, dịch vụ hoặc thiết bị trong môi trường mạng, phát hiện sự cố tức thì.

Hỗ trợ ứng dụng thời gian thực khác: như trò chơi trực tuyến, đồng bộ dữ liệu, hay thông báo nhanh cho người dùng.

Đặc điểm nổi bật:

Nhận thông tin tức thì: giảm thiểu thời gian phản hồi khi có sự cố.

Hỗ trợ nhiều client cùng lúc: dữ liệu được gửi đến toàn bộ thiết bị trong mạng mà không cần kết nối riêng lẻ.

Hiệu quả tài nguyên: giảm băng thông và tải CPU nhờ sử dụng cơ chế truyền dữ liệu tối ưu.

Hệ thống minh họa rõ ràng cách ứng dụng công nghệ mạng và lập trình GUI để xây dựng một công cụ hữu ích trong việc nâng cao khả năng ứng phó khẩn cấp, đặc biệt phù hợp với môi trường có thiên tai thường xuyên như ở Việt Nam.


## 🔧 2. Công nghệ sử dụng




Công nghệ sử dụng

Hệ thống cảnh báo thời gian thực này được xây dựng bằng các công nghệ và công cụ sau:

2.1 Ngôn ngữ lập trình

Java: Hỗ trợ UDP socket và lập trình GUI dễ dàng, chạy được trên nhiều hệ điều hành.

2.2 Giao thức

UDP (User Datagram Protocol):

Truyền dữ liệu nhanh, phù hợp với cảnh báo thời gian thực.

Ưu điểm: nhẹ, tốc độ cao, không cần thiết lập kết nối lâu dài.

2.3 Thư viện / API

Java Swing: Dùng để xây dựng giao diện GUI cho client, hiển thị cảnh báo trong JTable hoặc JTextArea.

java.net.DatagramSocket / DatagramPacket: Dùng để gửi và nhận dữ liệu qua UDP.

2.4 Công cụ hỗ trợ

IDE: IntelliJ IDEA / Eclipse / NetBeans

Git & GitHub: Lưu trữ code và báo cáo online, dễ nộp bài cho thầy và theo dõi phiên bản.

JDK: Java Development Kit 8 trở lên.


## 🚀 3. Hình ảnh các chức năng



<p align="center">
  <img src="https://github.com/user-attachments/assets/570934a6-bc80-4058-9667-0a1fe55da903" alt="Hình 1" width="400"/>
</p>
<p align="center"><em>Hình 1: Giao diện đăng nhập quản trị (Admin Login)</em></p>


<p align="center">
  <img src="https://github.com/user-attachments/assets/00f0d4b9-b56e-44f9-933d-28f0539c84c5" alt="Hình 2" width="500"/>
</p>
<p align="center"><em>Hình 2: Giao diện Client hiển thị các cảnh báo thời tiết (có nút Start/Stop Client)</em></p>


<p align="center">
  <img src="https://github.com/user-attachments/assets/5d5d9ec2-961e-4ef1-bc8f-17cfd8403724" alt="Hình 3" width="500"/>
</p>
<p align="center"><em>Hình 3: Cảnh báo quan trọng hiển thị khi có thời tiết nguy hiểm</em></p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/2c155982-c4a8-4f24-b9c0-88431afd95fb" alt="Hình 4" width="500"/>
</p>
<p align="center"><em>Hình 4: Giao diện Weather Alert Server hiển thị log dữ liệu thời gian thực</em></p>

## 4. Hướng dẫn cài đặt và sử dụng

Yêu cầu hệ thống

Java Development Kit (JDK): Phiên bản 8 trở lên

Hệ điều hành: Windows, Linux, macOS

IDE: Eclipse, IntelliJ IDEA, VS Code, hoặc terminal/command prompt

Bộ nhớ: ≥ 512 MB RAM

Dung lượng mã nguồn: ~10 MB

Cài đặt và chạy hệ thống

Bước 1: Chuẩn bị

Kiểm tra Java:

java -version
javac -version


Tải mã nguồn gồm:

WeatherAlertServerAuto.java (Server)

WeatherAlertClient.java (Client + Admin Login)

Bước 2: Biên dịch

javac WeatherAlertServerAuto.java
javac WeatherAlertClient.java


Nếu thành công, các file .class sẽ được tạo.

Bước 3: Khởi động Server

java WeatherAlertServerAuto


Giao diện Server xuất hiện với các nút: Start / Stop, và bảng log.

Nhấn Start → server gửi cảnh báo tự động tới multicast 239.255.0.1:4446 mỗi 5 giây.

Bước 4: Khởi động Client

java WeatherAlertClient


Màn hình Admin Login:

Username: admin

Password: 123456

Đăng nhập → cửa sổ Client xuất hiện.

Nhấn Start Client để nhận cảnh báo thời gian thực.

Chức năng chính

Server

Start: Bắt đầu gửi dữ liệu thời gian thực.

Stop: Dừng gửi dữ liệu.

Log: Hiển thị các bản tin đã gửi.

Client

Hiển thị cảnh báo trên bảng theo thời gian thực.

Lọc thông tin theo tên thành phố.

Cảnh báo quan trọng (bão, mưa lớn…) hiển thị popup và đổi màu để dễ nhận biết.


## 📚 5. Thông tin liên hệ
Họ tên: Nguyễn Trung Hiếu  
Lớp: CNTT 16-01.  
Email: mongkobiripface@gmail.com

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
