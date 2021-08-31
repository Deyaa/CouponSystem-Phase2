package com.bnhp.CouponSystemPhase2.repos;

import com.bnhp.CouponSystemPhase2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsById(int id);

    boolean existsByEmailAndPassword(String email, String password);

    Company findByEmailAndPassword(String email, String password);
}
