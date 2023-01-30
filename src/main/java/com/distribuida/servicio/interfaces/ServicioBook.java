package com.distribuida.servicio.interfaces;

import com.distribuida.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface ServicioBook {
    public void create (Book obj) throws SQLException;
    public Book find(Integer id);
    public List<Book> findAll();
    public boolean delete(Integer id) throws SQLException;
    public  boolean update(Integer id, Book obj) throws SQLException;

}
