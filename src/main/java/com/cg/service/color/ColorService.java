package com.cg.service.color;

import com.cg.entity.Color;
import com.cg.repository.ColorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ColorService implements IColorService{
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void save(Color color) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
