package com.iwebui.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 * 数据错误统一抛出new IllegalArgumentException
 *
 * @author lgl
 */
public class JsonUtils {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 序列换成json时,将所有的long变成string
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        // 日期序列化设置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        OBJECT_MAPPER.registerModule(simpleModule).registerModule(javaTimeModule);
    }

    private JsonUtils() {
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * javaBean、列表数组转换为json字符串
     *
     * @param obj
     * @return
     */
    public static String obj2json(Object obj) {
        String returnData;
        try {
            returnData = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return returnData;
    }

    /**
     * javaBean、列表数组转换为json字符串,忽略空值
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String obj2jsonIgnoreNull(Object obj) {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * json 转JavaBean
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T json2pojo(String jsonString, Class<T> clazz) {
        OBJECT_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * json字符串转换为map
     *
     * @param jsonString
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> json2map(String jsonString) {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return OBJECT_MAPPER.readValue(jsonString, Map.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }


    /**
     * 深度转换json成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> json2mapDeeply(String json) {
        return json2MapRecursion(json, OBJECT_MAPPER);
    }

    /**
     * 把json解析成list，如果list内部的元素存在jsonString，继续解析
     *
     * @param json
     * @param mapper 解析工具
     * @return
     * @
     */
    private static List<Object> json2ListRecursion(String json, ObjectMapper mapper) {
        if (json == null) {
            return null;
        }
        List<Object> list;
        try {
            list = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = json2ListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = json2MapRecursion(str, mapper);
                }
            }
        }
        return list;
    }

    /**
     * 把json解析成map，如果map内部的value存在jsonString，继续解析
     * <p>
     * 递归解析下级
     *
     * @param json
     * @param mapper
     * @return
     * @
     */
    private static Map<String, Object> json2MapRecursion(String json, ObjectMapper mapper) {
        if (json == null) {
            return null;
        }
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);
                if (str.startsWith("[")) {
                    List<?> list = json2ListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = json2MapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }
        return map;
    }

    /**
     * json数组字符串转换为列表
     *
     * @param <T>
     * @param jsonArrayStr
     * @param clazz
     * @return
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = new ArrayList<>();
        try {
            lst = OBJECT_MAPPER.readValue(jsonArrayStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }


    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    /**
     * map转换JavaBean
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }

    /**
     * map转换为json字符串
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        try {
            return OBJECT_MAPPER.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对象转换为pojo
     *
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(obj, clazz);
    }
}
