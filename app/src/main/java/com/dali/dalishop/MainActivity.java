package com.dali.dalishop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.dali.dalishop.adapter.ViewPagerAdapter;
import com.dali.dalishop.bean.Tab;
import com.dali.dalishop.fragment.CartFragment;
import com.dali.dalishop.fragment.CategoryFragment;
import com.dali.dalishop.fragment.HomeFragment;
import com.dali.dalishop.fragment.HotFragment;
import com.dali.dalishop.fragment.MineFragment;
import com.dali.dalishop.widget.CnToolbar;
import com.dali.dalishop.widget.FragmentTabHost;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    private FragmentTabHost tabHost;
    private List<Tab> tabs;
    private LayoutInflater inflater;
    private List<Fragment> list;
    private android.support.v4.view.ViewPager viewPager;

    private CnToolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindWidget();

        initTabHost();

        initPager();

    }

    private void initPager() {
        HomeFragment homeFragment = new HomeFragment();
        HotFragment hotFragment = new HotFragment();
        CartFragment cartFragment = new CartFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        MineFragment mineFragment = new MineFragment();

        list.add(homeFragment);
        list.add(hotFragment);
        list.add(cartFragment);
        list.add(categoryFragment);
        list.add(mineFragment);

        //绑定Fragment适配器
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), list));
    }

    private void bindWidget() {

        mToolbar = (CnToolbar) findViewById(R.id.toolbar);

        tabs = new ArrayList<>(5);
        list = new ArrayList<>(5);
        inflater = LayoutInflater.from(this);
        tabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.view_pager);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        /*实现setOnTabChangedListener接口,目的是为监听界面切换，然后实现TabHost里面图片文字的选中状态切换*/
        /*简单来说,是为了当点击下面菜单时,上面的ViewPager能滑动到对应的Fragment*/
        tabHost.setOnTabChangedListener(this);
        /*实现OnPageChangeListener接口,目的是监听Tab选项卡的变化，然后通知ViewPager适配器切换界面*/
        /*简单来说,是为了让ViewPager滑动的时候能够带着底部菜单联动*/
        viewPager.setOnPageChangeListener(this);
    }

    private void initTabHost() {

        Tab tab_home = new Tab(R.string.home, R.drawable.selector_icon_home, HomeFragment.class);
        Tab tab_hot = new Tab(R.string.hot, R.drawable.selector_icon_hot, HotFragment.class);
        Tab tab_category = new Tab(R.string.category, R.drawable.selector_icon_category, CategoryFragment.class);
        Tab tab_cart = new Tab(R.string.cart, R.drawable.selector_icon_cart, CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine, R.drawable.selector_icon_mine, MineFragment.class);

        tabs.add(tab_home);
        tabs.add(tab_hot);
        tabs.add(tab_category);
        tabs.add(tab_cart);
        tabs.add(tab_mine);

        for (Tab tab : tabs) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            tabHost.addTab(tabSpec,tab.getFragment(),null);
        }

        //去掉分割线
        tabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        //默认选择第一个
        tabHost.setCurrentTab(0);

    }


    private View buildIndicator(Tab tab) {
        View view = inflater.inflate(R.layout.tab_indicator, null);

        ImageView img = (ImageView) view.findViewById(R.id.img_tab);
        TextView title = (TextView) view.findViewById(R.id.tv_title);

        img.setImageResource(tab.getImage());
        title.setText(tab.getTitle());
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {//当前选中的页面位置Postion，页面跳转完毕的时候调用。

        TabWidget widget = tabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);//设置View覆盖子类控件而直接获得焦点
        tabHost.setCurrentTab(position);//根据位置Postion设置当前的Tab
        widget.setDescendantFocusability(oldFocusability);//设置取消分割线
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int position = tabHost.getCurrentTab();
        viewPager.setCurrentItem(position);//把选中的Tab的位置赋给适配器，让它控制页面切换
    }
}
