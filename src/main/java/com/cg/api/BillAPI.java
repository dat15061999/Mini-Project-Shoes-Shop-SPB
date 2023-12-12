package com.cg.api;

import com.cg.entity.Bill;
import com.cg.entity.BillDetail;
import com.cg.entity.dto.BillDetailResDTO;
import com.cg.entity.dto.BillResDTO;
import com.cg.entity.dto.OrderResDTO;
import com.cg.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {
    @Autowired
    private IBillService billService;

    @GetMapping
    public ResponseEntity<?> getAllBill() {
        List<BillResDTO> bills = billService.findAll()
                .stream()
                .map(bill -> new BillResDTO()
                        .setId(bill.getId())
                        .setTotalProduct(billService.countBillDetailByBill_Id(bill.getId()))
                        .setStatus(bill.getStatus())
                        .setCreated(bill.getCreated())
                        .setCustomerName(bill.getCustomerName())
                        .setTotal(billService.totalBill(bill.getId())))
                .toList();

        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
    @GetMapping("/{idBill}")
    public ResponseEntity<?> getBill(@PathVariable Long idBill) {
        Optional<Bill> bill = billService.findById(idBill);
        List<BillDetailResDTO> billDetails = billService.findByBillId(idBill)
                .stream()
                .map(BillDetail::toBillDetailResDTO)
                .toList();

        OrderResDTO newBill = bill.get().toOrderResDTO();

        newBill.setBillDetails(billDetails);
        newBill.setTotal(billService.totalBill(bill.get().getId()));

        return new ResponseEntity<>(newBill, HttpStatus.OK);
    }
}
