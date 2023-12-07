package com.cg.service.customer;

import com.cg.entity.Customer;
import com.cg.service.IGenerateService;

import java.util.Optional;

public interface ICustomerService extends IGenerateService<Customer,Long> {
    Optional<Customer> findByName(String name);
}
