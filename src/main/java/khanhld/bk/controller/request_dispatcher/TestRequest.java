package khanhld.bk.controller.request_dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestRequest", value = "/test-request")
public class TestRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //sử dụng query String trong url để lấy thông tin client muốn forward hay include
        //cấu tạo query String ?key=value&key1=value1
        // để lấy giá trị dùng request  và truyền đúng key
        String forward= request.getParameter("forward");
        response.getWriter().println(forward);
    }
}
