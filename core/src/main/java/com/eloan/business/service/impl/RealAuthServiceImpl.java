package com.eloan.business.service.impl;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.RealauthMapper;
import com.eloan.business.query.RealAuthQueryObject;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BitStatesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RealAuthServiceImpl implements IRealAuthService {
    @Autowired
    private RealauthMapper realauthMapper;

    @Autowired
    private IUserService userService;

    @Override
    public Realauth get(Long id) {
        return this.realauthMapper.selectByPrimaryKey(id);
    }

    @Override
    public void apply(Realauth realAuth) {
        realAuth.setApplyTime(new Date());
        realAuth.setApplier(UserContext.getCurrent());
        realAuth.setState(Realauth.STATE_APPLY);
        realauthMapper.insert(realAuth);
        Userinfo current =this.userService.get(UserContext.getCurrent().getId());
        current.setRealauthId(realAuth.getId());
        this.userService.update(current);
    }

    @Override
    public void audit(Long id, String remark, int state) {
        Realauth realauth =this.realauthMapper.selectByPrimaryKey(id);
        if (realauth !=null &&realauth.getState()==Realauth.STATE_APPLY) {
            realauth.setAuditor(UserContext.getCurrent());
            realauth.setApplyTime(new Date());
            realauth.setState(state);
            Userinfo userinfo =this.userService.get(realauth.getApplier().getId());
            if (state == Realauth.STATE_REJECT) {//不通过时设置审核id为空
                userinfo.setRealauthId(null);
            } else if (state==Realauth.STATE_PASS && !userinfo.getRealAuth()) {
                userinfo.setRealName(realauth.getRealname());
                userinfo.setIdNumber(realauth.getIdNumber());
                userinfo.addState(BitStatesUtils.OP_REAL_AUTH);
            }
            this.userService.update(userinfo);
            this.realauthMapper.updateByPrimaryKey(realauth);
        }
    }

    @Override
    public PageResult query(RealAuthQueryObject qo) {
        int count =this.realauthMapper.queryForCount(qo);
        if (count>0) {
            List<Realauth> list =this.realauthMapper.query(qo);
            return new PageResult(count,qo.getPageSize(),qo.getCurrentPage(),list);
        }
        return PageResult.empty(qo.getPageSize());
    }
}
