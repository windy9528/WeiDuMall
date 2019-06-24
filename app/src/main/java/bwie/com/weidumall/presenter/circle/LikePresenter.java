package bwie.com.weidumall.presenter.circle;

import bwie.com.weidumall.api.CircleApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CircleModel;
import io.reactivex.Observable;

/**
 * @author 杨高峰  点赞功能
 * @date 2019 2019/05/23 20:05
 */
public class LikePresenter extends CircleModel {

    public LikePresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CircleApi circleApi, Object... args) {
        return circleApi.like(String.valueOf(args[0]));
    }
}
