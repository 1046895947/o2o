package bagedate.o2o.Service;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.service.ShopCatrgoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCatrgoryService shopCatrgoryService;
    @Test
    public void testGetShopCategoryList(){
        System.out.println(shopCatrgoryService.getShopCategoryList(new ShopCategory()));

    }
}
