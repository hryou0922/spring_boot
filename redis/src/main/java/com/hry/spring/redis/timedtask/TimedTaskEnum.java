package com.hry.spring.redis.timedtask;

/**
 * 定义定时任务的枚举信息
 * 	定时任务可以有很多种，不同的定时任务的信息以不同redis key的后缀进行区分
 * @author Administrator
 *
 */
public enum TimedTaskEnum {
	ONCE_RUN(1, "OnceRun", "cdrs"), REPUSH(2, "Repush", "repush");
	
	private int id; // 类型id
	private String name; // 名称
	private String keySuffix; // 存储到redis中key的后缀
	
	private TimedTaskEnum(int id, String name, String keySuffix){
		this.id = id;
		this.name = name;
		this.keySuffix = keySuffix;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeySuffix() {
		return keySuffix;
	}

	public void setKeySuffix(String keySuffix) {
		this.keySuffix = keySuffix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
