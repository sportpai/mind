package com.mind.cache.facade.impl;

import com.alibaba.fastjson.JSON;
import com.mind.cache.facade.CacheFacade;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * Created by lc on 2016/10/10.
 */
@Service("cacheFacade")
public class CacheFacadeImpl implements CacheFacade {

    @Resource
    private ShardedJedisPool shardedJedisPool;

    @Override
    public void set(String key, Object o) {
        if(o instanceof String ){
            shardedJedisPool.getResource().set(key,o.toString());
        }else {
            shardedJedisPool.getResource().set(key,JSON.toJSONString(o));
        }
    }

    @Override
    public Long setnx(String key, String value) {
        return shardedJedisPool.getResource().setnx(key, value);
    }

    @Override
    public <K> K get(String key, Class<K> clazz) {
        K k = null;
        String entityClassStr = shardedJedisPool.getResource().get(key);
        if(StringUtils.hasText(entityClassStr)){
            k = JSON.parseObject(entityClassStr,clazz);
        }
        return k;
    }

    @Override
    public String get(String key) {
        return shardedJedisPool.getResource().get(key);
    }

    @Override
    public Long del(String key) {
        return shardedJedisPool.getResource().del(key);
    }

    @Override
    public Boolean exists(String key) {
        return shardedJedisPool.getResource().exists(key);
    }

    @Override
    public Long pexpire(String key, long milliseconds) {
        return shardedJedisPool.getResource().pexpire(key, milliseconds);
    }

    @Override
    public Long ttl(String key) {
        return shardedJedisPool.getResource().ttl(key);
    }


    @Override
    public void sadd(String key, String... members) {
        shardedJedisPool.getResource().sadd(key, members);
    }

    @Override
    public void srem(String key, String... members) {
        shardedJedisPool.getResource().srem(key, members);
    }

    @Override
    public Set<String> smembers(String key) {
        return shardedJedisPool.getResource().smembers(key);
    }

    @Override
    public Boolean sismember(String key, String member) {
        return shardedJedisPool.getResource().sismember(key, member);
    }

    @Override
    public Long scard(String key) {
        return shardedJedisPool.getResource().scard(key);
    }

    @Override
    public void hset(String key, String field, String value) {
        shardedJedisPool.getResource().hset(key, field, value);
    }

    @Override
    public void hmset(String key, Map<String, String> map) {
        shardedJedisPool.getResource().hmset(key, map);
    }

    @Override
    public String hget(String key, String field) {
        return  shardedJedisPool.getResource().hget(key, field);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return shardedJedisPool.getResource().hgetAll(key);
    }

    @Override
    public void hdel(String key, String... fields) {
        shardedJedisPool.getResource().hdel(key, fields);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return shardedJedisPool.getResource().hexists(key,field);
    }
}
