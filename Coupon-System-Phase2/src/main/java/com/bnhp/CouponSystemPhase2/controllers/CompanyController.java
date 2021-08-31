package com.bnhp.CouponSystemPhase2.controllers;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.security.ClientType;
import com.bnhp.CouponSystemPhase2.security.LoginManager;
import com.bnhp.CouponSystemPhase2.services.CompanyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Company API", description = "Company API")
@RestController
@RequestMapping("api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final LoginManager loginManager;
    private CompanyService companyService;

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password) {
        companyService = (CompanyService) loginManager.login(email, password, ClientType.Company);
        return new ResponseEntity<>("LogIn success!", HttpStatus.OK) ;
    }

    @PostMapping("/coupon")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon couponToAdd) throws CouponSystemException {
        companyService.addCoupon(couponToAdd);
        return new ResponseEntity<>(couponToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/coupon")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon couponToUpdate) throws CouponSystemException {
        companyService.updateCoupon(couponToUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("id") int couponId) {
        companyService.deleteCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/coupons")
    public ResponseEntity<?> getCompanyCoupons() {
        return new ResponseEntity<>(companyService.getCompanyCoupons(), HttpStatus.OK);
    }

    @GetMapping("/coupons/category/{categoryId}")
    public ResponseEntity<?> getCompanyCoupons(@PathVariable("categoryId") int categoryId) {
        return new ResponseEntity<>(companyService.getCompanyCoupons(categoryId), HttpStatus.OK);
    }

    @GetMapping("/coupons/price/{maxPrice}")
    public ResponseEntity<?> getCompanyCoupons(@PathVariable("maxPrice") double maxPrice) {
        return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice), HttpStatus.OK);
    }

    @GetMapping("/coupons/{id}")
    public ResponseEntity<?> getCompanyDetails(@PathVariable("id") int couponId) {
        Coupon coupon = null;
        return new ResponseEntity<>(companyService.getCompanyDetails(couponId), HttpStatus.OK);
    }
}
