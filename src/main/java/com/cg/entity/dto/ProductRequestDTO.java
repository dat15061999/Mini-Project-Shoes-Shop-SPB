package com.cg.entity.dto;

import com.cg.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductRequestDTO {
    private String title;
    private BigDecimal newPrice;
    private BigDecimal prevPrice;
    private Long idCategory;
    private Long idCompany;
    private Long idColor;
    private Long idImage;
    public Product toProduct(){
        return new Product()
                .setCategory(new Category(idCategory))
                .setColor(new Color(idColor))
                .setTitle(title)
                .setCompany(new Company(idCompany))
                .setNewPrice(newPrice)
                .setPrevPrice(prevPrice)
                .setImage(new Image(idImage))
                ;
    }
}
