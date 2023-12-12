package com.cg.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BillResDTO {
    private Long id;
    private Long totalProduct;
    private LocalDate  created;
    private String status;
    private String customerName;
    private BigDecimal total;
}
