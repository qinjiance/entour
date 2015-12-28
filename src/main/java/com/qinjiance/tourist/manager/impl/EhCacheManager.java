/**
 * 
 */
package com.qinjiance.tourist.manager.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.qinjiance.tourist.manager.IEhCacheManager;
import com.qinjiance.tourist.util.cache.EhCacheUtil;
import com.qinjiance.tourist.util.cache.exception.CacheException;

/**
 * @author "Jiance Qin"
 * 
 * @date 2014年3月14日
 * 
 * @time 上午11:04:28
 * 
 * @desc
 * 
 */
@Service
public class EhCacheManager implements IEhCacheManager {

	protected final static Logger logger = LoggerFactory.getLogger(EhCacheManager.class);

	/**
	 * cache名称，对于cache配置文件中的cache名.
	 * 
	 * @author Jiance Qin
	 * 
	 * @Revision revision
	 * 
	 * @Date 2014-5-16
	 * 
	 * @Time 上午12:11:15
	 * 
	 */
	public static enum CacheType {
		DAYLY("daylyCache"), MIN1("min1Cache"), MIN3("min3Cache");
		private CacheType(String cacheName) {

			this.cacheName = cacheName;
		}

		private String cacheName;

		/**
		 * @return the cacheType
		 */
		public String getCacheName() {

			return cacheName;
		}
	}

	@Autowired
	private CacheManager cacheManager;

	private Cache dalyCache;
	private Cache min3Cache;
	private Cache min1Cache;

	/**
	 * @throws CacheException
	 * 
	 */
	public EhCacheManager() {

	}

	/**
	 * @throws CacheException
	 */
	@PostConstruct
	public void postInit() throws CacheException {

		dalyCache = EhCacheUtil.getCache(cacheManager, CacheType.DAYLY.getCacheName());
		min3Cache = EhCacheUtil.getCache(cacheManager, CacheType.MIN3.getCacheName());
		min1Cache = EhCacheUtil.getCache(cacheManager, CacheType.MIN1.getCacheName());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#getCache(com.biancloud
	 * .biancloudweb.manager.impl.EhCacheManagerImpl.CacheType)
	 */
	@Override
	public Cache getCache(CacheType cacheType) {

		if (cacheType == null) {
			return null;
		}
		switch (cacheType) {
		case DAYLY:
			return dalyCache;
		case MIN1:
			return min1Cache;
		case MIN3:
			return min3Cache;
		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#getNativeEhCache(com
	 * .biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType)
	 */
	@Override
	public Ehcache getNativeEhCache(CacheType cacheType) {

		if (cacheType == null) {
			return null;
		}
		switch (cacheType) {
		case DAYLY:
			return (Ehcache) dalyCache.getNativeCache();
		case MIN1:
			return (Ehcache) min1Cache.getNativeCache();
		case MIN3:
			return (Ehcache) min3Cache.getNativeCache();
		default:
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#getFromCache(com.biancloud
	 * .biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFromCache(CacheType cacheType, Object key) {

		Cache cache = getCache(cacheType);
		if (cache == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Get from ").append(cacheType.getCacheName()).append(": key=").append(key);
		logger.debug(sb.toString());
		ValueWrapper obj = cache.get(key);
		return obj == null ? null : (T) obj.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#putToCache(com.biancloud
	 * .biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	public void putToCache(CacheType cacheType, Object key, Object value) {

		Cache cache = getCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Put to ").append(cacheType.getCacheName()).append(": key=").append(key);
		logger.debug(sb.toString());
		cache.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#evictFromCache(com.
	 * biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@Override
	public void evictFromCache(CacheType cacheType, Object key) {

		Cache cache = getCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Evict from ").append(cacheType.getCacheName()).append(": key=").append(key);
		logger.debug(sb.toString());
		cache.evict(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#getKeysFromCache(com
	 * .biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getKeysFromCache(CacheType cacheType) {

		Ehcache cache = getNativeEhCache(cacheType);
		if (cache == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Get all keys from ").append(cacheType.getCacheName());
		logger.debug(sb.toString());
		List<Object> keys = cache.getKeys();
		return (keys == null || keys.isEmpty()) ? null : (List<T>) keys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#acquireReadLockOnKey
	 * (com.biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@Override
	public void acquireReadLockOnKey(CacheType cacheType, Object key) {

		Ehcache cache = getNativeEhCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Acquire read lock in ").append(cacheType.getCacheName()).append(" on key: ").append(key);
		logger.debug(sb.toString());
		cache.acquireReadLockOnKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#acquireWriteLockOnKey
	 * (com.biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@Override
	public void acquireWriteLockOnKey(CacheType cacheType, Object key) {

		Ehcache cache = getNativeEhCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Acquire write lock in ").append(cacheType.getCacheName()).append(" on key: ").append(key);
		logger.debug(sb.toString());
		cache.acquireWriteLockOnKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#releaseReadLockOnKey
	 * (com.biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@Override
	public void releaseReadLockOnKey(CacheType cacheType, Object key) {

		Ehcache cache = getNativeEhCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Release read lock in ").append(cacheType.getCacheName()).append(" on key: ").append(key);
		logger.debug(sb.toString());
		cache.releaseReadLockOnKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.biancloud.biancloudweb.manager.EhCacheManager#releaseWriteLockOnKey
	 * (com.biancloud.biancloudweb.manager.impl.EhCacheManagerImpl.CacheType,
	 * java.lang.Object)
	 */
	@Override
	public void releaseWriteLockOnKey(CacheType cacheType, Object key) {

		Ehcache cache = getNativeEhCache(cacheType);
		if (cache == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Release write lock in ").append(cacheType.getCacheName()).append(" on key: ").append(key);
		logger.debug(sb.toString());
		cache.releaseWriteLockOnKey(key);
	}

}
