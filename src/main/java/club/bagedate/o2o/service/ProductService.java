package club.bagedate.o2o.service;

import club.bagedate.o2o.dto.ProductExecution;
import club.bagedate.o2o.entity.Product;
import club.bagedate.o2o.entity.ProductExample;
import club.bagedate.o2o.exceptions.ProductOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ProductService {
    /**
     * 添加商品信息以及图片处理
     * @return
     * @throws ProductOperationException
     */
    ProductExecution insertProduct(Product product, CommonsMultipartFile shopImg, List<CommonsMultipartFile
            > commonsMultipartFileList) throws ProductOperationException;

    /**
     * 获取商品信息
     * @param productid
     * @return
     */
    Product getProductById(Integer productid);

    /**
     * 修改商品信息及图片处理
     * @param product
     * @param shopImg
     * @param commonsMultipartFileList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, CommonsMultipartFile shopImg, List<CommonsMultipartFile
            > commonsMultipartFileList) throws ProductOperationException;

    /**
     * 查询商品列表并分页,可输入好多参数哈哈
     * @param productExample
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(ProductExample productExample, int pageIndex, int pageSize);

    Product getProductById(int productId);
}
