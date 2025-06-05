-- Tạo user admin
CREATE USER 'admin_user'@'%' IDENTIFIED BY 'admin123';

-- Tạo user employee
CREATE USER 'employee_user'@'%' IDENTIFIED BY 'employee123';

-- Cấp full quyền trên toàn bộ database
GRANT ALL PRIVILEGES ON CTDT.* TO 'admin_user'@'%';

-- Áp dụng thay đổi
FLUSH PRIVILEGES;

-- Chỉ cấp quyền thao tác dữ liệu, không được DROP, ALTER, TRUNCATE
GRANT SELECT, INSERT, UPDATE, DELETE ON CTDT.* TO 'employee_user'@'%';

-- Cho phép employee dùng 4 SP (giữ nguyên tên SP như trên)
GRANT EXECUTE ON PROCEDURE CTDT.sp_delete_chuong_trinh_dao_tao TO 'employee_user'@'%';
GRANT EXECUTE ON PROCEDURE CTDT.sp_delete_ke_hoach_hoc_tap TO 'employee_user'@'%';
GRANT EXECUTE ON PROCEDURE CTDT.sp_delete_ki_hoc TO 'employee_user'@'%';
GRANT EXECUTE ON PROCEDURE CTDT.sp_them_mon_vao_kihoc TO 'employee_user'@'%';

-- Áp dụng thay đổi
FLUSH PRIVILEGES;


