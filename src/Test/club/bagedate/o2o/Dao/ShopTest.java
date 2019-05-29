package bagedate.o2o.Dao;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.entity.ShopExample;
import club.bagedate.o2o.mapper.ShopMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopTest extends BaseTest {
    @Autowired
    public ShopMapper shopMapper;
    @Test
    public void testSelectByExample(){
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andUseridEqualTo(1001);
        shopExample.setLeftLimit(0);
        shopExample.setLimitSize(2);
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        for (int i = 0; i <shopList.size() ; i++) {
            System.out.println(shopList.get(i).toString());
        }
        System.out.println(shopMapper.countByExample(shopExample));
    }
}
