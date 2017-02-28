package outsource.cp.cithr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Raymon on 2017/2/28.
 * 用户首页的viewpager适配器
 */

public class User_mainf_viewpager_adapter extends FragmentPagerAdapter {
    private List<Fragment> list_user_mainf;
    private List<String> list_user_mainTitle;

    public User_mainf_viewpager_adapter(FragmentManager fm,List<Fragment> fragments,List<String> list_title){
        super(fm);
        this.list_user_mainf=fragments;
        this.list_user_mainTitle=list_title;
    }


    @Override
    public Fragment getItem(int position) {
        return list_user_mainf.get(position);
    }

    @Override
    public int getCount() {
        return list_user_mainTitle.size();
    }


    //用来显示tab上的标题
    @Override
    public CharSequence getPageTitle(int postion){

        return list_user_mainTitle.get(postion%list_user_mainTitle.size());
    }


}
