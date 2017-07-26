package com.hry.spring.redis.timedtask.simple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.hry.spring.redis.timedtask.ITimedTaskService;
import com.hry.spring.redis.timedtask.TimedTaskEnum;

/**
 * 一次任务的服务类
 * @author Administrator
 *
 */
@Component
public class OnceRunService implements IOnceRunService {
	
	private String keySuffix = TimedTaskEnum.ONCE_RUN.getKeySuffix();
	
	@Autowired
	private ITimedTaskService timedTaskService;
	
	@Override
	public void save(OnceRunModel model, Date executeTime) {
		Assert.notNull(model, "model can't be null!");
		Assert.notNull(executeTime, "executeTime can't be null!");
		// 保存到缓存
		timedTaskService.add(keySuffix, executeTime, model);
	}

	@Override
	public void delete(String id) {
		Assert.notNull(id, "id can't be null!");
		timedTaskService.bathDel(keySuffix, id);
	}

	@Override
	public List<OnceRunModel> queryAll() {
		List<OnceRunModel> list = timedTaskService.getTimedTaskContent(keySuffix, OnceRunModel.class);
		return list;
	}

}
