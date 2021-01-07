package com.devsuperior.dsdeliver.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdeliver.models.Product;

public class ProductDTO implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageURI;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageURI = product.getImageURI();
    }
    public ProductDTO(){
        super();
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public String getDescription() {
        return description;
    }


    public String getImageURI() {
        return imageURI;
    }
    public static List<ProductDTO> convert(List<Product> products){
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
    
}
