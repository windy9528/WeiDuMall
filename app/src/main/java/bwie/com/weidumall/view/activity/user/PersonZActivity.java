package bwie.com.weidumall.view.activity.user;


import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.common.App;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.UserData;
import bwie.com.weidumall.entity.user.UserInfo;
import bwie.com.weidumall.green.DaoMaster;
import bwie.com.weidumall.presenter.user.SearchUserPresenter;
import bwie.com.weidumall.presenter.user.UpIconPresenter;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/5/22 17:05
 * Description:把修改的照片和昵称存入数据库
 */
public class PersonZActivity extends BaseActivity implements ImplView<UserData> {

    @BindView(R.id.name)
    TextView userName;
    @BindView(R.id.img_icon)
    SimpleDraweeView imgIcon;
    private List<UserInfo> userInfos;
    private SearchUserPresenter searchUserPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personz;
    }

    @Override
    protected void initView() {
        //查询用户信息
        searchUserPresenter = new SearchUserPresenter(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
    }

    @Override
    protected void initData() {
        searchUserPresenter.requestData();  //根据id查询用户
    }


    /**
     * 查询用户资料成功的回调
     *
     * @param data
     * @param args
     */
    @Override
    public void success(Result data, Object... args) {
        UserData userData = (UserData) data.getResult();
        //设置头像
        imgIcon.setImageURI(userData.getHeadPic());
        userName.setText(userData.getNickName());
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

    /**
     * 点击头像进入相册 更改头像
     */
    @OnClick(R.id.img_icon)
    public void changIcon() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        BaseActivity
                .getForegroundActivity()
                .startActivityForResult(intent, BaseActivity.PHOTO);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null, requestCode, data);
            if (!StringUtils.isEmpty(filePath)) {
                //调用上传头像
                new UpIconPresenter(new ImplView() {
                    @Override
                    public void success(Result data, Object... args) {
                        UserData userData = (UserData) data.getResult();
                        imgIcon.setImageURI(Uri.parse(userData.getHeadPic()));
                    }

                    @Override
                    public void fail(Result result, Object... args) {
                        showToast(result.getMessage());
                    }
                }).requestData(filePath);
            }
        }
    }

    @OnClick(R.id.btn_quit)
    public void quitLogin() {
        //App.getLoginStatus().edit().clear().commit(); //清除“登录状态”
        intent(LoginActivity.class);
        finish();
    }

    @Override
    protected void destoryData() {

    }

}
