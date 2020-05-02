package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/accountManagement")
@SessionAttributes(value = {"account" ,"cartList","cart","order","cartListSize"})
public class AccountManagementController {

    @Autowired
    private AccountService accountService;

    //返回所有Account列表
    @GetMapping("/getAllAccount")
    public String getAllAccount(Model model)
    {
        model.addAttribute("accountList" , accountService.getAllAccount());
        return "accountManagerment/index";
    }

    //进入指定Account信息显示页面
    @GetMapping("/enterAccount")
    public String enterAccount(String username,Model model)
    {
        model.addAttribute("account", accountService.getAccount(username));
        return "accountManagerment/changeAccount";
    }

    //更新指定Account
    @PostMapping("/updateAccount")
    public String updateAccount(Account account, Model model)
    {
        accountService.updateAccount(account);
        model.addAttribute("accountList" , accountService.getAllAccount());
        return "accountManagerment/index";
    }

    //返回搜索结果
    @GetMapping("/getSearchAccount")
    public String getSearchAccount(String keywords,Model model)
    {
        model.addAttribute("accountList" , accountService.searchAccount(keywords));
        return " ";
    }
}
