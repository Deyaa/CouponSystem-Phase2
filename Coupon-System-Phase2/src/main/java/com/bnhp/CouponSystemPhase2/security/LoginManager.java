package com.bnhp.CouponSystemPhase2.security;

import com.bnhp.CouponSystemPhase2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {

    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) {
        switch (clientType){
            case Administrator:
                AdminServiceImpl adminService = ctx.getBean(AdminServiceImpl.class);
                if(adminService.login(email,password)){
                    return (ClientService)adminService;
                }
                break;
            case Company:
                CompanyServiceImpl companyService = ctx.getBean(CompanyServiceImpl.class);
                if(companyService.login(email,password)){
                    return companyService;
                }
                break;
            case Customer:
                CustomerServiceImpl customerService = ctx.getBean(CustomerServiceImpl.class);
                if(customerService.login(email, password)){
                    return customerService;
                }
                break;
            default:
                break;
        }
        return null;
    }

}
