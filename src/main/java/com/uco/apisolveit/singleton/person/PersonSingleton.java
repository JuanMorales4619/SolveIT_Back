package com.uco.apisolveit.singleton.person;

import com.uco.apisolveit.domain.person.Person;

public class PersonSingleton {
    private PersonSingleton() {//Instant the class
    }
    private static class SingletonHelper{
        private static final Person INSTANCE = new Person();
    }
    public static Person getInstance(){
        return SingletonHelper.INSTANCE;
    }
}
