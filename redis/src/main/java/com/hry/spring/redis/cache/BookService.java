package com.hry.spring.redis.cache;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hry.spring.redis.cache.support.Book;
import com.hry.spring.redis.cache.support.BookQry;

/**
 * 缓存的基本用法
 * @author hry
 *
 */
@Component
public class BookService extends AbstractService {
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	// ==================== @Cacheable ========================
	/**
	 * cacheNames 设置缓存的值 
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames="book1", key="#id")
	public Book queryBookCacheable(String id){
		logger.info("queryBookCacheable,id={}",id);
		return repositoryBook.get(id);
	}
	
	@Cacheable(cacheNames="book2", key="#id")
	public Book queryBookCacheable_2(String id){
		logger.info("queryBookCacheable_2,id={}",id);
		return repositoryBook.get(id);
	}
	
	@Cacheable(cacheNames="book1", key="#qry.id")
	public Book queryBookCacheableByBookQry(BookQry qry){
		logger.info("queryBookCacheableByBookQry,qry={}",qry);
		String id = qry.getId();
		Assert.notNull(id, "id can't be null!");
		String name = qry.getName();
		Book book = null;
		if(id != null){
			book = repositoryBook.get(id);
			if(book != null && !(name != null && book.getName().equals(name))){
				book = null;
			}
		}
		return book;
	}
	
	// ==================== @CacheEvict ========================
	/**
	 * allEntries = true: 清空book1里的所有缓存
	 */
	@CacheEvict(cacheNames="book1", allEntries=true)
	public void clearBook1All(){
		logger.info("clearAll");
	}
	/**
	 * 对符合key条件的记录从缓存中book1移除
	 */
	@CacheEvict(cacheNames="book1", key="#id")
	public void updateBook(String id, String name){
		logger.info("updateBook");
		Book book = repositoryBook.get(id);
		if(book != null){
			book.setName(name);
			book.setUpdate(new Date());
		}
	}
	
	// ==================== @CachePut ========================
	/**
	 * 每次执行都会执行方法，无论缓存里是否有值，同时使用新的返回值的替换缓存中的值
	 * 	这里不同于@Cacheable：@Cacheable如果缓存没有值，从则执行方法并缓存数据，如果缓存有值，则从缓存中获取值
	 * @param id
	 * @return
	 */
	@CachePut(cacheNames="book1", key="#id")
	public Book queryBookCachePut(String id){
		logger.info("queryBookCachePut,id={}",id);
		return repositoryBook.get(id);
	}
	
}
