package com.eloan.base.service.impl;

import com.eloan.base.domain.IpLog;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.mapper.LogininfoMapper;
import com.eloan.base.service.ILogininfoService;
import com.eloan.base.util.MD5;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
    private LogininfoMapper logininfoMapper;

	@Autowired
    private IpLogMapper ipLogMapper;

	@Autowired
    private AccountMapper accountMapper;

	@Autowired
    private UserinfoMapper userinfoMapper;


	@Override
	public void register(String username, String password) {
		int count = this.logininfoMapper.getCountByUsername(username,Logininfo.USERTYPE_NORMAL);
		if (count <= 0) {
			Logininfo logininfo = new Logininfo();
			logininfo.setUsername(username);
			logininfo.setPassword(MD5.encode(password));
			logininfo.setState(Logininfo.STATE_NORMAL);
			logininfo.setUsertype(Logininfo.USERTYPE_NORMAL);
			this.logininfoMapper.insert(logininfo);
//
			//初始化一个account
			Account account = Account.empty(logininfo.getId());
			accountMapper.insert(account);
//
//			//初始化一个Userinfo
			Userinfo userinfo = Userinfo.empty(logininfo.getId());
			userinfoMapper.insert(userinfo);
		} else {
			throw new RuntimeException("用户名已经存在!");
		}
	}

	@Override
	public boolean checkUsername(String name,int userType) {
		return this.logininfoMapper.getCountByUsername(name,userType) <= 0;
	}

	@Override
	public Logininfo login(String name, String password,int userType,String ip) {
        IpLog log =new IpLog(name,new Date(),ip,userType,null);
        Logininfo current = this.logininfoMapper.login(name,MD5.encode(password),userType);
        if (current !=null) {
            UserContext.putLogininfo(current);
            log.setLoginInfoId(current.getId());
            log.setLoginState(IpLog.LOGINSTATE_SUCCESS);
        }
        ipLogMapper.insert(log);
        return current;
	}

    @Override
    public boolean hasAdmin() {
        return this.logininfoMapper.getCountByUsername(BidConst.DEFAULT_ADMIN_NAME,Logininfo.USERTYPE_SYSTEM) > 0;
    }

    @Override
    public void createDefaultAdmin() {
        Logininfo admin =new Logininfo();
        admin.setUsername(BidConst.DEFAULT_ADMIN_NAME);
        admin.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
        admin.setUsertype(Logininfo.USERTYPE_SYSTEM);
        admin.setAdmin(true);
        this.logininfoMapper.insert(admin);
    }

    @Override
    public List<Map<String, Object>> autoComplate(String word, int userType) {
        return this.logininfoMapper.autoComplate(word,userType);
    }
}
