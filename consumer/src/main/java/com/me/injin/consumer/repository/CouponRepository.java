package com.me.injin.consumer.repository;

import com.me.injin.consumer.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
