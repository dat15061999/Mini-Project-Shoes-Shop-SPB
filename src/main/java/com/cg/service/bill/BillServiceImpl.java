package com.cg.service.bill;

import com.cg.entity.Bill;
import com.cg.entity.BillDetail;
import com.cg.entity.CartDetail;
import com.cg.entity.Customer;
import com.cg.repository.BillDetailRepository;
import com.cg.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements IBillService{
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;


    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Bill bill) {

    }

    @Override
    public void save(Customer customer) {
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setCreated(LocalDate.now());
        billRepository.save(bill);
    }

    @Override
    public Optional<Bill> findBillByCustomer_Name(String name){
        return billRepository.findBillByCustomer_Name(name);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void save(Bill bill,CartDetail cartDetail) {
        BillDetail billDetail = cartDetail.toBillDetail();
        billDetail.setBill(bill);
        billDetailRepository.save(billDetail);
    }
}
