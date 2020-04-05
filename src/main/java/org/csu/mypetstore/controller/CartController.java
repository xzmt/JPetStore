package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartDb;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes(value = {"account" ,"cartList","cart","order"})
public class CartController {
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartService cartService;
    @GetMapping("/addItemToCart")
    public String addItemToCart(String workingItemId , Model model)
    {

        if(model.getAttribute("account") == null)
            return "account/signonForm";
        else
        {
            //已经登录，可以加入购物车
            String userId = ((Account) model.getAttribute("account")).getUsername();
            cartService.addCart(userId, workingItemId);

            //查询最新的购物车信息
            List<CartDb> cartList = cartService.getCartList(userId);
            Integer cartListSize = cartList.size();

            //将结果存到session中，cartList存放购物车数据集，cartListSize存放购物车总行数
            model.addAttribute("cartList", cartList);
            model.addAttribute("cartListSize", cartListSize);

            return "cart/cart";
        }
    }

    @GetMapping("/viewCart")
    public String viewCart(Model model)
    {
        if(model.getAttribute("account") == null)
            return "account/signonForm";
        else
        {
            //已经登录，可以查看购物车
            String userId = ((Account)model.getAttribute("account")).getUsername();
            List<CartDb> cartList = cartService.getCartList(userId);
            Integer cartListSize=cartList.size();
            //将结果存到session中，cartList存放购物车数据集，cartListSize存放购物车总行数
            model.addAttribute("cartList",cartList);
            model.addAttribute("cartListSize",cartListSize);

            return "cart/cart";
        }
    }

    @GetMapping("/removeItemFromCart")
    public String removeItemToCart(String workingItemId, Model model)
    {

        String userId = ((Account)model.getAttribute("account")).getUsername();
        //删除购物车中当前行
        cartService.deleteCart(userId,workingItemId);
        //查询最新的购物车信息
        List<CartDb> cartList = cartService.getCartList(userId);
        Integer cartListSize=cartList.size();

        //将结果存到session中，cartList存放购物车数据集，cartListSize存放购物车总行数
        model.addAttribute("cartList",cartList);
        model.addAttribute("cartListSize",cartListSize);

        return "cart/cart";
    }


    @PostMapping("updateCartQuantities")
    public String updateCartQuantities(HttpServletRequest httpServletRequest, Model model)
    {
        String userId = ((Account)model.getAttribute("account")).getUsername();

        //从session值为cartList中获取购物车信息，此时"cartList"中购物车的数量不是最新的，但商品行数没变化

        List<CartDb> cartDbList = (List<CartDb>) model.getAttribute("cartList");
        for (int i=0;i<cartDbList.size();i++)
        {
            int quantity= Integer.parseInt(httpServletRequest.getParameter(cartDbList.get(i).getItemId()));
            cartService.updateCart(userId,cartDbList.get(i).getItemId(),quantity);
        }
        List<CartDb> cartListNew = cartService.getCartList(userId);
        Integer cartListSize=cartListNew.size();

        //将结果存到session中，cartList存放购物车数据集，cartListSize存放购物车总行数

        model.addAttribute("cartList",cartListNew);
        model.addAttribute("cartListSize",cartListSize);

        return "cart/cart";
    }

    @RequestMapping("/ajaxUpdate")
    public void ajaxUpdateServlet(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("成功了呀");
//        CartService cartService=new CartService();
        HttpSession session = request.getSession();
        String userId = ((Account)session.getAttribute("account")).getUsername();

        //从session值为cartList中获取购物车信息，此时"cartList"中购物车的数量不是最新的，但商品行数没变化
        List<CartDb> cartDbList = (List<CartDb>)session.getAttribute("cartList");

        
        for (int i=0;i<cartDbList.size();i++)
        {
            int quantity = Integer.parseInt(request.getParameter("count"));
            cartService.updateCart(userId,cartDbList.get(i).getItemId(),quantity);
        }
        List<CartDb> cartListNew = cartService.getCartList(userId);
        Integer cartListSize=cartListNew.size();

        //将结果存到session中，cartList存放购物车数据集，cartListSize存放购物车总行数

        session.setAttribute("cartList",cartListNew);
        session.setAttribute("cartListSize",cartListSize);
    }
}
