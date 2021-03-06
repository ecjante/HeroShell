package com.enrico.heroshell.data;

import com.enrico.heroshell.HeroApplication;

/**
 * Created by enrico on 10/19/17.
 */

public class User {
    String username;
    String displayName;
    String coverImageUrl;
    String profileImageUrl;
    Boolean isOnline;
    Boolean followingYou;
    Integer followingCount;
    Integer followersCount;
    Integer playlistCount;

    Boolean needsProfileGrab = true;

    public User() {
        username = "enrico";
        displayName = "Enrico Jante";
        coverImageUrl = "http://www.planwallpaper.com/static/images/Desktop-Wallpaper-HD2.jpg";
        profileImageUrl = "http://jmaruyama.com/blog/wp-content/uploads/2015/11/HolidayMickey.jpg";
        isOnline = true;
        followingYou = true;
        followersCount = 109;
        followingCount = 87;
        playlistCount = 124;
    }
    public User(String username) {
        this();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getCoverImageUrl() {
        return coverImageUrl;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public Boolean getOnline() {
        return isOnline;
    }
    public Boolean getFollowingYou() {
        return followingYou;
    }
    public Integer getFollowingCount() {
        return followingCount;
    }
    public Integer getFollowersCount() {
        return followersCount;
    }
    public Integer getPlaylistCount() {
        return playlistCount;
    }
    public Boolean getNeedsProfileGrab() {
        return needsProfileGrab;
    }
    public void setNeedsProfileGrab(Boolean needsProfileGrab) {
        this.needsProfileGrab = needsProfileGrab;
    }

    public boolean isLoggedInUser() {
        return HeroApplication.isLoggedInUser(username);
    }
}
