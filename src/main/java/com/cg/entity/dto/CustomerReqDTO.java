package com.cg.entity.dto;

import com.cg.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerReqDTO {
    private String name;
    private String email;
    private Long phone;
    private String address;

    public Customer toCustomer(){
        return new Customer()
                .setAddress(address)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                ;
    }
}
