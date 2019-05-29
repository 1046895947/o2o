package bagedate.o2o.Dao;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.entity.ProductImg;
import club.bagedate.o2o.mapper.ProductImgMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImgTest extends BaseTest {
    @Autowired
    ProductImgMapper productImgMapper;

    @Test
    public void testBatchInsertProductImg(){
        List<ProductImg> productImgList = new ArrayList<>();
        ProductImg productImg = new ProductImg();
        productImg.setProductid(1001);
        productImg.setProductimgcreatetime(new Date());
        productImg.setProductimgdesc("哈哈哈哈");
        productImg.setProductimgplace("没有");
        productImg.setProductimgpriority(0);
        productImgList.add(productImg);
        productImgList.add(productImg);
        productImgMapper.batchInsertProductImg(productImgList);
    }
}
