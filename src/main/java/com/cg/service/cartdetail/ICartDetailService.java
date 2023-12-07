package com.cg.service.cartdetail;

import com.cg.entity.CartDetail;
import com.cg.service.IGenerateService;

import java.util.Optional;

public interface ICartDetailService extends IGenerateService<CartDetail,Long> {
    Long countAll();

    Optional<CartDetail> findCartDetailByProductId(Long idProduct);
}
