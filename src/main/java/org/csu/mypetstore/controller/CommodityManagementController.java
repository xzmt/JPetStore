package org.csu.mypetstore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/commodityManagement")
@SessionAttributes(value = {"account" ,"cartList","cart","order","cartListSize"})
public class CommodityManagementController {

    @GetMapping("/index")
    public String index() {
        return "commodityManagement/index";
    }

    @GetMapping("/category")
    public String category()
    {
        return "commodityManagement/catagory";
    }

    @GetMapping("/product")
    public String product()
    {
        return "commodityManagement/product";
    }

    @GetMapping("/item")
    public String item()
    {
        return "commodityManagement/item";
    }
}
