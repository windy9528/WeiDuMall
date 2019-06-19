package bwie.com.weidumall.common;

import com.blankj.utilcode.util.EncryptUtils;

/**
 * date:2019/6/18
 * name:windy
 * function:
 * 静态常量 ：  请求链接
 */
public class Constant {

    public static boolean isRelease = false; //测试环境&正式环境

    //测试版url
    public static final String TEST_URL = "http://172.17.8.100/small/";
    //正式版url
    public static final String OFFICIAL_URL = "http://mobile.bwstudent.com/small/";
    //当前url
    public static final String BASE_URL = isRelease ? TEST_URL : OFFICIAL_URL;


    //登录url
    public static final String LOGIN_URL = "user/v1/login";
    //注册url
    public static final String REGISTER_URL = "user/v1/register";
    //首页轮播图
    public static final String HOME_BANNER_URL = "commodity/v1/bannerShow";
    //首页数据(多条目 热销新品、魔力时尚、品质生活)
    public static final String HOME_DATA_URL = "commodity/v1/commodityList";
    //商品详情页
    public static final String DETAIL_GOOD_URL = "commodity/v1/findCommodityDetailsById";
    //根据关键字查找
    public static final String SEARCH_GOOD_URL = "commodity/v1/findCommodityByKeyword";
    //同步购物车
    public static final String SYNC_CART_URL = "order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String QUERY_CART_URL = "order/verify/v1/findShoppingCart";
    //展示圈子
    public static final String SHOW_CIRCLE_URL = "circle/v1/findCircleList";
    //圈子点赞
    public static final String GREAT_CIRCLE_URL = "circle/verify/v1/addCircleGreat";
    //取消点赞
    public static final String UNGREAT_CIRCLE_URL = "circle/verify/v1/cancelCircleGreat";
    //根据订单状态查询订单信息
    public static final String STATUS_ORDER_URL = "order/verify/v1/findOrderListByStatus";
    //发表圈子
    public static final String PUBLISH_CIRCLE_URL = "circle/verify/v1/releaseCircle";
    //创建订单
    public static final String CREATE_ORDER_URL = "order/verify/v1/createOrder";
    //展示收获地址列表
    public static final String SHOW_ADDRESS_URL = "user/verify/v1/receiveAddressList";
    //删除订单功能
    public static final String DELETE_ORDER_URL = "order/verify/v1/deleteOrder";

}
