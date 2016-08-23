package com.chengtao.framework.leancloud.response;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;
import com.chengtao.framework.leancloud.LeanCloudHandler;
import com.chengtao.framework.leancloud.request.IRequest;
import com.chengtao.framework.leancloud.utils.LeanCloudUtils;

/**
 * Created by Lenovo on 2016/5/21.
 * <p/>
 * LeanCloudResponse类，用于处理请求成功和失败，
 * 并将消息发送给LeanCloudHandler，
 * 将结果交给LeanCloudHandler处理
 *
 * @author ChengTao
 */
public class LeanCloudSaveResponse extends SaveCallback {
    private final static String TAG = "LeanCloudSaveResponse";
    private IRequest request;//请求
    private LeanCloudHandler handler;//自定义的消息处理类

    /**
     * 初始化request和handler
     * @param request 请求
     * @param handler 自定义的消息处理类
     */
    public LeanCloudSaveResponse(IRequest request, LeanCloudHandler handler) {
        this.request = request;
        this.handler = handler;
    }

    /**
     * 继承DeleteCallback所要完成的方法
     * @param e AVException异常
     */
    @Override
    public void done(AVException e) {
        if (e==null){
            LeanCloudUtils.printLog("--------LeanCloudSaveResponse----------sendSuccessMessage---------");
            handler.sendSuccessMessage(request.getRequestId(),null);
        }else {
            LeanCloudUtils.printLog("--------LeanCloudSaveResponse----------sendFailureMessage---------");
            handler.sendFailureMessage(request.getRequestId(),e);
        }
    }
}
