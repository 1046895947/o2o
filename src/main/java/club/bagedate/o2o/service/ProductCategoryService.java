package club.bagedate.o2o.service;

import club.bagedate.o2o.dto.ProductCategoryExecution;
import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 根据用户店铺id获得该店铺的所有分类
     * @param shopId
     * @return
     */
    public List<ProductCategory> queryProductCategoryList(Integer shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id置为空
     *
     * 根据类别Id和店铺Id删除商品类别信息，防止这个用户删掉另一个用户的商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     */
    ProductCategoryExecution deleteProductCategory(Integer productCategoryId,Integer shopId) throws ProductCategoryOperationException;
}
