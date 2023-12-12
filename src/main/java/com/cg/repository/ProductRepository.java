package com.cg.repository;

import com.cg.entity.Product;
import com.cg.entity.dto.FilterProduct;
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
            "WHERE " +
            "(c.name LIKE %:#{#filter.category}% AND cl.name LIKE %:#{#filter.color}% " +
            "AND cp.name LIKE %:#{#filter.company}% AND p.prevPrice BETWEEN :#{#filter.min} AND :#{#filter.max}) " +
            "AND (c.name LIKE %:#{#filter.search}% OR cl.name LIKE %:#{#filter.search}% OR cp.name LIKE %:#{#filter.search}%)")
    Page<Product> searchAllByService(Pageable pageable, @Param("filter") FilterProduct filterProduct);
}
