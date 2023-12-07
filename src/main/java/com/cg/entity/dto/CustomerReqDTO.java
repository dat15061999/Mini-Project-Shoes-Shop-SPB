package com.cg.entity.dto;

import com.cg.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerReqDTO {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;

    public Customer toCustomer() {
        return new Customer()
                .setAddress(address)
                .setName(name)
                .setEmail(email)
                .setPhone(phone)
                ;
    }


}
