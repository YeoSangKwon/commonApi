/*
 * Create by SangKwon on 2019. 10. 22.
 */

package md.ysk5898.com.material;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityMaterialBinding;

public class MaterialActivity extends AppCompatActivity {

    ActivityMaterialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_material);
        binding.setActivity(this);

        if (binding.toobar != null) {
            setSupportActionBar((Toolbar) binding.toobar);
        }
        Toolbar toolbar = (Toolbar) binding.toobar;
        assert toolbar != null;

        //햄버거 메뉴 설정
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawer, toolbar, R.string.ns_menu_open, R.string.ns_menu_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.e("111111", "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.e("111111", "onDrawerClosed");
            }
        };
        binding.drawer.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            binding.drawer.closeDrawers();

            switch (menuItem.getItemId()) {
                case R.id.drama:
                    Log.e("111111", "drama");
                    break;
                case R.id.film:
                    Log.e("111111", "film");
                    break;
                case R.id.news:
                    Log.e("111111", "news");
                    break;
                case R.id.sport:
                    Log.e("111111", "sport");
                    break;
                default:
            }

            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.e("1111", "action_search");
                break;
        }
        return true;
    }
}
