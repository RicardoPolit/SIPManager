package com.github.ricardopolit.sipmanager.util;

public class HashString {

    public static String hashValue( String value ){
        return org.mindrot.jbcrypt.BCrypt.hashpw(value, org.mindrot.jbcrypt.BCrypt.gensalt(4));
    }

    public static Boolean checkValue( String candidate, String hashed ){
        return org.mindrot.jbcrypt.BCrypt.checkpw(candidate,hashed);
    }

}
