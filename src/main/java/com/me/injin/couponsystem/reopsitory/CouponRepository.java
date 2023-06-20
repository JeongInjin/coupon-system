package com.me.injin.couponsystem.reopsitory;

import com.me.injin.couponsystem.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
