package com.devsuperior.dsdeliver.models.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsdeliver.models.Order;
import com.devsuperior.dsdeliver.models.enums.OrderStatus;

public class OrderDTO {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;
    private List<ProductDTO> products = new ArrayList<>();

    public OrderDTO(Order order){
        this.id = order.getId();
        this.address = order.getAddress();
        this.latitude = order.getLatitude();
        this.longitude = order.getLongitude();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.products = order.getProducts().stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
    }

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

   
    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
    public static List<OrderDTO> convert(List<Order> orders){
        return orders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }
  
}
