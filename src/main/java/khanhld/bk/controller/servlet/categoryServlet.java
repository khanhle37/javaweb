package khanhld.bk.controller.servlet;

import com.google.gson.Gson;
import khanhld.bk.dao.CategoryDao;
import khanhld.bk.dao_impl.CategoryDaoImpl;
import khanhld.bk.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "categoryServlet", urlPatterns = {"/api/admin/category/*"})// su dung cho dev
public class categoryServlet extends HttpServlet {
    private CategoryDaoImpl categoryDao= new CategoryDaoImpl();
    private Gson gson= new Gson();
    // thêm dữ liệu
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer= response.getWriter();
        Category category= gson.fromJson(request.getReader(),Category.class);
        category.setDeleted(false);
        try {
            Category saved=categoryDao.insert(category);

            if (saved != null)
                writer.println(gson.toJson(saved));
            else {
                response.setStatus(400);
                writer.println("data is invalid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.setStatus(500);
            writer.println("error: " + throwables);
        }

    }
// lấy dữ liệu
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer=response.getWriter();
        String rs= null;
        String pathInfo= request.getPathInfo();
        if (pathInfo!=null){
            if (pathInfo.equals("/find-all")){
                try {
                  List<Category> list= categoryDao.findAll();
                  writer.println(gson.toJson(list));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else if (pathInfo.equals("/find-by-id")){
                int id= Integer.parseInt(request.getParameter("id"));

                try {
                    Category category=categoryDao.findById(id);
                    rs= category.toString();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }else {
            response.sendError(404,"API not found");
        }
    }
// sửa dữ liệu
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer= resp.getWriter();
        Category category= gson.fromJson(req.getReader(),Category.class);
        try {
            if (categoryDao.update(category)){
                writer.println(gson.toJson(category));
            }else{
                resp.setStatus(400);
                writer.println("data is invalid");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error"+ throwables);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer= resp.getWriter();
        int id= Integer.parseInt(req.getParameter("id"));
        try {
            if (categoryDao.deleted(id)){
                writer.println("deleted is successfully!");
            }else {
                writer.println("delete false");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.setStatus(500);
            writer.println("error"+ throwables);
        }
    }
}
