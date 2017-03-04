package outsource.cp.cithr.fragment;




import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import outsource.cp.cithr.R;
import outsource.cp.cithr.adapter.User_mainf_outsource_adapter;


public class User_MainF_outsource extends LazyLoadFragment {

    @BindView(R.id.recy_mainf_userout)
    RecyclerView mrecyclerview_out;
    private LinearLayoutManager mlayoutmanager_out;
    private User_mainf_outsource_adapter mainf_job_adapter_out;
    private List<String> mdata_out=new ArrayList<>();
    private List<String> tempdata_out = new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.fragment_user__main_f_outsource;
    }

    @Override
    protected void lazyLoad() {
        mlayoutmanager_out=new LinearLayoutManager(this.getActivity());
        mlayoutmanager_out.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview_out.setLayoutManager(mlayoutmanager_out);
        mainf_job_adapter_out= new User_mainf_outsource_adapter(getActivity());
        mrecyclerview_out.setAdapter(mainf_job_adapter_out);
        mrecyclerview_out.addOnScrollListener(mOnScrollListener);
        initdata();
    }


    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener()
    {
        private int lastVisibleItem;

        // 滑动状态改变
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            super.onScrollStateChanged(recyclerView, newState);
            /**
             * scrollState有三种状态，分别是SCROLL_STATE_IDLE、SCROLL_STATE_TOUCH_SCROLL、SCROLL_STATE_FLING
             * SCROLL_STATE_IDLE是当屏幕停止滚动时
             * SCROLL_STATE_TOUCH_SCROLL是当用户在以触屏方式滚动屏幕并且手指仍然还在屏幕上时
             * SCROLL_STATE_FLING是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
             */
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mainf_job_adapter_out.getItemCount()
                    && mainf_job_adapter_out.isShowFooter())
            {

                // 加载更多


                getMoreData();

            }
        }

        // 滑动位置
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
            // 给lastVisibleItem赋值
            // findLastVisibleItemPosition()是返回最后一个item的位置
            lastVisibleItem = mlayoutmanager_out.findLastVisibleItemPosition();
        }
    };


    public void initdata() {

        mdata_out.clear();
        for(int i=1;i<30;i++){
            mdata_out.add(String.valueOf(i));
        }

        mainf_job_adapter_out.setData(mdata_out);
    }
    private void getMoreData(){
        tempdata_out.clear();
        for(int i=30;i<=50;i++){
            tempdata_out.add(String.valueOf(i));
        }

        mdata_out.addAll(tempdata_out);
        Log.v("size",String.valueOf(mdata_out.size()));
        if(mdata_out.size() >= 51) {
//                    mainf_job_adapter.isShowFooter(false);
            Toast.makeText(getActivity(), "没有更多内容了", Toast.LENGTH_SHORT).show();
//                    return;
        }
        mainf_job_adapter_out.setData(mdata_out);

    }

}

