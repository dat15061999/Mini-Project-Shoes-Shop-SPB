package com.cg.entity;

import com.cg.entity.dto.ProductResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
@Accessors(chain = true)
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal prevPrice;
    private BigDecimal newPrice;
    @ManyToOne
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

    private boolean deleted = Boolean.FALSE;

    public ProductResDTO toProductResDTO() {
        return new ProductResDTO(id,title,prevPrice,newPrice,image,company,category,color);
    }

    public Product(Long id) {
        this.id = id;
    }


}
