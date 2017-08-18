package br.com.astri.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TalkHistory {

	@JsonProperty("talk")
	List<Talk> talks;

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

	@Override
	public String toString() {
		return "TalkHistory [talks=" + talks + "]";
	}

	
}
