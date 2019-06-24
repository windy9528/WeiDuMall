package bwie.com.weidumall.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/20 9:41
 * Description: xbanner  返回一个ImageBean对象
 */
public class ImageBean extends SimpleBannerInfo {

    public String imgUrl;

    @Override
    public Object getXBannerUrl() {
        return imgUrl;
    }
}
