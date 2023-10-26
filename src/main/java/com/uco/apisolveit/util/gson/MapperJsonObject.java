package com.uco.apisolveit.util.gson;

import java.util.Optional;

public interface MapperJsonObject {

    Optional<String> ejecutar(Object objeto);

    <T> Optional<T> ejecutar(String json, Class<T> claseDestino);

    Optional<String> ejecutarGson(Object objecto);
}
