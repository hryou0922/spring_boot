package com.hry.spring.redis.repush.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hry.spring.redis.repush.support.RetryPushModel;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class OnceRunService implements IOnceRunService {

	@Override
	public void save(RetryPushModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RetryPushModel> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
