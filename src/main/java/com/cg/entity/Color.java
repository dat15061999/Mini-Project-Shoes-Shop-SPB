package com.cg.entity;

import com.cg.entity.dto.ColorResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public ColorResDTO toColorResDTO() {
        return new ColorResDTO()
                .setName(name);
    }

    public Color(Long id) {
        this.id = id;
    }
}
