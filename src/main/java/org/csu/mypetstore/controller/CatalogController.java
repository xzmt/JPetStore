package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@SessionAttributes(value = {"account" ,"cartList"})
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/view")
    public String view()
    {
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId , Model model)
    {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            model.addAttribute("category" ,category);
            model.addAttribute("productList",productList);
            return "catalog/category";
        }

        return "catalog/main";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(String productId , Model model) {
        if (productId != null) {
            List<Item>itemList = catalogService.getItemListByProduct(productId);
            Product product = catalogService.getProduct(productId);
            model.addAttribute("itemList" ,itemList);
            model.addAttribute("product",product);
        }
        return "catalog/product";
    }

    @GetMapping("/viewItem")
    public String viewItem(String itemId , Model model) {
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        model.addAttribute("item" ,item);
        model.addAttribute("product",product);
        return "catalog/Item";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(String keyword,Model model){
        List<Product> productList = catalogService.searchProductList(keyword);
        model.addAttribute("productList",productList);
        return "catalog/SearchProducts";
    }
}
