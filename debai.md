Hệ thống đăng ký học phần cho sinh viên 
Mô tả hệ thống 
Xây dựng ứng dụng Course Registration Application cho phép sinh viên đăng ký học phần. Database gợi ý 
Student (student_id, username, password, email) 
Role (role_id, name) ; có 2 quyền là (ADMIN, STUDENT) 
student_role (student_id, role_id) 
course (id, name, image, credits, lecturer, category_id) 
Category (id,name) 
Enrollment (id, student_id, course_id, enroll_date) 
Chức năng:
Câu 1:
Xây dựng trang Home hiển thị danh sách tất cả các học phần (Course) gồm (2đ): 
• Tên học phần 
• Số tín chỉ 
• Giảng viên 
• Hình ảnh minh họa 
Xây đựng chức năng phân trang tại trang home, mỗi trang có 5 học phần (0,5đ).
Câu 2:
Xây dựng chức năng CRUD học phần cho ADMIN: 
• Create Course
• Update Course 
• Delete Course 
Câu 3:
Tạo trang đăng ký tài khoản sinh viên gồm: 
• Username 
• Password 
• Email 
Sau khi đăng ký thành công (lưu vào bảng student), quyền mặc định là STUDENT.
Câu 4:
Cấu hình Spring Security: 
• Trang /admin/** chỉ ADMIN truy cập 
• Trang /courses cho tất cả người dùng 
• Trang /enroll/** chỉ STUDENT 
Câu 5:
Xây dựng chức năng đăng nhập (login) bằng username và password. Sau khi đăng nhập thành công chuyển tới:/home 
Câu 6:
Sinh viên có thể đăng ký học phần (Enroll Course). 
• Nút Enroll hiển thị trong danh sách học phần 
• Chỉ STUDENT mới có quyền đăng ký 
Câu 7:
Xây dựng trang My Courses hiển thị các học phần mà sinh viên đã đăng ký. 
Câu 8 Tìm kiếm và Lọc dữ liệu:
• Thực hiện chức năng tìm kiếm học phần theo tên (Course Name) trên thanh Header. • Kết quả trả về phải hiển thị đúng các học phần có chứa từ khóa tương ứng. 