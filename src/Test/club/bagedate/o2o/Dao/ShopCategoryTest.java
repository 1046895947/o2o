package bagedate.o2o.Dao;


import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.mapper.ShopCategoryMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryTest extends BaseTest {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Test
    public void testQueryShopCategory(){
        ShopCategory shopCategory = new ShopCategory();
//        shopCategory.setShopcategoryparentid(1);
        List<ShopCategory> shopCategories = shopCategoryMapper.queryShopCategory(shopCategory);
        System.out.println(shopCategories.size());
    }
}
