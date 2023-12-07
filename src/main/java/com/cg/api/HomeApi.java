package com.cg.api;

import com.cg.entity.Bill;
import com.cg.entity.CartDetail;
import com.cg.entity.Customer;
import com.cg.entity.Product;
import com.cg.entity.dto.CartDetailDTO;
import com.cg.entity.dto.CartDetailResDTO;
import com.cg.entity.dto.CustomerReqDTO;
import com.cg.entity.dto.ProductResDTO;
import com.cg.service.bill.IBillService;
import com.cg.service.cartdetail.CartDetailServiceImpl;
import com.cg.service.customer.ICustomerService;
import com.cg.service.product.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class HomeApi {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartDetailServiceImpl cartDetailService;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IBillService billService;

    @GetMapping
    public ResponseEntity<Page<ProductResDTO>> getAll(@PageableDefault(size = 2) Pageable pageable,
                                                      @RequestParam(defaultValue = "", value = "search") String search,
                                                      @RequestParam(defaultValue = "", value = "category") String categoryName,
                                                      @RequestParam(defaultValue = "", value = "company") String companyName,
                                                      @RequestParam(defaultValue = "", value = "color") String colorName,
                                                      @RequestParam(defaultValue = "1", value = "min") BigDecimal min,
                                                      @RequestParam(defaultValue = "500000000000000000", value = "max") BigDecimal max

    ) {

        return new ResponseEntity<>(productService.showAllProduct(categoryName, companyName, colorName, search, pageable, min, max), HttpStatus.OK);
    }

    @GetMapping("/amountCartDetail")
    public ResponseEntity<?> countAllCartDetail() {
        return new ResponseEntity<>(cartDetailService.countAll(), HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> createCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {
        Optional<Product> product = productService.findById(cartDetailDTO.getIdProduct());
        Optional<CartDetail> cartDetail = cartDetailService.findCartDetailByProductId(cartDetailDTO.getIdProduct());
        if (cartDetail.isPresent()) {
            CartDetail oldCartDetail = cartDetail.get();
            oldCartDetail.setQuantity(oldCartDetail.getQuantity() + cartDetailDTO.getQuantity());
            oldCartDetail.setTotal(oldCartDetail.getProductPrice().multiply(BigDecimal.valueOf(oldCartDetail.getQuantity())));
            cartDetailService.save(oldCartDetail);
        } else {
            CartDetail newCartDetail = cartDetailDTO.toCartDetail();
            newCartDetail.setQuantity(cartDetailDTO.getQuantity());
            newCartDetail.setProductPrice(product.get().getPrevPrice());
            newCartDetail.setProductName(product.get().getTitle());
            newCartDetail.setTotal(product.get().getPrevPrice());
            cartDetailService.save(newCartDetail);
        }
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PatchMapping("/cart/{idCart}")
    public ResponseEntity<?> updateCartDetail(@PathVariable Long idCart,@RequestBody CartDetailDTO cartDetailDTO) {
        Optional<CartDetail> cartDetail = cartDetailService.findById(idCart);
        CartDetail oldCartDetail = cartDetail.get();
        oldCartDetail.setQuantity(oldCartDetail.getQuantity() + cartDetailDTO.getQuantity());
        oldCartDetail.setTotal(oldCartDetail.getProductPrice().multiply(BigDecimal.valueOf(oldCartDetail.getQuantity())));
        cartDetailService.save(oldCartDetail);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getAllCartDetail() {
        List<CartDetailResDTO> cartDetail = cartDetailService.findAll().stream()
                .map(CartDetail::toCartDetailResDTO).toList();
        return new ResponseEntity<>(cartDetail, HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/{idCart}")
    public ResponseEntity<?> deletedCart(@PathVariable Long idCart) {
        cartDetailService.delete(idCart);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    @GetMapping("/cart/{idCart}")
    public ResponseEntity<?> findByID(@PathVariable Long idCart) {
        Optional<CartDetail> cartDetail = cartDetailService.findById(idCart);
        CartDetail oldCartDetail = cartDetail.get();
        CartDetailResDTO cartDetailResDTO = oldCartDetail.toCartDetailResDTO();
        return new ResponseEntity<>(cartDetailResDTO, HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> createBill(@RequestBody CustomerReqDTO customerReqDTO) {
        Customer customer = customerReqDTO.toCustomer();
        customerService.save(customer);
        billService.save(customer);
        List<CartDetail> cartDetails = cartDetailService.findAll();
        Optional<Bill> bill = billService.findBillByCustomer_Name(customer.getName());
        for (CartDetail item : cartDetails) {
            billService.save(bill.get(),item);
            cartDetailService.delete(item.getId());
        }
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }


}
