package bwie.com.weidumall.entity.home;

import java.util.List;

/**
 * date:2019/6/20
 * name:windy
 * function:  首页商品分类管理
 */
public class HomeData {

    private int id;  //  1002:rxxp;1003:mlss;1004:pzsh
    private String name;
    private List<HomeGoods> commodityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HomeGoods> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<HomeGoods> commodityList) {
        this.commodityList = commodityList;
    }
}
