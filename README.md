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


## 1. Tổng quan về hệ thống



Hệ thống cảnh báo thời gian thực (Real-time Alert System) là một giải pháp công nghệ cho phép truyền tải thông tin khẩn cấp hoặc cập nhật nhanh chóng từ một nguồn trung tâm (server) đến nhiều thiết bị nhận (client) trong mạng máy tính. Trong đề tài này, hệ thống tập trung vào việc sử dụng giao thức UDP (User Datagram Protocol) để gửi cảnh báo, chẳng hạn như thông báo thiên tai (lũ lụt, cháy rừng), sự cố hệ thống, hoặc cập nhật trạng thái thời gian thực.

UDP được chọn vì đặc tính không kết nối (connectionless) và tốc độ cao, phù hợp với các ứng dụng yêu cầu độ trễ thấp, nơi việc truyền dữ liệu nhanh chóng quan trọng hơn độ tin cậy tuyệt đối (có thể chấp nhận mất một số gói tin nhỏ). Hệ thống này thường được triển khai trong môi trường mạng LAN hoặc localhost, sử dụng cơ chế broadcast (phát sóng) để server gửi một thông điệp duy nhất đến tất cả client mà không cần thiết lập kết nối riêng lẻ. Điều này làm cho hệ thống trở nên hiệu quả về tài nguyên, tiết kiệm băng thông và CPU so với các giao thức như TCP.

Ví dụ thực tế: Trong các hệ thống giám sát như Bizfly Cloud Watcher, công cụ này sử dụng các giao thức tương tự UDP để theo dõi thời gian thực tình trạng máy chủ, website, và gửi cảnh báo tự động về sự cố (quá tải, host down) qua webhook hoặc thông báo tức thì, giúp giảm thời gian gián đoạn xuống mức tối thiểu.

1.1 Lý do chọn UDP cho hệ thống cảnh báo thời gian thực
Tốc độ và độ trễ thấp: UDP không yêu cầu "bắt tay" (handshaking) hoặc xác nhận nhận dữ liệu (acknowledgment), giúp gói tin được gửi ngay lập tức. Điều này lý tưởng cho ứng dụng thời gian thực như streaming video, VoIP, trò chơi trực tuyến, hoặc cảnh báo khẩn cấp, nơi độ trễ chỉ vài mili giây có thể cứu mạng sống.
Hỗ trợ broadcast và multicast: Server có thể gửi thông điệp đến địa chỉ 255.255.255.255 (broadcast toàn mạng) hoặc nhóm địa chỉ multicast, đạt đến hàng trăm client cùng lúc mà không cần biết IP cụ thể của từng cái.
Tiết kiệm tài nguyên: Header UDP chỉ 8 byte (so với 20 byte của TCP), giảm tải cho hệ thống, đặc biệt trong môi trường có nhiều người dùng đồng thời.
Nhược điểm cần lưu ý: Không đảm bảo thứ tự gói tin hoặc độ tin cậy (có thể mất gói mà không thông báo), nên phù hợp hơn với dữ liệu không quan trọng tính toàn vẹn, như cảnh báo (nếu mất một thông báo, có thể gửi lại).
So sánh nhanh với TCP:

Tiêu chí	UDP	TCP
Kết nối	Không kết nối, nhanh chóng	Có kết nối (handshake), chậm hơn
Độ tin cậy	Thấp (có thể mất gói)	Cao (xác nhận và retransmit)
Ứng dụng	Thời gian thực (cảnh báo, video, game)	Truyền file, web (yêu cầu chính xác)
Băng thông	Tiết kiệm hơn	Cao hơn do overhead

1.2 Kiến trúc hệ thống cơ bản
Hệ thống bao gồm hai thành phần chính:

Server: Phát hiện sự kiện (từ cảm biến, nhập lệnh thủ công, hoặc tích hợp API) và gửi thông điệp cảnh báo qua UDP broadcast đến port cố định (ví dụ: 12345). Sử dụng thư viện socket trong Python để tạo socket UDP và bật tùy chọn broadcast.
Client: Nhiều thiết bị (máy tính, điện thoại) lắng nghe trên port tương ứng, nhận gói tin và hiển thị cảnh báo (qua console, popup, hoặc thông báo âm thanh).
Sơ đồ đơn giản:

text
[Server] --(UDP Broadcast: "CẢ3 Ứng dụng thực tế
Cảnh báo thiên tai: Tích hợp với hệ thống quan trắc (vệ tinh, radar) để gửi thông báo đến cộng đồng, như dự báo bão hoặc lũ lụt với độ chính xác cao.
Giám sát hệ thống: Trong cloud computing (như Bizfly Cloud), theo dõi máy chủ và cảnh báo sự cố ngay lập tức.
Trò chơi và giải trí: Truyền vị trí người chơi thời gian thực mà không gián đoạn.
DNS và NTP: Truy vấn nhanh địa chỉ web hoặc đồng bộ thời gian.
Hệ thống này không chỉ minh họa nguyên lý lập trình mạng mà còn có giá trị thực tiễn trong việc nâng cao khả năng ứng phó khẩn cấp, đặc biệt ở Việt Nam với tần suất thiên tai cao.



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

4. Hướng dẫn cài đặt và sử dụng

🔧 Yêu cầu hệ thống

Java Development Kit (JDK): Phiên bản 8 trở lên

Hệ điều hành: Windows, Linux hoặc macOS

Môi trường phát triển: IDE (Eclipse, IntelliJ IDEA, VS Code) hoặc terminal/command prompt

Bộ nhớ: Tối thiểu 512MB RAM

Dung lượng: Khoảng 10MB cho mã nguồn và file biên dịch

📦 Cài đặt và triển khai
Bước 1: Chuẩn bị môi trường

Kiểm tra Java đã cài đặt:

java -version
javac -version


Nếu kết quả hiển thị Java 8 trở lên → có thể chạy chương trình.

Tải mã nguồn, bao gồm hai file chính:

WeatherAlertServerAuto.java (Server)

WeatherAlertClient.java (Client + màn hình đăng nhập Admin)

Bước 2: Biên dịch mã nguồn

Mở terminal, điều hướng đến thư mục chứa mã nguồn và chạy lệnh:

javac WeatherAlertServerAuto.java
javac WeatherAlertClient.java


Nếu biên dịch thành công, các file .class sẽ được tạo trong thư mục.

Bước 3: Chạy ứng dụng
Khởi động Server

Mở terminal, chạy lệnh:

java WeatherAlertServerAuto


Giao diện server xuất hiện với các nút Start/Stop và ô log.

Nhấn Start → server sẽ gửi cảnh báo thời tiết tự động đến nhóm multicast 239.255.0.1:4446 mỗi 5 giây.

Khởi động Client

Mở terminal khác, chạy lệnh:

java WeatherAlertClient


Giao diện Admin Login xuất hiện. Nhập:

Username: admin

Password: 123456

Sau khi đăng nhập thành công, cửa sổ Weather Alert Client sẽ hiện ra:

Nhấn Start Client để bắt đầu nhận cảnh báo.

Cảnh báo sẽ hiển thị theo thời gian thực trên bảng.

Có thể sử dụng ô tìm kiếm để lọc theo tên thành phố.

Các cảnh báo quan trọng (bão, mưa lớn, lũ…) sẽ hiển thị popup và đổi màu trong bảng.

🚀 Sử dụng ứng dụng
Server

Start: Bắt đầu gửi dữ liệu thời gian thực.

Stop: Dừng gửi dữ liệu.

Log: Hiển thị trực tiếp các bản tin đã gửi trên giao diện.

Client

Start Client: Nhận cảnh báo từ server tự động.

Stop Client: Ngắt kết nối.

Tìm kiếm: Lọc thông tin theo thành phố.

## 📚 5. Thông tin liên hệ
Họ tên: Nguyễn Trung Hiếu  
Lớp: CNTT 16-01.  
Email: mongkobiripface@gmail.com

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
