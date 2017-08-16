package br.com.astri.model;

import java.io.Serializable;
import java.util.Date;

public class Talk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4572425778450359849L;
	
	private Long id;
	private String name;
	private String message;
	private Date dateMessage;
	private String cssClass;
	
	public Talk() {
		// TODO Auto-generated constructor stub
	}
	
	public Talk(String mensagem) {
		this.message = mensagem;
	}
	
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
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	@Override
	public String toString() {
		return "Talk [id=" + id + ", name=" + name + ", message=" + message + ", dateMessage=" + dateMessage + "]";
	}
	
	
}
