package club.bagedate.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    //class根路径
    public static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    /**
     * 通过用户上传的文件流和用户信息创建文件夹及保存文件
     * @param thumbnail 文件流
     * @param targetAddr 文件存储相对路径
     * @return
     */
    public static String generatrThumbnail(CommonsMultipartFile thumbnail,String targetAddr){
        //文件随机名（下方函数实现）
        String realFileName = getReandomFileName();
        //文件扩展名（函数实现）
        String extension = getFileExtension(thumbnail);
        //创建文件存储位置（函数实现）
        makeDirPath(targetAddr);
        //相对路径              以上的操作都是字符串操作，数据文件读入 在Thumbnails中
        String relativeAddr = targetAddr+realFileName+extension;
        //File对象，文件和路径名的抽象表示形式
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        try{
            //创建带水印的缩略图，并存储到商户命名的文件夹下
            Thumbnails.of(thumbnail.getInputStream()).size(200,200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath+"/Start.png")),1.0f) .outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        //注意注意这个是自己加的
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/zhang/xxx.jpg,
     * 那么home work zhang这三个文件夹都得自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    private static String getReandomFileName() {
        //获取随机的五位数
        int rannum = r.nextInt(89999)+10000;
        //根据系统时间转换为字符串
        String nowTimeStr = simpleDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * @param file
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile file){
        //获取文件名
        String originalFileName = file.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
        if (fileOrPath.exists()){
            if (fileOrPath.isDirectory()){
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i <files.length ; i++) {
                    files[i].delete();
                }
            }else {
                fileOrPath.delete();
            }
        }
    }
//    /**
//     * 加水印测试
//     * @param args
//     * @throws IOException
//     */
//    public static void main(String[] args) throws IOException {
//        //调整水印大小
//        //Thumbnails.of("F:\\javaStudy2\\o2o\\src\\main\\resources\\Start2.png").size(40,40).toFile("F:\\javaStudy2\\o2o\\src\\main\\resources\\Start.png");
//        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        //缩略图+水印
//        Thumbnails.of(new File("F:\\javaStudy2\\o2o\\src\\main\\resources\\zhang.jpg"))
//                .size(200,200).watermark(Positions.BOTTOM_RIGHT,
//                ImageIO.read(new File(basePath+"/Start.png")),1.0f)
//                .outputQuality(0.8f).toFile("F:\\javaStudy2\\o2o\\src\\main\\resources\\zhangnew.jpg");
//    }
}
