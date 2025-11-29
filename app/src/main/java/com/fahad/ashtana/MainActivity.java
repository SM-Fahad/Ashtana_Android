package com.fahad.ashtana;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private TextView tabHome, tabForYou;

    // Fragments
    private HomeFragment homeFragment;
    private CategoriesFragment categoriesFragment;
    private CartFragment cartFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFragments();
        setupBottomNavigation();
        setupTopTabs();

        // Load home fragment by default
        loadFragment(homeFragment);
    }

    private void initializeFragments() {
        homeFragment = new HomeFragment();
        categoriesFragment = new CategoriesFragment();
        cartFragment = new CartFragment();
        profileFragment = new ProfileFragment();
    }

    private void setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = homeFragment;
                    break;
                case R.id.nav_categories:
                    selectedFragment = categoriesFragment;
                    break;
                case R.id.nav_cart:
                    selectedFragment = cartFragment;
                    break;
                case R.id.nav_profile:
                    selectedFragment = profileFragment;
                    break;
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            return true;
        });
    }

    private void setupTopTabs() {
        tabHome = findViewById(R.id.tabHome);
        tabForYou = findViewById(R.id.tabForYou);

        tabHome.setOnClickListener(v -> switchTopTab(true));
        tabForYou.setOnClickListener(v -> switchTopTab(false));

        // Set home as default selected
        switchTopTab(true);
    }

    private void switchTopTab(boolean isHome) {
        if (isHome) {
            tabHome.setTextColor(getResources().getColor(android.R.color.black));
            tabHome.setTypeface(tabHome.getTypeface(), android.graphics.Typeface.BOLD);

            tabForYou.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabForYou.setTypeface(tabForYou.getTypeface(), android.graphics.Typeface.NORMAL);

            // Load home content
            // You can implement different content for HOME vs FOR YOU
        } else {
            tabForYou.setTextColor(getResources().getColor(android.R.color.black));
            tabForYou.setTypeface(tabForYou.getTypeface(), android.graphics.Typeface.BOLD);

            tabHome.setTextColor(getResources().getColor(android.R.color.darker_gray));
            tabHome.setTypeface(tabHome.getTypeface(), android.graphics.Typeface.NORMAL);

            // Load "For You" content
            // You can implement personalized recommendations here
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, fragment);
        transaction.commit();
    }
}