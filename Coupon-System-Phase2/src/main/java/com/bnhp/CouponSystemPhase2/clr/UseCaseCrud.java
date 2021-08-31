package com.bnhp.CouponSystemPhase2.clr;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CouponRepository;
import com.bnhp.CouponSystemPhase2.repos.CustomerRepository;
import com.bnhp.CouponSystemPhase2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

//@Component
@Order(2)
@RequiredArgsConstructor
public class UseCaseCrud implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Art.BOOTSTRAP);

        Company c1 = Company.builder()
                .email("deyaa1@gmail.com")
                .password("1234")
                .name("apple")
                .build();

        Company c2 = Company.builder()
                .email("deyaa2@gmail.com")
                .password("4569")
                .name("samsung")
                .build();

        System.out.println(Art.COMPANIES);
        System.out.println(Art.INSERT);
        companyRepository.saveAll(Arrays.asList(c1,c2));
        companyRepository.findAll().forEach(System.out::println);

        System.out.println(Art.UPDATE);
        c1.setName("apple updated");
        c1.setPassword("999999999");
        companyRepository.saveAndFlush(c1);
        companyRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_SINGLE);
        System.out.println(companyRepository.getById(2));

        System.out.println(Art.DELETE);
        companyRepository.deleteById(2);
        companyRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_ALL);
        companyRepository.findAll().forEach(System.out::println);

        Customer cust1 = Customer.builder()
                .email("dey@gmail.com")
                .password("123")
                .firstName("deyaaa")
                .lastName("tahaha")
                .build();

        Customer cust2 = Customer.builder()
                .email("dey12121@gmail.com")
                .password("12322")
                .firstName("deyaeeaa")
                .lastName("tahahttta")
                .build();

        System.out.println(Art.CUSTOMERS);
        System.out.println(Art.INSERT);
        customerRepository.saveAll(Arrays.asList(cust1,cust2));
        customerRepository.findAll().forEach(System.out::println);

        System.out.println(Art.UPDATE);
        cust1.setFirstName("11111");
        cust1.setLastName("222222");
        cust1.setPassword("4333333333");
        customerRepository.saveAndFlush(cust1);
        customerRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_SINGLE);
        System.out.println(customerRepository.getById(2));

        System.out.println(Art.DELETE);
        customerRepository.deleteById(2);
        customerRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_ALL);
        customerRepository.findAll().forEach(System.out::println);


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

        System.out.println(Art.COUPONS);
        System.out.println(Art.INSERT);
        couponRepository.saveAll(Arrays.asList(coupon1, coupon2));
        couponRepository.findAll().forEach(System.out::println);


        System.out.println(Art.UPDATE);
        coupon1.setTitle("Free coupon 1+1 - sale");
        coupon1.setDescription("222222");
        coupon1.setAmount(30);
        couponRepository.saveAndFlush(coupon1);
        couponRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_SINGLE);
        System.out.println(couponRepository.getById(2));

        System.out.println(Art.DELETE);
        couponRepository.deleteById(2);
        couponRepository.findAll().forEach(System.out::println);

        System.out.println(Art.GET_ALL);
        couponRepository.findAll().forEach(System.out::println);

    }
}
