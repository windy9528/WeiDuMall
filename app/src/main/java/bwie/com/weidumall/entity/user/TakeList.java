package bwie.com.weidumall.entity.user;

/**
 * date:2019/6/30
 * name:windy
 * function:收货地址列表
 */
public class TakeList {

    private String address;
    private String createTime;
    private int id;
    private String phone;
    private String realName;
    private int userId;
    public int whetherDefault;
    private String zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWhetherDefault() {
        return whetherDefault;
    }

    public void setWhetherDefault(int whetherDefault) {
        this.whetherDefault = whetherDefault;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
