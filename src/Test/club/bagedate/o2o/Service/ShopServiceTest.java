package bagedate.o2o.Service;

import club.bagedate.o2o.BaseTest;
import club.bagedate.o2o.dto.ShopExecution;
import club.bagedate.o2o.entity.Shop;
import club.bagedate.o2o.entity.ShopExample;
import club.bagedate.o2o.enums.ShopStateEnum;
import club.bagedate.o2o.service.ShopService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList(){
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andUseridEqualTo(1001);
        ShopExecution shopExecution = shopService.getShopList(shopExample,0,20);
        List<Shop> shopList = shopExecution.getShopList();
        Long count = shopExecution.getCount();
        for (int i = 0; i <shopList.size(); i++) {
            System.out.println(shopList.get(i).toString());
        }
        System.out.println(count);
    }

    @Test
    public void testInsertShop() throws IOException {
        Shop shop = new Shop();
        shop.setShoppriority(0);
        shop.setShopname("test2");
        shop.setShopdesc("test2");
        shop.setShopphonenumber("15069118115");
        shop.setShopplace("那博士院内");
        shop.setPlaceid(1);
        shop.setShopcategoryid(1);
        shop.setUserid(1001);
        shop.setShopstatus(ShopStateEnum.CHECK.getState());
        shop.setShopcreatetime(new Date());
        shop.setShopupdatetime(new Date());
        shop.setShopadvice("好好好");
        File file = new File("C:\\Users\\zhangguangxiang\\Desktop\\fufu.jpg");
        // 需要导入commons-fileupload的包
        FileItem fileItem = new DiskFileItem("fufu.jpg", Files.probeContentType(file.toPath()),false,file.getName(),(int)file.length(),file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()){
            while ( (n = inputStream.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            //也可以用IOUtils.copy(inputStream,os);
            CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            shopService.insertShop(shop,multipartFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
