package com.cg.repository;

import com.cg.entity.BillDetail;
import com.cg.entity.dto.BillResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail,Long> {
    @Query("SELECT sum(bd.total) FROM BillDetail bd where bd.bill.id = :idBill")
    BigDecimal totalBill(@Param("idBill") Long idBill);
    Long  countBillDetailByBill_Id(Long idBill);
    List<BillDetail> findBillDetailsByBill_Id(Long idBill);
}
