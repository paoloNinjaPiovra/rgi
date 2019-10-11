package com.rgi.rgi.util;

import com.rgi.rgi.entity.User;

import java.util.Set;

public class SetUtil {

    public static boolean equals(Set<User> set1, Set<User> set2){

        if(set1 == null || set2 ==null){
            return false;
        }

        if(set1.size() != set2.size()){
            return false;
        }

        return set1.containsAll(set2);

    }

}
