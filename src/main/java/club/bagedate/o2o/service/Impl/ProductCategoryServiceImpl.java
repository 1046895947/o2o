package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.dto.ProductCategoryExecution;
import club.bagedate.o2o.entity.Product;
import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.entity.ProductCategoryExample;
import club.bagedate.o2o.entity.ProductExample;
import club.bagedate.o2o.enums.ProductCategoryStateEnum;
import club.bagedate.o2o.exceptions.ProductCategoryOperationException;
import club.bagedate.o2o.mapper.ProductCategoryMapper;
import club.bagedate.o2o.mapper.ProductMapper;
import club.bagedate.o2o.service.ProductCategoryService;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Autowired
    ProductMapper productMapper;
    @Override
    public List<ProductCategory> queryProductCategoryList(Integer shopId) {
        ProductCategoryExample productCategoryExample = new ProductCategoryExample();
        productCategoryExample.createCriteria().andShopidEqualTo(shopId);
        return productCategoryMapper.selectByExample(productCategoryExample);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try {
                int effectedNum = productCategoryMapper.batchInsertProductCategory(productCategoryList);
                if (effectedNum<=0){
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw  new ProductCategoryOperationException("batchAddProductCategory error:"+e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(Integer productCategoryId,Integer shopId) throws ProductCategoryOperationException {
        try {
            int effectedNum = (int) productMapper.updateProductCategoryToNull(productCategoryId);
            if (effectedNum<0){
                throw  new ProductCategoryOperationException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }
        //商品类别下的商品的商品id置为空
        try {
            ProductCategoryExample productCategoryExample = new ProductCategoryExample();
            ProductCategoryExample.Criteria circle = productCategoryExample.createCriteria();
            circle.andShopidEqualTo(shopId);
            circle.andProductcategoryidEqualTo(productCategoryId);
            Integer effectedNum = productCategoryMapper.deleteByExample(productCategoryExample);
            if (effectedNum<=0){
                throw new ProductCategoryOperationException("商品类别删除失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (ProductCategoryOperationException e){
            throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
        }

    }
}
