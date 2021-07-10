package khanhld.bk.controller.request_dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// là một interface cung cấp cơ chế điều khiển request
//cung cấp hai cơ chế điều khiển: gửi request của client sang 1 tài nguyueen khác trên server: forward
//include: bao gồm tài nguyên khác trên server

@WebServlet(name = "LearnRequestDispatcher", urlPatterns = {"/request_dipatcher"})
public class LearnRequestDispatcher extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("request dispaatcher");
        String forward= request.getParameter("forward");
        // để sử dụng được request dispatcher phải sử dụng pt getRequestDispatcher trong đối tương
        RequestDispatcher rd= request.getRequestDispatcher("test-request");
        if (forward.equals("true")){
            //thực hiện forward
            rd.forward(request,response);
        }
        else {
            // thực hiện include
            rd.include(request,response);
        }
    }
}
