package com.netease.yuedu.snail.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.netease.yuedu.snail.biz.model.entity.ReadTimeTrade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public  class JsonUtil {

    private JsonUtil(){}

    public static String toString(Object obj){
        if (obj != null) {
            return JSON.toJSONString(obj);
        }
        return "";
    }

    public static <T> T toBean(String str, Class<T> clazzType){
        if (StringUtils.isNotEmpty(str) && clazzType != null) {
            return JSON.parseObject(str,clazzType);
        }
        return null;
    }

    public static <T> List<T> toList(String str, Class<T> clazzType){
        List<T> retList = new LinkedList<T>();
        if (StringUtils.isNotEmpty(str) && clazzType != null) {
            List<String> list = JSON.parseObject(str, List.class);
            if (CollectionUtils.isNotEmpty(list)) {
                for (String string : list) {
                    T t = JsonUtil.toBean(string, clazzType);
                    if (t != null) {
                        retList.add(t);
                    }
                }
            }
        }
        return retList;
    }

    public static void main(String[] args) {
        String str="{\"tradeStatus\":\"\",\"startTime\":null,\"endTime\":null,\"userId\":\"\",\"userName\":\"\",\"currentPage\":0,\"pageSize\":10,\"totalCount\":0}";
        ReadTimeTrade readTimeTrade = JSON.parseObject(str, ReadTimeTrade.class, Feature.IgnoreNotMatch);
        System.out.println(readTimeTrade);
    }

}
