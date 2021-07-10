package khanhld.bk.dao_impl;

import khanhld.bk.dao.DatabaseConnection;
import khanhld.bk.dao.ProductDao;
import khanhld.bk.model.Category;
import khanhld.bk.model.Product;
import khanhld.bk.util.Utility;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    protected String table;
    protected Class<Product> type;

    public ProductDaoImpl(String table, Class<Product> type) {
        this.table = table;
        this.type = type;
    }
    protected PreparedStatement prepare(String sql) throws SQLException {
        return DatabaseConnection.connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        try {
            Field[] fields = type.getDeclaredFields();// truy xuất vào thuộc tính của class
            Product object = (Product) type.newInstance();//
            for (Field f : fields) {
                f.setAccessible(true);
                f.set(object, resultSet.getObject(Utility.camelToSnake(f.getName())));
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public java.util.List<Product> getList(ResultSet resultSet)  {
        java.util.List<Product> data= new ArrayList<>();
        try {
            while (resultSet.next()) {
                data.add(getObject(resultSet));// thêm đối tượng tại vị trí rs trỏ tới
            }
        } catch (SQLException e) {
            //do nothing
        }
        return data;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql ="SELECT * FROM"+table+"WHERE delete= false";
        PreparedStatement prepare= prepare(sql);
        ResultSet resultSet= prepare.executeQuery();
        return getList(resultSet);
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql= "SELECT * FROM"+ table+ "WHERE id = ? AND deleted = false";
        PreparedStatement prepare=prepare(sql);
        ResultSet resultSet = prepare.executeQuery();
        resultSet.first();
        return getObject(resultSet);
    }

    @Override
    public Product insert(Product object) throws SQLException {
        PreparedStatement prepareUpdate;
        Field[] fields = type.getDeclaredFields();// cho phép truy cập vào tất cả các trường của lớp
        int fileNumber=fields.length;
        StringBuilder sql=new StringBuilder("INSERT INTO"+table+"(");
        for (int i =1; i<fileNumber;i++){
            Field f = fields[i];
            sql.append(Utility.camelToSnake(f.getName())).append(i!=fileNumber-1 ? "," : ") VALUES (");
        }
        for (int i =1; i<fileNumber;i++){
            Field f= fields[i];
            sql.append(i!=fileNumber - 1 ?"?," : "?)");
        }
        prepareUpdate=prepare(String.valueOf(sql));
        for (int i = 1;i<fileNumber;i++){
            Field f= fields[i];
            f.setAccessible(true);
            try {
                prepareUpdate.setObject(i,f.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return  null;
            }
        }
        int count= prepareUpdate.executeUpdate();
        if (count==1){
            PreparedStatement prepared1=prepare("SELECT  * FROM"+table);
            ResultSet resultSet= prepared1.executeQuery();
            resultSet.last();
            return getObject(resultSet);
        }else{
            return null;
        }

    }

    @Override
    public boolean update(Product product) throws SQLException {
        PreparedStatement prepared;
        Field[] field=type.getDeclaredFields();
        int fileNumber=field.length;
        StringBuilder sql =new StringBuilder("UPDATE"+ table+ "SET");
        for (int i=1; i<fileNumber;i++){
            Field f = field[i];
            sql.append(Utility.camelToSnake(f.getName())).append(i!=fileNumber-1 ? "= ?,":"=?");
        }
        sql.append("WHERE").append(Utility.camelToSnake(field[0].getName())).append("=?");//id trường
        prepared= prepare(String.valueOf(sql));
        for (int i=1; i<fileNumber;i++){
            Field f= field[i];
            f.setAccessible(true);
            prepared.setObject(i,getObject((ResultSet) product));
        }
        Field f=field[0];
        f.setAccessible(true);
        try {
            prepared.setObject(fileNumber, f.get(product));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        int count = prepared.executeUpdate();
        return count == 1;

    }

    @Override
    public boolean deleted(int id) throws SQLException {
        String sql = "UPDATE"+ table + "set deleted= true WHERE ID=?";
        PreparedStatement prepared= prepare(sql);
        prepared.setInt(1,id);
        int count=prepared.executeUpdate();
        return count==1;
    }
}
