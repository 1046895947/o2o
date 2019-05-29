package club.bagedate.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String VerifyCodeExpected = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String VerifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(VerifyCodeActual==null||!VerifyCodeActual.equals(VerifyCodeExpected)){
            return false;
        }else {
            return true;
        }
    }
}
