package bwie.com.weidumall.presenter.home;

import bwie.com.weidumall.api.HomeApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.HomeModel;
import io.reactivex.Observable;

/**
 * date:2019/6/20
 * name:windy
 * function:
 */
public class HomeDataPresenter extends HomeModel {

    public HomeDataPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(HomeApi homeApi, Object... args) {
        return homeApi.showIndexData();
    }
}
