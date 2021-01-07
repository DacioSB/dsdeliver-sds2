package com.devsuperior.dsdeliver.services;

import java.util.List;


import com.devsuperior.dsdeliver.models.Product;
import com.devsuperior.dsdeliver.models.dto.ProductDTO;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository pRepository;

   @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        List<Product> listProducts = this.pRepository.findAllByOrderByNameAsc();
        
        return ProductDTO.convert(listProducts);
    }
}
