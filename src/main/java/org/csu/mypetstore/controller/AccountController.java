package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import java.util.ArrayList;
import java.util.List;
//hh
@Controller
@RequestMapping("/account")
@SessionAttributes(value = {"account" ,"cartList"})
public class AccountController {
    private static final List<String> LANGUAGE_LIST;
    private static final List<String> CATEGORY_LIST;
    static {
        List<String> langList = new ArrayList<String>();
        langList.add("english");
        langList.add("japanese");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");
        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/newAccountForm")
    public String newAccountForm() {
        return "account/newAccount";
    }

    @GetMapping("/viewSignon")
    public String viewSignon(Model model)
    {
        model.addAttribute("languages",LANGUAGE_LIST);
        model.addAttribute("categories",CATEGORY_LIST);

        return "account/signonForm";
    }

    @GetMapping("/viewEditAccountForm")
    public String viewEditAccountForm(Model model)
    {
        model.addAttribute("languages",LANGUAGE_LIST);
        model.addAttribute("categories",CATEGORY_LIST);
        return "account/newAccount";
    }

    @PostMapping("/signon")
    public String signon(Account account1 , Model model)
    {
        //verificationCode = request.getParameter("verificationCode");

        Account account = accountService.getAccount(account1.getUsername(),account1.getPassword());

//        System.out.println(verificationCode);
//        if(verificationCode != session.getAttribute("verificationCode"))
//        {
//            session.setAttribute("errormsg", "验证码不正确");
//            request.getRequestDispatcher(SIGNON).forward(request,response);
//        }
        if(account != null)
        {
            model.addAttribute("account",account);
            return "catalog/main";
        }
        else if(account == null)
        {
            model.addAttribute("errormsg", "用户名或密码不正确");
            return "account/signonForm";
        }
        return null;
    }

    @GetMapping("signout")
    public String signout(Model model)
    {
        model.addAttribute("account" , null);
        return "catalog/main";
    }

    @PostMapping("/newAccount")
    public String newAccount(Account account , Model model)
    {

//        account.setListOption(request.getParameter("account.listOption")=="true");
//        account.setBannerOption(request.getParameter("account.bannerOption")=="true");

        accountService.insertAccount(account);
        return "catalog/main";
    }

    @PostMapping("/editAccountForm")
    public String editAccouintForm(Account account ,Model model)
    {
        accountService.updateAccount(account);
        return "catalog/main";
    }
//    public Resolution newAccount() {
//        accountService.insertAccount(account);
//        account = accountService.getAccount(account.getUsername());
//        myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
//        authenticated = true;
//        return new RedirectResolution(CatalogActionBean.class);
//    }
//
//    public Resolution editAccountForm() {
//        return new ForwardResolution(EDIT_ACCOUNT);
//    }
//
//    public Resolution editAccount() {
//        accountService.updateAccount(account);
//        account = accountService.getAccount(account.getUsername());
//        myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
//        return new RedirectResolution(CatalogActionBean.class);
//    }
//
//    @DefaultHandler
//    public Resolution signonForm() {
//        return new ForwardResolution(SIGNON);
//    }
//
//    public Resolution signon() {
//
//        account = accountService.getAccount(getUsername(), getPassword());
//
//        if (account == null) {
//            String value = "Invalid username or password.  Signon failed.";
//            setMessage(value);
//            clear();
//            return new ForwardResolution(SIGNON);
//        } else {
//            account.setPassword(null);
//            myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
//            authenticated = true;
//            HttpSession s = context.getRequest().getSession();
//            // this bean is already registered as /actions/Account.action
//            s.setAttribute("accountBean", this);
//            return new RedirectResolution(CatalogActionBean.class);
//        }
//    }
//
//    public Resolution signoff() {
//        context.getRequest().getSession().invalidate();
//        clear();
//        return new RedirectResolution(CatalogActionBean.class);
//    }
//
//    public boolean isAuthenticated() {
//        return authenticated && account != null && account.getUsername() != null;
//    }

}
