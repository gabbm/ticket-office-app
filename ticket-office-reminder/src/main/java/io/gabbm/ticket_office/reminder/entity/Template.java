package io.gabbm.ticket_office.reminder.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "Template")
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "language", nullable = false)
	@Enumerated(EnumType.STRING)
	private Constants.Language language;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = false, length = Constants.DESCRIPTION_MAX)
	private String description;
	
	@Column(name = "subject", nullable = false, length = Constants.DESCRIPTION_MAX)
	private String subject;
	
	@Column(name="html", columnDefinition="CLOB NOT NULL") 
    @Lob 
	private String html;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "template_file", joinColumns = @JoinColumn(name = "template_id", 
    			referencedColumnName = "id"), 
    			inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id"))
	private Set<File> files = new HashSet<File>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "template_template", joinColumns = @JoinColumn(name = "template_id", 
    			referencedColumnName = "id"), 
    			inverseJoinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"))
	private Set<Template> templates = new HashSet<Template>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Constants.Language getLanguage() {
		return language;
	}

	public void setLanguage(Constants.Language language) {
		this.language = language;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Set<File> getFiles() {
		return files;
	}

	public Set<Template> getTemplates() {
		return templates;
	}
}
