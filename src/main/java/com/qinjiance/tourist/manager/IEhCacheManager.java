/**
 * 
 */
package com.qinjiance.tourist.manager;

import java.util.List;

import net.sf.ehcache.Ehcache;

import org.springframework.cache.Cache;

import com.qinjiance.tourist.manager.impl.EhCacheManager.CacheType;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-4-4
 * 
 * @Time 上午12:58:52
 * 
 */
public interface IEhCacheManager {

	/**
	 * 获取缓存.
	 * 
	 * @param cacheType
	 * @return
	 */
	public Cache getCache(CacheType cacheType);

	/**
	 * 获取内部Ehcache缓存.
	 * 
	 * @param cacheType
	 * @return
	 */
	public Ehcache getNativeEhCache(CacheType cacheType);

	/**
	 * 从缓存里取值.
	 * 
	 * @param cacheType
	 * @param key
	 * @return
	 */
	public <T> T getFromCache(CacheType cacheType, Object key);

	/**
	 * 往缓存里存值.
	 * 
	 * @param cacheType
	 * @param key
	 * @param value
	 */
	public void putToCache(CacheType cacheType, Object key, Object value);

	/**
	 * 如果有值，则将缓存里的值清除.
	 * 
	 * @param cacheType
	 * @param key
	 */
	public void evictFromCache(CacheType cacheType, Object key);

	/**
	 * 获取缓存中的所有键.
	 * 
	 * @param cacheType
	 * @return
	 */
	public <T> List<T> getKeysFromCache(CacheType cacheType);

	/**
	 * 获取缓存中一个key的读锁.
	 * 
	 * @param cacheType
	 * @param key
	 */
	public void acquireReadLockOnKey(CacheType cacheType, Object key);

	/**
	 * 获取缓存中一个key的写锁.
	 * 
	 * @param cacheType
	 * @param key
	 */
	public void acquireWriteLockOnKey(CacheType cacheType, Object key);

	/**
	 * 释放缓存中一个key的读锁.
	 * 
	 * @param cacheType
	 * @param key
	 */
	public void releaseReadLockOnKey(CacheType cacheType, Object key);

	/**
	 * 释放缓存中一个key的写锁.
	 * 
	 * @param cacheType
	 * @param key
	 */
	public void releaseWriteLockOnKey(CacheType cacheType, Object key);
}
