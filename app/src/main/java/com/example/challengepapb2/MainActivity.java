package com.example.challengepapb2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {



    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    FragmentTransaction settingFragmentTransaction;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        Fragment1 newFragment = new Fragment1();
        fragmentTransaction.add(R.id.ui_container, newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.option){
            settingFragmentTransaction = fragmentManager.beginTransaction();
            SettingFragment settingFragment = new SettingFragment();

            settingFragmentTransaction.replace(R.id.ui_container, settingFragment);
            settingFragmentTransaction.addToBackStack(null);
            settingFragmentTransaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
