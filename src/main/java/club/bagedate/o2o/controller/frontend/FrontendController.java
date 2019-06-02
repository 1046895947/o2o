package club.bagedate.o2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
    @RequestMapping("/index")
    public String index(){
        return "/frontend/index";
    }
}
