package club.bagedate.o2o.service;

import club.bagedate.o2o.dto.ShopExecution;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.entity.ShopExample;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ShopService {
    /**
     * 根据shopExample实现分页查询（给Example添加属性）
     * 为了前台方便调用，属性赋值放到内部实现
     * @param shopExample
     * @param pageIndex 页码
     * @param pageSize 每页条数
     * @return 对Shop封装的dto对象(包含总条数和分页信息)
     */
    public ShopExecution getShopList(ShopExample shopExample,Integer pageIndex,Integer pageSize);
    /**
     * 添加一条店铺信息
     * @param shop 店铺信息
     * @param shopImg 店铺图片的输入流
     * @return
     */
    public ShopExecution insertShop(Shop shop, CommonsMultipartFile shopImg);

    /**
     * 该方法应该是没有用到，不过根据修改封闭原则没有删除
     * 根据主键更新shop信息
     * @param shop shop信息
     * @return 影响条数
     */
    public int updateShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop 店铺信息
     * @param shopImg 图片的输入流
     * @return 对Shop封装的dto对象
     */
    public ShopExecution modifyShop(Shop shop,CommonsMultipartFile shopImg);

    /**
     * 未实现
     * @param id
     * @return
     */
    public int deleteShop(Integer id);

    /**
     * 根据id查询店铺信息
     * @param id
     * @return 店铺信息
     */
    public Shop selectById(Integer id);
}
