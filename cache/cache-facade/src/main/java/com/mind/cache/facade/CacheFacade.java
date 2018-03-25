package com.mind.cache.facade;

import java.util.Map;
import java.util.Set;

/**
 * Created by wangyunlong on 2016/11/21.
 */
public interface CacheFacade {

    void set(String key, Object o);

    Long setnx(final String key, final String value);

    <K> K get(String key, Class<K> clazz);

    String get(String key);

    Long del(String key);

    Boolean exists(String key);

    Long pexpire(String key, long milliseconds);

    Long ttl(String key);

    void sadd(String key, String... members);

    void srem(String key, String... members);

    Set<String> smembers(String key);

    Boolean sismember(String key, String member);

    Long scard(String key);


    void hset(String key, String field, String value);

    void hmset(String key, Map<String, String> map);

    String hget(String key, String field);

    Map<String,String> hgetAll(String key);

    void hdel(String key, String... fields);

    Boolean hexists(String key, String field);


}

