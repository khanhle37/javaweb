package khanhld.bk.filter;

import khanhld.bk.dao.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/api/*"})
public class ConnectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (DatabaseConnection.connection==null){
            System.out.println("not connect");
            try {
                DatabaseConnection.connectDB();
                chain.doFilter(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void destroy() {

    }
}
