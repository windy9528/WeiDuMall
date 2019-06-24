package bwie.com.weidumall.presenter.circle;

import java.io.File;
import java.util.List;

import bwie.com.weidumall.api.CircleApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.CircleModel;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublishCirclePresenter extends CircleModel {

    public PublishCirclePresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(CircleApi circleApi, Object... args) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("content", (String) args[1]);
        builder.addFormDataPart("commodityId", "1");
        List<Object> list = (List<Object>) args[2];
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/octet-stream"),
                                file));
            }
        }
        return circleApi.publishCircle(builder.build());
    }
}
