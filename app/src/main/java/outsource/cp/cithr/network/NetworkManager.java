package outsource.cp.cithr.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;

import java.lang.reflect.Type;
import java.util.Map;

import outsource.cp.cithr.model.ErrorModel;

/**
 * Created by Raymon on 2017/2/27.
 * 单例类，getInstance()方法得到对象
 */

public class NetworkManager {

    private Context context;
    private Object futureGroup = new Object();
    /**
     * 双重校验锁方法构造的单例类
     */
    private volatile static NetworkManager instance;
    private NetworkManager(Context context){this.context=context.getApplicationContext();}
    public static NetworkManager getInstance(Context context){

        if(instance==null){
            synchronized (NetworkManager.class){
                if(instance==null) {
                    instance = new NetworkManager(context);
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
                        if(result==null){
                            ErrorModel errorModel_netfail=new ErrorModel();
                            errorModel_netfail.setStatus_code(-1);
                            listener.onFailed(errorModel_netfail);
                        }
                        else if (result.getHeaders().code() == 200) {//返回代码为200
                            //todo
                            T t = gson.fromJson(result.getResult(), type);
                            listener.onSuccess(t);
                        } else {
                            listener.onFailed(gson.fromJson(result.getResult(), ErrorModel.class));
                        }
                    }
                });
    }



    /**
     * @param url
     * @param map
     * @param listener
     * @param <T>
     * @return
     */
    public <T> Future<?> postForm (String url, Map<String, String> map, final OnResponseListener<T> listener,final Type type){
        final Builders.Any.B request = Ion.with(context).load(url);

        for (Map.Entry<String, String> data : map.entrySet()) {
            request.setBodyParameter(data.getKey(), data.getValue());
        }

        return request
                .group(futureGroup)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {
                        Gson gson = new Gson();

                        Log.i("network", result.getResult());
                        if(result.getHeaders().code()==200){
                            T t=gson.fromJson(result.getResult(),type);
                            listener.onSuccess(t);
                        }
                        else{
                            listener.onFailed(gson.fromJson(result.getResult(),ErrorModel.class));
                        }
                    }
                });
    }















}
