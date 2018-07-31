package com.demo.provaneogrid.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.demo.provaneogrid.constants.Constant;
import com.demo.provaneogrid.exception.ConferenceException;
import com.demo.provaneogrid.model.GenerateAfternoon;
import com.demo.provaneogrid.model.GenerateMorning;
import com.demo.provaneogrid.model.Session;
import com.demo.provaneogrid.model.Talk;
import com.demo.provaneogrid.model.Track;
import com.demo.provaneogrid.util.DateTimeUtil;

public class ConferenceBusiness {

	
	public ConferenceBusiness() {
	}
	
	/**
	 * Monta a Conferência a partir dos dados lidos.
	 * @param conteudoArquivo Conteudo do arquivo de input no formato List de String 
	 * @return JSONObject Objeto no formato JSON contendo todos os dados de retorno 
	 * 				      para ser utilizado pelo frontend.
	 * @throws Exception 
	 **/
	public JSONObject montarConferencia(List<String> conteudoArquivo) throws Exception {
		
		JSONObject jObjectFinal = new JSONObject();
		JSONArray jArrayFinal = new JSONArray();
		
		// Obtém a lista de retorno
		List<Track> lista = this.gerarListaTracks(conteudoArquivo);
		
		// Monta o retorno
		int numberTrack = 1;
		StringBuilder builder = new StringBuilder();		
		for (Track track : lista) {
			System.out.println("Track " + numberTrack);
			
			// Monta objeto JSON de retorno
			JSONObject jObject = new JSONObject();
			jObject.put("track", numberTrack);
	        
	        // Percorre as Sessions da Track
			JSONArray jArray = new JSONArray();
			for (Session session : track.getSessions()) {
				
				// Monta linha de retorno
				String linha = builder 
						.append(DateTimeUtil.formatDate(session.getHoraInicio()))
						.append(" ")
						.append(session.getTalk().getTitulo())
						.append(" ")
						.append(getDurationTitle(session))
						.toString();
				
				// Exibe o conteúdo no console
				System.out.println(linha);
				
				// Repassa a linha ao objeto JSON
				jArray.add(linha);
				
				// Elimina o string builder gerado
				builder.delete(0, builder.length());
				
			}
			
			// Incrementa nro track
			numberTrack++;
			
			// Inclui a lista no objeto JSON
			jObject.put("sessions", jArray);
			
			// Inclui o objeto na lista de retorno
			jArrayFinal.add(jObject);
			
		}
		
		// Repassa a lista de retorno ao objeto final
		jObjectFinal.put("response", jArrayFinal);
		
		return jObjectFinal;
		
	}
	
	/**
	 * Gera as trilhas da Conferência/Congresso
	 * @param conteudoArquivo Listagem das palestras possíveis e proveninetes do arquivo de input.
	 * */
	private List<Track> gerarListaTracks(List<String> conteudoArquivo) throws Exception {
		
		List<Talk> listTalks = this.generateTalksList(conteudoArquivo);
		
		// Obtém a quantidade máxima de Tracks possíveis
		int qtdMaxTracks = this.getNumberOfTracksPossible(listTalks);
		
		// Organiza a lista de talks
		Collections.sort(listTalks);
		
		List<Track> trackArray = new ArrayList<>();
		GenerateMorning generateMorningSessions = new GenerateMorning();
		GenerateAfternoon generateAfternoonSessions = new GenerateAfternoon();

		// Enquanto o limite de Tracks não é atingido...
		for (int i = 0; i < qtdMaxTracks; i++) {
			// Gera novo Track
			Track track = new Track();
			track.setSessions(generateMorningSessions.generateSession(listTalks));
			track.setSessions(generateAfternoonSessions.generateSession(listTalks));
			// Adiciona na lista final
			trackArray.add(track);
		}
		
		return trackArray;
		
	} // fim do mountTracks()

	
	/**
	 * Obtém a duração em minutos
	 **/
	private String getDurationTitle(Session session) {
		String durationTitle = session.getTalk().getDuracao() + Constant.SUFIXO_MINUTOS;
		// Se for Almoço ou Networking, ignora a duração.
		if ((Constant.SUFIXO_ALMOXO.equalsIgnoreCase(session.getTalk().getTitulo())) 
				|| (Constant.SUFIXO_NETWORKING.equalsIgnoreCase(session.getTalk().getTitulo()))) {
			durationTitle = "";
		// Verifica se é duração relâmpago...
		} else if (session.getTalk().getDuracao() == Constant.DURACAO_EM_MINUTOS_RELAMPAGO) {
			durationTitle = Constant.SUFIXO_DURACAO_RELAMPAGO;
		}
		return durationTitle;
	}		


	private int getNumberOfTracksPossible(List<Talk> talks) {
		int duracaoTotal = 0;
		for (Talk talk : talks) {
			duracaoTotal = duracaoTotal + talk.getDuracao();
		}
		int numberOfTracks = duracaoTotal / Constant.MAXIMA_DURACAO_EM_MINUTOS_TRACK;
		return numberOfTracks;
	}	
	
	
	/**
	 * Gera listagem de Talks a partir do conteúdo obtido do arquivo de input.
	 * Realiza algumas validações e extrai o nome e o tempo do conteúdo lido.
	 * @param conteudoArquivo Listagem do conteúdo do arquivo de input no formato de String.
	 * @return List<Talk> Listagem de objeto Talk, já preenchido (título e duração) e validado.  
	 **/
	private List<Talk> generateTalksList(List<String> conteudoArquivo) throws Exception { 

		// Verifica se a lista obtida do arquivo de input possui conteúdo
		if (conteudoArquivo == null || conteudoArquivo.isEmpty())
			throw new ConferenceException("No data found!");
		
        List<Talk> listagem = new ArrayList<>();
        
        // Percorre o conteúdo obtido do arquivo de input
        for(String talk : conteudoArquivo) {
        	
        	// Verifica última posição do espaço em branco
            int ultimaPosicaoEspaco = talk.lastIndexOf(" ");
            
            // Extrai o nome e o tempo, a partir do último espaço em branco
            String name = talk.substring(0, ultimaPosicaoEspaco);
            String timeStr = talk.substring(ultimaPosicaoEspaco + 1);
            
            int time = 0;
            try {
            	
            	// Valida o tempo
                if(timeStr.endsWith(Constant.SUFIXO_MINUTOS)) {
                	
                	// Extrai o tempo em minutos (valor inteiro)
                    time = Integer.parseInt(timeStr.substring(0, timeStr.indexOf(Constant.SUFIXO_MINUTOS)));
                    
                // Verifica se é de duração relâmpago
                } else if(timeStr.endsWith(Constant.SUFIXO_DURACAO_RELAMPAGO)) {         
                    time = Constant.DURACAO_EM_MINUTOS_RELAMPAGO;
                }
                
            } catch(Exception ex) {
            	throw new Exception(ex.getMessage());
            }
            
            // Adiciona objeto na lista resultante
            listagem.add(new Talk(name, time));
            
        }
        
        return listagem;
    }
	
}
