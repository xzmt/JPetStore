package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.CategoryMapper;
import org.csu.mypetstore.persistence.ItemMapper;
import org.csu.mypetstore.persistence.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.processor.templateboundaries.ITemplateBoundariesProcessor;

import java.util.List;

@Service
public class CommodityManagementService
{
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ItemMapper itemMapper;

    /*取得所有的category*/
    public List<Category> getAllCategory()
    {
        return categoryMapper.getCategoryList();
    }

    /*取得一个categories*/
    public Category getCategory(String categoryId)
    {
        return categoryMapper.getCategory(categoryId);
    }

    /*插入一个category*/
    public void insertCategory(Category category)
    {
        categoryMapper.insertCategory(category);
    }

    /*更改一个categories*/
    public void updateCategory(Category category)
    {
        categoryMapper.updateCategory(category);
    }

    /*删除一个categories*/
    public void deleteCategory(String categoryId)
    {
        categoryMapper.deleteCategory(categoryId);
    }

    /*好煞笔，不写了*/
    public List<Product> getProductListByCategory(String categoryId)
    {
        return productMapper.getProductListByCategory(categoryId);
    }

    public Product getProduct(String productId)
    {
        return productMapper.getProduct(productId);
    }

    public void insertProduct(Product product)
    {
        productMapper.insertProduct(product);
    }

    public void updateProduct(Product product)
    {
        productMapper.updateProduct(product);
    }

    public void deleteProduct(String productId)
    {
        productMapper.deleteProduct(productId);
    }

    public List<Item> getItemListByProduct(String productId)
    {
        return itemMapper.getItemListByProduct(productId);
    }

    public Item getItem(String itemId)
    {
        return itemMapper.getItem(itemId);
    }

    public void insertItem(Item item)
    {
        itemMapper.insertItem(item);
        itemMapper.insertInventory(item);
    }

    public void updateItem(Item item)
    {
        itemMapper.updateItem(item);
    }

    public void deleteItem(String itemId)
    {
        itemMapper.deleteItem(itemId);
    }

//    public List<Item> searchItem(String keywords)
//    {
//        return itemMapper.searchItem("%"+keywords.toLowerCase()+"%");
//    }
}
