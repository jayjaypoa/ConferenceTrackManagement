package com.demo.provaneogrid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.demo.provaneogrid.constants.Constant;
import com.demo.provaneogrid.util.DateTimeUtil;

public class GenerateMorning implements IGenerateSession {
	
	/**
	 * Gera a Session
	 * @param List<Talk> Lista de Talks possíveis
	 * @return List<Session> Lista da Session montada 
	 **/
	public List<Session> generateSession(List<Talk> talks) {
		
		List<Session> listagemSessions = new ArrayList<>();
		
		// Define horário de início da Session da manhã
		Date morningSessionTime = DateTimeUtil.generateTime(
					Constant.INICIO_SESSION_MANHA_HORA, Constant.INICIO_SESSION_MANHA_MINUTO);
		
		// Obtém o tempo (em minutos) do turno da manhã
		int morningTimeDuration = Constant.DURACAO_EM_MINUTOS_TURNO_MANHA;

		// Enquanto houver tempo disponível e enquanto houver talks a serem selecionadas
		while ((morningTimeDuration > 0) && (!talks.isEmpty())) {
			for (Talk talk : talks) {
				
				// Verifica se o tempo da Talk não supera o tempo ainda disponível
				if (talk.getDuracao() <= morningTimeDuration) {
					
					// Subtrai o tempo da Talk do tempo restante
					morningTimeDuration -= talk.getDuracao();
					
					// Cria objeto da Session com a Talk em questão
					Session session = new Session(talk, morningSessionTime);
					morningSessionTime = DateTimeUtil.addMinutesToTime(morningSessionTime, talk.getDuracao());
					
					// Atualiza a lista de Sessions
					listagemSessions.add(session);
					
					// Retira a Talk em questão das que ainda estão disponíveis para seleção
					talks.remove(talk);
					
					break;  // Força saída
				}
				
			}
		}
		
		// Define o intervalo do almoço e repassa para a Session
		Talk talkAlmoco = new Talk("Lunch", Constant.DURACAO_EM_MINUTOS_ALMOCO);
		Date horarioInicioAlmoco = DateTimeUtil.generateTime(Constant.INICIO_ALMOCO_HORA, Constant.INICIO_ALMOCO_MINUTO);
		Session sessionAlmoco = new Session(talkAlmoco, horarioInicioAlmoco);
		listagemSessions.add(sessionAlmoco);
		
		return listagemSessions;
	}
	
}
