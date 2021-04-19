package com.trial.test.data_metica;

public class Employee {
	
	private String Name;
	private Long Age;
	private String City;
	public Employee(String name, Long age, String city) {
		super();
		Name = name;
		Age = age;
		City = city;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getAge() {
		return Age;
	}
	public void setAge(Long age) {
		Age = age;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}

	
}
