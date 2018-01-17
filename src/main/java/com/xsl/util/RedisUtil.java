package com.xsl.util;

import com.alibaba.fastjson.JSONObject;
import com.xsl.bean.ShiroUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redicache 工具类
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtil {

    public static final String REDIS_KEY = "shiro:";

    private Logger logger = Logger.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = scanKeys(pattern);
        if (keys != null && !keys.isEmpty())
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        try {
            Object result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            return result;
        } catch (Exception e) {
            logger.error("get " + key + "from redis error:" + e.getMessage());
        }
        return null;

    }

    /**
     * 缓存储存的值加一
     *
     * @param key
     */
    public void incr(final String key) {
        if (exists(key)) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key, 1);
        }
    }

    /**
     * 缓存储存的值减一
     *
     * @param key
     */
    public void decr(final String key) {
        if (exists(key)) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key, -1);
        }
    }

    /**
     * 缓存存储的值减指定值
     */
    public void decrCount(final String key, Integer count) {
        Integer decr_count = -count;
        if (exists(key)) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.increment(key, decr_count);
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getString(final String key) {
        try {
            Object result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            if (result != null) {
                return String.valueOf(result);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("redis set err: " + e.getMessage());
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("redis set expire err: " + e.getMessage());
        }
        return result;
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：
     * 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH
     * mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。 如果 key 不存在，一个空列表会被创建并执行
     * LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(final String key, List<Object> value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().leftPush(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("redis lpush list err: " + e.getMessage());
        }
        return result;
    }

    /**
     * 将一个或多个值 value 插入到列表 key 的表头 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头：
     * 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，这等同于原子性地执行 LPUSH
     * mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。 如果 key 不存在，一个空列表会被创建并执行
     * LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForList().leftPush(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("redis lpush object err: " + e.getMessage());
        }
        return result;
    }

    /**
     * LRANGE key start stop 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     *
     * @param key
     * @return
     */
    public List lrange(final String key, long start, long end) {
        List result = null;
        result = redisTemplate.opsForList().range(key, start, end);
        return result;
    }

    /**
     * LREM key count value 根据参数 count 的值，移除列表中与参数 value 相等的元素。 count 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。 count < 0 :
     * 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。 count = 0 : 移除表中所有与 value
     * 相等的值。
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public void lrem(final String key, long count, String value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    public Set<String> getKeys(String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public Set<String> scanKeys(final String pattern) {
        try {
            ScanOptions.ScanOptionsBuilder scanOptionsBuilder = new ScanOptions.ScanOptionsBuilder();
            scanOptionsBuilder.match(pattern);
            scanOptionsBuilder.count(100);
            Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(scanOptionsBuilder.build());
            Set<String> strings = new HashSet<>();
            while (cursor.hasNext()) {
                strings.add(new String(cursor.next()));
            }
            return strings;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 清空指定key开头的缓存
     *
     * @param patten
     */
    public void delKeys(final String patten) {
        Set<String> keys = scanKeys(patten);
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Long getLong(final String key) {
        try {
            Object result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            if (result != null) {
                return Long.valueOf(String.valueOf(result));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * 读取Integer类型的值
     *
     * @param key
     * @return
     */
    public Integer getInteger(final String key) {
        try {
            Object result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            if (result != null) {
                return Integer.valueOf(String.valueOf(result));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public void setAuthInfo(ShiroUser shiroUser, String jwt) {
        set(REDIS_KEY+"auth_info:"+jwt,JSONObject.toJSONString(shiroUser));
    }

    public void setAuthPermission(SimpleAuthorizationInfo info, String jwt) {
        set(REDIS_KEY+"auth_permission:"+jwt,JSONObject.toJSONString(info));
    }

    public SimpleAuthorizationInfo getAuthPermission(String jwt) {
        return JSONObject.parseObject(getString(REDIS_KEY+"auth_permission:"+jwt),SimpleAuthorizationInfo.class);
    }

    public ShiroUser getAuthInfo(String jwt) {
        return JSONObject.parseObject(getString(REDIS_KEY+"auth_info:"+jwt),ShiroUser.class);
    }
}
