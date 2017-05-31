package com.hry.spring.spel.support;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventor {
	private String name;
    private String nationality;
    private String[] inventions;
    private Date birthdate;
    private PlaceOfBirth placeOfBirth;
    private List<PlaceOfBirth> placeOfBirthList;
    private Map<String,Integer> map = new HashMap<String, Integer>();

    public Inventor(String name, String nationality) {
        GregorianCalendar c= new GregorianCalendar();
        this.name = name;
        this.nationality = nationality;
        this.birthdate = c.getTime();
    }

    public Inventor(String name, Date birthdate, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    /**
     * 传入name是否和对象成员变量name相同
     * @param name
     * @return
     */
    public boolean nameIsSame(String name){
    	return this.name.equals(name);
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String[] getInventions() {
		return inventions;
	}

	public void setInventions(String[] inventions) {
		this.inventions = inventions;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public PlaceOfBirth getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(PlaceOfBirth placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public List<PlaceOfBirth> getPlaceOfBirthList() {
		return placeOfBirthList;
	}

	public void setPlaceOfBirthList(List<PlaceOfBirth> placeOfBirthList) {
		this.placeOfBirthList = placeOfBirthList;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
    
}
