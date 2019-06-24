package bwie.com.weidumall.presenter.home;

import bwie.com.weidumall.api.HomeApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.HomeModel;
import io.reactivex.Observable;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/23 14:15
 * Description:
 */
public class SearchPresenter extends HomeModel {

    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public SearchPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(HomeApi homeApi, Object... args) {
        boolean flag = (boolean) args[0];
        if (flag) {
            page = 1;
        } else {
            page++;
        }
        return homeApi.findGoodsByKeyword(
                String.valueOf(args[1]),
                String.valueOf(page),
                String.valueOf(args[2]));

    }
}
