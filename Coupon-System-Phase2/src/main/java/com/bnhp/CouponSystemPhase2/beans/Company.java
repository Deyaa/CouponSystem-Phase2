package com.bnhp.CouponSystemPhase2.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    //Each Company will have zero or more Coupons:
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @ToString.Exclude
    @Singular
    private List<Coupon> coupons = new ArrayList<>();
}
