package club.bagedate.o2o.controller;

import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.service.ShopService;
import club.bagedate.o2o.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/index")
public class testController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @RequestMapping("/index")
    public String index() {
        System.out.println(userService.findById(1001));
        return "index";
    }
    @RequestMapping("/insert")
    public String insert(){
        Shop shop = new Shop();
        shop.setShoppriority(0);
        shop.setShopimg("无");
        shop.setShopname("test2");
        shop.setShopdesc("test2");
        shop.setShopphonenumber("15069118115");
        shop.setShopplace("那博士院内");
        shop.setPlaceid(1);
        shop.setShopcategoryid(1);
        shop.setUserid(1001);
        shop.setShopstatus(0);
        shop.setShopcreatetime(new Date());
        shop.setShopupdatetime(new Date());
        shop.setShopadvice("好好好");
        //shopService.insertShop(shop);
        return "index";
    }
    @RequestMapping("/update")
    public String update(){
        Shop shop = new Shop();
        shop.setShopid(10002);
        shop.setShoppriority(1);
        shop.setShopimg("无wu");
        shop.setShopname("test22");
        shop.setShopdesc("test22");
        shop.setShopphonenumber("15069118115");
        shop.setShopplace("那博士院内1");
        shop.setPlaceid(1);
        shop.setShopcategoryid(1);
        shop.setUserid(1001);
        shop.setShopstatus(0);
        shop.setShopcreatetime(new Date());
        shop.setShopupdatetime(new Date());
        shop.setShopadvice("好好好2");
        shopService.updateShop(shop);
        return "index";
    }
}
