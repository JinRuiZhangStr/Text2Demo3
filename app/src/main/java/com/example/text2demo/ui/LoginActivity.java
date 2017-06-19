package com.example.text2demo.ui;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.text2demo.R;
import com.example.text2demo.modle.User;
import com.example.text2demo.presenter.BaseActivity;
import com.example.text2demo.presenter.UserLoginPresenter;
import com.example.text2demo.view.login.IUserLoginView;

public class LoginActivity extends BaseActivity implements IUserLoginView {


    private EditText edt_nmb;
    private EditText edt_psd;
    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);
    private boolean flag = true;

    @Override
    public void initData() {
        for (int i = 1; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        login();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        edt_nmb = (EditText) findViewById(R.id.edt_nmb);
        edt_psd = (EditText) findViewById(R.id.edt_psd);

    }

    @Override
    public String getUserName() {
        return edt_nmb.getText().toString();
    }

    @Override
    public String getPassword() {
        return edt_psd.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(User user) {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void showFailedError() {
        if (!edt_nmb.getText().toString().equals("")&&!edt_psd.getText().toString().equals("")){
            startActivity(new Intent(this,HomeActivity.class));
        }else {
            Toast.makeText(this,"账户或密码不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    private void login() {
        userLoginPresenter.login();
    }
}
