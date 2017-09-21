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

import br.com.astri.factory.AnalyzeFactory;
import br.com.astri.model.Context;
import br.com.astri.model.Talk;
import br.com.astri.model.Talk.TalkBuilder;
import br.com.astri.model.enums.AnswerTalk;
import br.com.astri.model.enums.ContextEnum;
import br.com.astri.nlp.Analyze;
import br.com.astri.nlp.IAnalyze;

@RestController
public class TalkController {
	
	private IAnalyze service = new Analyze();
	
	
	@RequestMapping(value="/api/hello", method= RequestMethod.POST)
	public ResponseEntity<Talk> getApresentation() {
		
		Talk talk = new Talk.TalkBuilder()
					.withMessage("Olá Meu nome é Atris e vou lhe auxiliar na criação de documentos <br> Vamos começar ? <br> Que tipo de documento você deseja ? <br><br> <ul><li>Informativo</li><li>Declaração</li></ul>")
					.withDateTime(new Date()).build();
		
		return new ResponseEntity<Talk>(talk, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/api/talk",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public ResponseEntity<Talk> getMessage( @RequestBody List<Talk> talks) throws Exception {
		
		Talk talk = getCurrentTalk(talks);
		
		Talk response = null;
		String contextType = null;
		
		talk.setContext(getLastTalk(talks).getContext());
		
		contextType = service.getContext(talk);
		
		if(StringUtils.isNotBlank(contextType)){
			final Context context = AnalyzeFactory.getContext(contextType);
			response = new Talk.TalkBuilder()
					.withMessage(context.getMessage())
					.withContext(contextType)
					.build();
		}
		
		
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
			.reduce((a,b) -> b).orElse(new Talk());
		
		return talk;
	}
	
	private String getTemplateFile(List<Talk> talks) throws Exception {
		Talk template = talks.stream()
					.filter(userTalk -> (StringUtils.isEmpty(userTalk.getTemplateFile())))
					.reduce((a, b) -> b).orElse(new Talk());
		
		
		if(StringUtils.isEmpty(template.getTemplateFile())){
			return "";
		}
		
		return template.getTemplateFile();
	}
	
}
