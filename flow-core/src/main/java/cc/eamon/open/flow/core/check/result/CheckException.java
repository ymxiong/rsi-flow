package cc.eamon.open.flow.core.check.result;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 这个类用于存储异常信息，比如入参不存在，或类型不匹配等一定会引起错误的Exception
 */
public class CheckException {
    //错误位置
    private String exceptionItem;

    //错误原因
    private String exceptionResource;

    public CheckException(String exceptionItem, String exceptionResource) {
        this.exceptionItem = exceptionItem;
        this.exceptionResource = exceptionResource;
    }

    public String getExceptionItem() {
        return exceptionItem;
    }

    public void setExceptionItem(String exceptionItem) {
        this.exceptionItem = exceptionItem;
    }

    public String getExceptionResource() {
        return exceptionResource;
    }

    public void setExceptionResource(String exceptionResource) {
        this.exceptionResource = exceptionResource;
    }
}
