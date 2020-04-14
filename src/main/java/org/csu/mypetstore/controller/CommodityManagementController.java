package org.csu.mypetstore.controller;


import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CommodityManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            model.addAttribute("categoryId",categoryId);
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
            model.addAttribute("productId",productId);
            return "commodityManagement/item";
        }
        else{
            //无item时返回
            return "commodityManagement/product";
        }
    }


    //更新指定category
    @PostMapping("/updateCategory")
    public String updateCategory(Model model)
    {
        Category category = (Category)model.getAttribute("category");
        commodityManagementService.updateCategory(category);
        return "";
    }


    //更新指定product
    @PostMapping("/updateProduct")
    public String updateProduct(Model model)
    {
        Product product = (Product)model.getAttribute("product");
        commodityManagementService.updateProduct(product);
        model.addAttribute("productList",commodityManagementService.getProductListByCategory(product.getCategoryId()));
        return "";
    }


    //更新指定item
    @PostMapping("/updateItem")
    public String updateItem(Model model)
    {
        Item item = (Item)model.getAttribute("item");
        commodityManagementService.updateItem(item);
        model.addAttribute("itemList",commodityManagementService.getItemListByProduct(item.getProductId()));
        return "";
    }


    //删除指定category
    @GetMapping("/deleteCategory")
    public String deleteCategory(String categoryId,Model model)
    {
        commodityManagementService.deleteCategory(categoryId);
        model.addAttribute("categoryList" , commodityManagementService.getAllCategory());
        return "commodityManagement/catagory";
    }


    //删除指定product
    @GetMapping("/deleteProduct")
    public String deleteProduct(String productId,Model model)
    {
        Product product = commodityManagementService.getProduct(productId);
        commodityManagementService.deleteProduct(productId);
        model.addAttribute("productList",commodityManagementService.getProductListByCategory(product.getCategoryId()));
        return "commodityManagement/product";
    }


    //删除指定item
    @GetMapping("/deleteItem")
    public String deleteItem(String itemId,Model model)
    {
        Item item = commodityManagementService.getItem(itemId);
        commodityManagementService.deleteItem(itemId);
        model.addAttribute("itemList",commodityManagementService.getItemListByProduct(item.getProductId()));
        return "commodityManagement/item";
    }


    //插入新的category
    @PostMapping("insertCategory")
    public String insertCategory(Category category,Model model)
    {
        commodityManagementService.insertCategory(category);
        model.addAttribute("categoryList" , commodityManagementService.getAllCategory());
        return "commodityManagement/catagory";
    }


    //插入新的product
    @PostMapping("/insertProduct")
    public String insertProduct(Product product,Model model)
    {
        commodityManagementService.insertProduct(product);
        model.addAttribute("productList", commodityManagementService.getProductListByCategory(product.getCategoryId()));
        return "commodityManagement/product";
    }


    //插入新的item
    @PostMapping("/insertItem")
    public String insertItem(Item item,Model model)
    {
        commodityManagementService.insertItem(item);
        model.addAttribute("itemList", commodityManagementService.getItemListByProduct(item.getProductId()));
        return "commodityManagement/item";
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


    //进入更改指定category页面
    @GetMapping("/enterCategory")
    public String enterCategory(String categoryId,Model model)
    {
        model.addAttribute("category", commodityManagementService.getCategory(categoryId));
        return "";
    }

    //进入更改指定product页面
    @GetMapping("/enterProduct")
    public String enterProduct(String productId,Model model)
    {
        model.addAttribute("product", commodityManagementService.getProduct(productId));
        return "";
    }


    //进入更改指定item页面
    @GetMapping("/enterItem")
    public String enterItem(String itemId,Model model)
    {
        model.addAttribute("item", commodityManagementService.getItem(itemId));
        return "";
    }

    //进入新增category页面
    @GetMapping("/enterNewCategory")
    public String enterNewCategory(Model model)
    {
        return "commodityManagement/newCategory";
    }


    //进入新增product页面
    @GetMapping("/enterNewProduct")
    public String enterNewProduct(String categoryId,Model model)
    {
        model.addAttribute("categoryId",categoryId);
        return "commodityManagement/newProduct";
    }


    //进入新增item页面
    @GetMapping("/enterNewItem")
    public String enterNewItem(String productId ,Model model)
    {
        model.addAttribute("productId",productId);
        return "commodityManagement/newItem";
    }

}
