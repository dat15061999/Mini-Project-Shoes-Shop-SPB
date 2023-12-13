package com.cg.entity;

import com.cg.entity.dto.CategoryResDTO;
import com.cg.entity.dto.CompanyResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public CompanyResDTO toCompanyResDTO(){
        return new CompanyResDTO()
                .setId(id)
                .setName(name);
    }

    public Company(Long id) {
        this.id = id;
    }
}
