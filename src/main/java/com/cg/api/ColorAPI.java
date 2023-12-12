package com.cg.api;

import com.cg.entity.Bill;
import com.cg.entity.BillDetail;
import com.cg.entity.Color;
import com.cg.entity.dto.BillDetailResDTO;
import com.cg.entity.dto.BillResDTO;
import com.cg.entity.dto.OrderResDTO;
import com.cg.service.bill.IBillService;
import com.cg.service.color.IColorService;
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
@RequestMapping("/api/colors")
public class ColorAPI {
    @Autowired
    private IColorService colorService;

    @GetMapping
    public ResponseEntity<?> getAllColors() {
        List<Color> colors = colorService.findAll();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

}
