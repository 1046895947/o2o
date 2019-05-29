package club.bagedate.o2o.service;

import club.bagedate.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCatrgoryService {
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
    public ShopCategory selectById(Integer id);
}
