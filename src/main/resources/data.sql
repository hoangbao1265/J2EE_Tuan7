-- Dữ liệu mẫu: Roles (INSERT IGNORE để tránh trùng)
INSERT IGNORE INTO role (name) VALUES ('ADMIN');
INSERT IGNORE INTO role (name) VALUES ('STUDENT');

-- Dữ liệu mẫu: Categories
INSERT IGNORE INTO category (name) VALUES ('Công nghệ thông tin');
INSERT IGNORE INTO category (name) VALUES ('Kinh tế');
INSERT IGNORE INTO category (name) VALUES ('Ngoại ngữ');
INSERT IGNORE INTO category (name) VALUES ('Khoa học tự nhiên');

-- Dữ liệu mẫu: Courses
INSERT IGNORE INTO course (id, name, image, credits, lecturer, category_id) VALUES
(1, 'Lập trình Java', 'https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg', 3, 'ThS. Nguyễn Văn A', 1),
(2, 'Cơ sở dữ liệu', 'https://cdn-icons-png.flaticon.com/512/4248/4248443.png', 3, 'TS. Trần Thị B', 1),
(3, 'Lập trình Web', 'https://cdn-icons-png.flaticon.com/512/1005/1005141.png', 3, 'ThS. Lê Văn C', 1),
(4, 'Kinh tế vi mô', 'https://cdn-icons-png.flaticon.com/512/2620/2620997.png', 2, 'PGS.TS. Phạm Văn D', 2),
(5, 'Toán cao cấp', 'https://cdn-icons-png.flaticon.com/512/3043/3043662.png', 4, 'TS. Hoàng Thị E', 4),
(6, 'Tiếng Anh cơ bản', 'https://cdn-icons-png.flaticon.com/512/5111/5111610.png', 3, 'ThS. Vũ Thị F', 3),
(7, 'Mạng máy tính', 'https://cdn-icons-png.flaticon.com/512/1516/1516960.png', 3, 'TS. Đặng Văn G', 1),
(8, 'Trí tuệ nhân tạo', 'https://cdn-icons-png.flaticon.com/512/2103/2103633.png', 4, 'PGS.TS. Bùi Thị H', 1),
(9, 'Kế toán tài chính', 'https://cdn-icons-png.flaticon.com/512/3135/3135706.png', 3, 'ThS. Lý Văn I', 2),
(10, 'Tiếng Anh nâng cao', 'https://cdn-icons-png.flaticon.com/512/5111/5111610.png', 3, 'TS. Mai Thị K', 3),
(11, 'Vật lý đại cương', 'https://cdn-icons-png.flaticon.com/512/2965/2965300.png', 3, 'ThS. Cao Văn L', 4),
(12, 'Học máy', 'https://cdn-icons-png.flaticon.com/512/2103/2103633.png', 4, 'TS. Trịnh Thị M', 1);

-- Admin mặc định: admin / admin123 (BCrypt)
INSERT IGNORE INTO student (username, password, email) VALUES
('admin', '$2a$10$w5vygAVv.jJQo/eKNNpPkegryzc1a6BynayqyLxQ3/JWXgHEg3C7u', 'admin@example.com');

-- Gán quyền ADMIN cho admin
INSERT IGNORE INTO student_role (student_id, role_id)
SELECT s.student_id, r.role_id FROM student s, role r
WHERE s.username = 'admin' AND r.name = 'ADMIN';
