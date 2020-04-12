package org.csu.mypetstore.controller;


import org.csu.mypetstore.service.CommodityManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/commodityManagement")
@SessionAttributes(value = {"account" ,"cartList","cart","order","cartListSize"})
public class CommodityManagementController {

    @Autowired
    private CommodityManagementService commodityManagementService;

    @GetMapping("/index")
    public String index() {
        return "commodityManagement/index";
    }

    @GetMapping("/category")
    public String category(Model model)
    {
        model.addAttribute("category" , commodityManagementService.getAllCategory());
        return "commodityManagement/catagory";
    }

}
