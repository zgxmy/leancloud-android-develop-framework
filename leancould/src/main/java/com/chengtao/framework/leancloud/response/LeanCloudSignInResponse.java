package com.chengtao.framework.leancloud.response;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.chengtao.framework.leancloud.LeanCloudHandler;
import com.chengtao.framework.leancloud.request.IRequest;
import com.chengtao.framework.leancloud.utils.LeanCloudUtils;

/**
 * Created by ChengTao on 2016-07-21.
 */
@SuppressWarnings("ALL")
public class LeanCloudSignInResponse extends SignUpCallback{
    private final static String TAG = "LeanCloudSignInResponse";
    private IRequest request;//请求
    private LeanCloudHandler handler;//自定义的消息处理类

    public LeanCloudSignInResponse(IRequest request, LeanCloudHandler handler) {
        this.request = request;
        this.handler = handler;
    }

    @Override
    public void done(AVException e) {
        if (e == null){
            handler.sendSuccessMessage(request.getRequestId(),null);
        }else {
            LeanCloudUtils.printLog("---LeanCloudSignInResponse-------------------"+e.toString());
            handler.sendFailureMessage(request.getRequestId(),e);
        }
    }
}
