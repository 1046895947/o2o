package club.bagedate.o2o.dto;

import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.enums.ShopStateEnum;

import java.util.List;

public class ShopExecution {
    //结果状态，0,1，2
    private int state;
    //状态标识，解释结果状态
    private String stateInfo;

    //店铺数量
    private Long count;

    //操作的shop(增删改店铺时用到)
    private Shop shop;
    //shop列表(查询店铺列表的时候使用)
    private List<Shop> shopList;

    public ShopExecution() {
    }

    /**
     * 店铺操作失败的构造器
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功的构造器
     * @param shopStateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopStateEnum,Shop shop){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop=shop;
    }
    public ShopExecution(ShopStateEnum shopStateEnum,List<Shop> shopList){
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
