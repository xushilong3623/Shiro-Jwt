package com.xsl.util;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

    private static final String MOBILENUM_REGULAR = "^(1[3,4,5,7,8])\\d{9}$"; //手机号

    /**
     * 判断是否为空，为空返回true,否则返回false.
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }

    /**
     * 判断是否为空，为空返回true,否则返回false.
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 验证是否为手机号
     *
     * @param telephone
     * @return 符合规则返回true
     */
    public static boolean isTel(String telephone) {
        Pattern p = Pattern.compile(MOBILENUM_REGULAR);
        Matcher m = p.matcher(telephone);
        return m.matches();
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 校验list中有无重复值
     *
     * @param list
     * @return
     */
    public static boolean hasSame(List<? extends Object> list) {
        if (null == list)
            return false;
        return list.size() == new HashSet<Object>(list).size();
    }

    /**
     * 验证邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {// 验证邮箱的正则表达式
        if (ValidateUtil.isNullOrEmpty(email)){
            return true;
        }
        String format = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        if (email.matches(format)) {
            return true;// 邮箱名合法，返回true
        } else {
            return false;// 邮箱名不合法，返回false
        }
    }

    /**
     * 去除重复项
     *
     * @param list
     * @return
     */
    public static List<String> getNoRepeatList(List<String> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

}
