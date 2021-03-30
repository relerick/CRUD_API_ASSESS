package com.example.catCrud;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;



import javax.persistence.*;

@Entity
@Table(name = "cat")
public class Cat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)


	Long id;
	String name;
	String breed;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}








}
