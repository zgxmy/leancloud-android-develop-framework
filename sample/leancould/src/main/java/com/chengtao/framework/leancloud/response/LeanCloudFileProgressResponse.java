package com.chengtao.framework.leancloud.response;

import com.avos.avoscloud.ProgressCallback;
import com.chengtao.framework.leancloud.LeanCloudHandler;
import com.chengtao.framework.leancloud.request.IRequest;

/**
 * Created by ChengTao on 2016/5/26.
 *
 * LeanCloudFileProgressResponse类，用于处理请求成功和失败，
 * 并将消息发送给LeanCloudHandler，
 * 将结果交给LeanCloudHandler处理
 *
 * @author ChengTao
 */
public class LeanCloudFileProgressResponse extends ProgressCallback{
    private final static String TAG = "LeanCloudFileProgressResponse";
    private IRequest request;
    private LeanCloudHandler handler;
    private int position;

    /**
     * 初始化request和handler
     * @param request 请求
     * @param handler 自定义的消息处理类
     */
    public LeanCloudFileProgressResponse(IRequest request, LeanCloudHandler handler,int position) {
        this.request = request;
        this.handler = handler;
        this.position = position;
    }

    /**
     * 继承ProgressCallback所要完成的方法
     * @param integer 上传进度
     */
    @Override
    public void done(Integer integer) {
        handler.sendFileProgressMessage(request.getRequestId(),integer,position);
    }
}
