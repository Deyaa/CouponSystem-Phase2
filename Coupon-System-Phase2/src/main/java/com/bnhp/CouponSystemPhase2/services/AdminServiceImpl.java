package com.bnhp.CouponSystemPhase2.services;

import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Coupon;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.exceptions.ErrorMsg;
import com.bnhp.CouponSystemPhase2.repos.CompanyRepository;
import com.bnhp.CouponSystemPhase2.repos.CustomerRepository;
import com.bnhp.CouponSystemPhase2.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service responsible for managing companies and customers
 */
@Service
public class AdminServiceImpl extends ClientService implements AdminService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param email - admin login email
     * @param password - admin login password
     * @return - if successfully login in or not
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     *
     * @param company - add new company
     *                Should validate input object
     * @throws CouponSystemException
     */
    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if(companyRepository.existsByName(company.getName())){
            throw new CouponSystemException(ErrorMsg.COMPANY_NAME_EXISTS);
        }
        if(companyRepository.existsByEmail(company.getEmail())){
            throw new CouponSystemException(ErrorMsg.COMPANY_EMAIL_EXISTS);
        }
        companyRepository.save(company);
    }

    /**
     *
     * @param company - updating an exist of company
     * @throws CouponSystemException
     */
    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        if(!companyRepository.existsById(company.getId())){
            throw new CouponSystemException(ErrorMsg.COMPANY_ID_NOT_EXISTS);
        }
        if(companyRepository.existsByName(company.getName())){
            throw new CouponSystemException(ErrorMsg.UPDATE_COMPANY_NAME_EXISTS);
        }
        companyRepository.saveAndFlush(company);
    }

    /**
     *
     * @param companyId - delete company by id
     *                  in addition all purchased coupon belongs to company
     *                  will be deleted
     */
    @Override
    public void deleteCompany(int companyId) {
        //Delete purchased coupons belongs to company
        List<Coupon> companyCoupons = couponRepository.findByCompanyId(companyId);
        for (Coupon c: companyCoupons) {
            couponRepository.deleteCustomerCouponByCouponId(c.getId());
        }
        //Delete company and coupons
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(ExceptionUtils::companyIdNotFound);
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new CouponSystemException(ErrorMsg.CUSTOMER_EMAIL_EXISTS);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CouponSystemException {
        Customer customerFromDB = customerRepository.getById(customer.getId());
        if(customerFromDB == null){
            throw new CouponSystemException(ErrorMsg.CUSTOMER_ID_NOT_EXISTS);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        //Delete the customer will delete also his purchased coupons
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(ExceptionUtils::customerNotFound);
    }
}
