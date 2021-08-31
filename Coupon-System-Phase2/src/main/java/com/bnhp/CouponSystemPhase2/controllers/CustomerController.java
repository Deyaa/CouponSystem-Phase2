package com.bnhp.CouponSystemPhase2.controllers;

import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.security.ClientType;
import com.bnhp.CouponSystemPhase2.security.LoginManager;
import com.bnhp.CouponSystemPhase2.services.CustomerService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Customer API", description = "Customer API")
@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final LoginManager loginManager;
    private CustomerService customerService;

    /**
     *
     * @param email - customer login email
     * @param password - customer password
     * @return - if the login success or failed
     */
    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        customerService = (CustomerService) loginManager.login(email, password, ClientType.Customer);
        return new ResponseEntity<>("LogIn success!", HttpStatus.OK);
    }

    /**
     *
     * @param coupon - The coupon customer want to purchase
     * @return - The status of purchasing the coupon
     * @throws CouponSystemException
     */
    @PostMapping("/coupons/purchase")
    public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon) throws CouponSystemException {
        customerService.purchaseCoupon(coupon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/coupons")
    public ResponseEntity<?> getCustomerCoupons() {
        return new ResponseEntity<>(customerService.getCustomerCoupons(), HttpStatus.OK);
    }

    @GetMapping("/coupons/category/{id}")
    public ResponseEntity<?> getCustomerCoupons(@PathVariable("id") int categoryId) {
        return new ResponseEntity<>(customerService.getCustomerCoupons(categoryId), HttpStatus.OK);
    }

    @GetMapping("/coupons/price/{maxPrice}")
    public ResponseEntity<?> getCustomerCoupons(@PathVariable("maxPrice") double maxPrice) {
        return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice), HttpStatus.OK);
    }

    @GetMapping("/customerDetails")
    public ResponseEntity<?> getCustomerDetails() throws CouponSystemException {
        return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.OK);
    }
}
