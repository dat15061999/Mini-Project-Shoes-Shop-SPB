package com.cg.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CartDetailResDTO {
    private Long id;
    private String color;
    private String url;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal total;
}
