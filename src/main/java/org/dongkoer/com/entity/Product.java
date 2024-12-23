package org.dongkoer.com.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "产品模型")
public class Product {

    private Integer Id;
    private String name;
    private String description;
    private Double price;

    public Product() {
    }

    public Product(Integer id, String name, String description, Double price) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
