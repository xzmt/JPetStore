package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
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

    //查看category列表
    @GetMapping("/category")
    public String category(Model model)
    {
        model.addAttribute("categoryList" , commodityManagementService.getAllCategory());
        return "commodityManagement/catagory";
    }


    //查看product列表
    @GetMapping("/product")
    public String product(String categoryId,Model model)
    {
        if (categoryId != null) {
            model.addAttribute("productList", commodityManagementService.getProductListByCategory(categoryId));
            return "commodityManagement/product";
        }
        else{
            //无product时返回
            return "commodityManagement/category";
        }
    }


    //查看item列表
    @GetMapping("/item")
    public String item(String productId ,Model model)
    {
        if (productId != null) {
            model.addAttribute("itemList", commodityManagementService.getItemListByProduct(productId));
            return "commodityManagement/item";
        }
        else{
            //无item时返回
            return "commodityManagement/product";
        }
    }


    //更新指定category
    @GetMapping("/updateCategory")
    public String updateCategory(Model model)
    {
        Category category = (Category)model.getAttribute("category");
        commodityManagementService.updateCategory(category);
        return "";
    }


    //更新指定product
    @GetMapping("/updateProduct")
    public String updateProduct(Model model)
    {
        Product product = (Product)model.getAttribute("product");
        commodityManagementService.updateProduct(product);
        return "";
    }


    //更新指定item
    @GetMapping("/updateItem")
    public String updateItem(Model model)
    {
        Item item = (Item)model.getAttribute("item");
        commodityManagementService.updateItem(item);
        return "";
    }


    //删除指定category
    @GetMapping("/deleteCategory")
    public String deleteCategory(String categoryId,Model model)
    {
        commodityManagementService.deleteCategory(categoryId);
        return "";
    }


    //删除指定product
    @GetMapping("/deleteProduct")
    public String deleteProduct(String productId,Model model)
    {
        commodityManagementService.deleteProduct(productId);
        return "";
    }


    //删除指定item
    @GetMapping("/deleteItem")
    public String deleteItem(String itemId,Model model)
    {
        commodityManagementService.deleteItem(itemId);
        return "";
    }


    //插入新的category
    @GetMapping("insertCategory")
    public String insertCategory(Model model)
    {
        Category category = (Category)model.getAttribute("category");
        commodityManagementService.insertCategory(category);
        return "";
    }


    //插入新的product
    @GetMapping("/insertProduct")
    public String insertProduct(Model model)
    {
        Product product = (Product)model.getAttribute("product");
        commodityManagementService.insertProduct(product);
        return "";
    }


    //插入新的item
    @GetMapping("/insertItem")
    public String insertItem(Model model)
    {
        Item item = (Item)model.getAttribute("item");
        commodityManagementService.insertItem(item);
        return "";
    }

    //上架新的item
    @GetMapping("/sellItem")
    public String sellItem(String itemId,Model model)
    {
        Item item = commodityManagementService.getItem(itemId);
        //修改上架状态
        item.setStatus(" ");
        commodityManagementService.updateItem(item);
        return "";
    }

}
