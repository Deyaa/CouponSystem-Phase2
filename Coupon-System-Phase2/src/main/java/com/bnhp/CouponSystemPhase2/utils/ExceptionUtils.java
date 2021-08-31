package com.bnhp.CouponSystemPhase2.utils;

import com.bnhp.CouponSystemPhase2.exceptions.CouponSystemException;
import com.bnhp.CouponSystemPhase2.exceptions.ErrorMsg;

public class ExceptionUtils {

    public static CouponSystemException customerNotFound(){
        return new CouponSystemException(ErrorMsg.CUSTOMER_ID_NOT_FOUND);
    }

    public static CouponSystemException companyIdNotFound(){
        return new CouponSystemException(ErrorMsg.COMPANY_ID_NOT_EXISTS);
    }
}
