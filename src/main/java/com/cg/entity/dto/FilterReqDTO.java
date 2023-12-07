package com.cg.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterReqDTO {
    private String search;
    private String categoryName;
    private String companyName;
    private String colorName;
    private BigDecimal min;
    private BigDecimal max;
}
