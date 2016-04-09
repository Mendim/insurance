package cn.annpeter.insurance.utils;

import cn.annpeter.insurance.utils.Exceptions.NotFoundException;
import cn.annpeter.insurance.utils.Exceptions.SetValueErrorException;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * Created by annpeter on 3/12/16.
 */
@Component
public class CommonUtils {

    /**
     * 利用反射将obj的fieldName属性取出
     * @param fieldName
     * @param obj
     * @return
     * @throws NotFoundException
     */
    public static Object getFieldValueByName(String fieldName, Object obj) throws NotFoundException {
        if(obj == null){
            throw new NullPointerException("待取值对象不能为空");
        }
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(obj, new Object[] {});
            return value;
        }catch (Exception e){
            if(Constant.DEBUG){
                e.printStackTrace();
            }
            throw new NotFoundException("在"+obj.getClass().getName()+"中没有找到"+fieldName+"属性的get方法");
        }
    }

    /**
     * 利用反射,对obj的fieldName赋值为value
     * @param fieldName
     * @param obj
     * @param value
     * @throws SetValueErrorException
     */
    public static void setFieldValueByName(String fieldName, Object obj, Object value) throws SetValueErrorException{
        if(obj == null){
            throw new NullPointerException("待赋值对象不能为空");
        }
        if(value == null){
            return;
        }
        Method method = null;
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            method = obj.getClass().getMethod(setter, new Class[] {value.getClass()});
            method.invoke(obj, new Object[] {value});
        }catch (Exception e) {
            if(Constant.DEBUG){
                e.printStackTrace();
            }
            throw new SetValueErrorException("不能通过方法"+ method.getName() +"将"+value.getClass().toString()+"类型的值赋值给"
                    +obj.getClass().toString()+"的"+fieldName);
        }
    }

    /**
     * 通过properties中定义的属性,将fromObject中的值赋值到对应的toObject中
     * 该方法可以用来控制被修改的字段
     *
     * @param fromObject
     * @param toObject
     * @param properties
     * @throws NotFoundException
     */
    public static void modifyObj(Object fromObject, Object toObject, String[]properties) throws NotFoundException {
        for(String property:properties){
            Object newValue = CommonUtils.getFieldValueByName(property, fromObject);
            CommonUtils.setFieldValueByName(property, toObject, newValue);
        }
    }

    /**
     * 主要提供给单表或者说一次dao查询可以完成的任务
     * 因为一次dao只查询出一个对象,我们就可以定义jsonBean,将对象填充至jsonBean中.
     * @param sourObj
     * @param clazz
     * @return
     */
    public static Object getSmallObj(Object sourObj, Class clazz) throws NotFoundException {
        try{
            Object destObj = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for(int fieldIndex = 0; fieldIndex < fields.length; fieldIndex++){
                Field field = fields[fieldIndex];

                String fieldName = field.getName();

                Object fieldValue = CommonUtils.getFieldValueByName(fieldName, sourObj);

                //将获取到的值赋值给新的对象
                CommonUtils.setFieldValueByName(field.getName(), destObj, fieldValue);
            }
            return destObj;
        }catch (Exception e){
            if(Constant.DEBUG){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 从一个list中,将list的子元素转换为clazz类型的子元素,
     * 注意,clazz类型中的名称一定要与list子元素相匹配
     * @param sourList
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List<?> getJsonList(List<?> sourList, Class clazz) throws NotFoundException {

        Field[] fields = clazz.getDeclaredFields();
        List<Object> result = new ArrayList<>();

        Iterator<?> iterator = sourList.iterator();

        while (iterator.hasNext()) {
            Object sourObj = iterator.next();
            Object destObj = getSmallObj(sourObj, clazz);

            result.add(destObj);
        }
        return result;
    }

    public static String getIpAddr() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
	 * 用数字和字符生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomCharAndNumr(int length) {
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
        	
            // 输出字母还是数字, num=1  char=0
            int isNum = random.nextInt(2) % 2; 
            
            if (isNum == 1) { // 数字
            	val.append(random.nextInt(10));
            } else {//字母
            	// 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
                val.append((char)(choice + random.nextInt(26)));
            }
        }
        return val.toString();
    }

    /**
     * 用数字生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomNum(int length){
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
                val.append(random.nextInt(10));
        }
        return val.toString();
    }

    /**
     * 用字母生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomChar(int length){
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
            val.append((char)(choice + random.nextInt(26)));
        }
        return val.toString();
    }
	
	/**
	 * 将字符串value，使用c， 扩充称为长度为len的字符串
	 * @param value
	 * @param len
	 * @param c
	 * @return
	 */
	public static String extendStrUseChar(String value, int len, char c){
		
		if (value.length() < len) {
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < len - value.length(); i++) {
				sBuffer.append(c);
			}
			value = sBuffer.append(value).toString();
		}
		return value;
	}


    /**
     * 根据日期格式, 获取日期字符串
     * @param date
     * @param format
     * @return
     */
    public static String getDateStr(Date date, String format){
        String retStr = "";
        if (date != null) {
            SimpleDateFormat dataFormat = new SimpleDateFormat(format);
            retStr = dataFormat.format(date);
        }
        return retStr;
    }

}
