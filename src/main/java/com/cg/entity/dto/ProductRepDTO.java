package com.cg.entity.dto;

import com.cg.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductRepDTO {
    private String title;
    private BigDecimal price;
    private Long idCategory;
    private Long idCompany;
    private Long idColor;
    private Long image;
    public Product toProduct(){
        return new Product()
                .setCategory(new Category(idCategory))
                .setColor(new Color(idColor))
                .setTitle(title)
                .setCompany(new Company(idCompany))
                .setNewPrice(price.subtract(BigDecimal.valueOf(10)))
                .setPrevPrice(price)
                .setImage(new Image(image))
                ;
    }
}
