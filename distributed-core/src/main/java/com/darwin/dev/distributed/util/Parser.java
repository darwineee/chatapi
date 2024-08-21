package com.darwin.dev.distributed.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    public static Map<String, String> parseQueryParam(String query) {
        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair.length > 1 ? pair[1] : ""));
    }
}
