package com.bnhp.CouponSystemPhase2.clr;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

//@Component
@Order(3)
@RequiredArgsConstructor
public class UseCaseOneToMany implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Use case OneToMony");

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
                .email("apple@gmail.com")
                .password("1234")
                .name("apple")
                .coupons(Arrays.asList(coupon1, coupon2))
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("1+1222222")
                .description("1+1 copoun22222")
                .categoryId(Category.Food.getValue())
                .company(companyRepository.getById(1))
                .amount(10)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://www.123.com")
                .build();

        Coupon coupon4 = Coupon.builder()
                .title("2+1566565")
                .description("2+1 copoun545454")
                .categoryId(Category.Food.getValue())
                .company(companyRepository.getById(1))
                .amount(100)
                .price(80)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://www.333.com")
                .build();

        Company c2 = Company.builder()
                .email("samsung@gmail.com")
                .password("1234")
                .name("samsung")
                .coupons(Arrays.asList(coupon3, coupon4))
                .build();

        companyRepository.save(c1);
        companyRepository.save(c2);
        companyRepository.findAll().forEach(System.out::println);

        companyRepository.deleteById(2);
        companyRepository.findAll().forEach(System.out::println);
    }
}
