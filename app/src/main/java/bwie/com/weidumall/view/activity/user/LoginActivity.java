package bwie.com.weidumall.view.activity.user;


import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.constract.ImplView;
import bwie.com.weidumall.entity.Result;
import bwie.com.weidumall.entity.user.UserInfo;
import bwie.com.weidumall.presenter.user.LoginPresenter;
import bwie.com.weidumall.util.SPUtil;

public class LoginActivity extends BaseActivity implements ImplView<UserInfo> {

    @BindView(R.id.phone_icon)
    ImageView phoneIcon;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.phone_layout)
    RelativeLayout phoneLayout;
    @BindView(R.id.phone_line)
    View phoneLine;
    @BindView(R.id.pwd_icon)
    ImageView pwdIcon;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.pwd_layout)
    RelativeLayout pwdLayout;
    @BindView(R.id.pwd_line)
    View pwdLine;
    @BindView(R.id.remember_pwd)
    CheckBox rememberPwd;
    @BindView(R.id.qucik_register)
    TextView qucikRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;
    private SharedPreferences loginSp;
    private String phone;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter(this);
    }

    @OnClick({R.id.btn_login, R.id.qucik_register})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.qucik_register:
                intent(RegisterActivity.class);
                break;
        }
    }

    /**
     * 用户登录相关的操作
     */
    private void login() {
        phone = loginPhone.getText().toString().trim();
        password = loginPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //登录请求
            loginPresenter.requestData(phone, password);
        }
    }

    /**
     * 初始化数据
     *
     * @return 回显手机号、密码等
     */
    @Override
    protected void initData() {
        loginSp = getSharedPreferences("loginStatus", MODE_PRIVATE);
        //显示用户此前录入的数据
        boolean flag = loginSp.getBoolean("flag", false);
        //回显复选框状态
        rememberPwd.setChecked(flag);
        if (flag) {
            phone = loginSp.getString("phone", null);
            password = loginSp.getString("password", null);
            //回显 手机号和密码
            loginPhone.setText(phone);
            loginPwd.setText(password);
        }
    }

    /**
     * 请求成功的回调
     *
     * @param data
     * @param args
     */
    @Override
    public void success(Result data, Object... args) {
        UserInfo userInfo = (UserInfo) data.getResult();
        SPUtil.saveLoginStatus(this, phone, password, rememberPwd, true);
        SPUtil.saveUserInfo(this, userInfo.getUserId(), userInfo.getSessionId());
        showToast("登录成功");
    }

    /**
     * 请求失败的回调
     *
     * @param result
     * @param args
     */
    @Override
    public void fail(Result result, Object... args) {
        loginSp.edit().clear().commit();//如果登录失败，则清除缓存
        showToast(result.getMessage());
    }

    @Override
    protected void destoryData() {
        if (loginPresenter != null) {
            loginPresenter.unBind();
            loginPresenter = null;
        }
    }
}
