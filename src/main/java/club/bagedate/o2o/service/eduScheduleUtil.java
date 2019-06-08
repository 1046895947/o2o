package club.bagedate.o2o.service;

import club.bagedate.o2o.entity.Grade;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟登陆教务系统工具类实现
 */
@Service
public class eduScheduleUtil {
    private String imageUrl = "http://jwxt.qlu.edu.cn/verifycode.servlet?t=0.020974584"; // 验证码
    private String passUrl = "http://jwxt.qlu.edu.cn/Logon.do?method=logon&flag=sess"; //账号密码加密
    private String url_Login = "http://jwxt.qlu.edu.cn/Logon.do?method=logon"; // 登录
    private String schedultUrl = "http://jwxt.qlu.edu.cn/jsxsd/xskb/xskb_list.do"; //课表url
    private String gradeUrl = "http://jwxt.qlu.edu.cn/jsxsd/kscj/cjcx_list"; //成绩
    private Map<String,String> cookie;
    private String username = "";
    private String password = "";
    //验证码暂时存储位置
    private String path = eduScheduleUtil.class.getResource("/").getPath().replaceAll("%20", " ") + "safecode.png";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *下载图片并保存
     * @throws IOException
     */
    public String getImage() throws IOException {
        //访问登陆页面
        Response response = Jsoup.connect(imageUrl).ignoreContentType(true).userAgent("Mozilla").method(Method.POST).timeout(3000).execute();
        //获取返回值中所有的cookie
        cookie = response.cookies();
        //获取返回值中的字节流对象
        byte[] bytes = response.bodyAsBytes();
        //将字节流对象转换为文件并保存
        saveFile(path, bytes);
        System.out.println("保存验证码到：" + path);
        return path;
    }

    /**
     * 登陆教务系统
     * @param code1 验证码
     * @throws IOException
     */
    public void initLogin(String code1) throws IOException {
        try {
            //登陆时提交的参数封装到map中
            Map<String, String> data = new HashMap<String, String>();
            data.put("view", "1");
            //传入加密后的账号密码
            data.put("encoded", Login());
            //传入验证码
            data.put("RANDOMCODE", code1);
            //访问登陆url并携带账号验证码等信息
            Connection connect = Jsoup.connect(url_Login)
                    .header("Accept",
                            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .userAgent("Mozilla").method(Method.POST).data(data).timeout(3000);
            //添加cookie信息
            for (Map.Entry<String, String> entry : cookie.entrySet()) {
                connect.cookie(entry.getKey(), entry.getValue());
            }
            //响应
            Response response = connect.execute();
            //输出响应信息
            System.out.println(response.parse().text().toString());
        } catch (IOException e) {

        }
    }

    /**
     * 重写js中加密算法
     * 对账号密码进行加密
     * @return
     * @throws IOException
     */
    public String Login() throws IOException {
        //访问加密URL
        Connection connect = Jsoup.connect(passUrl)
                .header("Accept",
                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .userAgent("Mozilla").method(Connection.Method.POST).timeout(3000);
        //给连接添加cookie
        for (Map.Entry<String, String> entry : cookie.entrySet()) {
            connect.cookie(entry.getKey(), entry.getValue());
        }
        //响应
        Response response = connect.execute();
        //响应为一个加密字符创用#分割
        String dataStr = response.parse().text();
        // 把JS中的加密算法用Java写一遍：
        String scode = dataStr.split("#")[0];
        String sxh = dataStr.split("#")[1];
        String code = username + "%%%" + password;
        String encoded = "";
        for (int i = 0; i < code.length(); i++) {
            if (i < 20) {
                encoded = encoded + code.substring(i, i + 1)
                        + scode.substring(0, Integer.parseInt(sxh.substring(i, i + 1)));
                scode = scode.substring(Integer.parseInt(sxh.substring(i, i + 1)), scode.length());
            } else {
                encoded = encoded + code.substring(i, code.length());
                i = code.length();
            }
        }
        //返回加密后的账号密码
        return encoded;
    }

    /**
     * 将字节流转换成文件
     *
     * @param filename
     * @param data
     * @throws Exception
     */
    public static void saveFile(String filename, byte[] data) {
        //如果字节流不为空
        if (data != null) {
            String filepath = filename;
            //创建一个文件
            File file = new File(filepath);
            //如果存在删除
            if (file.exists()) {
                file.delete();
            }
            //将字符流传入文件中
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data, 0, data.length);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取课程表
     * @return
     * @throws IOException
     */
    public Map<String,Object> getSchedule() throws IOException {
        Map<String,Object> modelmap = new HashMap<>();
        try {
            Connection connection = Jsoup.connect(schedultUrl).header("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .userAgent("Mozilla").method(Connection.Method.POST).timeout(3000);
            connection.cookies(cookie);
            Document document = connection.get();
            for (int i = 1; i <= 7 ; i++) {
                String class1 = document.getElementById("F42422317A5840339BF150F1CA9C1139-"+i+"-2").text();
                String class2 = document.getElementById("A37E1F2AD29D4A42A7DF32B779592814-"+i+"-2").text();
                String class3 = document.getElementById("6F4CF1CD26EB4E4DA90A302281EFC784-"+i+"-2").text();
                String class4 = document.getElementById("EEF5D11633A74E6181276A9A88713770-"+i+"-2").text();
                String class5 = document.getElementById("61B77005135344CC8D503F43A38F0716-"+i+"-2").text();
                String class6 = document.getElementById("AAF454A7E29E47C7AC5336C9A02609A3-"+i+"-2").text();
                modelmap.put("class"+i+"1",class1);
                modelmap.put("class"+i+"2",class2);
                modelmap.put("class"+i+"3",class3);
                modelmap.put("class"+i+"4",class4);
                modelmap.put("class"+i+"5",class5);
                modelmap.put("class"+i+"6",class6);
            }
            modelmap.put("success",true);
        }catch (Exception e){
            modelmap.put("success",false);
            modelmap.put("errMsg",e.getMessage());
        }
        return modelmap;
    }

    /**
     * 根据时间查询成绩
     * 时间格式2018-2019-1
     * @param date
     * @return
     * @throws IOException
     */
    public Map<String,Object> getGrade(String date) throws IOException {
        Map<String,Object> modelmap = new HashMap<>();
        try {
            Map<String, String> data = new HashMap<String, String>();
            if(date!=null){
                data.put("kksj", date);
            }
            data.put("xsfs", "all");
            Connection connection = Jsoup.connect(gradeUrl).header("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .userAgent("Mozilla").method(Connection.Method.POST).data(data).timeout(3000);
            connection.cookies(cookie);
            Document document = connection.get();
            int classNum = document.getElementById("dataList").select("tr").size();
            for (int i = 1; i <classNum ; i++) {
                Grade g = new Grade();
                g.setId(Integer.parseInt(document.getElementById("dataList").select("tr").get(i).select("td").get(0).text()));
                g.setDate(document.getElementById("dataList").select("tr").get(i).select("td").get(1).text());
                g.setClassId(document.getElementById("dataList").select("tr").get(i).select("td").get(2).text());
                g.setClassName(document.getElementById("dataList").select("tr").get(i).select("td").get(3).text());
                g.setGrade(Integer.parseInt(document.getElementById("dataList").select("tr").get(i).select("td").get(4).text()));
                g.setClassNum(Double.parseDouble(document.getElementById("dataList").select("tr").get(i).select("td").get(5).text()));
                g.setClassTime(Integer.parseInt(document.getElementById("dataList").select("tr").get(i).select("td").get(6).text()));
                g.setGPA(Double.parseDouble(document.getElementById("dataList").select("tr").get(i).select("td").get(7).text()));
                g.setClassForm(document.getElementById("dataList").select("tr").get(i).select("td").get(8).text());
                g.setClassProperty(document.getElementById("dataList").select("tr").get(i).select("td").get(9).text());
                g.setClassNature(document.getElementById("dataList").select("tr").get(i).select("td").get(10).text());
                modelmap.put(i+"",g);
            }
            modelmap.put("success",true);
        }catch (Exception e){
            modelmap.put("success",false);
            modelmap.put("errMsg",e.getMessage());
        }
        return modelmap;
    }
}
