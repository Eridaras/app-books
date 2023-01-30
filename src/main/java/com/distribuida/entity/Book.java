package com.distribuida.entity;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;

import java.math.BigDecimal;

@ApplicationScoped
@Data
public class Book {
    private Integer id;
    private String isbn;
    private String title;
    private Integer author_id;
    private BigDecimal price;
}
