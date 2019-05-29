package bagedate.o2o.Service;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.service.ProductCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class productCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    public void testQueryProductCategoryList(){
        List<ProductCategory> productCategoryList = productCategoryService.queryProductCategoryList(10015);
        for (int i = 0; i < productCategoryList.size() ; i++) {
            System.out.println(productCategoryList.get(i));
        }
    }
    @Test
    public void testBatchAddProductCategory(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setShopid(10015);
        productCategory.setProductcategorycreatetime(new Date());
        productCategory.setProductcategoryname("22222");
        productCategory.setProductcategorypriority(22);
        productCategoryList.add(productCategory);
        productCategoryService.batchAddProductCategory(productCategoryList);
    }
}
