package com.cg.entity.dto;

import com.cg.entity.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Accessors(chain = true)
public class OrderResDTO {
    private Long id;
    private LocalDate created;
    private Customer customer;
    private List<BillDetailResDTO> billDetails;
    private String status;
    private String customerName;
    private BigDecimal total;
}
