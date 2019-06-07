package club.bagedate.o2o.controller.frontend;

import club.bagedate.o2o.dto.ShopExecution;
import club.bagedate.o2o.entity.Place;
import club.bagedate.o2o.entity.ShopCategory;
import club.bagedate.o2o.entity.ShopExample;
import club.bagedate.o2o.service.PlaceService;
import club.bagedate.o2o.service.ShopCategoryService;
import club.bagedate.o2o.service.ShopService;
import club.bagedate.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ShopService shopService;

    /**
     * 如果有parentId获取二级ShopCategory列表
     * 如果没有获取到arentId将返回主区域列表
     * @param request
     * @return
     */
    @RequestMapping("/listshopspageinfo")
    @ResponseBody
    private Map<String,Object> listShopsPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //试着从前端请求中获取shopcategoryid
        Integer parentId = HttpServletRequestUtil.getInt(request,"shopcategoryid");
        List<ShopCategory> shopCategoryList = null;
        //如果parentId存在，则取出一级ShopCategory下的二级ShopCategory列表
        if (parentId!=-1){
            try {
                ShopCategory shopCategory = new ShopCategory();
                shopCategory.setShopcategoryparentid(parentId);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            }catch (Exception e){
                modelMap.put("success",true);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            //如果parentId不存在，则取出所有一级ShopCategory(用户咋首页选择的是全部商店列表)
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Place> placeList = null;
        try {
            //获取区域列表信息
            placeList = placeService.getPlaceList();
            modelMap.put("placeList",placeList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping("/listshops")
    @ResponseBody
    private Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取一页需要显示的条数
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        //非空判断
        if((pageIndex>-1)&&(pageSize>-1)){
            //试着获取一级类别Id
            int parentId = HttpServletRequestUtil.getInt(request,"parentId");
            //试着获取二级特定类别Id
            int shopCategoryId = HttpServletRequestUtil.getInt(request,"shopCategoryId");
            //试着获取区域Id
            int placeId = HttpServletRequestUtil.getInt(request,"placeId");
            //试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request,"shopName");
            //组合查询条件
            ShopExample shopExample = new ShopExample();
            ShopExample.Criteria criteria = shopExample.createCriteria();
            ShopExecution se = shopService.getShopList(shopExample,pageIndex,pageSize);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex");
        }
        return modelMap;
    }

    private ShopExample compactShopCondition4Search(int parentId,int shopCategoryId,int placeId,String shopName){
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        if(parentId!=-1){
            //查询某个一级ShopCategory下面的所有二级ShopCategory里面的店铺列表
            //查询一级列表下的所有二级列表
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopcategoryparentid(parentId);
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            //将条件加入规则
            List<Integer> shopCategoryIdList = new ArrayList<>();
            for (int i = 0; i < shopCategoryList.size(); i++) {
                shopCategoryIdList.add(shopCategoryList.get(i).getShopcategoryid());
            }
            criteria.andShopcategoryidIn(shopCategoryIdList);
        }
        if(shopCategoryId!=-1){
            //查询某个二级ShopCategory下面的店铺列表
            criteria.andShopcategoryidEqualTo(shopCategoryId);
        }
        if(placeId!=-1){
            //查询位于某个区域Id下的店铺列表
            criteria.andPlaceidEqualTo(placeId);
        }
        if(shopName!=null){
            //查询名字里包含shopName的店铺列表

        }
        return shopExample;
    }
}
