package club.bagedate.o2o.exceptions;

/**
 * 对RuntimeException做一层很小的封装
 * 使我们看到错误知道是哪个地方出的Exception
 */
public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg){
        super(msg);
    }
}
