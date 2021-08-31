package com.bnhp.CouponSystemPhase2.clr;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import com.bnhp.CouponSystemPhase2.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

//@Component
@Order(4)
@RequiredArgsConstructor
public class UseCaseManyToMany implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        Coupon coupon1 = Coupon.builder()
                .title("1+1")
                .description("1+1 copoun")
                .categoryId(Category.Food.getValue())
                .company(companyRepository.getById(1))
                .amount(10)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://www.123.com")
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("2+1")
                .description("2+1 copoun")
                .categoryId(Category.Food.getValue())
                .company(companyRepository.getById(1))
                .amount(100)
                .price(80)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://www.333.com")
                .build();


        Company c1 = Company.builder()
                .email("deyaa1@gmail.com")
                .password("1234")
                .name("apple")
                .coupons(Arrays.asList(coupon1, coupon2))
                .build();

        companyRepository.save(c1);
        companyRepository.findAll().forEach(System.out::println);

        Customer cust1 = Customer.builder()
                .email("dey@gmail.com")
                .password("123")
                .firstName("deyaaa")
                .lastName("tahaha")
                .coupon(coupon1)
                .coupon(coupon2)
                .build();

        customerRepository.save(cust1);
        customerRepository.findAll().forEach(System.out::println);

        //delete coupon from coupos table and customers_copouns table
        couponRepository.deleteCustomerCouponByCouponId(1);
        couponRepository.deleteById(1);
    }
}
