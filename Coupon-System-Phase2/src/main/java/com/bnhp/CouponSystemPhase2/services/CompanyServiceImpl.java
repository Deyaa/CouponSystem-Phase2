package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.exceptions.ErrorMsg;
import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Getter
    @Setter
    private int companyId;

    @Override
    public boolean login(String email, String password) {
        if(companyRepository.existsByEmailAndPassword(email, password)){
            setCompanyId(companyRepository.findByEmailAndPassword(email, password).getId());
            return true;
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if(couponRepository.existsByCompanyIdAndTitle(getCompanyId(), coupon.getTitle())){
            throw new CouponSystemException(ErrorMsg.COUPON_TILE_EXISTS_FOR_COMPANY);
        }
        couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        if(!companyRepository.existsById(getCompanyId())){
            throw new CouponSystemException(ErrorMsg.UPDATE_COMPANY_ID_NOT_EXISTS);
        }
        if(!couponRepository.existsById(coupon.getId())){
            throw new CouponSystemException(ErrorMsg.COUPON_ID_NOT_EXISTS);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) {
        couponRepository.deleteCustomerCouponByCouponId(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompanyId(getCompanyId());
    }

    @Override
    public List<Coupon> getCompanyCoupons(int categoryId) {
        return couponRepository.findByCompanyIdAndCategoryId(getCompanyId(), categoryId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepository.findByCompanyIdAndPriceLessThan(getCompanyId(), maxPrice);
    }

    @Override
    public Company getCompanyDetails(int couponId) {
        Coupon coupon = couponRepository.getById(couponId);
        return companyRepository.getById(coupon.getCompany().getId());
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.getAllCoupon();
    }

    @Override
    public Coupon getCouponDetails(int couponId) {
        return couponRepository.getById(couponId);
    }

}
