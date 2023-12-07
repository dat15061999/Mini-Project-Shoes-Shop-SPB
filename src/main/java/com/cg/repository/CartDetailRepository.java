package com.cg.repository;

import com.cg.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    @Query("SELECT count(c) FROM CartDetail c")
    Long countAll();

    Optional<CartDetail> findCartDetailByProductId(Long aLong);
}
