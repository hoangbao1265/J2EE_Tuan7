-- Tạo database
CREATE DATABASE IF NOT EXISTS course_registration CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE course_registration;

-- Bảng Role
CREATE TABLE IF NOT EXISTS role (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Bảng Student
CREATE TABLE IF NOT EXISTS student (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

-- Bảng student_role (many-to-many)
CREATE TABLE IF NOT EXISTS student_role (
    student_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, role_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Bảng Category
CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL
);

-- Bảng Course
CREATE TABLE IF NOT EXISTS course (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500),
    credits INT NOT NULL DEFAULT 3,
    lecturer VARCHAR(200),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Bảng Enrollment
CREATE TABLE IF NOT EXISTS enrollment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enroll_date DATE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

-- Dữ liệu mẫu: Roles
INSERT IGNORE INTO role (name) VALUES ('ADMIN'), ('STUDENT');

-- Dữ liệu mẫu: Categories
INSERT IGNORE INTO category (name) VALUES
('Công nghệ thông tin'),
('Kinh tế'),
('Ngoại ngữ'),
('Khoa học tự nhiên');

-- Dữ liệu mẫu: Courses
INSERT IGNORE INTO course (name, image, credits, lecturer, category_id) VALUES
('Lập trình Java', 'https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg', 3, 'ThS. Nguyễn Văn A', 1),
('Cơ sở dữ liệu', 'https://cdn-icons-png.flaticon.com/512/4248/4248443.png', 3, 'TS. Trần Thị B', 1),
('Lập trình Web', 'https://cdn-icons-png.flaticon.com/512/1005/1005141.png', 3, 'ThS. Lê Văn C', 1),
('Kinh tế vi mô', 'https://cdn-icons-png.flaticon.com/512/2620/2620997.png', 2, 'PGS.TS. Phạm Văn D', 2),
('Toán cao cấp', 'https://cdn-icons-png.flaticon.com/512/3043/3043662.png', 4, 'TS. Hoàng Thị E', 4),
('Tiếng Anh cơ bản', 'https://cdn-icons-png.flaticon.com/512/5111/5111610.png', 3, 'ThS. Vũ Thị F', 3),
('Mạng máy tính', 'https://cdn-icons-png.flaticon.com/512/1516/1516960.png', 3, 'TS. Đặng Văn G', 1),
('Trí tuệ nhân tạo', 'https://cdn-icons-png.flaticon.com/512/2103/2103633.png', 4, 'PGS.TS. Bùi Thị H', 1),
('Kế toán tài chính', 'https://cdn-icons-png.flaticon.com/512/3135/3135706.png', 3, 'ThS. Lý Văn I', 2),
('Tiếng Anh nâng cao', 'https://cdn-icons-png.flaticon.com/512/5111/5111610.png', 3, 'TS. Mai Thị K', 3),
('Vật lý đại cương', 'https://cdn-icons-png.flaticon.com/512/2965/2965300.png', 3, 'ThS. Cao Văn L', 4),
('Học máy', 'https://cdn-icons-png.flaticon.com/512/2103/2103633.png', 4, 'TS. Trịnh Thị M', 1);

-- Admin mặc định: admin / admin123 (BCrypt encoded)
-- Mật khẩu: admin123
INSERT IGNORE INTO student (username, password, email) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOZ.1K5YX0Kba', 'admin@example.com');

-- Gán quyền ADMIN cho admin
INSERT IGNORE INTO student_role (student_id, role_id)
SELECT s.student_id, r.role_id FROM student s, role r
WHERE s.username = 'admin' AND r.name = 'ADMIN';
