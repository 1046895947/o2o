package club.bagedate.o2o.controller.frontend;

import club.bagedate.o2o.entity.HeadLine;
import club.bagedate.o2o.entity.HeadLineExample;
import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.service.HeadLineService;
import club.bagedate.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    ShopCategoryService shopCategoryService;
    @Autowired
    HeadLineService headLineService;

    /**
     * 初始化前端展示系统主页信息，包括获取以及店铺列表以及头条列表
     * @return
     */
    @RequestMapping("/listmainpageinfo")
    @ResponseBody
    private Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopcategoryid(1);
            //获取一级店铺类别列表（即parentId为空的ShopCategory）
            shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        List<HeadLine> headLineList = new ArrayList<>();
        try {
            HeadLineExample headLineExample = new HeadLineExample();
            headLineExample.createCriteria().andLinestatusEqualTo(1);
            headLineList = headLineService.getHeadLineList(headLineExample);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        return modelMap;
    }
}
