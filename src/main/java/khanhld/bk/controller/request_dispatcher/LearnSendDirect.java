package khanhld.bk.controller.request_dispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LearnSendDirect",value = "/send-direct")
public class LearnSendDirect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 cơ chế điều hướng request sang trang khác ở trong hoặc ngoài server
        response.sendRedirect("https://www.facebook.com");
    }
}
