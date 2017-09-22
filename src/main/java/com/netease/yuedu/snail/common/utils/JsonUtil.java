package com.netease.yuedu.snail.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.netease.yuedu.snail.common.core.EmptyToNullStringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);// JSON节点不包含属性值为NULL
    }

    public static <T> T toBean(String json, Class<T> valueType) {
        try {
            SimpleModule deserializeModule = new SimpleModule("DeserializeModule", new Version(1, 0, 0, null));
            deserializeModule.addDeserializer(String.class, new EmptyToNullStringDeserializer()); // assuming serializer declares correct class to bind to
            objectMapper.registerModule(deserializeModule);
            return objectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("toBean(String, Class<T>)", e); //$NON-NLS-1$
        } catch (JsonMappingException e) {
            logger.error("toBean(String, Class<T>)", e); //$NON-NLS-1$
        } catch (IOException e) {
            logger.error("toBean(String, Class<T>)", e); //$NON-NLS-1$
        }
        return null;
    }

    public static String toString(Object obj) {
        if (obj != null) {
            try {
                objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                logger.error("toString(String, Class<T>)", e);
            }
        }
        return null;
    }

    private JsonUtil() {
    }

}
