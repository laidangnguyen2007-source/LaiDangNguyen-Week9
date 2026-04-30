# BÁO CÁO LỖI (BUG REPORT) VÀ CÁCH SỬA

File này giải thích các lỗi trong đề bài gốc để bạn nắm được cách thức debug pipeline. Code hiện tại trong thư mục này đã được sửa (màu xanh) để bạn nộp bài trên máy.

## Lỗi 1: Sai version thư viện
- **File**: `pom.xml` (dòng chứa version của logback-classic)
- **Log lỗi Maven**: `Could not resolve dependencies for project com.lab:shipping-app:jar:1.0-SNAPSHOT: Failure to find ch.qos.logback:logback-classic:jar:9.9.9`
- **Nguyên nhân**: Phiên bản `9.9.9` của `logback-classic` không tồn tại trên Maven Central Repository. Quá trình tải dependency thất bại làm pipeline dừng ngay ở pha đầu tiên.
- **Cách sửa**: Đổi thành phiên bản hợp lệ, ví dụ `1.4.11`.

## Lỗi 2: Lỗi tương thích Maven Surefire Plugin
- **File**: `pom.xml` (dòng chứa version của maven-surefire-plugin)
- **Log lỗi Maven**: Hoặc không chạy test nào (Tests run: 0), hoặc văng lỗi `UnsupportedClassVersionError` nếu chạy trên JDK 17 với plugin quá cũ.
- **Nguyên nhân**: `maven-surefire-plugin` version `2.12.4` được phát hành từ rất lâu trước khi JUnit 5 và JDK 17 ra đời. Nó không biết cách tìm và chạy các bài test viết bằng annotation `@Test` của `org.junit.jupiter.api`.
- **Cách sửa**: Nâng cấp version lên `3.0.0-M9` hoặc `3.2.5` để tương thích hoàn toàn với JUnit 5.

## Lỗi 3: Lỗi logic Code (NullPointerException tiềm ẩn)
- **File**: `src/main/java/com/lab/ShippingCalculator.java` (dòng `if (type.equals("EXPRESS"))`)
- **Nguyên nhân**: Nếu biến `type` được truyền vào là `null`, lời gọi hàm `type.equals()` sẽ ném ra ngoại lệ `NullPointerException`, làm ứng dụng bị crash. Đây là lỗi kinh điển trong Java.
- **Cách sửa**: Đảo ngược phép so sánh, sử dụng hằng số gọi hàm: `if ("EXPRESS".equals(type))`. Khi đó nếu `type` là null thì nó chỉ trả về `false` một cách an toàn.

## Lỗi 4 (Tự tạo thêm): Sai số khi so sánh số thực
- **Kịch bản**: Sau khi pipeline xanh, cố tình viết thêm một test có dạng:
  ```java
  @Test
  void testPrecisionError() {
      // 5.1 * 3000 = 15300.0, nhưng máy tính có thể ra 15300.000000000002
      assertEquals(15300.0, calc.calculate(5.1, "STANDARD"));
  }
  ```
- **Log lỗi Pipeline**: `org.opentest4j.AssertionFailedError: expected: <15300.0> but was: <15300.000000000002>`
- **Nguyên nhân**: Kiểu `double` trong Java không thể biểu diễn chính xác tuyệt đối các số thập phân.
- **Cách sửa**: Sử dụng hàm `assertEquals` có thêm tham số `delta` (sai số cho phép):
  ```java
  assertEquals(15300.0, calc.calculate(5.1, "STANDARD"), 0.01);
  ```
