package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;

import com.devsuperior.dsdeliver.models.Order;
import com.devsuperior.dsdeliver.models.Product;
import com.devsuperior.dsdeliver.models.dto.OrderDTO;
import com.devsuperior.dsdeliver.models.dto.ProductDTO;
import com.devsuperior.dsdeliver.models.enums.OrderStatus;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository oRepository;
    @Autowired
    private ProductRepository pRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        return OrderDTO.convert(this.oRepository.findOrdersWithProducts());
    }
    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), 
        dto.getLatitude(), dto.getLongitude(), 
        Instant.now(), OrderStatus.PENDING);

        for (ProductDTO p : dto.getProducts()) {
            //nao podia pegar direto e converter em produto 
            //tem que ir atras no banco e ficar com ele na mao
            Product product = pRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = this.oRepository.save(order);
        return new OrderDTO(order);
    }
    @Transactional
    public OrderDTO updateStatus(Long id){
        Order order = this.oRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        order = this.oRepository.save(order);
        return new OrderDTO(order);
    }
}
