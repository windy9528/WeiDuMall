package bwie.com.weidumall.presenter.home;


import bwie.com.weidumall.api.HomeApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.HomeModel;
import io.reactivex.Observable;

/**
 * Author: 杨高峰(windy)
 * Description:
 */
public class DetailPresenter extends HomeModel {


    public DetailPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(HomeApi homeApi, Object... args) {
        return homeApi.findDetailsById((String) args[0]);
    }
}
