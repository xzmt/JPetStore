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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.List;

@Controller
@RequestMapping("/catalog")
@SessionAttributes(value = {"account" ,"cartList","cart","order"})
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/view")
    public String view()
    {
        return "catalog/main";
    }

    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model)
    {
        if (categoryId != null)
        {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            model.addAttribute("category", category);
            model.addAttribute("productList", productList);
            return "catalog/category";
        }

        return "catalog/main";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(String productId, Model model)
    {
        if (productId != null)
        {
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            Product product = catalogService.getProduct(productId);
            model.addAttribute("itemList", itemList);
            model.addAttribute("product", product);
        }
        return "catalog/product";
    }

    @GetMapping("/viewItem")
    public String viewItem(String itemId, Model model)
    {
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        model.addAttribute("item", item);
        model.addAttribute("product", product);
        return "catalog/Item";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(String keyword, Model model)
    {
        List<Product> productList = catalogService.searchProductList(keyword);
        model.addAttribute("productList", productList);
        return "catalog/SearchProducts";
    }

    @RequestMapping("/ajaxSearch")
    public String ajaxSearch(HttpServletRequest request, HttpServletResponse response)
    {


        response.setContentType("text/html;charset=utf-8");//解决乱码
        String keyword = request.getParameter("term");//默认传过来的参数名成为term，封装了文本框中输入的值
        System.out.println(keyword);
        StringBuffer sb = new StringBuffer("[");
        try
        {
            List<String> names = catalogService.searchAllProductList(keyword);
            for (int i = 0; i < names.size(); i++)
            {
                if (i == names.size() - 1)
                {
                    //注意在拼接的时候，要用双引号，单引号我试过，不起作用好像是,至少key必须是双引号，最好都写成双引号
                    /*
                     * 其中label属性用于显示在autocomplete弹出菜单，而value属性则是选中后给文本框赋的值。如果没有指定其中一个属性
                     * 则用另一个属性替代(即value和label值一样)，如果label和value都没有指定，则无法用于autocomplete的提示。
                     */
                    sb.append("{\"lable\":\"" + names.get(i) + "\",\"value\":\"" + names.get(i) + "\"}]");
                }
                else
                {
                    sb.append("{\"lable\":\"" + names.get(i) + "\",\"value\":\"" + names.get(i) + "\"},");
                }
            }

            System.out.println(names.size());
            response.getWriter().write(sb.toString());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/ajaxWindow")
    public void ajaxWindow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryId = request.getParameter("description");
//        CatalogService service = new CatalogService();
//        Category category = service.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);

        System.out.println("成功了也");

        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=utf-8");//解决乱码
        PrintWriter sb =response.getWriter();
        try {
            for(int i=0;i<productList.size();i++){
                if(i==productList.size()-1){

                    sb.println("<b>productId: " + productList.get(i).getProductId() +" description: " + productList.get(i).getDescription() + " </b>" + "<br>");
                }else{
                    sb.println("<b>productId: " + productList.get(i).getProductId() +" description: " + productList.get(i).getDescription() + " </b>" + "<br>");
                }
            }

            sb.flush();
            sb.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
