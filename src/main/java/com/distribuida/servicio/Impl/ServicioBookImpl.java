package com.distribuida.servicio.Impl;

import com.distribuida.entity.Book;
import com.distribuida.servicio.interfaces.ServicioBook;
import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class ServicioBookImpl implements ServicioBook {
    @Inject
    private DbClient dbClient;

    public Book find(Integer id){
        Book book = new Book();
        try {
            Multi<Book> listBooks = dbClient
                    .execute(exec->exec
                            .namedQuery("select-id",id)
                            .map(item -> {
                                Book b = new Book();
                                b.setId(item.column("id").as(Integer.class));
                                b.setIsbn(item.column("isbn").as(String.class));
                                b.setTitle(item.column("title").as(String.class));
                                b.setAuthor_id(item.column("author_id").as(Integer.class));
                                b.setPrice(item.column("price").as(BigDecimal.class));
                                return b;
            }));
            Single<List<Book>> books = listBooks.collectList();
            List<Book>  listaAux = books.get();
            book = listaAux.get(0);
        }catch (Exception e){
            e.printStackTrace();
            return book;
        }
        return book;
    }

    //listar todos los libros
    public List<Book> findAll(){
        Multi<Book> rows = dbClient
                .execute(exec -> exec
                        .namedQuery("select-books")
                        .map(item -> {
                            Book b = new Book();
                            b.setId(item.column("id").as(Integer.class));
                            b.setIsbn(item.column("isbn").as(String.class));
                            b.setTitle(item.column("title").as(String.class));
                            b.setAuthor_id(item.column("author_id").as(Integer.class));
                            b.setPrice(item.column("price").as(BigDecimal.class));
                            return b;
                        }));
        Single<List<Book>> listbooks = rows.collectList();
        try {
            List<Book> books = listbooks.get();
            return books;
        }catch (ExecutionException | InterruptedException e  ){
            e.printStackTrace();
        }
        return null;
    }
    //crear una book
    public void create (Book obj)throws SQLException {
        System.out.println(obj.getIsbn() + " " + obj.getTitle() + " " + obj.getAuthor_id());
        dbClient.execute(exec->exec
                .createInsert("INSERT INTO books (isbn,title, author_id, price) VALUES('"+obj.getIsbn()+"','"+obj.getTitle()+"',"+obj.getAuthor_id()+","+obj.getPrice()+");")
                        .execute()).thenAccept(count-> System.out.printf("Creado ",count));
    }


    //Eliminar book
    public boolean delete(Integer id) throws SQLException {
        boolean borrado;
        dbClient.execute(exec -> exec
                        .delete("DELETE FROM bookS WHERE id = ?",id))
                .thenAccept(count -> System.out.printf("Borrado ",count));
        borrado = true;
        return borrado;
    }

    //actualizar book
    public boolean update(Integer id, Book obj) throws SQLException {
        boolean actualizado;
        dbClient.execute(exec -> exec
                .createUpdate("UPDATE books SET isbn = '" + obj.getIsbn()
                        + "', title = '" + obj.getTitle()
                        + "', author_id = "+obj.getAuthor_id()
                        + ", price =" +obj.getPrice()
                         +"WHERE id = " + id)
                .execute()).thenAccept(count -> System.out.printf("Actualizado book", count));
        actualizado = true;
        return actualizado;
    }
}
