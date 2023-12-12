package com.cg.entity;

import com.cg.entity.dto.BillDetailResDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "bill_details")
@Accessors(chain = true)
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    @JsonIgnore
    private Bill bill;
    private String url;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal total;

    public BillDetailResDTO toBillDetailResDTO(){
        return new BillDetailResDTO()
                .setUrl(url)
                .setTotal(total)
                .setQuantity(quantity)
                .setProductName(productName)
                .setProductPrice(productPrice)
                ;
    }
}
