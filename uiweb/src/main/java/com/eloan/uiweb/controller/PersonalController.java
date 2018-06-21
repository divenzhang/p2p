package com.eloan.uiweb.controller;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IEmailActiveService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonalController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IEmailActiveService emailService;
    @RequestMapping("/personal")
     public String personal(Model model){
        Logininfo current = UserContext.getCurrent();
        model.addAttribute("userinfo",userService.get(current.getId()));
        model.addAttribute("account",accountService.get(current.getId()));
        return "personal";
    }
    @RequiredLogin
    @RequestMapping("/bindPhone")
    @ResponseBody
    public ResultJSON bindPhone(String phoneNumber, String verifyCode) {
        ResultJSON json = new ResultJSON();
        try {
            json.setSuccess(this.userService.bindPhone(phoneNumber,
                    verifyCode));
        } catch (Exception e) {

        }
        return json;
    }

    @RequestMapping("/bindEmail")
    @ResponseBody
    public ResultJSON bindEmail(String email){
        ResultJSON json =new ResultJSON();
        try {
            emailService.sendActiveEmail(email);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
        }
        return json;
    }
    @RequestMapping("checkMailActive")
    public String checkMailActive(String code,Model model){
        try {
            this.emailService.bindEmail(code);
            model.addAttribute("success",true);
        } catch (Exception e) {
            model.addAttribute("success",false);
            model.addAttribute("msg",e.getMessage());
        }
        return "checkmail_result";
    }
}
