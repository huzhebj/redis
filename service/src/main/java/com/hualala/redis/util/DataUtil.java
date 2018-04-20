package com.hualala.redis.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhaoxiaodong on 2016/12/1.
 */
public class DataUtil {

    private static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        //如果bean  是map
        if (bean instanceof Map) {
            map = (Map<String, Object>)bean;
        } else {
            try {
                Map<String, String> tmap = BeanUtils.describe(bean);
                map.putAll(tmap);
                map.remove("class");
            } catch (Exception e) {
                logger.error("bean To Map system error ", e);
                logger.error("bean [" + bean + "]");
            }
        }
        return map;
    }
    public static Map<String, Object> beanToMap(Object bean, boolean includeNull) {
        Map<String, Object> map = new HashMap<String, Object>();
        //如果bean  是map
        if (bean instanceof Map) {
            map = (Map<String, Object>)bean;
        } else {
            try {
                Map<String, String> tmpMap = BeanUtils.describe(bean);
                tmpMap.remove("class");
                if (includeNull) {
                    map.putAll(tmpMap);
                } else {
                    Iterator<String> ite = tmpMap.keySet().iterator();
                    while (ite.hasNext()) {
                        String key = ite.next();
                        if (tmpMap.get(key) != null) {
                            map.put(key, tmpMap.get(key));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("bean To Map system error ", e);
                logger.error("bean [" + bean + "]");
            }
        }
        return map;
    }

    public static void mapToBean(Map<String, Object> dest, Object orig) {
        Iterator<String> iterator = dest.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                BeanUtils.setProperty(orig, key, dest.get(key));
            } catch (Exception e) {
                logger.error("system error map to bean" , e);
                logger.error("dest [" + dest + "]");
                continue;
            }
        }
    }

    public static <T> T mapToBean(Map<String, Object> dest, Class<T> className) {
        try {
            T t = className.newInstance();
            mapToBean(dest, t);
            return t;
        } catch (Exception e) {
            logger.error("system error " , e);
            logger.error("dest [" + dest + "]");
        }
        return null;
    }

    public static <T> List<T> mapToBean(List<Map<String, Object>> dests, Class<T> className) {

        if(dests == null)
            return null;

        List<T> list = new ArrayList<T>();

        for(Map<String,Object> dest : dests)
            list.add(mapToBean(dest,className));

        return list;
    }

    public static <T> boolean matching(List<T> o1, List<T> o2){
        if (o1 == null && o2 == null) return true;
        if (o1.size() == 0 && o2.size() == 0) return true;
        if (o1.size() != o2.size()) return false;
        for(T o : o1)
            if(!o2.contains(o))
                return false;
        return true;
    }

    public static <T> List<T> diff(List<T> o1, List<T> o2){
        if (o1 == null || o1.size()== 0 || o2 == null|| o2.size() == 0) return o1;
        List<T> list = new ArrayList<>();
        for(T o : o1)
            if(!o2.contains(o))
                list.add(o);
        return list;
    }

    public static <T> List<T> mapListToBeanList(List<Map<String, Object>> mapList, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        if (mapList == null || mapList.size() == 0)
            return list;
        for (Map<String, Object> m : mapList) {
            list.add(mapToBean(m, tClass));
        }
        return list;
    }

    public static <T> List<Map<String, Object>> beanListToMapList(List<T> dataList) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (dataList == null || dataList.size() == 0)
            return mapList;
        for (T o : dataList) {
            mapList.add(beanToMap(o));
        }
        return mapList;
    }

    public static List<String> getShopIDList(String shopID,String shopIDs){
        List<String> shopIDList = new ArrayList<>();
        if(StringUtils.isNotBlank(shopID))
            shopIDList.add(shopID);
        if(StringUtils.isNotBlank(shopIDs)){
            shopIDList.addAll(Arrays.asList(shopIDs.split(",")));
        }
        return shopIDList;
    }

}
