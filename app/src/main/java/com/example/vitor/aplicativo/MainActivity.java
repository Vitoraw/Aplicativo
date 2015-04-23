package com.example.vitor.aplicativo;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toolbar;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener {
    private MaterialTabHost tabHost;
    private android.support.v7.widget.Toolbar toolbar;
    private ViewPager viewPager;
    public static final int ESQUERDA =0;
    public static final int PRINCIPAL =1;
    public static final int DIREITA =2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appbar);

        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);
        tabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);
                
            }
        });
        for (int i=0;i<adapter.getCount();i++){
            tabHost.addTab(
                    tabHost.newTab().setIcon(adapter.getIcon(i)).setTabListener(this));


        }
        ImageView imageView =new ImageView(this);
        imageView.setImageResource(R.drawable.contatoicon);

        ImageView imageView1 =new ImageView(this);
        imageView1.setImageResource(R.drawable.dinheiro);

        ImageView imageView2 =new ImageView(this);
        imageView2.setImageResource(R.drawable.homeicon);

        ImageView imageView3 =new ImageView(this);
        imageView3.setImageResource(R.drawable.contatoicon);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(imageView).build();
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton button1 = itemBuilder.setContentView(imageView1).build();
        SubActionButton button2 = itemBuilder.setContentView(imageView2).build();
        SubActionButton button3 = itemBuilder.setContentView(imageView3).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.plus){

            startActivity(new Intent(this, NovaActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        int icons[] = {R.drawable.homeicon,R.drawable.dinheiro,R.drawable.tarefaicon};
        public ViewPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case ESQUERDA:
                    fragment=Esquerda.newInstance("","");
                    break;
                case PRINCIPAL:
                    fragment=Principal.newInstance("","");
                    break;
                case DIREITA:
                    fragment=Direita.newInstance("","");
                    break;



            }
            return fragment;

        }

        @Override
        public int getCount() {
            return 3;
        }
        public CharSequence getPageTitle(int position){
            return getResources().getStringArray(R.array.tabs)[position];
        }
        private Drawable getIcon(int position){
            return getResources().getDrawable(icons[position]);
        }
    }
}
