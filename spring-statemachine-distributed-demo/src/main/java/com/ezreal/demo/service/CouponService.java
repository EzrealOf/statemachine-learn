package com.ezreal.demo.service;

import com.ezreal.demo.dao.CouponDao;
import com.ezreal.demo.entity.CouponDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    public CouponDao couponDao;

    public CouponDO findOneById(Long id ){
        return couponDao.selectById(id);
    }
}
