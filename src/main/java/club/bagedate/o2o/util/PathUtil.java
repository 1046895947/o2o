package club.bagedate.o2o.util;

public class PathUtil {
    //获取文件分隔符即/\
    private static String separator = System.getProperty("file.separator");
    /**
     * 依据操作环境的不同，提供不同的根路径
     * 功能就是，确定项目图片的根路径
     * @return
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projecto2o/image";
        }else {
            basePath = "/home/zhang/image";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }

    /**
     * 项目图片放到每个店铺的文件夹下，确定子路径
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/"+shopId+"/";
        imagePath = imagePath.replace("/",separator);
        System.out.println(imagePath);
        return imagePath.replace("/",separator);
    }
}
