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
	private Date dateTime;
	private String cssClass;
	private String context;
	private int step;
	
	public Talk() {}
	
	public Talk(String mensagem) {
		this.message = mensagem;
	}
	
	
	public Talk(String name, String message, Date dateTime, String cssClass, String context) {
		this.name = name;
		this.message = message;
		this.dateTime = dateTime;
		this.cssClass = cssClass;
		this.context = context;
	}
	
	public Talk(TalkBuilder TalkBuilder) {
		this.id = TalkBuilder.id;
		this.name = TalkBuilder.name;
		this.message = TalkBuilder.message;
		this.dateTime = TalkBuilder.dateTime;
		this.cssClass = TalkBuilder.cssClass;
		this.context = TalkBuilder.context;
		this.step = TalkBuilder.step;
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
	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public String toString() {
		return "Talk [id=" + id + ", name=" + name + ", message=" + message + ", dateTime=" + dateTime + ", cssClass="
				+ cssClass + ", context=" + context + "]";
	}
	
	public static class TalkBuilder {
		private Long id;
		private String name;
		private String message;
		private Date dateTime;
		private String cssClass;
		private String context;
		private int step;
		
		public TalkBuilder() {
			this.id = 01l;
			this.cssClass = "other";
			this.dateTime = new Date();
		}
		
		public TalkBuilder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public TalkBuilder withName(String name) {
			this.name = name;
			return this;
		}
		
		public TalkBuilder withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public TalkBuilder withDateTime(Date dateTime) {
			this.dateTime = dateTime;
			return this;
		}
		
		public TalkBuilder withCssClass(String cssClass) {
			this.cssClass = cssClass;
			return this;
		}
		
		public TalkBuilder withContext(String context) {
			this.context = context;
			return this;
		}
		
		public TalkBuilder withStep(Talk talk) {
			if(talk == null) {
				this.step = 1;
			}else {
				this.step = talk.getStep() + 1;
			}
			return this;
		}
		
		public Talk build() {
			return new Talk(this);
		}
	}
	
}
