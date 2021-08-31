package com.bnhp.CouponSystemPhase2.exceptions;

public enum ErrorMsg {

    COMPANY_NAME_EXISTS("Can't add company with name already exists"),
    COMPANY_EMAIL_EXISTS("Can't add company with email already exists"),
    COMPANY_ID_NOT_EXISTS("Can't update company, id does not exist"),
    UPDATE_COMPANY_NAME_EXISTS("Can't update company, name already exist"),
    CUSTOMER_EMAIL_EXISTS("Can't add customer, email already exists"),
    CUSTOMER_ID_NOT_EXISTS("Can't update customer, id does not exists"),
    COUPON_TILE_EXISTS_FOR_COMPANY("Can't add coupon, coupon title already exists on another company"),
    UPDATE_COMPANY_ID_NOT_EXISTS("Can't update coupon, company id does not exists"),
    COUPON_ID_NOT_EXISTS("Can't update coupon, coupon id does not exist"),
    COUPON_AMOUNT_ZERO("Can't purchase coupon has zero amount"),
    COUPON_EXPIRED("Can't purchase expired coupon"),
    COUPON_DOUBLE_PURCHASE("Can't purchase coupon already purchased by another customer"),
    CUSTOMER_ID_NOT_FOUND("Customer id not found");

    private String msg;
    ErrorMsg(String msg){
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }
}
