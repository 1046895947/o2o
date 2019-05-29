package club.bagedate.o2o.controller.shopController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping("/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
    @RequestMapping("/shoplist")
    public String shopList(){
        return "shop/shopList";
    }
    @RequestMapping("/shopmanagement")
    public String shopManagement(){
        return "shop/shopmanagement";
    }
    @RequestMapping("/productcategorymanagement")
    public String productCategoryManagement(){
        return "shop/productcategorymanagement";
    }
    @RequestMapping("/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }
    @RequestMapping("/productmanagement")
    public String productManagement(){
        return "shop/productmanagement";
    }
}
