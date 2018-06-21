package com.eloan.business.service;

import com.eloan.business.domain.Userinfo;

public interface IUserService {
    void update(Userinfo userinfo);

    Userinfo get(Long id);

    boolean bindPhone(String phoneNumber,String verifyCode);

    void updateBasicInfo(Userinfo userinfo);
}
