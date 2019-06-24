package bwie.com.weidumall.entity.home;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/20 20:30
 * Description:
 */
public class HomeBanner {

    private String imageUrl;
    private String jumpUrl;
    private int rank;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
