package com.cg.entity;


import com.cg.entity.dto.ImageResDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Long id;
    private String url;
    public ImageResDTO toImageResDTO(){
        return new ImageResDTO()
                .setUrl(url);
    }
}
