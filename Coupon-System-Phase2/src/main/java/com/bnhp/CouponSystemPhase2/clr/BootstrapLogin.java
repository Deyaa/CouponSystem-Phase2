package com.bnhp.CouponSystemPhase2.clr;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.security.ClientType;
import com.bnhp.CouponSystemPhase2.security.LoginManager;
import com.bnhp.CouponSystemPhase2.services.*;
import com.bnhp.CouponSystemPhase2.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class BootstrapLogin implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        //1. Test Administrator login and logic
        System.out.println(Art.ADMINISTRATOR);
        System.out.println("====FAIL IN VALIDATION::Test Administrator Bad Login with wrong parameters values");
        AdminService adminService1 = (AdminService) loginManager.login("dey@admin.com", "deyaa", ClientType.Administrator);
        System.out.println("====FAIL IN VALIDATION::adminService object returned with null value:");
        System.out.println(adminService1);

        System.out.println("===== Good Administrator Login");
        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

        Company c1 = Company.builder()
                .email("Apple@test.mail")
                .password("pass01")
                .name("Apple")
                .build();

        Company c2 = Company.builder()
                .email("Samsung@test.mail")
                .password("pass02")
                .name("Samsung")
                .build();

        Company c3 = Company.builder()
                .email("TaT@test.mail")
                .password("pass03")
                .name("TaT")
                .build();

        Company c4 = Company.builder()
                .email("XFONE@test.mail")
                .password("pass04")
                .name("XFONE")
                .build();

        Company c5 = Company.builder()
                .email("MOBILTY@test.mail")
                .password("pass05")
                .name("MOBILTY")
                .build();

        Company c6 = Company.builder()
                .email("mobi@test.mail")
                .password("pass06")
                .name("mobi")
                .build();

        System.out.println("===================================");
        System.out.println("====================> ADD COMPANIES");
        System.out.println("===================================");
        adminService.addCompany(c1);
        adminService.addCompany(c2);
        adminService.addCompany(c3);
        adminService.addCompany(c4);
        adminService.addCompany(c5);
        adminService.addCompany(c6);
        adminService.getAllCompanies().forEach(System.out::println);

        Company c7 = Company.builder()
                .email("123@test.mail")
                .password("123")
                .name("MOBILTY")
                .build();

        System.out.println("====FAIL IN VALIDATION::Add company with exist name");
        try {
            adminService.addCompany(c7);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Company c8 = Company.builder()
                .email("mobi@test.mail")
                .password("654")
                .name("dddd")
                .build();

        System.out.println("====FAIL IN VALIDATION::Add company with exist email");
        try {
            adminService.addCompany(c8);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("===================================");
        System.out.println("===================> UPDATE COMPANY");
        System.out.println("===================================");
        Company companyTest = adminService.getOneCompany(1);
        System.out.println("===== The name of the company before change is:");
        System.out.println(adminService.getOneCompany(companyTest.getId()));
        companyTest.setName("Appleeeeee");
        adminService.updateCompany(companyTest);
        System.out.println("===== The name of the company after change is:");
        System.out.println(adminService.getOneCompany(companyTest.getId()));

        System.out.println("====FAIL IN VALIDATION::Update company with id does not exist");
        try{
            companyTest.setId(20);
            adminService.updateCompany(companyTest);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("====FAIL IN VALIDATION::Update company name, name already exist");
        try{
            companyTest.setName("Apple");
            adminService.updateCompany(companyTest);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("===================================");
        System.out.println("===================> DELETE COMPANY");
        System.out.println("===================================");
        System.out.println("==== Deleting company id = 6");
        adminService.deleteCompany(6);
        System.out.println("==== The list of the companies without company id = 6");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("===================================");
        System.out.println("===============> GET SINGLE COMPANY");
        System.out.println("===================================");
        System.out.println(adminService.getOneCompany(1));

        System.out.println("===================================");
        System.out.println("================> GET ALL COMPANIES");
        System.out.println("===================================");
        adminService.getAllCompanies().forEach(System.out::println);

        Customer cust1 = Customer.builder()
                .email("deyaa@mail")
                .password("dey")
                .firstName("Deyaa")
                .lastName("Taha")
                .build();

        Customer cust2 = Customer.builder()
                .email("Moshe@mail")
                .password("Mosh")
                .firstName("Moshe")
                .lastName("Mosh")
                .build();

        Customer cust3 = Customer.builder()
                .email("Roni@mail")
                .password("ron")
                .firstName("Roni")
                .lastName("ron")
                .build();

        Customer cust4 = Customer.builder()
                .email("Avi@mail")
                .password("or")
                .firstName("Avi")
                .lastName("or")
                .build();

        Customer cust5 = Customer.builder()
                .email("David@mail")
                .password("dov")
                .firstName("David")
                .lastName("Dov")
                .build();

        Customer cust6 = Customer.builder()
                .email("Saba@mail")
                .password("sab")
                .firstName("Saba")
                .lastName("Sabaa")
                .build();

        System.out.println("===================================");
        System.out.println("====================> ADD CUSTOMERS");
        System.out.println("===================================");
        adminService.addCustomer(cust1);
        adminService.addCustomer(cust2);
        adminService.addCustomer(cust3);
        adminService.addCustomer(cust4);
        adminService.addCustomer(cust5);
        adminService.addCustomer(cust6);
        adminService.getAllCustomers().forEach(System.out::println);

        Customer cust7 = Customer.builder()
                .email("Saba@mail")
                .password("sab")
                .firstName("Saba")
                .lastName("Sabaa")
                .build();

        System.out.println("====FAIL IN VALIDATION::Add customer with exist email");
        try {
            adminService.addCustomer(cust7);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("===================================");
        System.out.println("==================> UPDATE CUSTOMER");
        System.out.println("===================================");
        Customer customerTest = adminService.getOneCustomer(1);
        System.out.println("===== The first name of the customer before change is:");
        System.out.println(adminService.getOneCustomer(customerTest.getId()));
        customerTest.setFirstName("DeyaaDeyaaTaha");
        adminService.updateCustomer(customerTest);
        System.out.println("===== The first name of the customer after change is:");
        System.out.println(adminService.getOneCustomer(customerTest.getId()));

        System.out.println("====FAIL IN VALIDATION::Update customer with id does not exist ");
        try {
            Customer cust = adminService.getOneCustomer(2);
            cust.setId(20);
            cust.setLastName("DeyaaDe");
            adminService.updateCustomer(cust);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("===================================");
        System.out.println("==================> DELETE CUSTOMER");
        System.out.println("===================================");
        adminService.deleteCustomer(6);
        System.out.println("==== Deleting customer id = 6");
        System.out.println("==== The customers list without deleted customer id = 6");
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("===================================");
        System.out.println("==============> GET SINGLE CUSTOMER");
        System.out.println("===================================");
        System.out.println(adminService.getOneCustomer(1));

        System.out.println("===================================");
        System.out.println("================> GET ALL CUSTOMERS");
        System.out.println("===================================");
        adminService.getAllCustomers().forEach(System.out::println);


        //2. Test Company login and logic
        System.out.println(Art.COMPANY);
        System.out.println("====FAIL IN VALIDATION::Test Company Bad Login with wrong parameters");
        CompanyService companyService1 = (CompanyService) loginManager.login("App@test.mail", "123", ClientType.Company);
        System.out.println("====FAIL IN VALIDATION::companyService object returned with null value:");
        System.out.println(companyService1);

        System.out.println("===== Good Company Login");
        CompanyService companyService = (CompanyService) loginManager.login("Apple@test.mail", "pass01", ClientType.Company);

        Coupon coupon1 = Coupon.builder()
                .title("coupon01 title")
                .description("coupon 01 description")
                .categoryId(Category.Restaurant.getValue())
                .company(adminService.getOneCompany(1))
                .amount(10)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://coupon01.com")
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("coupon02 title")
                .description("coupon02 description")
                .categoryId(Category.Electricity.getValue())
                .company(adminService.getOneCompany(1))
                .amount(15)
                .price(90)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(10)))
                .image("http://coupon02.com")
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("coupon03 title")
                .description("coupon03 description")
                .categoryId(Category.Restaurant.getValue())
                .company(adminService.getOneCompany(1))
                .amount(20)
                .price(60)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://coupon03.com")
                .build();

        Coupon coupon4 = Coupon.builder()
                .title("coupon04 title")
                .description("coupon04 description")
                .categoryId(Category.Vacation.getValue())
                .company(adminService.getOneCompany(1))
                .amount(5)
                .price(70)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .image("http://coupon04.com")
                .build();

        Coupon coupon5 = Coupon.builder()
                .title("coupon05 title")
                .description("coupon05 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(1))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(18)))
                .image("http://coupon05.com")
                .build();

        Coupon coupon6 = Coupon.builder()
                .title("coupon06 title")
                .description("coupon06 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(1))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon06.com")
                .build();

        Coupon coupon7 = Coupon.builder()
                .title("coupon07 title")
                .description("coupon07 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(1))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon07.com")
                .build();

        Coupon coupon8 = Coupon.builder()
                .title("coupon08 title")
                .description("coupon08 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(1))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon08.com")
                .build();

        Coupon coupon9 = Coupon.builder()
                .title("coupon09 title")
                .description("coupon09 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(2))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon09.com")
                .build();

        Coupon coupon10 = Coupon.builder()
                .title("coupon10 title")
                .description("coupon10 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(2))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon10.com")
                .build();

        Coupon coupon11 = Coupon.builder()
                .title("coupon11 title")
                .description("coupon11 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(3))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon11.com")
                .build();

        Coupon coupon12 = Coupon.builder()
                .title("coupon12 title")
                .description("coupon12 description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(3))
                .amount(25)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .image("http://coupon12.com")
                .build();

        Coupon couponZeroAmount = Coupon.builder()
                .title("coupon ZeroAmount title")
                .description("coupon ZeroAmount description")
                .categoryId(Category.Food.getValue())
                .company(adminService.getOneCompany(2))
                .amount(0)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(18)))
                .image("http://couponZeroAmount.com")
                .build();

        System.out.println("==========================================");
        System.out.println("=============================> ADD COUPONS");
        System.out.println("==========================================");
        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        companyService.addCoupon(coupon3);
        companyService.addCoupon(coupon4);
        companyService.addCoupon(coupon5);
        companyService.addCoupon(coupon6);
        companyService.addCoupon(coupon7);
        companyService.addCoupon(coupon8);
        companyService.addCoupon(coupon9);
        companyService.addCoupon(coupon10);
        companyService.addCoupon(coupon11);
        companyService.addCoupon(coupon12);
        companyService.addCoupon(couponZeroAmount);
        System.out.println(companyService.getAllCoupons());

        Coupon coupon = Coupon.builder()
                .title("coupon01 title")
                .description("coupon 01 description")
                .categoryId(Category.Restaurant.getValue())
                .company(adminService.getOneCompany(1))
                .amount(10)
                .price(50)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .image("http://coupon01.com")
                .build();

        System.out.println("========FAIL IN VALIDATION::Add coupon with title exist in company coupons");
        try{
            companyService.addCoupon(coupon);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("==========================================");
        System.out.println("===========================> UPDATE COUPON");
        System.out.println("==========================================");
        System.out.println("The coupon id:1 details before change amount and image address");
        Coupon couponTest = companyService.getCouponDetails(1);
        System.out.println(couponTest);
        couponTest.setAmount(250);
        couponTest.setImage("http://www.coupon01.com");
        companyService.updateCoupon(couponTest);
        System.out.println("The coupon id:1 details after change amount and image address");
        System.out.println(companyService.getCouponDetails(1));

        System.out.println("====FAIL IN VALIDATION::Update coupon with coupon id does not exist");
        try{
            couponTest.setId(20);
            couponTest.setImage("http://www.coupon01.com");
            companyService.updateCoupon(coupon);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Company comp = Company.builder()
                .id(30)
                .email("mobi30@test.mail")
                .password("pass06")
                .name("mobi30")
                .build();

        System.out.println("====FAIL IN VALIDATION::Update coupon with company id does not exist");
        try{
            couponTest.setCompany(comp);
            couponTest.setImage("http://www.coup.com");
            companyService.updateCoupon(coupon);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("==========================================");
        System.out.println("===========================> DELETE COUPON");
        System.out.println("==========================================");
        System.out.println("==== Delete coupon id = 5");
        companyService.deleteCoupon(5);
        System.out.println("==== The coupons list without coupon id = 5");
        System.out.println(companyService.getAllCoupons());

        System.out.println("==========================================");
        System.out.println("=======> GET COMPANY COUPONS BY COMPANY ID");
        System.out.println("==========================================");
        System.out.println("The coupons for company login to system");
        System.out.println(companyService.getCompanyCoupons());

        System.out.println("==========================================");
        System.out.println("======> GET COMPANY COUPONS BY CATEGORY ID");
        System.out.println("==========================================");
        System.out.println(companyService.getCompanyCoupons(Category.Restaurant.ordinal()));

        System.out.println("==========================================");
        System.out.println("========> GET COMPANY COUPONS BY MAX RPICE");
        System.out.println("==========================================");
        System.out.println(companyService.getCompanyCoupons(70.0));

        System.out.println("==========================================");
        System.out.println("==============> GET COMPANY COUPON DETAILS");
        System.out.println("==========================================");
        System.out.println(companyService.getCompanyDetails(coupon1.getId()));

        //3. Test Customer login and logic
        System.out.println(Art.CUSTOMER);
        System.out.println("====FAIL IN VALIDATION::Test Customer Bad Login with wrong parameters");
        CustomerService customerService1 = (CustomerService) loginManager.login("deyaawrong@mail", "dey22", ClientType.Customer);
        System.out.println("====FAIL IN VALIDATION::customerService object returned with null value:");
        System.out.println(customerService1);

        System.out.println("===== Good Customer Login");
        CustomerService customerService = (CustomerService) loginManager.login("deyaa@mail", "dey", ClientType.Customer);
        System.out.println("=============================================================");
        System.out.println("============================================> PURCHASE COUPON");
        System.out.println("=============================================================");
        Coupon coup1 = companyService.getCouponDetails(1);
        customerService.purchaseCoupon(coup1);
        Coupon coup2 = companyService.getCouponDetails(2);
        customerService.purchaseCoupon(coup2);
        Coupon coup3 = companyService.getCouponDetails(3);
        customerService.purchaseCoupon(coup3);
        Coupon coup4 = companyService.getCouponDetails(4);
        customerService.purchaseCoupon(coup4);
        System.out.println(Arrays.asList(coup1, coup2, coup3, coup4));

        System.out.println("====FAIL IN VALIDATION::Purchase the same coupon more than one time");
        try{
            customerService.purchaseCoupon(coup1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("====FAIL IN VALIDATION::Purchase coupon with zero amount");
        try{
            customerService.purchaseCoupon(couponZeroAmount);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("====FAIL IN VALIDATION::Purchase coupon with expired date");
        try{
            customerService.purchaseCoupon(coupon12);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("=============================================================");
        System.out.println("==========================> GET Coupons purchased by customer");
        System.out.println("=============================================================");
        System.out.println(customerService.getCustomerCoupons());

        System.out.println("=============================================================");
        System.out.println("===> GET Coupons purchased by customer from specific category");
        System.out.println("=============================================================");
        System.out.println(customerService.getCustomerCoupons(Category.Restaurant.getValue()));

        System.out.println("=============================================================");
        System.out.println("=============> GET Coupons purchased by customer BY MAX PRICE");
        System.out.println("=============================================================");
        System.out.println(customerService.getCustomerCoupons(100.0));

        System.out.println("=============================================================");
        System.out.println("=======================================> GET CUSTOMER DETAILS");
        System.out.println("=============================================================");
        System.out.println(customerService.getCustomerDetails());

    }
}
