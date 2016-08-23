package com.chengtao.framework.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.chengtao.framework.leancloud.LeanCloudClient;
import com.chengtao.framework.leancloud.LeanCloudListener;
import com.chengtao.framework.leancloud.request.IRequest;
import com.chengtao.framework.leancloud.utils.LeanCloudUtils;

import java.util.List;

/**
 * Created by Lenovo on 2016/5/21.
 *
 * @author ChengTao
 */
@SuppressWarnings("ALL")
public abstract class BaseActivity extends Activity implements LeanCloudListener {
    private final static String TAG = "BaseActivity";
    /**
     * 主线程handler
     */
    protected Handler mHandler;
    /**
     * 本页面上下文
     */
    protected Context mContext;
    /**
     * LeanCloudClient的实体，用于进行增删改查的操作
     */
    protected LeanCloudClient mClient = new LeanCloudClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        mContext = this;
        mHandler = new Handler();
        initView();
        setListener();
        initData();
    }

    /**
     * 返回当前布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取View,省去findViewById
     */
    public <T extends View> T obtainView(int id) {
        return (T) findViewById(id);
    }


    /**
     * 向leanColud发出请求
     *
     * @param request
     */
    public void leanColud(IRequest request) {
        mClient.chooseHandleWay(request);
    }

    /**
     * 默认的请求失败处理
     *
     * @param requestId 请求Id
     * @param e         异常
     */
    @Override
    public void onFailure(int requestId, AVException e) {
        Log.e(TAG, "onFailure");
        LeanCloudUtils.printLog("-------onFailure---------"+e.getMessage());
        showToast(e.getMessage());
    }

    /**
     * 默认的请求开始处理
     *
     * @param requestId 请求Id
     */
    @Override
    public void onStart(int requestId) {
        Log.v(TAG, "onStart");
    }

    /**
     * 默认的请求成功处理
     *
     * @param requestId 请求Id
     * @param list      返回的数据集合
     */

    @Override
    public void onSuccess(int requestId, List<AVObject> list) {
        Log.e(TAG, "onSuccess");
    }

    /**
     * @param requestId
     * @param position
     */
    @Override
    public void onFileSuccess(int requestId, int position) {
        Log.e(TAG, "onFileSuccess");
    }

    /**
     * @param requestId
     * @param e
     * @param position
     */
    @Override
    public void onFileFailure(int requestId, AVException e, int position) {
        Log.e(TAG, "onFileFailure");
    }

    /**
     * 默认文件上传进度处理
     *
     * @param requestId 请求Id
     * @param integer   文件上传进度值
     */
    @Override
    public void onFileProgress(int requestId, Integer integer, int position) {
        Log.e(TAG, "Progress Num------" + integer);
    }

    /**
     * 弹出吐司
     *
     * @param s 所要展示的字符串
     */
    protected void showToast(String s) {
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

}
