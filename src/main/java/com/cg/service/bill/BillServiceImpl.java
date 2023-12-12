package com.cg.service.bill;

import com.cg.entity.Bill;
import com.cg.entity.BillDetail;
import com.cg.entity.CartDetail;
import com.cg.entity.Customer;
import com.cg.entity.dto.BillDetailResDTO;
import com.cg.entity.dto.BillResDTO;
import com.cg.entity.dto.OrderResDTO;
import com.cg.repository.BillDetailRepository;
import com.cg.repository.BillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long aLong) {
        return billRepository.findById(aLong) ;
    }

    @Override
    public void save(Bill bill) {

    }

    @Override
    public void save(Customer customer) {
        Bill bill = new Bill();
        bill.setCustomer(customer);
        bill.setCreated(LocalDate.now());
        bill.setStatus("draft");
        bill.setCustomerName(customer.getName());
        billRepository.save(bill);
    }

    @Override
    public Optional<Bill> findBillByCustomer_Name(String name){
        return billRepository.findBillByCustomer_Name(name);
    }

    @Override
    public BigDecimal totalBill(Long idBill) {
        return billDetailRepository.totalBill(idBill);
    }

    @Override
    public Long countBillDetailByBill_Id(Long idBill) {
        return billDetailRepository.countBillDetailByBill_Id(idBill);
    }

    @Override
    public List<BillDetail> findByBillId(Long aLong) {
        return billDetailRepository.findBillDetailsByBill_Id(aLong);
    }

//    @Override
//    public Optional<OrderResDTO> findAllOrder() {
//        Optional<Bill> entities = billRepository.findAll();
//        return null;
//    }
//
//    @Override
//    public List<BillDetailResDTO> findAll(Long idBill) {
//        return null;
//    }


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
