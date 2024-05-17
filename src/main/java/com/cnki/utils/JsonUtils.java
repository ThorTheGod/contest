package com.cnki.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.formula.functions.T;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();


    public static String getJson(Object object){
            return getJson(object,"yyyy-MM-dd");
    }


    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {

        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    //合并两个对象为json
    public static String TwoObjectToJson(Object object1,Object object2) throws JsonProcessingException {

        //将传入的object1对象转为树结点
        ObjectNode jsonNode =  mapper.valueToTree(object1);
        System.out.println("first:"+jsonNode);
        //将传入的对象转为json格式的newJson
        ObjectNode newJsonNode = mapper.valueToTree(object2);
        System.out.println("second:"+newJsonNode);
        //将树结点拼接为新树并转为json
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode.setAll(newJsonNode));
    }

    public static String ObjectAddStateCodeJson(Object object,int i) throws JsonProcessingException {
        String jsonObject = getJson(object);
        return StringAddStateCodeJson(jsonObject,i);
    }

    //给json对象新增statecode属性表状态
    public static String StringAddStateCodeJson(String oldJson,int i) throws JsonProcessingException {
        ObjectNode jsonNode = (ObjectNode) mapper.readTree(oldJson);
        jsonNode.put("statecode",i);
        return mapper.writeValueAsString(jsonNode);
    }
    //将key-value 转为json
    public static String keyValueToJson(String key,String value) throws JsonProcessingException {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put(key,value);
        return mapper.writeValueAsString(objectNode);

    }

    //给json对象新增key-value属性
    public static String StringAddKeyValue(String oldJson,String key,
                                           String value) throws JsonProcessingException {

        ObjectNode jsonNode = (ObjectNode) mapper.readTree(oldJson);
        jsonNode.put(key,value);

        return mapper.writeValueAsString(jsonNode);
    }
    //时间格式
    public static String getJson(Object object,String dataFormat){
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        mapper.setDateFormat(sdf);
        try {
            return mapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

}
