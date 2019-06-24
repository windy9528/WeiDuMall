package bwie.com.weidumall.presenter.circle;


import bwie.com.weidumall.api.CircleApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CircleModel;
import io.reactivex.Observable;

/**
 * @author 杨高峰 取消点赞功能
 * @date 2019 2019/05/23 20:05
 */
public class UnLikePresenter extends CircleModel {

    public UnLikePresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CircleApi circleApi, Object... args) {
        return circleApi.unLike(String.valueOf(args[0]));
    }
}
