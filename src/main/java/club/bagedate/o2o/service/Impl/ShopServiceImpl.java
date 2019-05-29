package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.dto.ShopExecution;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.entity.ShopExample;
import club.bagedate.o2o.enums.ShopStateEnum;
import club.bagedate.o2o.exceptions.ShopOperationException;
import club.bagedate.o2o.mapper.ShopMapper;
import club.bagedate.o2o.service.ShopService;
import club.bagedate.o2o.util.ImageUtil;
import club.bagedate.o2o.util.PageCalculator;
import club.bagedate.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 根据shopExample实现分页查询（给Example添加属性）
     * 为了前台方便调用，属性赋值放到内部实现
     * @param shopExample
     * @param pageIndex 页码
     * @param pageSize 每页条数
     * @return 对Shop封装的dto对象(包含总条数和分页信息)
     */
    @Override
    public ShopExecution getShopList(ShopExample shopExample, Integer pageIndex, Integer pageSize) {
        //设置分页查询开始的位置
        shopExample.setLeftLimit(PageCalculator.calculateRowIndex(pageIndex,pageSize));
        //设置每页条数
        shopExample.setLimitSize(pageSize);
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        long count = shopMapper.countByExample(shopExample);
        ShopExecution shopExecution = new ShopExecution();
        if(shopList!=null){
            shopExecution.setShopList(shopList);
            shopExecution.setCount(count);
        }else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }

    /**
     * 添加一条店铺信息
     * @param shop 店铺信息
     * @param shopImg 店铺图片的输入流
     * @return
     */
    @Override
    @Transactional
    public ShopExecution insertShop(Shop shop, CommonsMultipartFile shopImg) {
        //空值判断，可以再加一些判断
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //给店铺信息赋初始值
            shop.setShopstatus(0);
            shop.setShopcreatetime(new Date());
            shop.setShopupdatetime(new Date());
            //添加店铺信息
            int effectedNum = shopMapper.insert(shop);
            if(effectedNum<=0){
                //只有RuntimeException回回滚事务
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImg!=null){
                    //存储图片
                    try{
                        addShopImg(shop,shopImg);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error"+e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = shopMapper.updateByPrimaryKey(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShop error"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    /**
     * 保存图片文件到本地，并将相对值路径加入到shop对象当中
     * @param shop
     * @param shopImg
     */
    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(shop.getShopid());
        //通过文件流和文件地址保存和处理文件
        String shopImgAddr = ImageUtil.generatrThumbnail(shopImg,dest);
        //将相对值路径加到shop中
        shop.setShopimg(shopImgAddr);
    }

    /**
     * 根据主键更新shop信息
     * @param shop shop信息
     * @return 条数
     */
    @Override
    public int updateShop(Shop shop) {
        return shopMapper.updateByPrimaryKey(shop);
    }

    /**
     * 更新店铺信息
     * @param shop 店铺信息
     * @param shopImg 图片的输入流
     * @return 对Shop封装的dto对象
     */
    @Override
    public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) {
        if (shop==null||shop.getShopid()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else {
            try {
                //1、判断是否处理图片
                if(shopImg!=null){
                    Shop tempShop = shopMapper.selectByPrimaryKey(shop.getShopid());
                    if (tempShop.getShopimg()!=null){
                        ImageUtil.deleteFileOrPath(tempShop.getShopimg());
                    }
                    addShopImg(shop,shopImg);
                }
                //2、更新店铺信息
                shop.setShopupdatetime(new Date());
                int effectedNum = shopMapper.updateByPrimaryKeySelective(shop);
                if (effectedNum<=0){
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }else {
                    shop = shopMapper.selectByPrimaryKey(shop.getShopid());
                    return new ShopExecution(ShopStateEnum.SUCCESS,shop);
                }
            }catch (Exception e){
                throw new ShopOperationException("modifyShop error:"+e.getMessage());
            }
        }

    }

    @Override
    public int deleteShop(Integer id) {
        return 0;
    }

    @Override
    public Shop selectById(Integer id) {
        return shopMapper.selectByPrimaryKey(id);
    }
}
