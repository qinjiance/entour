/**
 * 
 */
package com.qinjiance.tourist.util.cache;

import java.util.List;

import net.sf.ehcache.Ehcache;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.qinjiance.tourist.util.cache.exception.CacheInstanceNotFoundException;
import com.qinjiance.tourist.util.cache.exception.NullCacheManagerException;

/**
 * @author Jiance Qin
 * 
 * @Revision revision
 * 
 * @Date 2014-4-4
 * 
 * @Time 上午12:57:50
 * 
 */
public class EhCacheUtil {

	/**
	 * 
	 */
	public EhCacheUtil() {

	}

	/**
	 * Get all cached keys from cacheManager by name.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @return
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getAllKeys(CacheManager cacheManager, String cacheName)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Ehcache ehcache = getEhCache(cacheManager, cacheName);
		List<Object> keys = ehcache.getKeys();
		return (keys == null || keys.isEmpty()) ? null : keys;
	}

	/**
	 * Get native ehcache from cacheManager by name.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @return
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static Ehcache getEhCache(CacheManager cacheManager, String cacheName)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Cache cacheInstance = getCache(cacheManager, cacheName);
		if (cacheInstance == null) {
			throw new CacheInstanceNotFoundException("Cache instance: " + cacheName + " not found.");
		}
		return (Ehcache) cacheInstance.getNativeCache();
	}

	/**
	 * Get cache from cacheManager by name.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @return
	 * @throws NullCacheManagerException
	 */
	/**
	 * @param cacheManager
	 * @param cacheName
	 * @return
	 * @throws NullCacheManagerException
	 */
	public static Cache getCache(CacheManager cacheManager, String cacheName) throws NullCacheManagerException {

		if (cacheManager == null) {
			throw new NullCacheManagerException("Cache manager cannot be null.");
		}
		return cacheManager.getCache(cacheName);
	}

	/**
	 * Get object from cache.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @return
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static Object getCacheValue(CacheManager cacheManager, String cacheName, Object key)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Cache cacheInstance = getCache(cacheManager, cacheName);
		if (cacheInstance == null) {
			throw new CacheInstanceNotFoundException("Cache instance: " + cacheName + " not found.");
		}
		ValueWrapper valueWrapper = cacheInstance.get(key);
		return valueWrapper == null ? null : valueWrapper.get();
	}

	/**
	 * Put a key-value to cache.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws NullCacheManagerException
	 * @throws CacheInstanceNotFoundException
	 */
	public static void putCacheValue(CacheManager cacheManager, String cacheName, Object key, Object value)
			throws NullCacheManagerException, CacheInstanceNotFoundException {

		Cache cacheInstance = getCache(cacheManager, cacheName);
		if (cacheInstance == null) {
			throw new CacheInstanceNotFoundException("Cache instance: " + cacheName + " not found.");
		}
		cacheInstance.put(key, value);
	}

	/**
	 * Evict a cache value.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @throws NullCacheManagerException
	 * @throws CacheInstanceNotFoundException
	 */
	public static void evictCacheValue(CacheManager cacheManager, String cacheName, Object key)
			throws NullCacheManagerException, CacheInstanceNotFoundException {

		Cache cacheInstance = getCache(cacheManager, cacheName);
		if (cacheInstance == null) {
			throw new CacheInstanceNotFoundException("Cache instance: " + cacheName + " not found.");
		}
		cacheInstance.evict(key);
	}

	/**
	 * Acquire read lock on a key.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static void acquireReadLockOnKey(CacheManager cacheManager, String cacheName, Object key)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Ehcache ehcache = getEhCache(cacheManager, cacheName);
		ehcache.acquireReadLockOnKey(key);
	}

	/**
	 * Acquire write lock on a key.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static void acquireWriteLockOnKey(CacheManager cacheManager, String cacheName, Object key)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Ehcache ehcache = getEhCache(cacheManager, cacheName);
		ehcache.acquireWriteLockOnKey(key);
	}

	/**
	 * Release read lock on a key.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static void releaseReadLockOnKey(CacheManager cacheManager, String cacheName, Object key)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Ehcache ehcache = getEhCache(cacheManager, cacheName);
		ehcache.releaseReadLockOnKey(key);
	}

	/**
	 * Release write lock on a key.
	 * 
	 * @param cacheManager
	 * @param cacheName
	 * @param key
	 * @throws CacheInstanceNotFoundException
	 * @throws NullCacheManagerException
	 */
	public static void releaseWriteLockOnKey(CacheManager cacheManager, String cacheName, Object key)
			throws CacheInstanceNotFoundException, NullCacheManagerException {

		Ehcache ehcache = getEhCache(cacheManager, cacheName);
		ehcache.releaseWriteLockOnKey(key);
	}
}
