package bwie.com.weidumall.entity.cart;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author 杨高峰
 * @date 2019 2019/05/23 10:07
 * 每次同步购物车所需的数据
 */
@Entity
public class SysnGoods {

    @Id
    public long commodityId;
    public int count;
    @Generated(hash = 1989275691)
    public SysnGoods(long commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }
    @Generated(hash = 76612041)
    public SysnGoods() {
    }
    public long getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
