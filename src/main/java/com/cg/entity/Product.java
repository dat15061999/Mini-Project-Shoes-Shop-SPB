package com.cg.entity;

import com.cg.entity.dto.ProductResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal prevPrice;
    private BigDecimal newPrice;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    public ProductResDTO toProductResDTO() {
        return new ProductResDTO(id,title,prevPrice,newPrice,image,company,category,color);
    }

}
