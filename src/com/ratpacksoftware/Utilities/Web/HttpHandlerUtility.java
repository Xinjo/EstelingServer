package com.ratpacksoftware.Utilities.Web;

import com.sun.net.httpserver.HttpExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michel on 20-5-2017.
 */
public class HttpHandlerUtility {
    // http://www.rgagnon.com/javadetails/java-get-url-parameters-using-jdk-http-server.html
    public static Map<String, String> getQueryParameters(String query) {
        Map<String, String> _mappedQuery = new HashMap<>();

        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                _mappedQuery.put(pair[0], pair[1]);
            }else{
                _mappedQuery.put(pair[0], "");
            }
        }

        return _mappedQuery;
    }
}
