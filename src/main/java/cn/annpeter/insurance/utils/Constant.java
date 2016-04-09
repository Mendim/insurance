package cn.annpeter.insurance.utils;

import org.springframework.stereotype.Repository;

/**
 * Created by annpeter on 3/17/16.
 */
@Repository
public class Constant {
    public static Integer smsDuration = 60; //短信过期时间,单位秒
    public static Integer pageSize = 13;    //分页每页的大小


    public static boolean DEBUG = true;

}
