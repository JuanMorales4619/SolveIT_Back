package com.uco.apisolveit.singleton.redperson;


import com.uco.apisolveit.domain.RedPerson;


public class RedPersonSingleton {
    private RedPersonSingleton() {//Instant the class
    }
    private static class SingletonHelper{
        private static final RedPerson INSTANCE = new RedPerson();
    }
    public static RedPerson getInstance(){
        return RedPersonSingleton.SingletonHelper.INSTANCE;
    }
}
