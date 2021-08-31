package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyId);
    List<Company> getAllCompanies();
    Company getOneCompany(int companyId) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId);
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerId) throws CouponSystemException;
}
