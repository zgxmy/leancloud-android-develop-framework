package com.chengtao.framework.leancloud.request;

import android.content.Context;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChengTao on 2016-07-11.
 */
@SuppressWarnings("ALL")
public abstract class IRequest {
    private final static String TAG ="IRequest";
    //上下文
    protected Context mContext;
    //请求ID
    private int requestId;
    //请求参数(对象集合)
    protected ArrayList<AVObject> mObjectListParams;
    //请求参数(非对象集合)
    protected Map<String, Object> mSimpleParams;
    //请求参数（单个对象）
    protected AVObject mObjectParams;
    //
    private ArrayList<String> fileUrls;
    //当前用户
    private AVUser currentUser;

    //请求方法---------------------------------------------
    /**
     * 插入
     */
    public final static int INSERT = 1;
    /**
     * 删除
     */
    public final static int DELETE = 2;
    /**
     * 更新
     */
    public final static int UPDATE = 3;
    /**
     * 查找
     */
    public final static int QUERY = 4;
    /**
     * 文集
     */
    public final static int FILE = 5;
    /**
     * 登录
     */
    public final static int LOGIN = 6;
    /**
     * 注册
     */
    public final static int SIGNIN = 7;

    //请求方法---------------------------------------------

    public IRequest(Context mContext) {
        this.mContext = mContext;
        mObjectListParams = new ArrayList<>();
        mSimpleParams = new HashMap<>();
        fileUrls = new ArrayList<>();
        currentUser = getCurrentUser();
    }

    /**
     * 设置请求ID
     *
     * @param requestId
     */
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    /**
     * 获取请求ID
     *
     * @return
     */
    public int getRequestId() {
        return requestId;
    }

    /**
     * 获取请求参数(对象集合)
     *
     * @return
     */
    public ArrayList<AVObject> getmObjectListParams() {
        return mObjectListParams;
    }

    /**
     * 获取请求参数(非对象集合)
     *
     * @return
     */
    public Map<String, Object> getmSimpleParams() {
        return mSimpleParams;
    }

    /**
     * 获取请求参数（单个对象）
     *
     * @return
     */
    public AVObject getmObjectParams() {
        return mObjectParams;
    }

    /**
     * 获取文件url集合
     *
     * @return
     */
    public ArrayList<String> getFileUrls() {
        return fileUrls;
    }

    /**
     * 添加文件url
     *
     * @param fileUrl
     */
    public void addFileUrl(String fileUrl) {
        fileUrls.add(fileUrl);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public Context getmContext() {
        return mContext;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public AVUser getCurrentUser() {
        return currentUser;
    }

    /**
     * 获取LeanClud表名
     *
     * @return
     */
    public abstract String getClassName();

    /**
     * 获取操作方法（增、删、改、查）
     *
     * @return
     */
    public abstract int getRequestWay();
}
