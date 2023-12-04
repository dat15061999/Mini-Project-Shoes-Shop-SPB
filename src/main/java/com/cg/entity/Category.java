package com.cg.entity;

import com.cg.entity.dto.CategoryResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public CategoryResDTO toCategory(){
        return new CategoryResDTO()
                .setName(name);
    }
}
