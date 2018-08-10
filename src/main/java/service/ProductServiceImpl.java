package service;

import model.Products;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/awesomeBusiness";
    static final String DB_USER = "root";
    static final String DB_PASS = "07091994";
    private static List<Products> products;

    public ProductServiceImpl() {

    }

    @Override
    public List<Products> findAll() throws ServerException, ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);

        Connection conn = null;
        Statement stm = null;

        log("dang ket noi toi co so du lieu...");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        log("Tao lenh truy van");
        stm = conn.createStatement();
        String sql = "select product_id, product_code, product_name, product_price from products";

        log("Dang thuc hien truy van...");
        ResultSet rs = stm.executeQuery(sql);


        log("Dang thu nhan ket qua...");
        products = new ArrayList<>();
        while (rs.next()) {
            Products product = new Products();
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_code(rs.getString("product_code"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_price(rs.getFloat("product_price"));

            products.add(product);
        }
        log("Hoan thanh thu nhan ket qua. Dang dong cac ket noi..");
        rs.close();
        stm.close();
        conn.close();
        log("Thanh cong");
        return products;
    }

    @Override
    public void save(Products product) throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        log("dang ket noi toi co so du lieu...");

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        log("Tao lenh insert");
        String sql = "insert into products(product_code, product_name, product_price) values (?,?,?)";

        log("Dang thuc hien insert...");
        PreparedStatement ps = conn.prepareStatement(sql);

        log("Dang thu nhan ket qua...");
        ps.setString(1, product.getProduct_code());
        ps.setString(2, product.getProduct_name());
        ps.setFloat(3, product.getProduct_price());
        ps.executeUpdate();

        products.add(product);
        log("Hoan thanh thu nhan ket qua. Dang dong cac ket noi..");
        ps.close();
        conn.close();
        log("Thanh cong");
    }

    @Override
    public Products findByID(int id) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        String sql = "select * from products where product_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Products product = new Products();
        while (rs.next()) {
            product.setProduct_id(rs.getInt("product_id"));
            product.setProduct_code(rs.getString("product_code"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_price(rs.getFloat("product_price"));
        }
        rs.close();
        ps.close();
        conn.close();
        return product;
    }

    @Override
    public void update(int id, Products product) throws SQLException, ClassNotFoundException {

        Class.forName(DB_DRIVER);

        log("dang ket noi toi co so du lieu...");

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        log("Tao lenh insert");
        String sql = "update products set product_code=?, product_name=?, product_price=? where product_id=?";

        log("Dang thuc hien insert...");
        PreparedStatement ps = conn.prepareStatement(sql);

        log("Dang thu nhan ket qua...");
        ps.setString(1, product.getProduct_code());
        ps.setString(2, product.getProduct_name());
        ps.setFloat(3, product.getProduct_price());
        ps.setInt(4, product.getProduct_id());
        ps.executeUpdate();

        products.add(product);
        log("Hoan thanh thu nhan ket qua. Dang dong cac ket noi..");
        ps.close();
        conn.close();
        log("Thanh cong");

    }

    @Override
    public void delete(int id) throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);

        log("dang ket noi toi co so du lieu...");

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        log("Tao lenh delete");
        String sql = "delete from products where product_id=?";

        log("Dang thuc hien delete...");
        PreparedStatement ps = conn.prepareStatement(sql);

        log("Dang thu nhan ket qua...");
        ps.setInt(1, id);
        ps.executeUpdate();

//        products.remove(id);
        log("Hoan thanh thu nhan ket qua. Dang dong cac ket noi..");
        ps.close();
        conn.close();
        log("Thanh cong");

    }

    public void log(String mess) {
        System.out.println(mess);
    }
}
