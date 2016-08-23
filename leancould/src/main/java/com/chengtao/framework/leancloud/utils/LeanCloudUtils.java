package com.chengtao.framework.leancloud.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;

/**
 * Created by ChengTao on 2016/5/25.
 * <p>
 * LeanCloud中的表
 *
 * @author ChengTao
 */
@SuppressWarnings("ALL")
public class LeanCloudUtils {
    public static AVException getExceptionWithMessage(AVException e) {
        AVException exception = null;
        switch (e.getCode()) {
            case AVException.ACCOUNT_ALREADY_LINKED:
                exception = new AVException("账户已经被锁定", new Throwable());
                break;
            case AVException.CACHE_MISS:
                exception = new AVException("未从缓存中找到结果", new Throwable());
                break;
            case AVException.COMMAND_UNAVAILABLE:
                exception = new AVException("方法仅限于内部测试", new Throwable());
                break;
            case AVException.CONNECTION_FAILED:
                exception = new AVException("连接失败", new Throwable());
                break;
            case AVException.DUPLICATE_VALUE:
                exception = new AVException("所要的值已经被取走", new Throwable());
                break;
            case AVException.EMAIL_MISSING:
                exception = new AVException("邮箱不能为空", new Throwable());
                break;
            case AVException.EMAIL_NOT_FOUND:
                exception = new AVException("未找到所填写的邮箱", new Throwable());
                break;
            case AVException.EMAIL_TAKEN:
                exception = new AVException("邮箱已存在", new Throwable());
                break;
            case AVException.EXCEEDED_QUOTA:
                exception = new AVException("申请配额超出", new Throwable());
                break;
            case AVException.FILE_DELETE_ERROR:
                exception = new AVException("删除文件失败", new Throwable());
                break;
            case AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE:
                exception = new AVException("checkSum值不一致", new Throwable());
                break;
            case AVException.INCORRECT_TYPE:
                exception = new AVException("字段类型不匹配", new Throwable());
                break;
            case AVException.INVALID_ACL:
                exception = new AVException("您没有权限", new Throwable());
                break;
            case AVException.INTERNAL_SERVER_ERROR:
                exception = new AVException("服务异常", new Throwable());
                break;
            case AVException.INVALID_CHANNEL_NAME:
                exception = new AVException("不合法的频道名", new Throwable());
                break;
            case AVException.INVALID_CLASS_NAME:
                exception = new AVException("不合法的类名", new Throwable());
                break;
            case AVException.INVALID_EMAIL_ADDRESS:
                exception = new AVException("不合法的邮箱", new Throwable());
                break;
            case AVException.INVALID_FILE_NAME:
                exception = new AVException("不合法的文件名", new Throwable());
                break;
            case AVException.INVALID_FILE_URL:
                exception = new AVException("不合法的文件地址", new Throwable());
                break;
            case AVException.INVALID_JSON:
                exception = new AVException("不合法的Json字符串", new Throwable());
                break;
            case AVException.INVALID_KEY_NAME:
                exception = new AVException("不合法的key值", new Throwable());
                break;
            case AVException.INVALID_LINKED_SESSION:
                exception = new AVException("不合法的Session", new Throwable());
                break;
            case AVException.INVALID_NESTED_KEY:
                exception = new AVException("Json对象中有不合法的key值", new Throwable());
                break;
            case AVException.INVALID_PHONE_NUMBER:
                exception = new AVException("手机号码格式错误", new Throwable());
                break;
            case AVException.INVALID_POINTER:
                exception = new AVException("指针异常", new Throwable());
                break;
            case AVException.INVALID_QUERY:
                exception = new AVException("查找失败", new Throwable());
                break;
            case AVException.INVALID_ROLE_NAME:
                exception = new AVException("不合法的角色名", new Throwable());
                break;
            case AVException.LINKED_ID_MISSING:
                exception = new AVException("用户ID丢失", new Throwable());
                break;
            case AVException.MISSING_OBJECT_ID:
                exception = new AVException("对象ID丢失", new Throwable());
                break;
            case AVException.MUST_CREATE_USER_THROUGH_SIGNUP:
                exception = new AVException("用户不存在", new Throwable());
                break;
            case AVException.NOT_INITIALIZED:
                exception = new AVException("软件未进行初始化操作", new Throwable());
                break;
            case AVException.OBJECT_NOT_FOUND:
                exception = new AVException("对象不存在", new Throwable());
                break;
            case AVException.OBJECT_TOO_LARGE:
                exception = new AVException("对象过大", new Throwable());
                break;
            case AVException.OPERATION_FORBIDDEN:
                exception = new AVException("操作不合法", new Throwable());
                break;
            case AVException.OTHER_CAUSE:
                exception = new AVException("其他错误", new Throwable());
                break;
            case AVException.PASSWORD_MISSING:
                exception = new AVException("密码丢失", new Throwable());
                break;
            case AVException.PUSH_MISCONFIGURED:
                exception = new AVException("推送没有被设置", new Throwable());
                break;
            case AVException.RATE_LIMITED:
                exception = new AVException("您被限制了", new Throwable());
                break;
            case AVException.SCRIPT_ERROR:
                exception = new AVException("脚本错误", new Throwable());
                break;
            case AVException.SESSION_MISSING:
                exception = new AVException("Session丢失", new Throwable());
                break;
            case AVException.TIMEOUT:
                exception = new AVException("连接超时", new Throwable());
                break;
            case AVException.UNKNOWN:
                exception = new AVException("未知异常", new Throwable());
                break;
            case AVException.UNSUPPORTED_SERVICE:
                exception = new AVException("不被支持的服务", new Throwable());
                break;
            case AVException.USER_DOESNOT_EXIST:
                exception = new AVException("用户已存在", new Throwable());
                break;
            case AVException.USER_ID_MISMATCH:
                exception = new AVException("用户ID不匹配", new Throwable());
                break;
            case AVException.USER_MOBILE_PHONENUMBER_TAKEN:
                exception = new AVException("账户已经被锁定", new Throwable());
                break;
            case AVException.USER_MOBILEPHONE_MISSING:
                exception = new AVException("此号码已经绑定过别的账号了", new Throwable());
                break;
            case AVException.USER_MOBILEPHONE_NOT_VERIFIED:
                exception = new AVException("手机号码尚未被验证过", new Throwable());
                break;
            case AVException.USER_WITH_MOBILEPHONE_NOT_FOUND:
                exception = new AVException("没有找到绑定了该手机号的用户", new Throwable());
                break;
            case AVException.USERNAME_MISSING:
                exception = new AVException("用户名不能为空", new Throwable());
                break;
            case AVException.USERNAME_PASSWORD_MISMATCH:
                exception = new AVException("密码不匹配", new Throwable());
                break;
            case AVException.USERNAME_TAKEN:
                exception = new AVException("改用户名已存在", new Throwable());
                break;
            case AVException.VALIDATION_ERROR:
                exception = new AVException("验证失败", new Throwable());
                break;
            default:
                exception = new AVException("未知的异常", new Throwable());
                break;
        }
        return exception;
    }
    /**
     * 加载图片
     * @param imageView
     * @param uri
     * @param <T>
     */
    public static  <T extends ImageView> void loadImage(Activity activity,final T imageView, String uri) {
        Glide.with(activity).
                load(uri)
                .asBitmap()
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);
                        imageView.setTag(resource);
                    }
                });
    }
    /**
     * 判断字符串是否为空
     * @param s
     * @return
     */
    public static boolean isStrNotNull(String s){
        if (s != null && !TextUtils.isEmpty(s)){
            return true;
        }else {
            return false;
        }
    }
    /**
     * ViewHolder基类
     */
    public static class ViewHolder {
        /**
         * 通过id返回布局中的指定的控件
         *
         * @param view 父布局
         * @param id   子控件的id
         * @param <T>  泛型，继承View
         * @return 返回指定的控件
         */
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }


    /**
     * 将Bitmap转换成字节数组
     *
     * @param imageView
     * @param <T>
     * @return
     */
    public static <T extends ImageView> byte[] getByteArrayFromUri(T imageView) {
        byte[] result = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = (Bitmap) imageView.getTag();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            result = baos.toByteArray();
        }
        return result;
    }

    /**
     * @param s
     */
    public static void printLog(String s) {
        Log.e("TAG", s);
    }
}
