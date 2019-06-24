package bwie.com.weidumall.presenter.user;

import java.io.File;

import bwie.com.weidumall.api.UserApi;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.model.UserModel;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class UpIconPresenter extends UserModel {

    public UpIconPresenter(ImplView implView) {
        super(implView);
    }

    @Override
    protected Observable getModel(UserApi userApi, Object... args) {
        MultipartBody.Builder builder = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM);
        File file = new File((String) args[0]);
        builder.addFormDataPart("image", file.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"),  file));
        return userApi.upIncon(builder.build());
    }
}
