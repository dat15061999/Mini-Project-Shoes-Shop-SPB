package com.cg.entity.dto;


import com.cg.entity.CartDetail;
import com.cg.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartDetailDTO {
    private Long idProduct;
    private int quantity;

    public CartDetail toCartDetail(){
        return new CartDetail()
                .setProduct(new Product(idProduct));
    }
}
