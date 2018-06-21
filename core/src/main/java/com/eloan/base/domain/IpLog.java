package com.eloan.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@Alias("IpLog")
public class IpLog extends BaseDomain {
    public static int LOGINSTATE_FAILD = 0;//登陆失败
    public static int LOGINSTATE_SUCCESS = 1;//登陆成功

    private String username;
    private Date loginTime;
    private String ip;

    private int loginState;
    private int loginType;
    private Long loginInfoId;

    public String getDisplayState(){
        return this.loginState==LOGINSTATE_FAILD?"登录失败":"登录成功" ;
    }

    //mybatis先创建对象再使用,所以需要这个方法
    public IpLog(){ super(); }

    public IpLog(String username,Date loginTime,String ip,int loginType, Long loginInfoId){
        super();
        this.username = username;
        this.loginTime = loginTime;
        this.ip = ip;
        this.loginState = IpLog.LOGINSTATE_FAILD;
        this.loginType = loginType;
        this.loginInfoId = loginInfoId;
    }
}
