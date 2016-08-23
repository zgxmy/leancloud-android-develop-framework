package com.chengtao.sample;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.chengtao.framework.activity.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onStart(int requestId) {
        super.onStart(requestId);
    }

    @Override
    public void onSuccess(int requestId, List<AVObject> list) {
        super.onSuccess(requestId, list);
    }

    @Override
    public void onFailure(int requestId, AVException e) {
        super.onFailure(requestId, e);
    }

    @Override
    public void onFileProgress(int requestId, Integer integer, int position) {
        super.onFileProgress(requestId, integer, position);
    }

    @Override
    public void onFileSuccess(int requestId, int position) {
        super.onFileSuccess(requestId, position);
    }

    @Override
    public void onFileFailure(int requestId, AVException e, int position) {
        super.onFileFailure(requestId, e, position);
    }
}
