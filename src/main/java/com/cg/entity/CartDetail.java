package com.cg.entity;

import com.cg.entity.dto.CartDetailResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cart_details")
@Accessors(chain = true)
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal total;
    public CartDetailResDTO toCartDetailResDTO(){
        return new CartDetailResDTO()
                .setColor(product.getColor().getName())
                .setId(id)
                .setUrl(product.getImage().getUrl())
                .setTotal(total)
                .setQuantity(quantity)
                .setProductPrice(productPrice)
                .setProductName(productName);
    }
    public BillDetail toBillDetail(){
        return new BillDetail()
                .setProduct(product)
                .setUrl(product.getImage().getUrl())
                .setTotal(total)
                .setQuantity(quantity)
                .setProductPrice(productPrice)
                .setProductName(productName);
    }
}
