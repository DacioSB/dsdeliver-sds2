package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import com.devsuperior.dsdeliver.models.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService oService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok().body(this.oService.findAll());
    }
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto){
        dto = this.oService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id){
        return ResponseEntity.ok().body(this.oService.updateStatus(id));

    }
}
