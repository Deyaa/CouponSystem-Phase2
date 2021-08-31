package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.exceptions.ErrorMsg;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import com.bnhp.CouponSystemPhase2.repos.CustomerRepository;
import com.bnhp.CouponSystemPhase2.utils.ExceptionUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Getter
    @Setter
    private int customerId;

    @Override
    public boolean login(String email, String password) {
        if(customerRepository.existsByEmailAndPassword(email, password)){
            setCustomerId(customerRepository.findByEmailAndPassword(email, password).getId());
            return true;
        }
        return false;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        Coupon couponFounded = couponRepository.getById(coupon.getId());
        if(couponFounded == null){
            throw new CouponSystemException(ErrorMsg.COUPON_ID_NOT_EXISTS);
        }
        if (couponFounded.getAmount() <= 0){
            throw new CouponSystemException(ErrorMsg.COUPON_AMOUNT_ZERO);
        }
        if(couponFounded.getEndDate().before(Date.valueOf(LocalDate.now()))){
            throw new CouponSystemException(ErrorMsg.COUPON_EXPIRED);
        }
        if(couponRepository.purchasedCouponByCustomer(coupon.getId(), getCustomerId()).size() > 0) {
            throw new CouponSystemException(ErrorMsg.COUPON_DOUBLE_PURCHASE);
        }

        //Lower the amount of coupons
        coupon.setAmount(coupon.getAmount() - 1) ;
        couponRepository.saveAndFlush(coupon);
        //Add the new coupon to customer list purchase
        Customer customer = customerRepository.getById(getCustomerId());
        customer.getCoupons().add(coupon);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return couponRepository.findCustomerCoupons(getCustomerId());
    }

    @Override
    public List<Coupon> getCustomerCoupons(int categoryId) {
        return couponRepository.findCustomerCouponsByCategory(getCustomerId(), categoryId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponRepository.findCustomerCouponsLessThan(getCustomerId(), maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return customerRepository.findById(getCustomerId()).orElseThrow(ExceptionUtils::customerNotFound);
    }

    public List<Coupon> findExpiredCoupons() {
        return couponRepository.findExpiredCoupons();
    }
}
