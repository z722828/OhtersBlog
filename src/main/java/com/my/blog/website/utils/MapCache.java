package com.my.blog.website.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map缓存实现
 */
public class MapCache {

    /**
     * 默认存储1024个缓存
     */
    private static final int DEFAULT_CACHES = 1024;

    /**
     * 单例
     */
    private static final MapCache INS = new MapCache();

    /**
     * 获取实例
     * @return
     */
    public static MapCache single() {
        return INS;
    }

    /**
     * 缓存容器
     */
    private Map<String, CacheObject> cachePool;

    /**
     * 使用默认值构造缓存容器
     */
    public MapCache() {
        this(DEFAULT_CACHES);
    }

    /**
     * 指定大小构造缓存容器
     * @param cacheCount
     */
    public MapCache(int cacheCount) {
        cachePool = new ConcurrentHashMap<>(cacheCount);
    }

    /**
     * 根据键值读取一个缓存
     * @param key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        CacheObject cacheObject = cachePool.get(key);
        if(null != cacheObject) {
            long cur = System.currentTimeMillis() / 1000;
            /**
             * 缓存内容没有过期才返回
             */
            if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
                Object result = cacheObject.getValue();
                return (T) result;
            }
        }
        return null;
    }

    /**
     * 读取一个hash类型缓存
     * @param key
     * @param field
     * @param <T>
     * @return
     */
    public <T> T hget(String key, String field) {
        key = key + ":" + field;
        return this.get(key);
    }

    /**
     * 设置一个不带过期时间的缓存 （expired = -1）
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        this.set(key, value, -1);
    }

    /**
     * 设置一个带过期时间的缓存
     * @param key
     * @param value
     * @param expired
     */
    public void set(String key, Object value, long expired) {
        expired = expired > 0 ? System.currentTimeMillis() /1000 + expired :expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    /**
     * 设置一个hash缓存
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        this.hset(key, field, value, -1);
    }

    /**
     * 设置一个hash缓存并带过期时间
     * @param key
     * @param field
     * @param value
     * @param expired
     */
    public void hset(String key, String field, Object value, long expired) {
        key = key + ":" + field;
        expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    /**
     * 根据key删除缓存
     * @param key
     */
    public void del(String key) {
        cachePool.remove(key);
    }

    /**
     * 根据key和field删除缓存
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        key = key + ":" + field;
        this.del(key);
    }

    /**
     * 清空缓存
     */
    public void clean() {
        cachePool.clear();
    }

    /**
     * CacheObject
     */
    static class CacheObject {

        /**
         * 键
         */
        private String key;

        /**
         * 值
         */
        private Object value;

        /**
         * 过期时间
         */
        private long expired;

        public CacheObject(String key, Object value, long expired) {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public long getExpired() {
            return expired;
        }
    }
}
