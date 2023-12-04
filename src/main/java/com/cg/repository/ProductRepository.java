package com.cg.repository;

import com.cg.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p " +
            "JOIN p.category c " +
            "JOIN p.color cl " +
            "JOIN p.company cp " +
            "WHERE (c.name LIKE %:categoryName% AND cl.name LIKE %:colorName% AND cp.name LIKE %:companyName% AND p.prevPrice BETWEEN :min AND :max) " +
            "AND (c.name LIKE %:search% OR cl.name LIKE %:search% OR cp.name LIKE %:search%)")
    Page<Product> searchAllByService(@Param("categoryName") String categoryName,@Param("companyName") String companyName,@Param("colorName") String colorName,@Param("search") String search, Pageable pageable , @Param("min") BigDecimal min, @Param("max") BigDecimal max);
}
