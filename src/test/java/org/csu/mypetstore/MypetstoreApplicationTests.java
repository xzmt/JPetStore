package org.csu.mypetstore;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.CommodityManagementService;
import org.csu.mypetstore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.OrderComparator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import java.util.List;

@SpringBootTest
@MapperScan("org.csu.mypetstore.persistence")
class MypetstoreApplicationTests {

    @Autowired
    CommodityManagementService service;

    @Test
    public void testCommodityManagementService()
    {
        Item item = new Item();
        item.setItemId("123");
        item.setProductId("AV-CB-01");
        item.setName("12345");
        item.setStocknum(20);
        item.setSupplierId(1);
        System.out.println(service.getItemListByProduct("FL-DSH-01").get(0).getProductId());;
    }

    @Autowired
    AccountService accountService;
    @Test
    public void testUserManagement()
    {
//        Account account = new Account();
//        account.setUsername("123");
//        account.setPassword("123");
//        account.setBannerName("123");
//        account.setEmail("123");
//        account.setFirstName("132");
//        account.setLastName("1223");
//        account.setAddress1("21");
//        account.setAddress2("213");
//        account.setBannerOption(true);
//        account.setCity("12");
//        account.setCountry("sd");
//        account.setFavouriteCategoryId("CAT");
//        account.setLanguagePreference("ad");
//        account.setListOption(true);
//        account.setPhone("sdf");
//        account.setZip("d");
//        account.setStatus("p");
//        account.setState("sdf");
//        accountService.insertAccount(account);

        accountService.deleteAccount("123");
    }

    @Autowired
    OrderService orderService;
    @Test
    public void testOrder()
    {
        List<Order> orders = orderService.getAllOrder();

        for (Order order : orders)
        {
            System.out.println(order.getOrderId());
        }

        orderService.changeOrderStatus(1005,"p");
    }

    @Autowired
    CatalogService catalogService;
    @Test
    public void testSearch()
    {
        List<Account> accounts= accountService.searchAccount("a");
        for (Account a :
                accounts)
        {
            System.out.println(a.getUsername());
        }

        List<Order> orders = orderService.searchOrder("5");
        for (Order o :
                orders)
        {
            System.out.println(o.getOrderId());
        }

        List<Item> items = catalogService.searchItem("1");
        for (Item e :
                items)
        {
            System.out.println(e.getItemId());
        }
    }
}
