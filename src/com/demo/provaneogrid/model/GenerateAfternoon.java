package com.demo.provaneogrid.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.provaneogrid.constants.Constant;
import com.demo.provaneogrid.util.DateTimeUtil;

public class GenerateAfternoon implements IGenerateSession {

	/**
	 * Gera a Session
	 * @param List<Talk> Lista de Talks possíveis
	 * @return List<Session> Lista da Session montada 
	 **/
	public List<Session> generateSession(List<Talk> talks) {
		
		List<Session> listagemSessions = new ArrayList<>();
		
		// Define horário de início da Session da tarde
		Date afternoonSessionTime = DateTimeUtil.generateTime(
				Constant.INICIO_SESSION_TARDE_HORA, Constant.INICIO_SESSION_TARDE_MINUTO);
		
		// Obtém o tempo (em minutos) do turno da tarde
		int afternoonTimeDuration = Constant.DURACAO_EM_MINUTOS_TURNO_TARDE;
		
		// Enquanto houver tempo disponível e enquanto houver talks a serem selecionadas
		while ((afternoonTimeDuration > 0) && (!talks.isEmpty())) {
			for (Talk talk : talks) {
				
				// Verifica se o tempo da Talk não supera o tempo ainda disponível
				if (talk.getDuracao() <= afternoonTimeDuration) {
					
					// Subtrai o tempo da Talk do tempo restante
					afternoonTimeDuration -= talk.getDuracao();
					
					// Cria objeto da Session com a Talk em questão
					Session session = new Session(talk, afternoonSessionTime);
					afternoonSessionTime = DateTimeUtil.addMinutesToTime(afternoonSessionTime, talk.getDuracao());
					
					// Atualiza a lista de Sessions
					listagemSessions.add(session);
					
					// Retira a Talk em questão das que ainda estão disponíveis para seleção
					talks.remove(talk);
					
					break;  // Força saída
				}
				
			}
		}
		
		// Define o Networking final e repassa para a Session
		Talk networkingTalk = new Talk("Networking Event", Constant.DURACAO_EM_MINUTOS_NETWORKING);
		Session networkingSession = new Session(networkingTalk, afternoonSessionTime);
		listagemSessions.add(networkingSession);		
		
		return listagemSessions;
	}
	
}
