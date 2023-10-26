package com.uco.apisolveit.singleton.identificationtype;


import com.uco.apisolveit.domain.identificationtype.IdentificationType;


public class IdentificationTypeSingleton {
    private IdentificationTypeSingleton() {//Instant the class
    }
    private static class SingletonHelper{
        private static final IdentificationType INSTANCE = new IdentificationType();
    }
    public static IdentificationType getInstance(){
        return IdentificationTypeSingleton.SingletonHelper.INSTANCE;
    }
}
