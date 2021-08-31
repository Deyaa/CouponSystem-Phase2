package com.bnhp.CouponSystemPhase2.job;

import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import com.bnhp.CouponSystemPhase2.services.CustomerService;
import com.bnhp.CouponSystemPhase2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyRemoval {

    private final static int DAY = 1000*60*60*24;
    private final static int SEC3 = 1000*5;
    private final CustomerService customerService;
    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = SEC3)
    public void runJob(){
        System.out.println(Art.DAILY_JOB);

        System.out.println("====================> Start job removing expired coupons");
        List<Coupon> expiredCoupons = customerService.findExpiredCoupons();
        System.out.println("Before removing expired coupons: " + couponRepository.findAll().size());
        for (Coupon c : expiredCoupons) {
            couponRepository.deleteById(c.getId());
            couponRepository.deleteCustomerCouponByCouponId(c.getId());
        }
        System.out.println("After removing expired coupons: " + couponRepository.findAll().size());
    }
}
