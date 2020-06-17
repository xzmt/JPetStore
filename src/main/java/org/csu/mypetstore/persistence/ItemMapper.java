package org.csu.mypetstore.persistence;

import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemMapper
{
    void updateInventoryQuantity(Map<String, Object> param);

    int getInventoryQuantity(String itemId);

    List<Item> getItemListByProduct(String productId);

    Item getItem(String itemId);

    int getStockNumByItemId(String itemId);

    void updateStockNum(@Param("itemId") String itemId, @Param("stocknum") int stockNum);

    List<Item> getAllItem();

    void insertItem(Item item);

    void updateItem(Item item);

    void deleteItem(String itemId);

    void insertInventory(Item item);

    List<Item> searchItem(String keywords);
}
