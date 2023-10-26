package com.uco.apisolveit.util.exception;



public class GeneralException extends RuntimeException{

    private final String userMessage;
    private final String technicalMessage;
    private final Exception rootException;

    protected GeneralException(String userMessage, String technicalMessage, Exception rootException) {
        super();
        this.userMessage = userMessage;
        this.rootException =rootException;
        this.technicalMessage = technicalMessage;
    }

    public static GeneralException build(String technicalMessage) {
        return new GeneralException(null, technicalMessage, null);
    }

    public static GeneralException build(String technicalMessage, Exception rootExeption) {
        return new GeneralException(null, technicalMessage,rootExeption);
    }


    public String getUserMessage() {
        return userMessage;
    }
    public String getTechnicalMessage() {
        return technicalMessage;
    }
    public Exception getRootException() {
        return rootException;
    }

}
