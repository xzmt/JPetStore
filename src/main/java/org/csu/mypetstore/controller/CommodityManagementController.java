package org.csu.mypetstore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/commodityManagement")
@SessionAttributes(value = {"account" ,"cartList","cart","order","cartListSize"})
public class CommodityManagementController {

    @GetMapping("/newAccountForm")
    public String newAccountForm() {
        return "account/newAccount";
    }

}
