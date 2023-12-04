package com.cg.api;

import com.cg.entity.dto.ProductResDTO;
import com.cg.service.product.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class HomeApi {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<ProductResDTO> list = productService.findAllProduct();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
