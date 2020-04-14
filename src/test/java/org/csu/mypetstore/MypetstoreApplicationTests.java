package org.csu.mypetstore;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.CommodityManagementService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
}
