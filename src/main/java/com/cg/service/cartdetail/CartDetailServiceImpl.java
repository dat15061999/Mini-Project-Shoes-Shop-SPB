package com.cg.service.cartdetail;

import com.cg.entity.CartDetail;
import com.cg.repository.CartDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartDetailServiceImpl implements ICartDetailService{
    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long aLong) {
        return cartDetailRepository.findById(aLong);
    }

    @Override
    public void save(CartDetail cartDetail) {
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void delete(Long aLong) {
        cartDetailRepository.deleteById(aLong);
    }

    @Override
    public Long countAll() {
        return cartDetailRepository.countAll();
    }

    @Override
    public Optional<CartDetail> findCartDetailByProductId(Long idProduct) {
        return cartDetailRepository.findCartDetailByProductId(idProduct);
    }
}
