package club.bagedate.o2o.service.Impl;

import club.bagedate.o2o.dto.ProductExecution;
import club.bagedate.o2o.entity.Product;
import club.bagedate.o2o.entity.ProductExample;
import club.bagedate.o2o.entity.ProductImg;
import club.bagedate.o2o.entity.ProductImgExample;
import club.bagedate.o2o.enums.ProductStateEnum;
import club.bagedate.o2o.exceptions.ProductOperationException;
import club.bagedate.o2o.mapper.ProductImgMapper;
import club.bagedate.o2o.mapper.ProductMapper;
import club.bagedate.o2o.service.ProductService;
import club.bagedate.o2o.util.ImageUtil;
import club.bagedate.o2o.util.PageCalculator;
import club.bagedate.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductImgMapper productImgMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    @Transactional
    public ProductExecution insertProduct(Product product, CommonsMultipartFile productImg, List<CommonsMultipartFile> commonsMultipartFileList) throws ProductOperationException {
        //空值判断
        if (product!=null&&product.getShopid()!=null){
            //创建时间
            product.setProductcreatetime(new Date());
            //修改时间
            product.setProductupdatetime(new Date());
            //默认为上架状态
            product.setProductstatus(1);
            if(productImg!=null){
                addThumbnail(product,productImg);
            }
            //添加商品
            try {
                int effectedNum = productMapper.insert(product);
                if (effectedNum<=0){
                    throw new ProductOperationException("商品创建失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("商品创建失败："+e.toString());
            }
            //若商品详情图不为空则添加
            if(commonsMultipartFileList!=null&&commonsMultipartFileList.size()>0){
                addProductImgList(product,commonsMultipartFileList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public Product getProductById(Integer productid) {
        return productMapper.selectByPrimaryKey(productid);
    }


    //1、若缩略图参数有值，则处理缩略图
    //   若原来存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
    //3、若商品详情图列表有值，对详情图列表做同样操作
    //   将productimg下面的该商品的详情图记录全部清除
    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, CommonsMultipartFile shopImg, List<CommonsMultipartFile> commonsMultipartFileList) throws ProductOperationException {
        //空值判断
        if(product!=null&&product.getShopid()!=null){
            //给商品添加默认属性
            product.setProductupdatetime(new Date());
            //若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
            if(shopImg!=null){
                //获取原有信息
                Product tempProduct = productMapper.selectByPrimaryKey(product.getProductid());
                if(tempProduct.getProductplace()!=null){
                    ImageUtil.deleteFileOrPath(tempProduct.getProductplace());
                }
                addThumbnail(product,shopImg);
            }
            //如果有新入的商品详情图，则将原来的删除，并添加新的图片
            if(commonsMultipartFileList!=null&&commonsMultipartFileList.size()>0){
                deleteProductImgList(product.getProductid());
                addProductImgList(product,commonsMultipartFileList);
            }
            //更新商品信息
            try {
                int effectedNum = productMapper.updateByPrimaryKeySelective(product);
                if(effectedNum<0){
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS,product);
            }catch (Exception e){
                throw new ProductOperationException("更新商品信息失败"+e.toString());
            }
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(ProductExample productExample, int pageIndex, int pageSize) {
        //页码转换为数据库的行码，并调用dao层取回指定页码的商品列表
        int leftLimit = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        productExample.setLeftLimit(leftLimit);
        productExample.setLimitSize(pageSize);
        List<Product> productList = productMapper.selectByExample(productExample);
        //基于同样的查询条件下返回该查询条件下的商品总数
        Long count =  productMapper.countByExample(productExample);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    @Override
    public Product getProductById(int productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    /**
     * 删除已有的商品详情图
     * @param productid
     */
    private void deleteProductImgList(Integer productid) {
        ProductImgExample productImgExample = new ProductImgExample();
        productImgExample.createCriteria().andProductidEqualTo(productid);
        //根据productId获取原来的图片
        List<ProductImg> productImgList = productImgMapper.selectByExample(productImgExample);
        //删除原来的图片
        for (ProductImg productImg:productImgList){
            ImageUtil.deleteFileOrPath(productImg.getProductimgplace());
        }
        productImgMapper.deleteByExample(productImgExample);
    }

    /**
     * 店铺缩略图存储
     * @param product
     * @param productImg
     */
    private void addThumbnail(Product product,CommonsMultipartFile productImg){
        //获取图片目录的相对值路径,直接存放到店铺文件夹下
        String dest = PathUtil.getShopImagePath(product.getShopid());
        //通过文件流和文件地址保存和处理图片
        String productImgAddr = ImageUtil.generatrThumbnail(productImg,dest);
        //将相对值路径加入到product中
        product.setProductplace(productImgAddr);
    }

    /**
     * 店铺详情图存储
     * @param product
     * @param commonsMultipartFileList
     */
    private void addProductImgList(Product product,List<CommonsMultipartFile> commonsMultipartFileList){
        //获取图片目录的相对值路径,直接存放到店铺文件夹下
        String dest = PathUtil.getShopImagePath(product.getShopid());
        List<ProductImg> productImgList = new ArrayList<>();
        for (CommonsMultipartFile commonsMultipartFile:commonsMultipartFileList){
            String imgAddr = ImageUtil.generatrThumbnail(commonsMultipartFile,dest);
            ProductImg productImg = new ProductImg();
            productImg.setProductimgplace(imgAddr);
            productImg.setProductid(product.getProductid());
            productImg.setProductimgpriority(0);
            productImg.setProductimgdesc("暂无简介");
            productImg.setProductimgcreatetime(new Date());
            productImgList.add(productImg);
        }
        //如果确实有图片需要添加，就执行批量添加操作
        if(productImgList.size()>0){
            try {
                int effectedNum = productImgMapper.batchInsertProductImg(productImgList);
                if(effectedNum<=0){
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            }catch (Exception e){
                throw new ProductOperationException("创建商品详情图片失败"+e.toString());
            }
        }
    }
}
