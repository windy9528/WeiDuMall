package bwie.com.weidumall.view.fragment.user;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import bwie.com.weidumall.R;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.common.BaseFragment;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.UserData;
import bwie.com.weidumall.entity.user.UserInfo;
import bwie.com.weidumall.green.DaoMaster;
import bwie.com.weidumall.presenter.user.SearchUserPresenter;
import bwie.com.weidumall.presenter.user.UpIconPresenter;
import bwie.com.weidumall.view.MainActivity;
import bwie.com.weidumall.view.activity.user.PersonDActivity;
import bwie.com.weidumall.view.activity.user.PersonZActivity;

/**
 * Author: 杨高峰(windy)
 * Date: 2019/6/20 21:19
 * Description:
 */
public class UserFragment extends BaseFragment implements ImplView, View.OnClickListener {

    @BindView(R.id.p_zl) //个人资料
            TextView p_zl;
    @BindView(R.id.p_qz) //我的圈子
            TextView p_qz;
    @BindView(R.id.p_zj) //我的足迹
            TextView p_zj;
    @BindView(R.id.p_qb) //我的钱包
            TextView p_qb;
    @BindView(R.id.p_dz) //我的收货地址
            TextView p_dz;
    @BindView(R.id.user_name) //我的名字
            TextView user_name;
    @BindView(R.id.user_icon) //我的头像
            SimpleDraweeView user_icon;
    private UpIconPresenter upIconPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {

        //个人资料
        //我的圈子
        //我的足迹
        //我的钱包
        //我的收货地址
        //我的名字
        //我的头像

        new SearchUserPresenter(new ImplView<UserData>() {
            @Override
            public void success(Result data, Object... args) {
                UserData userData = (UserData) data.getResult();
                //设置头像
                user_icon.setImageURI(userData.getHeadPic());
                user_name.setText(userData.getNickName());
            }

            @Override
            public void fail(Result result, Object... args) {
                showToast(result.getMessage());
            }
        }).requestData();
    }

    @Override
    protected void initData() {
        p_zl.setOnClickListener(this);
        p_qz.setOnClickListener(this);
        p_zj.setOnClickListener(this);
        p_qb.setOnClickListener(this);
        p_dz.setOnClickListener(this);
        user_name.setOnClickListener(this);
        user_icon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.p_zl:
                zl();//个人资料
                break;
            case R.id.p_qz:
                qz();//我的圈子
                break;
            case R.id.p_zj:
                zj();  //我的足迹
                break;
            case R.id.p_qb:
                qb(); //我的钱包
                break;
            case R.id.p_dz:
                dz();   //我的收货地址
                break;
            case R.id.user_name:
                name();    //我的名字
                break;
            case R.id.user_icon:
                icon(); //我的头像
                break;
        }
    }

    private void zl() {
        intent(PersonZActivity.class);
    }

    private void qz() {
        //直接去第二个fragment
        if (getContext() instanceof MainActivity) {
            // ((MainActivity) getContext()).jumpToQz();
        }
        Toast.makeText(getContext(), "我的圈子", Toast.LENGTH_SHORT).show();
    }

    private void zj() {
        Toast.makeText(getContext(), "我的足迹", Toast.LENGTH_SHORT).show();
    }

    private void qb() {
        Toast.makeText(getContext(), "我的钱包", Toast.LENGTH_SHORT).show();
    }

    private void dz() {
        intent(PersonDActivity.class);
    }

    private void name() {
        Toast.makeText(getContext(), "我的姓名", Toast.LENGTH_SHORT).show();
    }

    private void icon() {

    }

    @Override
    public void success(Result data, Object... args) {

        UserInfo userInfo = new UserInfo();

        String headPath = data.getHeadPath();

        userInfo.setHeadpic(headPath);

        user_icon.setImageURI(Uri.parse(headPath));
    }

    @Override
    public void fail(Result result, Object... args) {
        showToast(result.getMessage());
    }

    @Override
    protected void destoryData() {

    }
}
