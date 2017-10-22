package com.enrico.heroshell.ui.base;

import android.support.v4.app.Fragment;

/**
 * Created by enrico on 10/19/17.
 */

public abstract class BaseFragment extends Fragment {
    public String fragmentName;
    public abstract void didAppear();
    public abstract void didDisappear();
}
