package club.bagedate.o2o.controller.productCategory;

import club.bagedate.o2o.dto.ProductExecution;
import club.bagedate.o2o.entity.Product;
import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.entity.ProductExample;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.enums.ProductStateEnum;
import club.bagedate.o2o.exceptions.ProductOperationException;
import club.bagedate.o2o.service.ProductCategoryService;
import club.bagedate.o2o.service.ProductService;
import club.bagedate.o2o.util.CodeUtil;
import club.bagedate.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    //支持上传商品详情图的最大数量
    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping("/insertproduct")
    @ResponseBody
    private Map<String,Object> insertProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //验证码校验
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }
        //接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        //商品对象
        Product product =null;
        //从前端获取商品的字符数据
        String productStr = HttpServletRequestUtil.getString(request,"productStr");
//处理前台传送的图片对象
        //处理文件流
        MultipartHttpServletRequest multipartHttpServletRequest =null;
        //处理缩略图文件流
        CommonsMultipartFile thumbnail=null;
        //处理详情图文件流列表
        List<CommonsMultipartFile> commonsMultipartFileList = new ArrayList<>();
        //从session中获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            //若请求中存在文件流，则取出相关的文件（包含缩略图和详情图）
            if(multipartResolver.isMultipart(request)){
                //处理文件流中的图片
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                //取出缩略图
                thumbnail = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                //取出详情图片
                for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                    CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
                    if (commonsMultipartFile != null) {
                        commonsMultipartFileList.add(commonsMultipartFile);
                    } else {
                        break;
                    }
                }
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg","上传图片不能为空");
                return modelMap;
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
//将前台传送的JSON数据转换为商品对象
        try {
            //尝试获取前端传过来的表单string流并将其转换成Product实体类
            product = mapper.readValue(productStr,Product.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
//开始进行数据添加操作
        //若Product信息，缩略图以及详情图片列表为非空，则开始进行商品添加操作
        if(product!=null&&thumbnail!=null&&commonsMultipartFileList.size()>0){
            try {
                //获取session中店铺信息
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                //为商品赋值店铺id
                product.setShopid(currentShop.getShopid());
                //插入数据，详情图，缩略图都在这上传
                ProductExecution pe = productService.insertProduct(product,thumbnail,commonsMultipartFileList);
                if(pe.getState()== ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping("/getproductbyid")
    @ResponseBody
    private Map<String,Object> getProductById(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Integer productId = HttpServletRequestUtil.getInt(request,"productId");
        //非空判断
        if (productId>-1){
            //获取商品信息
            Product product = productService.getProductById(productId);
            //获取该店铺下的商品类别列表
            List<ProductCategory> productCategoryList = productCategoryService.queryProductCategoryList(product.getShopid());
            modelMap.put("product",product);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }

    @RequestMapping("/modifyproduct")
    @ResponseBody
    public Map<String,Object> modifyProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //商品是编辑的时候调用还是上下架操作的时候调用
        //若前者则进行验证码判断，后者则跳过验证码判断
        boolean statusChange = HttpServletRequestUtil.getBoolean(request,"statusChange");
        //验证码判断
        if(!statusChange&&!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        //接收前端参数的变量的初始化
        ObjectMapper mapper = new ObjectMapper();
        //商品对象
        Product product =null;
        //从前端获取商品的字符数据
        //String productStr = HttpServletRequestUtil.getString(request,"productStr");
//处理前台传送的图片对象
        //处理文件流
        MultipartHttpServletRequest multipartHttpServletRequest =null;
        //处理缩略图文件流
        CommonsMultipartFile thumbnail=null;
        //处理详情图文件流列表
        List<CommonsMultipartFile> commonsMultipartFileList = new ArrayList<>();
        //从session中获取文件流
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            //若请求中存在文件流，则取出相关的文件（包含缩略图和详情图）
            if(multipartResolver.isMultipart(request)){
                multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                //取出缩略图
                thumbnail = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                //取出详情图片
                for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                    CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
                    if (commonsMultipartFile != null) {
                        commonsMultipartFileList.add(commonsMultipartFile);
                    } else {
                        break;
                    }
                }
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg","111");
            return modelMap;
        }
        //获取前端穿过来得表单String流并将其转换成Product实体类
        try {
            String productStr = HttpServletRequestUtil.getString(request,"productStr");
            product = mapper.readValue(productStr,Product.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg","222");
            return modelMap;
        }
        //非空判断
        if(product!=null){
            try {
                //从session获取当前店铺的ID并赋值给product，减少对前端的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShopid(currentShop.getShopid());
                //开始进行商品信息变更操作
                ProductExecution pe = productService.modifyProduct(product,thumbnail,commonsMultipartFileList);
                if (pe.getState()==ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (RuntimeException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
        }
        return modelMap;
    }

//    private void handleImg(MultipartHttpServletRequest request, List<CommonsMultipartFile> commonsMultipartFileList) {
//        MultipartHttpServletRequest multipartHttpServletRequest;
//        CommonsMultipartFile thumbnail;//取出文件流
//
//    }

    /**
     * 根据店铺id获取该店铺下的所有商品类别分类
     * @param request
     * @return
     */
    @RequestMapping("/getproductcategorylist")
    @ResponseBody
    public Map<String,Object> getproductcategorylist(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Shop shop = (Shop) request.getSession().getAttribute("currentShop");
        if (shop!=null&&shop.getShopid()!=null){
            List<ProductCategory> productCategoryList = productCategoryService.queryProductCategoryList(shop.getShopid());
            modelMap.put("success",true);
            modelMap.put("productCategoryList",productCategoryList);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    @RequestMapping("/getproductlistbyshop")
    @ResponseBody
    private Map<String,Object> getProductListByShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //获取前台传过来的页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取前台传过来的商品条数
        int pagesize = HttpServletRequestUtil.getInt(request,"pageSize");
        //从当前session中获取店铺信息，主要获取shopId
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        modelMap.put("shopId",currentShop.getShopid());
        //空值判断
        if(pageIndex>-1&&pagesize>-1&&currentShop!=null&&currentShop.getShopid()!=null){
            //获取传入的需要检索的条件，包括是否需要从某个商品类别以及模糊查找商品名去筛选每个店铺下的商品列表
            //筛选的条件可以进行排序组合
            int productCategoryId = HttpServletRequestUtil.getInt(request,"productCategoryId");
            String productName = HttpServletRequestUtil.getString(request,"productName");
            ProductExample productExample = new ProductExample();
            productExample.setFuzzySelectName(productName);
            ProductExample.Criteria criteria = productExample.createCriteria();
            if(productCategoryId>-1){
                criteria.andProductcategoryidEqualTo(productCategoryId);
            }
            criteria.andShopidEqualTo(currentShop.getShopid());

            ProductExecution pe = productService.getProductList(productExample,pageIndex,pagesize);
            modelMap.put("productList",pe.getProductList());
            modelMap.put("count",pe.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }
}
