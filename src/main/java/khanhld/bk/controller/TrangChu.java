package khanhld.bk.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TrangChu",value = "/trang-chu")
/*
* servlet là một class bình thuonfg được extent httpsServlet
* Một servlet có hàm init() chỉ chạy một lần khi nó được khởi tạo, destroy() chỉ chạy một lần khi servlet bị hủy
* doget, doPost là các hàm chạy đi chạy lại nhiều nhần mỗi khi người dùng resquest đến server
* do http có 4 phương thức, get,pust,post,deleted nên có 4 phương thức này sẽ có 4 hàm mtương ứng
* khi request đến server phải kèm theo 1 trong 4 phướng thức
* + get: lấy dữ liêtuj
* put: sửa dữ liệu
* post: thêm dữ liệu
* deleted: xóa dữ liệu
* khi request mặc định là pt get và để gọi các phương thức khác thì phải dùng các công cụ khác (postman)
* */
public class TrangChu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // khi gọi bằng trình duyệt sẽ xử lý hàm này
        // một hàm do thường có 2 tham số truyền vào
        // httprequet dùng để chứa các thông tin người dùng truyền lên serve
        //respon trả về sau khi server xử lý
        response.getWriter().println("<h1>Trang chủ</h1>");

    }
}
