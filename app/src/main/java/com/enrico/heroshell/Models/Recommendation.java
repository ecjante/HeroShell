package com.enrico.heroshell.Models;

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
        recommendedBy.add(new User());
        recommendedBy.add(new User());
        recommendedBy.add(new User());
        recommendedBy.add(new User());
    }

    public Stream getStream() {
        return stream;
    }

    public ArrayList<User> getRecommendedBy() {
        return recommendedBy;
    }
}
