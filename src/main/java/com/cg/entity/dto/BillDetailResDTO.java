package com.cg.entity.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Getter
@Setter
@Accessors(chain = true)
public class BillDetailResDTO {
    private String productName;
    private String url;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal total;
}
