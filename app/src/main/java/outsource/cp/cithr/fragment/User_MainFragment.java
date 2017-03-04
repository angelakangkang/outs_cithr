package outsource.cp.cithr.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import outsource.cp.cithr.R;
import outsource.cp.cithr.activity.User_MainActivity;
import outsource.cp.cithr.adapter.User_mainf_viewpager_adapter;

public class User_MainFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.toolbar_user_mainf)
    Toolbar user_maintoolbar;
    @BindView(R.id.tablayout_mainf)
    TabLayout user_maintablayout;
    @BindView(R.id.viewpager_mainf)
    ViewPager user_mainviewpager;
    @BindView(R.id.search_view)
    CardView mysearch;
    @BindView(R.id.swl_mainf_user)
    SwipeRefreshLayout mswipe;

    private User_mainf_viewpager_adapter user_mainafdapter;
    private List<Fragment> list_fragment=new ArrayList<>();
    private List<String> list_title=new ArrayList<>();

    private User_MainF_outsource user_mainF_outsource;
    private User_MainF_jobs user_mainF_jobs;
    private Unbinder unbinder;
    private int mposition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__main, container, false);
        unbinder = ButterKnife.bind(this, view);


        initview();

        return view;
    }






    private void initview() {
        user_mainF_outsource=new User_MainF_outsource();
        user_mainF_jobs=new User_MainF_jobs();
        //装入list
        list_fragment.add(user_mainF_outsource);
        list_fragment.add(user_mainF_jobs);
        list_title.add("外包");
        list_title.add(("招聘"));

        //初始化下拉刷新
        mswipe.setOnRefreshListener(this);
        mswipe.setColorSchemeResources(R.color.colorAccent);
        inittoolbar();
        initviewpager();
    }

    private void initviewpager() {
        user_mainafdapter=new User_mainf_viewpager_adapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);
        user_mainviewpager.setAdapter(user_mainafdapter);
        user_maintablayout.setupWithViewPager(user_mainviewpager);
        user_mainviewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mposition=position;
            }

            @Override
             public void onPageScrollStateChanged(int state) {

                enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });

    }
    protected void enableDisableSwipeRefresh(boolean enable) {
        if (mswipe != null) {
            mswipe.setEnabled(enable);
        }
    }

    private void inittoolbar() {
        ((User_MainActivity)getActivity()).setSupportActionBar(user_maintoolbar);
        mysearch.setOnClickListener(this);
        user_maintablayout.addTab(user_maintablayout.newTab().setText(list_title.get(0)));
        user_maintablayout.addTab(user_maintablayout.newTab().setText(list_title.get(1)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_view://进入搜索界面
        }
    }

    @Override
    public void onRefresh() {
        //下拉刷新的操作
        if(mposition==1){
            user_mainF_jobs.initdata();
            Toast.makeText(getContext(),"下拉刷新job",Toast.LENGTH_SHORT).show();
        }
        else if(mposition==0){
            user_mainF_outsource.initdata();
            Toast.makeText(getContext(),"下拉刷新outsource",Toast.LENGTH_SHORT).show();
        }


        //完成后关闭刷新动画
        mswipe.setRefreshing(false);
    }
}
