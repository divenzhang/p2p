package com.eloan.uiweb.controller;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;
import com.eloan.base.service.IpLogService;
import com.eloan.base.util.UserContext;
import com.eloan.uiweb.interceptor.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IpLogController extends BaseController {

    @Autowired
    private IpLogService ipLogService;
    @RequestMapping("iplog")
    @RequiredLogin
    public String ipLog(@ModelAttribute("qo")IpLogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        qo.setLike(false);
        qo.setUserType(Logininfo.USERTYPE_NORMAL);
        PageResult result = this.ipLogService.query(qo);
        model.addAttribute("pageResult",result);
        return "iplog_list";
    }
}
