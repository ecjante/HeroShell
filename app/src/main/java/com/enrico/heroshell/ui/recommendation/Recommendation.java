package com.enrico.heroshell.ui.recommendation;

import com.enrico.heroshell.data.Stream;
import com.enrico.heroshell.data.User;

import java.util.ArrayList;

/**
 * Created by enrico on 10/21/17.
 */

public class Recommendation {
    Stream stream;
    ArrayList<User> recommendedBy;

    public Recommendation() {
        stream = new Stream();
        recommendedBy = new ArrayList<User>();
        recommendedBy.add(new User("abc"));
        recommendedBy.add(new User("def"));
        recommendedBy.add(new User("ghi"));
        recommendedBy.add(new User("jkl"));
    }

    public Stream getStream() {
        return stream;
    }

    public ArrayList<User> getRecommendedBy() {
        return recommendedBy;
    }
}
