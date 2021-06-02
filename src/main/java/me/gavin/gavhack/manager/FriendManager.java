package me.gavin.gavhack.manager;

import me.gavin.gavhack.friends.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendManager {

    public static List<Friend> friends;

    public FriendManager(){
        friends = new ArrayList<>();
    }

    public static List<Friend> getFriends(){
        return friends;
    }

    public static boolean isFriend(String name){
        boolean b = false;
        for(Friend f : getFriends()){
            if(f.getName().equalsIgnoreCase(name)) b = true;
        }
        return b;
    }

    public static List<String> getFriendByName() {
        ArrayList<String> friendsName = new ArrayList<>();
        friends.forEach(friend -> {
            friendsName.add(friend.getName());
        });
        return friendsName;
    }

    public static Friend getFriendByName(String name){
        Friend fr = null;
        for(Friend f : getFriends()){
            if(f.getName().equalsIgnoreCase(name)) fr = f;
        }
        return fr;
    }

    public static void addFriend(String name){
        friends.add(new Friend(name));
    }

    public static void delFriend(String name){
        friends.remove(getFriendByName(name));
    }
}
