package com.alenayasko.javamentoring.reactivesports.util;

public interface ApiConstants {

    String REQUEST_PARAM_QUERY = "q";
    String PATH_PARAM_SPORT_NAME = "sportname";

    String URI_V1_SPORT = "/api/v1/sport";

    String URI_V1_CREATE_SPORT = String.format("%s/{%s}", URI_V1_SPORT, PATH_PARAM_SPORT_NAME);
}
