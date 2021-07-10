package khanhld.bk.controller.pack_url_patern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// 1 servlet có thể xử lý cho nhiều url
// quy tắc đặt tên url: chữ thường phân cách nhau bằng dấu -
@WebServlet(name = "LearnUrlPatern", urlPatterns ={ "/learn-url-patern","/url-patern"})
public class LearnUrlPatern extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("learn url patern");
        // để lấy ra url đang sử dụng request
        response.getWriter().println("<h2>"+request.getRequestURL()+"</h2>");
    }
}
