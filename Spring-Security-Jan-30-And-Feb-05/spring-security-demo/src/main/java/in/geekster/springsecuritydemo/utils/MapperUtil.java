package in.geekster.springsecuritydemo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.findAndRegisterModules();
    }

    public static <F, T> T convert(final F from, final Class<T> tClass) {
        return MAPPER.convertValue(from, tClass);
    }


}
