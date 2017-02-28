package outsource.cp.cithr.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import outsource.cp.cithr.R;
import outsource.cp.cithr.activity.User_MainActivity;
import outsource.cp.cithr.adapter.User_mainf_viewpager_adapter;

public class User_MainFragment extends Fragment{

    @BindView(R.id.toolbar_user_mainf)
    Toolbar user_maintoolbar;
    @BindView(R.id.tablayout_mainf)
    TabLayout user_maintablayout;
    @BindView(R.id.viewpager_mainf)
    ViewPager user_mainviewpager;

    private User_mainf_viewpager_adapter user_mainafdapter;
    private List<Fragment> list_fragment=new ArrayList<>();
    private List<String> list_title=new ArrayList<>();

    private User_MainF_outsource user_mainF_outsource;
    private User_MainF_jobs user_mainF_jobs;
    private Unbinder unbinder;
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

        inittoolbar();
        initviewpager();
    }

    private void initviewpager() {
        user_mainafdapter=new User_mainf_viewpager_adapter(getActivity().getSupportFragmentManager(),list_fragment,list_title);
        user_mainviewpager.setAdapter(user_mainafdapter);
        user_maintablayout.setupWithViewPager(user_mainviewpager);
    }

    private void inittoolbar() {
        ((User_MainActivity)getActivity()).setSupportActionBar(user_maintoolbar);
        user_maintablayout.setTabMode(TabLayout.MODE_FIXED);
        user_maintablayout.addTab(user_maintablayout.newTab().setText(list_title.get(0)));
        user_maintablayout.addTab(user_maintablayout.newTab().setText(list_title.get(1)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
