package com.cg.service.bill;

import com.cg.entity.Bill;
import com.cg.entity.BillDetail;
import com.cg.entity.CartDetail;
import com.cg.entity.Customer;
import com.cg.entity.dto.BillDetailResDTO;
import com.cg.entity.dto.BillResDTO;
import com.cg.entity.dto.OrderResDTO;
import com.cg.service.IGenerateService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBillService extends IGenerateService<Bill,Long> {
    void save(Bill bill, CartDetail cartDetail);
    void save(Customer customer);
    Optional<Bill> findBillByCustomer_Name(String name);


    BigDecimal totalBill(@Param("idBill") Long idBill);
    Long  countBillDetailByBill_Id(Long idBill);
    List<BillDetail> findByBillId(Long idBill);
//    List<OrderResDTO> findAllOrder();
//    List<BillDetailResDTO> findAll(Long idBill);

}
