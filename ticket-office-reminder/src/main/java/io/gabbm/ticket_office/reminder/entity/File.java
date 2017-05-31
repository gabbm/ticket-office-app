package io.gabbm.ticket_office.reminder.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.gabbm.ticket_office.reminder.util.Constants;

@Entity
@Table(name = "File")
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name="file", columnDefinition="CLOB NOT NULL") 
    @Lob 
	private byte[] file;
	
	@Column(name="image", nullable = false) 
	private boolean image = true;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "template_file", joinColumns = @JoinColumn(name = "file_id", 
    			referencedColumnName = "id"), 
    			inverseJoinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"))
	private Set<Template> templates = new HashSet<Template>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public Set<Template> getTemplates() {
		return templates;
	}
	
	public String buildURL(){
		return Constants.FILE_URL + id;
	}
}
