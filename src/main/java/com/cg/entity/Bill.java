package com.cg.entity;

import com.cg.entity.dto.OrderResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate created;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private List<BillDetail> billDetails;
    private String status;
    private String customerName;

    public OrderResDTO toOrderResDTO(){
        return new OrderResDTO()
                .setId(id)
                .setStatus(status)
                .setCreated(created)
                .setCustomer(customer)
                ;
    }
}
