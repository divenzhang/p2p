package com.eloan.uiweb.controller;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IUserService;
import com.eloan.business.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BorrowController extends BaseController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserService userService;

    @RequestMapping("borrow")
    public String borrowIndex(Model model){
        Logininfo current = UserContext.getCurrent();
        if (current ==null) {
            return "redirect:borrow.html";
        }
        model.addAttribute("account", this.accountService.get(current.getId()));
        model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
        model.addAttribute("userinfo", this.userService.get(current.getId()));

        return "borrow";
    }

    @RequestMapping("borrowInfo")
    public String borrowInfo(Model model){
        return "borrow_apply";
    }
}
