package bwie.com.weidumall.presenter.circle;


import bwie.com.weidumall.api.CircleApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CircleModel;
import io.reactivex.Observable;

/**
 * @date 2019 2019/05/23 15:52
 */

public class CirclePresenter extends CircleModel {

    private int page = 1;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public CirclePresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CircleApi circleApi, Object... args) {
        if ((boolean) args[0]) {
            page = 1;
        } else {
            page++;
        }

        return circleApi.showCircle(
                String.valueOf(page),
                String.valueOf(args[1])
        );

    }
}
