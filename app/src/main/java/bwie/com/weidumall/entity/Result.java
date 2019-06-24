package bwie.com.weidumall.entity;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class Result<T> {

    private String message = "请求失败!";
    private String status = "-1";
    private T result;
    private String headPath;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
