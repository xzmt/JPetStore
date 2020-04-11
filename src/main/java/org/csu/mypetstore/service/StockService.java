package org.csu.mypetstore.service;

import org.apache.ibatis.annotations.Param;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StockService {
    @Autowired
    private ItemMapper itemMapper;

    public int getStockNumByItemId(String itemId){
        return itemMapper.getStockNumByItemId(itemId);
    }

    public void updateStockNum(@Param("itemId") String itemId, @Param("stocknum") int stockNum){

        itemMapper.updateStockNum(itemId,stockNum);
    }

    public List<Item> getAllItem(){
        return itemMapper.getAllItem();
    }
}
