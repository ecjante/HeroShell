package com.enrico.heroshell.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.enrico.heroshell.HeroApplication;
import com.enrico.heroshell.ui.chat.ChatFragment;
import com.enrico.heroshell.ui.home.HomeFragment;
import com.enrico.heroshell.ui.recommendation.RecommendationPagerFragment;
import com.enrico.heroshell.ui.profile.UserProfileFragment;
import com.enrico.heroshell.data.User;
import com.enrico.heroshell.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;


/**
 * Created by enrico on 10/19/17.
 */

public class ContainerActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    public static final long PROFILE_ID = 0L;
    private static final String ROUTE_PROFILE = "Profile";
    public static final long HOME_ID = 1L;
    private static final String ROUTE_HOME = "Home";
    public static final long CHAT_ID = 2L;
    private static final String ROUTE_CHAT = "Chat";
    public static final long RECOMMENDATION_ID = 3L;
    private static final String ROUTE_RECOMMENDATION = "Recommendation";

    private Drawer drawer;
    private Toolbar toolbar;

    private HomeFragment homeFragment;
    private UserProfileFragment profileFragment;
    private ChatFragment chatFragment;
    private RecommendationPagerFragment recommendationPagerFragment;

    private static ContainerActivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HeroApplication();
        self = this;
        setContentView(R.layout.activity_container);

        ((ImageView) findViewById(R.id.global_background_image))
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.primary_dark));

//        Glide.with(getApplicationContext())
//                .load("http://images.herostreams.com/android_global_default.jpg")
//                .into((ImageView) findViewById(R.id.global_background_image));

        SecondaryDrawerItem profileDrawerItem = new SecondaryDrawerItem()
                .withIdentifier(PROFILE_ID)
                .withName(ROUTE_PROFILE)
                .withIcon(R.drawable.ic_person_white_48dp)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        open(ROUTE_PROFILE);
                        return false;
                    }
                });

        SecondaryDrawerItem homeDrawerItem = new SecondaryDrawerItem()
                .withIdentifier(HOME_ID)
                .withName(ROUTE_HOME)
                .withIcon(R.drawable.ic_home_white_48dp)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        open(ROUTE_HOME);
                        return false;
                    }
                });

        SecondaryDrawerItem chatDrawerItem = new SecondaryDrawerItem()
                .withIdentifier(CHAT_ID)
                .withName(ROUTE_CHAT)
                .withIcon(R.drawable.ic_chat_bubble_white_48dp)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        open(ROUTE_CHAT);
                        return false;
                    }
                });

        SecondaryDrawerItem recommendationDrawerItem = new SecondaryDrawerItem()
                .withIdentifier(RECOMMENDATION_ID)
                .withName(ROUTE_RECOMMENDATION)
                .withIcon(R.drawable.ic_hero_logo_white_48dp)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        open(ROUTE_RECOMMENDATION);
                        return false;
                    }
                });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withTranslucentStatusBar(false)
                .addDrawerItems(profileDrawerItem, homeDrawerItem, chatDrawerItem,
                        recommendationDrawerItem)
                .build();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        homeFragment = new HomeFragment();
        drawer.setSelection(1);
    }

    private void open(String route) {
        shouldDisplayHomeUp();
        switch (route) {
            case ROUTE_PROFILE: {
                if (profileFragment == null) {
                    profileFragment = new UserProfileFragment();
                    Bundle b = new Bundle();
                    b.putString(UserProfileFragment.USERNAME_ARGUMENT, "ecjpiano");
                    profileFragment.setArguments(b);
                }
                pushFragment(profileFragment);
                break;
            }
            case ROUTE_HOME: {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(0);
                    getSupportFragmentManager().popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getSupportFragmentManager().executePendingTransactions();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, homeFragment);
//                transaction.addToBackStack(null);
                transaction.commit();
                shouldDisplayHomeUp();
                break;
            }
            case ROUTE_CHAT: {
                if (chatFragment == null) {
                    chatFragment = new ChatFragment();
                }
                pushFragment(chatFragment);
                break;
            }
            case ROUTE_RECOMMENDATION: {
                if (recommendationPagerFragment == null) {
                    recommendationPagerFragment = new RecommendationPagerFragment();
                }
                pushFragment(recommendationPagerFragment);
                break;
            }
        }
    }

    public static void pushFragment(Fragment fragment) {
        FragmentTransaction transaction = self.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.enter_from_right, R.anim.exit_to_left,
                R.anim.enter_from_left, R.anim.exit_to_right
        );
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        self.shouldDisplayHomeUp();
    }
    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        shouldDisplayHomeUp();
    }

    public static Context getContext() {
        return self.getApplicationContext();
    }
    public static long getDrawerSelection() {
        return self.drawer.getCurrentSelection();
    }
    public static void updateDrawerSelection(long drawerId) {
        self.drawer.setSelection(drawerId, false);
    }
    public static void updateToolbarTitle(String title) {
        self.getSupportActionBar().setTitle(title);
    }
    public static void hideKeyboard() {
        if (KeyboardVisibilityEvent.isKeyboardVisible(self)) {
            UIUtil.hideKeyboard(self);
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    @Override
    public boolean onSupportNavigateUp() {
        popFragment();
        return true;
    }

    public void shouldDisplayHomeUp() {
        boolean canBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (canBack) {
            drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popFragment();
                }
            });
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer();
                }
            });
        }
    }
}
