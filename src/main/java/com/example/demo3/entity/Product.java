package com.example.demo3.entity;

import jakarta.persistence.*;

@Entity
@NamedQuery(
        name = "Product.getProductByNameOrId_6",
        query = "SELECT p FROM Product p WHERE p.name = ?1 OR p.id = ?2"
)

//@Table(name = "products")
public class Product {

    @Id
    private long id;
    private String name;

    @ManyToOne
    Category category;

    public Product() {
    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
