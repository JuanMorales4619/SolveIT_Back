package com.uco.apisolveit.domain;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -97361747373156369L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

}
