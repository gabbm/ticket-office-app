package io.gabbm.ticket_office.api.entity;

import org.springframework.data.annotation.Id;

public class Activity {
	
	@Id
	public Integer id;
	
	/*
	 * Activity Id from Ticket Management Platform
	 */
	public String tmId;
	public String title;
	public String description;
	public String image;
	public String type;
	
	public Activity(){}
	
	public Activity(String tmId, String title, String description, 
			String image, String type){
		this.tmId = tmId;
		this.title = title;
		this.description = description;
		this.image = image;
		this.type = type;
	}
	
	@Override
	public String toString(){
		return String.format(
				"Activity[id=%s, tmId=%s, title='%s']", 
				id, tmId, title);
	}
}
