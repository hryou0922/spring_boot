package com.hry.spring.redis.timedtask.repush;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hry.spring.redis.timedtask.ITimedTaskService;
import com.hry.spring.redis.timedtask.TimedTaskEnum;

@Service
public class RetryPushService implements IRetryPushService{
	
	private String keySuffix = TimedTaskEnum.REPUSH.getKeySuffix();
	
	@Autowired
	private ITimedTaskService timedTaskService;

	@Override
	public void save(RetryPushModel model) {
		// 输入验证
		Assert.notNull(model, "model can't be null!");
		Assert.notNull(model.getUrl(), "url can't be null!");
		Assert.notNull(model.getType(), "type cant't be null!");
		Date executeTime = model.getPushNextTime();
		Assert.notNull(executeTime, "executeTime can't be null!");
		// 给一个默认值
		model.setPushTime(model.getPushTime() == null ? 1 : model.getPushTime());
		// 保存到缓存
		model = timedTaskService.add(keySuffix, executeTime, model);
	}

	@Override
	public void delete(String id) {
		Assert.notNull(id, "id can't be null!");
		timedTaskService.bathDel(keySuffix, id);
	}

	@Override
	public List<RetryPushModel> queryAll() {
		List<RetryPushModel> list = timedTaskService.getTimedTaskContent(
				keySuffix, RetryPushModel.class);
		// 按照时间排序
		Collections.sort(list, new Comparator<RetryPushModel>() {
			@Override
			public int compare(RetryPushModel o1, RetryPushModel o2) {
				if(o1 == null || o1.getPushNextTime() == null){
					return -1;
				}
				if(o2 == null || o2.getPushNextTime() == null){
					return 1;
				}
				return (int)(o1.getPushNextTime().getTime() - o1.getPushNextTime().getTime());
			}
		});
		return list;
	}

}
