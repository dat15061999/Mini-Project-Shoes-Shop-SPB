package com.cg.entity.dto;

import com.cg.entity.Category;
import com.cg.entity.Color;
import com.cg.entity.Company;
import com.cg.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ProductResDTO {
    private Long id;
    private String title;
    private BigDecimal prevPrice;
    private BigDecimal newPrice;

    private ImageResDTO image;

    private CompanyResDTO company;

    private CategoryResDTO category;

    private ColorResDTO color;

    public ProductResDTO(Long id, String title, BigDecimal prevPrice, BigDecimal newPrice, Image image, Company company, Category category, Color color) {
        this.id = id;
        this.title = title;
        this.prevPrice = prevPrice;
        this.newPrice = newPrice;
        this.image = image.toImageResDTO();
        this.company = company.toCompanyResDTO();
        this.category = category.toCategory();
        this.color = color.toColorResDTO();
    }
}
