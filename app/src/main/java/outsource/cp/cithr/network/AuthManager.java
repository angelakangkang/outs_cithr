package outsource.cp.cithr.network;

import android.content.Context;

import com.google.gson.Gson;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.lang.reflect.Type;

import outsource.cp.cithr.model.ErrorModel;

/**
 * Created by Raymon on 2017/2/27.
 * 登录和用户的管理在这里
 * 单例类getInstance（）得到对象
 */

public class AuthManager {
    private Context context;
    private Future<?> authFuture;
    private Object futureGroup = new Object();

    //单例类 usage: AuthManager manager = AuthManager.getInstance(this);
    //////////////////////////////////////////////////////
    private volatile static AuthManager instance;

    private AuthManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public static AuthManager getInstance(Context context) {
        if (instance == null){
            synchronized (AuthManager.class) {
                if (instance == null){
                    instance = new AuthManager(context);
                }
            }
        }
        return instance;
    }




    /***
     * 一个通用的get请求方法
     * @param url 请求地址
     * @param listener 回调接口
     * @param type 泛型的Type对象, 如果是普通类 type =  (Type) MyClass.class; 带泛型(如数组) type = new TypeToken<List<MyClass>>(){}.getType();
     * @param <T> 请求得到的对象类型
     * @return
     */
    public <T> Future<?> get (String url, final OnResponseListener<T> listener, final Type type) {
        return Ion.with(context)
                .load(url)
                .group(futureGroup)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        Gson gson = new Gson();
                        if (result.getHeaders().code() == 200) {
                            //todo
                            T t = gson.fromJson(result.getResult(), type);
                            listener.onSuccess(t);
                        } else {
                            listener.onFailed(gson.fromJson(result.getResult(), ErrorModel.class));
                        }
                    }
                });
    }























}
