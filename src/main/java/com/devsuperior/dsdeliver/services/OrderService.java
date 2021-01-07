package com.devsuperior.dsdeliver.services;

import java.util.List;

import com.devsuperior.dsdeliver.models.dto.OrderDTO;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository oRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        return OrderDTO.convert(this.oRepository.findOrdersWithProducts());
    }
}
