package com.chengtao.framework.leancloud.response;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.chengtao.framework.leancloud.LeanCloudHandler;
import com.chengtao.framework.leancloud.request.IRequest;

/**
 * Created by ChengTao on 2016-07-21.
 * @author Cheng Tao
 */
@SuppressWarnings("ALL")
public class LeanCloudLoginResponse extends LogInCallback<AVUser>{
    private final static String TAG = "LeanCloudLoginResponse";
    private IRequest request;//请求
    private LeanCloudHandler handler;//自定义的消息处理类

    public LeanCloudLoginResponse(IRequest request, LeanCloudHandler handler) {
        this.request = request;
        this.handler = handler;
    }

    @Override
    public void done(AVUser avUser, AVException e) {
        if(e == null){
            handler.sendSuccessMessage(request.getRequestId(),null);
        }else {
            handler.sendFailureMessage(request.getRequestId(),e);
        }
    }

}
