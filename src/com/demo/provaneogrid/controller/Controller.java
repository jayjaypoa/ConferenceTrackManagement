package com.demo.provaneogrid.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.provaneogrid.business.ConferenceBusiness;
import com.demo.provaneogrid.constants.Constant;
import com.demo.provaneogrid.util.ListToFileUtil;

@RestController
@RequestMapping("/conference")
public class Controller {
    
    /**
     * Realiza o processamento do arquivo de input dos dados.
     **/
    @RequestMapping(value = "/executar-processamento", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity executarProcessamento() {
    	
    	JSONObject objResponse = new JSONObject();
    	
    	// Obtém o caminho, nome e extensão do arquivo de input dos dados
    	String caminhoArquivo = System.getProperty("user.dir") 
    			+ Constant.LOCAL_ARQUIVO_ENTRADA + Constant.NOME_ARQUIVO_ENTRADA;
    	
    	// Obtém o conteúdo do arquivo no formato de List de String
		List<String> conteudoArquivo = ListToFileUtil.getListFromFile(caminhoArquivo);
		
		try {
			
			// Realiza o processamento a fim de gerar as Tracks
			ConferenceBusiness conferenceBusiness = new ConferenceBusiness();
			objResponse = conferenceBusiness.montarConferencia(conteudoArquivo);
			
		} catch (Exception e) {
			e.printStackTrace();
			String msgError = "ERROR: " + e.getMessage();
			System.out.println(msgError);
			// Retorna indicando erro no processamento
			return new ResponseEntity(msgError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Retorna indicando sucesso no processamento
		return new ResponseEntity(objResponse, HttpStatus.OK);
		
    }
   
    
}
