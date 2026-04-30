# 📚 BÍ KÍP TỪ ĐIỂN CÔNG NGHỆ TUẦN 9: CI/CD, MAVEN & QUALITY ASSURANCE

Vì tuần 9 anh nghỉ học, file này được soạn lại giống như một **cuốn giáo trình thu nhỏ** thay thế hoàn toàn cho bài giảng trên lớp. Mọi ngóc ngách về tại sao viết code như vậy, thư viện này dùng làm gì, cấu hình CI/CD ra sao đều được mổ xẻ tận gốc. Anh hãy đọc từ trên xuống dưới, đảm bảo "Master" tuyệt đối.

---

## 🌟 PHẦN 1: TỔNG QUAN KIẾN THỨC TUẦN 9 (MỤC TIÊU LÕI)

Tuần 9 không dạy anh cách viết thuật toán hay logic Java phức tạp. Tuần 9 dạy anh cách **"Làm việc như một Kỹ sư phần mềm chuyên nghiệp"**.

Trong môi trường doanh nghiệp (Professional Environment), code chạy đúng trên máy mình là chưa đủ. Quy trình chuẩn phải là:
1. Quản lý thư viện tự động (Maven).
2. Code phải đẹp, theo đúng chuẩn mực định dạng chung của team (Checkstyle).
3. Phải biết code của mình đã được test bao nhiêu phần trăm (Test Coverage - JaCoCo).
4. Phải biết lưu lại dấu vết hệ thống để khi sập còn biết đường tìm lỗi (Logging - SLF4J/Logback).
5. Cuối cùng, và quan trọng nhất: **Tự động hóa mọi thứ (CI/CD - GitHub Actions)**. Mỗi khi anh đẩy code lên, một con robot (máy ảo) sẽ tự động tải code về, tự động test, tự động báo lỗi nếu có, và tự đóng gói sản phẩm.

Dưới đây là chi tiết từng bài tập áp dụng các quy trình trên.

---

## 🌟 PHẦN 2: GIẢI PHẪU CHI TIẾT TỪNG BÀI TẬP

### 🛠 Bài 1: Quản lý Dependency với Maven
**1. Kiến thức nền tảng:**
Ngày xưa, muốn dùng thư viện (ví dụ kết nối Database), anh phải lên mạng tải file `.jar` về, copy paste thủ công vào thư mục dự án. Nếu thư viện đó lại cần 10 thư viện con khác, anh sẽ khóc vì tải mỏi tay.
**Maven** ra đời để giải quyết việc đó. Anh chỉ cần khai báo tên thư viện vào file `pom.xml`, Maven sẽ tự động lên mạng (Maven Central) tải nó và toàn bộ thư viện con phụ thuộc về máy.

**2. Phân tích Code & Cấu hình:**
- **Thư viện Logback Classic (`1.4.11`)**: Thay thế cho `System.out.println()`.
- **Thư viện Hibernate Core (`6.2.0.Final`)**: Đây là công cụ ORM (Object-Relational Mapping). Nó giúp anh biến một class trong Java (ví dụ class `User`) tự động tạo thành một bảng trong SQL mà không cần viết câu lệnh `CREATE TABLE`.
- **Thư viện JUnit Jupiter (`5.9.2`)**: Khung làm việc (Framework) để viết Unit Test. Từ khóa `<scope>test</scope>` cực kỳ quan trọng, nó báo cho Maven biết: "Thư viện này chỉ dùng lúc chạy test, lúc gói sản phẩm đem bán (file jar cuối cùng) thì bỏ nó ra cho nhẹ".

---

### 🛠 Bài 2: Code Quality (Chất lượng Code) - Checkstyle
**1. Kiến thức nền tảng:**
10 developer trong một team sẽ có 10 cách gõ code (người thích thụt lùi 4 dấu cách, người thích 2 dấu, người thích đóng ngoặc cùng dòng, người thích xuống dòng). Để code cả dự án đồng nhất như 1 người viết, ta dùng **Checkstyle**.

**2. Phân tích Code & Cấu hình:**
- Trong `pom.xml` của Bài 2, em đã cài `maven-checkstyle-plugin`.
- Dòng `<configLocation>google_checks.xml</configLocation>` bắt buộc code của anh phải tuân theo tiêu chuẩn viết code của Google (rất khắt khe về thụt lề, khoảng trắng, đặt tên).
- Dòng `<failsOnError>true</failsOnError>`: Nếu có 1 lỗi format dù nhỏ nhất (thừa 1 khoảng trắng), toàn bộ quá trình Build sẽ báo màu đỏ (Thất bại). Bắt buộc developer phải sửa cho đẹp mới được nộp.
- **Về Code BankSystem.java**: Em đã dùng Logback để ghi log. Thay vì `System.out.println("Ban rut tien")`, em dùng `logger.info("Deposited: {}", amount);`. `{}` là tham số giữ chỗ, nó chạy nhanh hơn nhiều so với việc dùng dấu `+` cộng chuỗi trong Java.

---

### 🛠 Bài 3: CI/CD automation (Tự động hóa)
**1. Kiến thức nền tảng:**
- **CI (Continuous Integration)**: Tích hợp liên tục. Anh code xong, đẩy lên GitHub. GitHub ngay lập tức thuê một máy ảo (Server ảo), cài Java, chạy lệnh test code của anh. Đảm bảo code mới không làm hỏng code cũ.
- **GitHub Actions**: Nền tảng cung cấp máy ảo miễn phí của GitHub để chạy CI/CD.

**2. Phân tích Code & Cấu hình (File `.github/workflows/ci.yml`):**
- `on: [push, pull_request]`: Sự kiện kích hoạt. Hễ anh `git push` hoặc tạo `PR`, robot sẽ chạy.
- `runs-on: ubuntu-latest`: Thuê một máy ảo chạy hệ điều hành Linux Ubuntu bản mới nhất.
- `uses: actions/checkout@v3`: Bước 1: Máy ảo copy toàn bộ code của anh từ kho lưu trữ về máy ảo.
- `uses: actions/setup-java@v3`: Bước 2: Máy ảo tự động cài đặt JDK 17.
- `run: mvn clean package`: Bước 3: Ra lệnh cho máy ảo chạy Maven để test và đóng gói.
- `uses: actions/upload-artifact@v3`: Bước 4: Lưu file `.jar` vừa tạo ra để anh có thể bấm nút tải về (Artifact).

---

### 🛠 Bài 4: Kiểm thử đa hệ điều hành (Matrix Strategy)
**1. Kiến thức nền tảng:**
Lỗi "Nó chạy được trên máy em nhưng lên server thì chết" rất phổ biến. Windows dùng dấu `\` cho đường dẫn (`C:\temp\file.txt`), còn Linux/Mac dùng dấu `/` (`/var/tmp/file.txt`). Nếu anh code cứng `C:\` trong Java, đẩy lên Server Linux là sập.

**2. Phân tích Code & Cấu hình:**
- **Trong file `ci.yml`**: Dùng `strategy: matrix: os: [ubuntu-latest, windows-latest, macos-latest]`. Cấu hình này bảo GitHub: "Hãy bật cùng lúc 3 máy ảo với 3 hệ điều hành khác nhau lên, và chạy chung 1 quy trình test cho tao". Nhờ thế ta biết chắc code sống tốt trên cả 3 môi trường.
- **Trong file Java (FileHandler.java)**: Em dùng `File.separator`. Lệnh này cực kỳ thông minh: nếu nó đang chạy trên Windows, nó sẽ tự nhả ra dấu `\`; nếu trên Linux, nó tự nhả ra dấu `/`. Do đó, code không bao giờ chết vì sai đường dẫn.

---

### 🛠 Bài 5: Test Coverage & Quality Enforcement (JaCoCo)
**1. Kiến thức nền tảng:**
Anh viết 100 dòng code, nhưng anh chỉ viết Test cho 20 dòng. Vậy "Độ bao phủ" (Test Coverage) của anh chỉ là 20%. Rất nguy hiểm vì 80% code kia có thể chứa bug chưa được kiểm tra. **JaCoCo** là plugin tính toán ra con số % này.

**2. Phân tích Code & Cấu hình:**
- Trong `pom.xml`, plugin `jacoco-maven-plugin` được chèn vào.
- Thẻ `<value>COVEREDRATIO</value>` và `<minimum>0.80</minimum>`: Đây là **Strict Rule** (Luật thép). Nó dặn Maven: Cứ build là tao sẽ soi. Nếu mày lười viết test, độ bao phủ dưới 80%, tao đánh sập (Fail) bản build này ngay lập tức. Ép dev phải viết test.
- Bước upload artifact lấy từ đường dẫn `target/site/jacoco/` để thầy cô có thể tải báo cáo dạng biểu đồ HTML về xem (Báo cáo hiển thị dòng code nào màu xanh là đã test, màu đỏ là chưa test).

---

### 🛠 Bài 6: CI/CD Pipeline Optimization & Caching
**1. Kiến thức nền tảng:**
Mỗi lần máy ảo của GitHub Actions khởi động lại (mỗi lần anh Push code), máy ảo đó trắng tinh côi. Nó phải tải lại hàng trăm MB thư viện Maven (Logback, JUnit...) từ đầu, mất rất nhiều phút. Ta cần tối ưu nó bằng **Caching** (Bộ nhớ tạm).

**2. Phân tích Code & Cấu hình:**
- Trong `ci.yml`, ở bước `setup-java`, em chèn thêm `cache: 'maven'`.
- Chức năng: GitHub sẽ lưu lại các thư viện `.jar` sau lần chạy đầu tiên vào kho lưu trữ bí mật của nó. Ở lần Push thứ 2, nó không tải từ Internet nữa mà bốc thẳng từ kho bí mật ném vào máy ảo. Thời gian chạy Pipeline giảm từ vài phút xuống còn vài giây.

---

### 🛠 Bài 7: Automated Code Review via Pull Request
**1. Kiến thức nền tảng:**
Thay vì Tech Lead phải soi từng dòng code của nhân viên xem có sai thụt lề hay không, ta cấu hình Bot.

**2. Phân tích Code & Cấu hình:**
- Sử dụng Action có sẵn của bên thứ 3: `uses: dbelyaev/action-checkstyle@master`.
- Nó sẽ tự động đọc kết quả lỗi do Checkstyle bắn ra, sau đó tự động login vào GitHub bằng cái tên "GitHub Actions Bot" và **comment trực tiếp vào dòng code bị lỗi** trong thẻ Pull Request (Ví dụ: "Ê, dòng 15 anh thiếu dấu cách ở ngoặc nhọn kìa").
- Để làm được, nó cần quyền `github_token: ${{ secrets.GITHUB_TOKEN }}`. (Đây là chìa khóa do GitHub tự cấp ngầm để bot có quyền comment thay anh).

---

### 🛠 Bài 8: Đóng gói sản phẩm thực thi (Executable JAR)
**1. Kiến thức nền tảng:**
File `.java` chỉ dùng để đọc, máy không chạy được. Phải compile ra `.class`. Để ném cho khách hàng dùng, ta phải nén hàng trăm file `.class` đó thành một cục duy nhất gọi là file `.jar` (Java ARchive).

**2. Phân tích Code & Cấu hình:**
- Nếu nén thông thường, khi nhấp đúp vào cục `.jar`, máy không biết bắt đầu chạy từ file nào.
- Nên ta phải dùng `maven-jar-plugin` và chỉ định `<mainClass>com.lab.App</mainClass>`. Lệnh này đính kèm một file `MANIFEST.MF` vào bên trong cục nén `.jar`, làm la bàn chỉ đường báo cho máy tính biết: "Hãy bắt đầu chạy phần mềm từ hàm main của class App nhé".
- Lệnh để chạy sản phẩm: `java -jar target/Bai08-Packaging-1.0-SNAPSHOT.jar`.

---

### 🛠 Bài 9: Triển khai Logging chuyên nghiệp
**1. Kiến thức nền tảng:**
- Bỏ hẳn tư duy sinh viên: `System.out.println()`. Vì sao? Vì khi hệ thống chạy thực tế, console bị đóng lại, dòng chữ đó bay vào hư không, sập server không biết vì sao sập.
- Chuyển sang tư duy chuyên gia: Dùng thư viện Log (SLF4J + Logback).

**2. Phân tích Code & Cấu hình:**
- Trong thư mục `resources` có file `logback.xml`. File này cấu hình Appender.
- **ConsoleAppender**: Để chữ in ra vẫn hiện trên màn hình nền đen (cho dev nhìn).
- **FileAppender**: Chức năng quan trọng nhất. Cấu hình `<file>target/app.log</file>`. Toàn bộ thông báo, dù là lỗi hay bình thường, đều được bí mật ghi vào file văn bản `app.log`. Chạy 1 tháng, file này sẽ lưu lại toàn bộ lịch sử (như hộp đen máy bay). Lỗi lúc mấy giờ, nguyên nhân do biến nào `null`, xem hộp đen là ra hết.

---

### 🛠 Bài 10: The Broken Pipeline (Debug thực chiến)
**1. Tại sao Pipeline báo đỏ (Lỗi)?**
Dự án được cấu hình cố tình sai ở nhiều chỗ để thử thách kỹ năng phân tích `log`:
- **Lỗi Maven ngớ ngẩn (Dependencies)**: Nhập version thư viện là `9.9.9` (phiên bản đến từ tương lai, không tồn tại). Nghĩa là chưa nói đến code sai hay đúng, ngay vòng gửi xe Maven đã báo đỏ vì không tải được đồ nghề.
- **Lỗi Plugin lỗi thời**: Dùng plugin `surefire-plugin 2.12.4`. Thằng này sinh ra từ thời tống, không hiểu được framework test JUnit 5 (jupiter). Kết quả là vòng chạy test bị bỏ qua hoàn toàn. Đã fix bằng bản 3.x.
- **Lỗi Code Logic (Null Pointer)**: Hàm viết `if (type.equals("EXPRESS"))`. Hàm `.equals()` được gọi từ biến `type`. Nếu ai đó nghịch ngợm truyền vào `type = null`, chương trình sập lập tức vì Không-có-gì (null) không thể gọi hàm. Đã fix bằng định luật bất thành văn trong Java: Đặt chuỗi hằng số lên trước `if ("EXPRESS".equals(type))`.

Đó, anh đọc thuộc cái sớ này là anh thừa sức "bán hành" lại cho giáo viên nếu bị vặn vẹo bất cứ ngóc ngách nào của bài! Chúc anh "Master"!
