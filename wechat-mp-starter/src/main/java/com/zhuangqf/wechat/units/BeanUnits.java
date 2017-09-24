package com.zhuangqf.wechat.units;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhuangqf on 9/24/17.
 */
public class BeanUnits {

    private static Logger logger = LoggerFactory.getLogger(BeanUnits.class);

    public static <T> T newInstance(String instanceClassName){
        try {
            Class<?> instanceClass = Class.forName(instanceClassName);
            return (T) instanceClass.newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            logger.debug("create new instance error:",instanceClassName);
            e.printStackTrace();
        }
        return null;
    }

}
