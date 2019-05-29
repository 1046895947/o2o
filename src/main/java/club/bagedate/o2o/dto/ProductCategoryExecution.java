package club.bagedate.o2o.dto;

import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;

    //列表(查询店铺列表的时候使用)
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }

    //失败时使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功时使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
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

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
