package com.hry.spring.spel.value;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hry.spring.spel.support.Inventor;
import com.hry.spring.spel.support.PlaceOfBirth;

@SpringBootApplication
@Configurable
public class SpELAnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpELAnnotationApplication.class, args);
	}
	
	// 初始化测试所需要的对象
	@Bean("inventor")
	public Inventor getInventor(){
		Inventor invertor = new Inventor("name",new Date(),"nationality");
		// 设置placeOfBirth
		invertor.setPlaceOfBirth(new PlaceOfBirth("city", "country"));
		// 设置string数组
		invertor.setInventions(new String[]{"inventions_1","inventions_2","inventions_3","inventions_4"});
		// 设置PlaceOfBirth的List
		List<PlaceOfBirth> placeOfBirthList = new ArrayList<PlaceOfBirth>();
		placeOfBirthList.add(new PlaceOfBirth("city_list_1", "country_list_1"));
		placeOfBirthList.add(new PlaceOfBirth("city_list_2", "country_list_2"));
		placeOfBirthList.add(new PlaceOfBirth("city_list_3", "country_list_3"));
		invertor.setPlaceOfBirthList(placeOfBirthList);
		// 设置map值 
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("mapKey_1", 1);
		map.put("mapKey_2", 2);
		map.put("mapKey_3", 3);
		invertor.setMap(map);
		return invertor;
	}
}
