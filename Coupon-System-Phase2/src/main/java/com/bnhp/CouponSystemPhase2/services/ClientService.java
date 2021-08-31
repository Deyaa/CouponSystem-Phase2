package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import com.bnhp.CouponSystemPhase2.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login(String email,String password);
}
