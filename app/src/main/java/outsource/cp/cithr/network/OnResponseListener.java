package outsource.cp.cithr.network;

import outsource.cp.cithr.model.ErrorModel;

/**
 * Created by Raymon on 2017/2/27.
 */

public interface OnResponseListener<T> {
    /***
     * 正常调用返回的结果(返回代码为20x)的结果
     * @param result 请求返回的对象
     */
    void onSuccess(T result);

    /***
     * 返回代码为40x或50x时的回调
     * @param errorModel 包含错误信息的对象
     */
    void onFailed(ErrorModel errorModel);
}
