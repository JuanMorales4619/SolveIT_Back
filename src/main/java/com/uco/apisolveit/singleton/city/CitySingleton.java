package com.uco.apisolveit.singleton.city;

import com.uco.apisolveit.domain.city.City;


public class CitySingleton {
    private CitySingleton() {//Instant the class
    }
    private static class SingletonHelper{
        private static final City INSTANCE = new City();
    }
    public static City getInstance(){
        return CitySingleton.SingletonHelper.INSTANCE;
    }
}
