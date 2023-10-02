package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserFriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@PreAuthorize("isAuthenticated()")
@RestController
public class UserFriendController {

    @Autowired
    UserFriendDao userFriendDao;
    @Autowired
    UserDao userDao;

    @RequestMapping(path = "/addFriend", method = RequestMethod.POST)
    public int addFriend(@RequestParam int friendId, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        boolean areFriends = userFriendDao.isFriended(userId, friendId);
        return areFriends?
                -1:
                userFriendDao.friendUser(userId, friendId);
    }

    @RequestMapping(path = "/unFriend", method = RequestMethod.DELETE)
    public int deleteFriend(@RequestParam int friendId, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        boolean areFriends = userFriendDao.isFriended(userId, friendId);
        return areFriends?
                userFriendDao.unfriendUser(userId, friendId):
                -1;
    }

    @RequestMapping(path = "/isFriend", method = RequestMethod.GET)
    public boolean isFriend(@RequestParam int friendId, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        return userFriendDao.isFriended(userId, friendId);
    }

}
