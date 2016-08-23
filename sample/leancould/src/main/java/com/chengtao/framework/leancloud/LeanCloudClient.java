package com.chengtao.framework.leancloud;

import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVACL;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.chengtao.framework.leancloud.request.IRequest;
import com.chengtao.framework.leancloud.response.LeanCloudDeleteResponse;
import com.chengtao.framework.leancloud.response.LeanCloudFileProgressResponse;
import com.chengtao.framework.leancloud.response.LeanCloudFileResponse;
import com.chengtao.framework.leancloud.response.LeanCloudLoginResponse;
import com.chengtao.framework.leancloud.response.LeanCloudQueryResponse;
import com.chengtao.framework.leancloud.response.LeanCloudSaveResponse;
import com.chengtao.framework.leancloud.response.LeanCloudSignInResponse;
import com.chengtao.framework.leancloud.utils.LeanCloudUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by ChengTao on 2016/5/25.
 * <p/>
 * 基于LeanCloud数据库，用于处理页面的各种请求类
 *
 * @author ChengTao
 */
@SuppressWarnings("ALL")
public class LeanCloudClient {
    private final static String TAG = "LeanCloudClient";
    /**
     * 自定义的用于向主线程发送消息的handler
     */
    private LeanCloudHandler handler;
    /**
     * 用于查找的LeanCloud类
     */
    private AVQuery<AVObject> mAVQuery;
    /**
     * 查找限制数目
     */
    private int defaultLimit = 20;

    /**
     * 构造函数，初始化LeanCloudHandler(用于发送消息)和ILeanCloudListener(数据接口，用于activity页面做相应的处理)
     *
     * @param listener 数据接口
     */
    public LeanCloudClient(LeanCloudListener listener) {
        this.handler = new LeanCloudHandler(listener);
    }

    /**
     * 向LeanCloud数据库批量插入数据
     *
     * @param request 请求
     */
    public void leanCloudInsert(IRequest request) {
        if (request != null) {
            LeanCloudUtils.printLog("----------leanCloudInsert------sendStartMessage-----");
            handler.sendStartMessage(request.getRequestId());
            if (request.getCurrentUser() != null){
                for (AVObject object:request.getmObjectListParams()){
                    AVACL acl = new AVACL();
                    acl.setPublicReadAccess(true);
                    acl.setWriteAccess(request.getCurrentUser(),true);
                    object.setACL(acl);
                }
            }
            LeanCloudUtils.printLog("----------leanCloudInsert------getCurrentUser()-----"+request.getCurrentUser());
            AVObject.saveAllInBackground(request.getmObjectListParams(), new LeanCloudSaveResponse(request, handler));
        }
    }

    /**
     * 批量删除LeanCloud数据库中的数据
     *
     * @param request 请求
     */
    public void leanCloudDelete(IRequest request) {
        if (request != null) {
            LeanCloudUtils.printLog("--------leanCloudDelete----------sendStartMessage---------");
            handler.sendStartMessage(request.getRequestId());
            AVObject.deleteAllInBackground(request.getmObjectListParams(), new LeanCloudDeleteResponse(request, handler));
        }
    }

    /**
     * 更新LeanCloud数据库的数据
     *
     * @param request 请求
     */
    public void leanCloudUpdate(IRequest request) {
        if (request != null) {
            LeanCloudUtils.printLog("--------leanCloudUpdate----------sendStartMessage---------");
            handler.sendStartMessage(request.getRequestId());
            request.getmObjectParams().saveInBackground(new LeanCloudSaveResponse(request, handler));
        }
    }

    /**
     * 从LeanCloud数据库查找数据
     *
     * @param request 请求
     */
    public void leanCloudQuery(IRequest request) {
        if (request != null) {
            LeanCloudUtils.printLog("--------leanCloudQuery----------sendStartMessage---------");
            handler.sendStartMessage(request.getRequestId());
            mAVQuery = new AVQuery<>(request.getClassName());
            Log.e("TAG","findInBackground");

            InitAVQuery(mAVQuery,request);
            mAVQuery.findInBackground(new LeanCloudQueryResponse(request, handler));
        }else {
            LeanCloudUtils.printLog("--------leanCloudQuery----------sendFailureMessage---------");
            handler.sendFailureMessage(request.getRequestId(),null);
        }
    }

    /**
     * 初始化AVQuery
     * @param mAVQuery
     * @param request
     */
    private void InitAVQuery(AVQuery<AVObject> mAVQuery, IRequest request) {
        //获取请求参数
        List<? extends AVObject> lists = (List<? extends AVObject>) request.getmSimpleParams().get("lists");
        boolean refresh = request.getmSimpleParams().get("refresh") == null ? true : (boolean) request.getmSimpleParams().get("refresh");
        String[] ascend = (String[]) request.getmSimpleParams().get("ascend");
        String[] descend = (String[]) request.getmSimpleParams().get("descend");
        int limit = request.getmSimpleParams().get("limit") == null ? 0 : (int) request.getmSimpleParams().get("limit");
        Map<String,Object> great = (Map<String, Object>) request.getmSimpleParams().get("great");
        Map<String,Object> less = (Map<String, Object>) request.getmSimpleParams().get("less");
        Map<String,Object> equal = (Map<String, Object>) request.getmSimpleParams().get("equal");
        Map<String,String> contains = (Map<String, String>) request.getmSimpleParams().get("contains");
        //设置是否刷新
        if (lists != null && lists.size() > 0){
            if (refresh){
                mAVQuery.whereGreaterThan("updatedAt",lists.get(0).getUpdatedAt());
            }else {
                mAVQuery.whereLessThan("updatedAt",lists.get(0).getUpdatedAt());
            }
        }
        //设置升序
        if (ascend != null && ascend.length > 0){
            for (String s : ascend){
                mAVQuery.orderByAscending(s);
            }
        }
        //设置降序
        if (descend != null && descend.length > 0){
            for (String s : descend){
                mAVQuery.orderByDescending(s);
            }
        }
        //设置限制个数
        if (limit == 0){
            mAVQuery.limit(defaultLimit);
        }else if (limit != -1){
            mAVQuery.limit(limit);
        }
        //设置大于
        if (great != null && great.size() > 0){
            for (String key : great.keySet()){
                mAVQuery.whereGreaterThan(key,great.get(key));
            }
        }
        //设置小于
        if (less != null && less.size() > 0){
            for (String key : less.keySet()){
                mAVQuery.whereLessThan(key,great.get(key));
            }
        }
        //设置等于
        if (equal != null && equal.size() > 0){
            for (String key : equal.keySet()){
                mAVQuery.whereEqualTo(key,great.get(key));
            }
        }
        //设置是否有包含的字符串
        if (contains != null && contains.size() > 0){
            for (String key : contains.keySet()){
                mAVQuery.whereContains(key,contains.get(key));
            }
        }
        //整体排序方式
        if (!ascend.toString().contains("createdAt") && !descend.toString().contains("createdAt")){
            mAVQuery.orderByDescending("createdAt");
        }
    }


    /**
     * 插入有图片的对象
     *
     * @param request 请求
     */
    @SuppressWarnings("unchecked")
    public void leanCloudInsertWithFile(IRequest request) {
        if (request != null) {
            LeanCloudUtils.printLog("--------leanCloudInsertWithFile----------sendStartMessage---------");
            handler.sendStartMessage(request.getRequestId());
            List<AVFile> lists = (List<AVFile>) request.getmSimpleParams().get("file");
            if (lists != null && lists.size() > 0){
                int position = 0;
                for (AVFile file:lists){
                    lists.get(position).saveInBackground(new LeanCloudFileResponse(request, handler,position,lists.size()),
                            new LeanCloudFileProgressResponse(request, handler,position));
                    position++;
                }
            }else {
                LeanCloudUtils.printLog("--------leanCloudInsertWithFile----------sendFileFailureMessage---------");
                handler.sendFileFailureMessage(request.getRequestId(),null,-1);
            }
        }
    }

    /**
     * 登录
     * @param request
     */
    public void leanCloudLogin(IRequest request){
        String userName = (String) request.getmSimpleParams().get("userName");
        String password = (String) request.getmSimpleParams().get("password");
        handler.sendStartMessage(request.getRequestId());
        if (LeanCloudUtils.isStrNotNull(userName) && LeanCloudUtils.isStrNotNull(password)){
            AVUser.logInInBackground(userName,password,new LeanCloudLoginResponse(request,handler));
        }else {
            Toast.makeText(request.getmContext(),"请输入用户名或密码",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 登录
     * @param request
     */
    public void leanCloudSignIn(IRequest request){
        String userName = (String) request.getmSimpleParams().get("userName");
        String password = (String) request.getmSimpleParams().get("password");
        handler.sendStartMessage(request.getRequestId());
        if (LeanCloudUtils.isStrNotNull(userName) && LeanCloudUtils.isStrNotNull(password)){
            AVUser user = new AVUser();// 新建 AVUser 对象实例
            user.setUsername(userName);// 设置用户名
            user.setPassword(password);// 设置密码
            user.signUpInBackground(new LeanCloudSignInResponse(request,handler));
        }else {
            Toast.makeText(request.getmContext(),"请输入用户名或密码",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 选择处理方法
     *
     * @param request
     */
    public void chooseHandleWay(IRequest request) {
        switch (request.getRequestWay()) {
            case IRequest.INSERT://增加
                leanCloudInsert(request);
                break;
            case IRequest.DELETE://删除
                leanCloudDelete(request);
                break;
            case IRequest.UPDATE://修改
                leanCloudUpdate(request);
                break;
            case IRequest.QUERY://查找
                leanCloudQuery(request);
                break;
            case IRequest.FILE://文件
                leanCloudInsertWithFile(request);
                break;
            case IRequest.LOGIN://登录
                leanCloudLogin(request);
                break;
            case IRequest.SIGNIN://注册
                leanCloudSignIn(request);
                break;
            default:
                break;
        }
    }

}
