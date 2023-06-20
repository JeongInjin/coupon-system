package com.me.injin.couponsystem.service;

import com.me.injin.couponsystem.producer.CouponCreateProducer;
import com.me.injin.couponsystem.reopsitory.CouponCountRepository;
import com.me.injin.couponsystem.reopsitory.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
