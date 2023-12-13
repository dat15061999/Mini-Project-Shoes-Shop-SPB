package com.cg.service.product;

import com.cg.entity.Product;
import com.cg.entity.dto.ProductRequestDTO;
import com.cg.entity.dto.ProductResDTO;
import com.cg.service.IGenerateService;

import java.util.List;

public interface IProductService extends IGenerateService<Product,Long> {
     List<ProductResDTO> findAllProduct();
     Product update(Long idProduct, ProductRequestDTO productRequestDTO);
}
