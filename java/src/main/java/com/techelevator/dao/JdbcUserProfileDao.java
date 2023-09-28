package com.techelevator.dao;

import com.techelevator.model.UserProfile;

import java.util.List;

public class JdbcUserProfileDao implements UserProfileDao {

    @Override
    public UserProfile findUserProfileByName(String username) {
        return null;
    }

    @Override
    public List<UserProfile> findProfilesByUsername(String username) {
        return null;
    }
}
