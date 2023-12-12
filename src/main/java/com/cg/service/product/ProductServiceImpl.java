package com.cg.service.product;

import com.cg.entity.Product;
import com.cg.entity.dto.FilterProduct;
import com.cg.entity.dto.ProductResDTO;
import com.cg.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<ProductResDTO> findAllProduct() {
        return productRepository.findAll().stream().map(Product::toProductResDTO).collect(Collectors.toList());
    }

    public Page<ProductResDTO> showAllProduct(Pageable pageable, FilterProduct filterProduct){
        return productRepository.searchAllByService(pageable, filterProduct)
                .map(Product::toProductResDTO);

    }
}
