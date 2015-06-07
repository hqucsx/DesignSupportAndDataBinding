package com.hqucsx.databindinganddesignsupport;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tb_test)
    TabLayout tbTest;
    @InjectView(R.id.vp_test)
    ViewPager vpTest;
    @InjectView(R.id.toobar)
    Toolbar toobar;
    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.nav_view)
    NavigationView navView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.fb_test)
    FloatingActionButton fbTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toobar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setNavView(navView);
        steupViewPager();

        fbTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fbTest,"Hi ,I'm SnackBar!",Snackbar.LENGTH_SHORT)
                        .setAction("ACTION", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,"SnackBar Action",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    /**
     * Nav 初始化
     *
     * @param navView
     */
    private void setNavView(NavigationView navView) {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * ViewPgaer 初始化
     */
    private void steupViewPager() {
        tbTest.setTabTextColors(Color.BLACK, Color.BLUE);

        final String[] titles = new String[]{"消息", "联系人", "我的"};
        tbTest.addTab(tbTest.newTab().setText(titles[0]), 0, true);
        tbTest.addTab(tbTest.newTab().setText(titles[1]), 1);
        tbTest.addTab(tbTest.newTab().setText(titles[2]), 2);

        FragmentStatePagerAdapter adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new FragmentList();
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };

        vpTest.setAdapter(adapter);
        tbTest.setupWithViewPager(vpTest);
        tbTest.setTabsFromPagerAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
//
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(MainActivity.this, WidgetTestActivity.class));
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
