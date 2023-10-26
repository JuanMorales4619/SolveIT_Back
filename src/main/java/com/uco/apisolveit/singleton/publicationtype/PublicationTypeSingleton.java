package com.uco.apisolveit.singleton.publicationtype;


import com.uco.apisolveit.domain.publicationtype.PublicationType;

public class PublicationTypeSingleton {
    private PublicationTypeSingleton() {
        //Instant the class
    }

    private static class SingletonHelper{
        private static final PublicationType INSTANCE = new PublicationType();
    }
    public static PublicationType getInstance(){
        return PublicationTypeSingleton.SingletonHelper.INSTANCE;
    }
}
