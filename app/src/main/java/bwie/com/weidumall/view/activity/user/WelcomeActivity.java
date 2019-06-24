package bwie.com.weidumall.view.activity.user;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.com.weidumall.R;
import bwie.com.weidumall.common.App;
import bwie.com.weidumall.common.BaseActivity;
import bwie.com.weidumall.view.MainActivity;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.seek_text)
    TextView seekText;

    private int count = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            count--;
            seekText.setText("跳过" + count + "s");
            if (count == 0) {
                if (App.getLoginStatus().getBoolean("loginStatus", false)) {
                    intent(MainActivity.class);//跳转到主页面
                } else {
                    intent(LoginActivity.class);//跳转到登录页
                }
                finish();
            } else {//消息不能复用，必须新建
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void initView() {
        seekText.setText("跳过" + count + "s");
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.seek_text)
    public void seek() {
        handler.removeMessages(1);
        if (App.getLoginStatus().getBoolean("loginStatus", false)) {
            intent(MainActivity.class);
        } else {
            intent(LoginActivity.class);
        }
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void destoryData() {

    }
}
