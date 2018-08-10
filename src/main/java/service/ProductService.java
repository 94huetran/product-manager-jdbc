package service;

import model.Products;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Products> findAll() throws ServerException, ClassNotFoundException, SQLException;

    void save(Products products) throws SQLException, ClassNotFoundException;

    Products findByID(int id) throws ClassNotFoundException, SQLException;

    void update(int id,Products products) throws SQLException, ClassNotFoundException;

    void delete(int id) throws ClassNotFoundException, SQLException;
}
