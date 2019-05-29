package club.bagedate.o2o.dto;

import club.bagedate.o2o.entity.Product;
import club.bagedate.o2o.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {
    //结果状态
    private int state;
    //装填表示
    private String stateInfo;
    //商品数量
    private long count;
    //操作的product（增删改商品时用到）
    private Product product;
    //获取的product列表（查询商品时用到）
    private List<Product> productList;

    public ProductExecution() {
    }

    //操作失败的构造器
    public ProductExecution(ProductStateEnum productStateEnum) {
    }

    //操作成功的构造器
    public ProductExecution(ProductStateEnum productStateEnum,Product product) {
        this.product = product;
    }

    //操作成功的构造器

    public ProductExecution(ProductStateEnum productStateEnum,List<Product> productList) {
        this.productList = productList;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public long getCount() {
        return count;
    }

    public Product getProduct() {
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
