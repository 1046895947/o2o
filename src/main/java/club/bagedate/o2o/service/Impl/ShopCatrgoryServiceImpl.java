package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.mapper.ShopCategoryMapper;
import club.bagedate.o2o.service.ShopCatrgoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCatrgoryServiceImpl implements ShopCatrgoryService {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryMapper.queryShopCategory(shopCategory);
    }

    @Override
    public ShopCategory selectById(Integer id) {
        return shopCategoryMapper.selectByPrimaryKey(id);
    }
}
