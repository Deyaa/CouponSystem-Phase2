package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon(Coupon coupon) throws CouponSystemException;
    void deleteCoupon(int couponId);
    List<Coupon> getCompanyCoupons();
    List<Coupon> getCompanyCoupons(int categoryId);
    List<Coupon> getCompanyCoupons(double maxPrice);
    Company getCompanyDetails(int couponId);
    List<Coupon> getAllCoupons();
    Coupon getCouponDetails(int couponId);
}
