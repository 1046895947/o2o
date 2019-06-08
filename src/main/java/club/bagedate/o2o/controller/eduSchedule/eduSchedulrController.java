package club.bagedate.o2o.controller.eduSchedule;

import club.bagedate.o2o.service.eduScheduleUtil;
import club.bagedate.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/edu")
public class eduSchedulrController {

    @Autowired
    eduScheduleUtil eduScheduleUtil;

    @RequestMapping("/login")
    public String login(){
        String path;
        try {
            path = eduScheduleUtil.getImage();
            return "eduSchedule/login?path="+path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "eduSchedule/login";
    }

    /**
     * 访问该URL传入账号密码验证码
     * 返回课程表Map类型 key为class11-class76
     * @param request
     * @return
     */
    @RequestMapping("/getschedule")
    @ResponseBody
    public Map<String,Object> getSchedule(HttpServletRequest request){
        Map<String,Object> modelmap = new HashMap<>();
        try {
            String username = HttpServletRequestUtil.getString(request,"username");
            String password = HttpServletRequestUtil.getString(request,"password");
            String code = HttpServletRequestUtil.getString(request,"code");
            eduScheduleUtil.setUsername(username);
            eduScheduleUtil.setPassword(password);
            eduScheduleUtil.initLogin(code);
            modelmap = eduScheduleUtil.getSchedule();
        } catch (IOException e) {
            modelmap.put("success",false);
            e.printStackTrace();
        }
        return modelmap;
    }

    /**
     * 登录后访问 传入时间 不传为全部
     * 返回值Map类型 key 1-map.size()  key为字符串类型
     * @param request
     * @return
     */
    @RequestMapping("/getgrade")
    @ResponseBody
    public Map<String,Object> getGrade(HttpServletRequest request){
        String date = null;
        date = HttpServletRequestUtil.getString(request,"date");
        Map<String,Object> modelmap = new HashMap<>();
        try {
            modelmap = eduScheduleUtil.getGrade(date);
        } catch (IOException e) {
            modelmap.put("success",true);
            e.printStackTrace();
        }
        return modelmap;
    }
}
