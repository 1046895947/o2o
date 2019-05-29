package club.bagedate.o2o.util;

public class PageCalculator {
    /**
     * 返回页码开始的位置
     * @param pageIndex 页码
     * @param pageSize 一页显示的条数
     * @return 返回页码开始的位置
     */
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
