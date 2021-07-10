package khanhld.bk.dao;

import khanhld.bk.controller.config.AppConfig;

import java.sql.*;

public class DatabaseConnection {
    public static Connection connection;// 1 project chỉ kết nối với 1db
    public static void driverTest() throws ClassNotFoundException {
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("jdbc not found"+e.getMessage());
        }
    }
    // connectDB
    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        driverTest();
        // sử dụng drivermanager.getconnection để tạo liên kết đến 3 tham số
        try {
            connection= DriverManager.getConnection(AppConfig.URL_DATABASE,AppConfig.USERNAME,AppConfig.PASSWORD);
            System.out.println("connectDB successfully");
        } catch (SQLException throwables) {
            throw new SQLException("Connect fail"+throwables.getMessage());
        }
        return connection;
    }
    // prepare nhận một câu lệnh tìm kiếm và thực hiện
    public PreparedStatement prepare(String sql){
        System.out.println(">>"+sql);
        try {
            // đối với các câu lệnh thì hàm prepareStatement cần truyền thêm tham sốResultSet.TYPE_SCROLL_SENSITIVE
            // lệnh này cho phép con trỏ resultSet chạy từ bản ghi đầu tiên đến bản ghi cuối cùng của db
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public PreparedStatement prepareUpdate(String sql){
        System.out.println(">>"+sql);
        try {
            // thêm thuộc tính statment.return... dùng để khi thêm 1 bản ghi thì nó trả về id cho mình
            return connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    // sau khi thao tác xong thì đóng tài nguyên
    public static void closeConnection() throws SQLException {
        if (connection!=null){
            connection.close();
            System.out.println("close connection!!!");
        }
    }
}
