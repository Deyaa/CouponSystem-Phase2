package com.bnhp.CouponSystemPhase2.exceptions;

public class CouponSystemException extends Exception{

    public CouponSystemException(ErrorMsg errors) {
        super(errors.getMsg());
    }
}
