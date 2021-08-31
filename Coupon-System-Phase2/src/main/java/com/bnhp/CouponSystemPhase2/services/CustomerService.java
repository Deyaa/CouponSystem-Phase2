package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    void purchaseCoupon(Coupon coupon) throws CouponSystemException;
    List<Coupon> getCustomerCoupons();
    List<Coupon> getCustomerCoupons(int categoryId);
    List<Coupon> getCustomerCoupons(double maxPrice);
    Customer getCustomerDetails() throws CouponSystemException;
    List<Coupon> findExpiredCoupons();
}
