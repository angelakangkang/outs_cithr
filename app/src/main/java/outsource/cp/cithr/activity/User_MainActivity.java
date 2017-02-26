package outsource.cp.cithr.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import outsource.cp.cithr.R;
import outsource.cp.cithr.fragment.User_MainFragment;
import outsource.cp.cithr.fragment.User_MineFragment;
import outsource.cp.cithr.fragment.User_ResumeFragment;
import outsource.cp.cithr.fragment.User_SmsFragment;

public class User_MainActivity extends AppCompatActivity{


    @BindView(R.id.bottom_nav_view_user) BottomNavigationView bottomNavigationView_user;
    @BindView(R.id.home_view_pager) ViewPager homeViewPager;
    @BindView(R.id.activity_user__main) LinearLayout activityBottomNavigationT;
    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__main);
        ButterKnife.bind(this);



        initViewPager();
        initBottomNav();

    }

    private void initBottomNav() {
        bottomNavigationView_user.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                homeViewPager.setCurrentItem(item.getOrder());
                return true;
            }
        });
    }

    private void initViewPager() {
        homeViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment mfragment=null;
                switch(position){
                    case 0: mfragment=new User_MainFragment();
                        break;
                    case 1:mfragment=new User_SmsFragment();
                        break;
                    case 2:mfragment=new User_ResumeFragment();
                        break;
                    case 3:mfragment=new User_MineFragment();
                        break;
                }
                return mfragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        homeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
                /**
                 * 该方法只有在有新的页面被选中时才会回调
                 *
                 * 如果 preMenuItem 为 null，说明该方法还没有被回调过
                 * 则ViewPager从创建到现在都处于 position 为 0 的页面
                 * 所以当该方法第一次被回调的时候，直接将 position 为 0 的页面的 selected 状态设为 false 即可
                 *
                 * 如果 preMenuItem 不为 null，说明该方法内的
                 * "prevMenuItem = bottomNavView.getMenu().getItem(position);"
                 * 之前至少被调用过一次
                 * 所以当该方法再次被回调的时候，直接将上一个 prevMenuItem 的 selected 状态设为 false 即可
                 * 在做完上一句的事情后，将当前页面设为 prevMenuItem，以备下次调用
                 *
                 */
                if(prevMenuItem==null){
                    bottomNavigationView_user.getMenu().getItem(0).setChecked(false);
                }
                else{
                    prevMenuItem.setChecked(false);
                }
                bottomNavigationView_user.getMenu().getItem(position).setChecked(true);
                prevMenuItem=bottomNavigationView_user.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
