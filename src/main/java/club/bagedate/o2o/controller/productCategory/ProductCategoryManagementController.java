package club.bagedate.o2o.controller.productCategory;

import club.bagedate.o2o.dto.ProductCategoryExecution;
import club.bagedate.o2o.dto.Result;
import club.bagedate.o2o.entity.ProductCategory;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.enums.ProductCategoryStateEnum;
import club.bagedate.o2o.exceptions.ProductCategoryOperationException;
import club.bagedate.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("productcategory")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/getproductcategorylist")
    @ResponseBody
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
//        Shop shop = new Shop();
//        shop.setShopid(10015);
//        request.getSession().setAttribute("currentShop",shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if(currentShop!=null&&currentShop.getShopid()>0){
            productCategoryList = productCategoryService.queryProductCategoryList(currentShop.getShopid());
            return new Result<List<ProductCategory>>(true,productCategoryList);
        }else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
        }
    }

    @RequestMapping("/addproductcategorys")
    @ResponseBody
    public Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory pc : productCategoryList) {
            pc.setShopid(currentShop.getShopid());
            pc.setProductcategorycreatetime(new Date());
        }
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategoryList);
                if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping("/removeproductcategory")
    @ResponseBody
    private Map<String,Object> removeProductCategory(Integer productCategoryId,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        if(productCategoryId!=null&&productCategoryId>0){
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId,currentShop.getShopid());
                if(pe.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少选择一个商品类别");
        }
        return modelMap;
    }
}
