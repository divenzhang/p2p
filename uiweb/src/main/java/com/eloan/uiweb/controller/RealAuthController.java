package com.eloan.uiweb.controller;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserService;
import com.eloan.uiweb.interceptor.RequiredLogin;
import com.eloan.uiweb.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
public class RealAuthController  extends BaseController{
    @Autowired
    private IUserService userService;

    @Autowired
    private IRealAuthService realAuthService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("realAuth")
    @RequiredLogin
    public String realAuth(Model model){
        Userinfo userinfo = userService.get(UserContext.getCurrent().getId());
        if (userinfo.getRealAuth()) {//已经通过实名认证,显示实名认证信息
            model.addAttribute("realAuth",this.realAuthService.get(userinfo.getRealauthId()));
            return "realAuth_result";
        }else if(userinfo.getRealauthId() !=null){//实名认证信息正在审核中
            model.addAttribute("auditing",true);
            return "realAuth_result";
        }else {
            return "realAuth";
        }
    }
    @RequestMapping("/realAuthUpload")
    @ResponseBody
    public String realAuthUpload(MultipartFile file) {
        String filePath = servletContext.getRealPath("/upload");
        String fileName = UploadUtil.upload(file, filePath);
        return "/upload/" + fileName;
    }
    @RequestMapping("realAuth_save")
    public String realAuthApply(Realauth realAuth) {
        this.realAuthService.apply(realAuth);
        return "redirect:realAuth.do";
    }

}
