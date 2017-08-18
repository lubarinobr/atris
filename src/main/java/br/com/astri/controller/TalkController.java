package br.com.astri.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.astri.analyze.AnalyzeText;
import br.com.astri.model.Talk;

@RestController
public class TalkController {
	
	private AnalyzeText analyze = new AnalyzeText();
	
	@RequestMapping("/api/hello")
	public String greet() {
		return "Hello World";
	}
	
	@RequestMapping(value="/api/talk",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Talk> getMessage(@RequestBody String message) {
		
		Talk talk = new Talk();
		talk.setMessage(analyze.analyzeToRDM(message));
		talk.setId(0l);
		talk.setCssClass("other");
		
		return new ResponseEntity<Talk>(talk, HttpStatus.OK);
	}
	
}
