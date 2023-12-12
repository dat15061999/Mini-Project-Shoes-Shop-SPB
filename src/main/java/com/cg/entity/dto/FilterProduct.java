package com.cg.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilterProduct {
    String search;
    String category;
    String company;
    String color;
    BigDecimal min;
    BigDecimal max;
}
