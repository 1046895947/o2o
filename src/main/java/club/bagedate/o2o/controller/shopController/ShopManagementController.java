package club.bagedate.o2o.controller.shopController;

import club.bagedate.o2o.dto.ShopExecution;
import club.bagedate.o2o.entity.*;
import club.bagedate.o2o.enums.ShopStateEnum;
import club.bagedate.o2o.service.PlaceService;
import club.bagedate.o2o.service.ShopCatrgoryService;
import club.bagedate.o2o.service.ShopService;
import club.bagedate.o2o.util.CodeUtil;
import club.bagedate.o2o.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/shop")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCatrgoryService shopCatrgoryService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/getshopmanagementinfo")
    @ResponseBody
    private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        int shopId = HttpServletRequestUtil.getInt(request,"shopId");
        if (shopId<=0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if(currentShopObj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","/o2o/shop/shopList");
            }else {
                Shop currentShop = (Shop)currentShopObj;
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopid());
            }
        }else {
            Shop currentShop = new Shop();
            currentShop.setShopid(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect",false);
        }
        return modelMap;
    }

    /**
     * 根据用户信息获取用户创建的店铺列表
     * @param request 中含有用户信息
     * @return 封装好的map对象，转成JSON字符返回
     */
    @RequestMapping("/getshoplist")
    @ResponseBody
    private Map<String,Object> getShopList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        User user1 = new User();
        user1.setUserid(1001);
        user1.setName("admin");
        request.getSession().setAttribute("user",user1);
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserid();
        try {
            ShopExample shopExample = new ShopExample();
            shopExample.createCriteria().andUseridEqualTo(userId);
            ShopExecution shopExecution = shopService.getShopList(shopExample,0,100);
            modelMap.put("shopList",shopExecution.getShopList());
            modelMap.put("count",shopExecution.getCount());
            modelMap.put("user",user);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping("/getshopbyid")
    @ResponseBody
    private Map<String,Object> getShopById(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Integer shopId = HttpServletRequestUtil.getInt(request,"shopId");
        if (shopId > -1){
            try {
                Shop shop = shopService.selectById(shopId);
                List<Place> placeList = placeService.getPlaceList();
                //ShopCategory不允许店家修改这里就不获取列表了
                String shopCategoryName = shopCatrgoryService.selectById(shop.getShopcategoryid()).getShopcategoryname();
                modelMap.put("shopCategoryName",shopCategoryName);
                modelMap.put("placeList",placeList);
                modelMap.put("shop",shop);
                modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/getshopinitinfo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    private  Map<String,Object> getShopInitInfo(){
        //创建返回值map
        Map<String,Object> modelMap = new HashMap<>();

        List<ShopCategory> shopCategoryList;
        List<Place> placeList;
        try {
            shopCategoryList = shopCatrgoryService.getShopCategoryList(new ShopCategory());
            placeList = placeService.getPlaceList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("placeList",placeList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String a = objectMapper.writeValueAsString(modelMap);
//            System.out.println(objectMapper.writeValueAsString(modelMap));
//            return a;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return modelMap;
    }

    /**
     * 注册店铺
     * @param request
     * @return
     */
    //@ResponseBody作用将返回值转换为json数据
    @RequestMapping(value = "/registershop" , method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object>  registerShop(HttpServletRequest request) {
        //创建一个返回值数据
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }
        //接收并转换相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        //jackson对象的核心对象 ，调用ObjectMapper的相关方法转换json数据
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //将json数据转换为shop对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            //出错返回
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            System.out.println(e.getMessage());
            return modelMap;
        }
        //CommonsMultipartFile Spring提供的读取文件的类！！！！！！不懂！！！！！！
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopimg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //注册店铺
        if (shop != null && shopImg != null) {
            //谁注册的，从session获取，在登录时将用户id放入session中
            shop.setUserid((Integer) request.getSession().getAttribute("userId"));
            ShopExecution se = shopService.insertShop(shop,shopImg);
            if(se.getState()== ShopStateEnum.CHECK.getState()){
                modelMap.put("success",true);
                //店铺创建成功后加载session中取到该用户可以操作的用户列表
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if(shopList==null||shopList.size()==0){
                    shopList = new ArrayList<Shop>();
                }
                //在list中添加刚刚创建的店铺
                shopList.add(se.getShop());
                request.getSession().setAttribute("shopList",shopList);
            }else {
                modelMap.put("success",false);
                System.out.println(se.getStateInfo());
                modelMap.put("errMsg",se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
        //返回结果
    }

    /**
     * 修改店铺信息
     * @param request
     * @return
     */
    //@ResponseBody作用将返回值转换为json数据
    @RequestMapping(value = "/modifyshop" , method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object>  modifyShop(HttpServletRequest request) {
        //创建一个返回值数据
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }
        //接收并转换相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        //jackson对象的核心对象 ，调用ObjectMapper的相关方法转换json数据
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //将json数据转换为shop对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            //出错返回
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            System.out.println(e.getMessage());
            return modelMap;
        }
        //CommonsMultipartFile Spring提供的读取文件的类！！！！！！不懂！！！！！！
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopimg");
        }
        //修改店铺信息
        if (shop != null && shop.getShopid()!=null) {
            ShopExecution se;
            if(shopImg==null){
                se = shopService.modifyShop(shop,null);
            }else {
                se = shopService.modifyShop(shop,shopImg);
            }
            if(se.getState()== ShopStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
            }else {
                modelMap.put("success",false);
                System.out.println(se.getStateInfo());
                modelMap.put("errMsg",se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息Id");
            return modelMap;
        }
        //返回结果
    }
}
