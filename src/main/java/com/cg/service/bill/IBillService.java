package com.cg.service.bill;

import com.cg.entity.Bill;
import com.cg.entity.CartDetail;
import com.cg.entity.Customer;
import com.cg.service.IGenerateService;

import java.util.Optional;

public interface IBillService extends IGenerateService<Bill,Long> {
    void save(Bill bill, CartDetail cartDetail);
    void save(Customer customer);
    Optional<Bill> findBillByCustomer_Name(String name);
}
