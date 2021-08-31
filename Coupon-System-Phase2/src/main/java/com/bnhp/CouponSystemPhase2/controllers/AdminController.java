package com.bnhp.CouponSystemPhase2.controllers;

import com.bnhp.CouponSystemPhase2.beans.Company;
import com.bnhp.CouponSystemPhase2.beans.Customer;
import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.security.ClientType;
import com.bnhp.CouponSystemPhase2.security.LoginManager;
import com.bnhp.CouponSystemPhase2.services.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Admin API", description = "Admin API")
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LoginManager loginManager;
    private AdminService adminService;

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password)
    {
        adminService = (AdminService) loginManager.login(email, password, ClientType.Administrator);
        return new ResponseEntity<>("LogIn success!", HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getCompany(@PathVariable("id") int companyId) throws CouponSystemException {
        return new ResponseEntity<> (adminService.getOneCompany(companyId), HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<?> getAllCompanies() {
        List<Company> companies = adminService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @ApiOperation(value = "create a new company!", response = Iterable.class, tags = "BugFixes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @PostMapping("/company")
    public ResponseEntity<?> addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @PutMapping("/company")
    public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int companyId) {
        adminService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/customer")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customerToAdd) throws CouponSystemException {
        adminService.addCustomer(customerToAdd);
        return new ResponseEntity<>(customerToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/customer")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int customerId) {
        adminService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getOneCustomer(@PathVariable("id") int customerId) throws CouponSystemException {
        return new ResponseEntity<>(adminService.getOneCustomer(customerId), HttpStatus.OK);
    }
}
