package br.com.astri.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.astri.analyze.AnalyzeText;
import br.com.astri.model.Talk;
import br.com.astri.model.enums.AnswerTalk;

@RestController
public class TalkController {
	
	private AnalyzeText analyze = new AnalyzeText();
	
	
	@RequestMapping(value="/api/hello", method= RequestMethod.POST)
	public ResponseEntity<Talk> getApresentation() {
		
		Talk talk = new Talk.TalkBuilder()
					.withMessage("Olá Meu nome é Atris e vou lhe auxiliar na criação de documentos para RDM <br> Vamos começar ? <br> Que tipo de documento você deseja ? <br><br> <ul><li>RDM</li></ul>")
					.withDateTime(new Date()).build();
		
		return new ResponseEntity<Talk>(talk, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/api/talk",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public ResponseEntity<Talk> getMessage( @RequestBody List<Talk> talks) throws Exception {
		
		Talk talk = getCurrentTalk(talks);
		Talk lastTalk = getLastTalk(talks);
		String message = "";
		String context = "";
		
		if(lastTalk == null || StringUtils.isBlank(lastTalk.getContext())) {
			message = this.analyze.analyze(talk.getMessage());
			if(AnswerTalk.DOCUMENT_TYPE.getType().equals(message)) {
				context = "RDM";
			}
			
		}else {
			
			message = this.analyze.analyzeRDMType(talk.getMessage());
			
		}
		
		
		Talk response = new Talk.TalkBuilder()
				.withMessage(message)
				.build();
		
		if(talk.getContext() == null)
			response.setContext(context);
		
		return new ResponseEntity<Talk>(response, HttpStatus.OK);
	}
	
	private Talk getCurrentTalk(List<Talk> talks) throws Exception {
		
		if(talks == null || talks.isEmpty())
			throw new Exception("A conversa não existe");
		
		return talks.get(talks.size() - 1);
	}
	
	private Talk getLastTalk(List<Talk> talks) throws Exception {
		if(talks == null || talks.isEmpty())
			throw new Exception("A conversa não existe");
		
		Talk talk = talks.stream()
			.filter(userTalk -> (userTalk.getContext() != null && !userTalk.getContext().trim().isEmpty()))
			.reduce((a,b) -> b).orElse(null);
		
		return talk;
	}
	
}
