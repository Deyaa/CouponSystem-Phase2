package com.bnhp.CouponSystemPhase2.repos;

import com.bnhp.CouponSystemPhase2.beans.Category;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsByCompanyIdAndTitle(int companyId, String title);

    boolean existsById(int id);

    List<Coupon> findByCompanyId(int companyId);

    List<Coupon> findByCompanyIdAndCategoryId(int companyId, int categoryId);

    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);

    List<Coupon> findByCompanyIdAndEndDateBefore(int companyId, LocalDate date);

    List<Coupon> findByEndDateBefore(LocalDate date);

    //Used in CompanyService
    @Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id")
    List<Coupon> findCompanyCoupon(int id);

    @Query("SELECT c from Company as company join company.coupons As c WHERE company.id=:id AND c.price<=:price")
    List<Coupon> findCompanyCouponByPrice(int id, double price);

    //Used in CustomerService
    @Query("select c from Customer as customer join customer.coupons As c where c.id=:couponId AND customer.id=:customerId")
    List<Coupon> purchasedCouponByCustomer(int couponId, int customerId);

    @Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:customerId")
    List<Coupon> findCustomerCoupons(int customerId);

    @Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:id AND c.price<=:price")
    List<Coupon> findCustomerCouponsLessThan(int id, double price);

    @Query("SELECT c from Customer as customer join customer.coupons As c WHERE customer.id=:customerId AND c.categoryId=:categoryId")
    List<Coupon> findCustomerCouponsByCategory(int customerId, int categoryId);

    //Used in Job Removal Expired Coupons
    @Query("SELECT c from Coupon As c WHERE c.endDate <= CURRENT_DATE")
    List<Coupon> findExpiredCoupons();

    @Query("SELECT c from Coupon As c")
    List<Coupon> getAllCoupon();

    @Transactional
    @Modifying
    @Query(value = "delete from customers_coupons where coupons_id = :couponId", nativeQuery = true)
    void deleteCustomerCouponByCouponId(@Param("couponId") int couponId);
}
