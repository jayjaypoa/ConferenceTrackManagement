package com.demo.provaneogrid.constants;

import java.io.File;

public final class Constant {

	// Define ARQUIVO DE INPUT
	public static String NOME_ARQUIVO_ENTRADA = "input.txt";
	public static String LOCAL_ARQUIVO_ENTRADA =  File.separator 
			+ "src" + File.separator + "resources" + File.separator;
	
	// Define os SUFIXOS
	public static String SUFIXO_MINUTOS = "min";
	public static String SUFIXO_DURACAO_RELAMPAGO = "lightning";
	public static String SUFIXO_NETWORKING = "Networking Event";
	public static String SUFIXO_ALMOXO = "Lunch";
	
	// Duracao dos TURNOS
	public static Integer DURACAO_EM_MINUTOS_TURNO_MANHA = 180;
	public static Integer DURACAO_EM_MINUTOS_TURNO_TARDE = 240;
	
	// Duracao dos INTERVALOS e DEMAIS EVENTOS
	public static Integer DURACAO_EM_MINUTOS_ALMOCO = 60;
	public static Integer DURACAO_EM_MINUTOS_NETWORKING = 0;  // Não considerar duração
	public static Integer DURACAO_EM_MINUTOS_RELAMPAGO = 5;
	
	// Define HORARIOS DE INICIO
	public static Integer INICIO_SESSION_MANHA_HORA = 9;
	public static Integer INICIO_SESSION_MANHA_MINUTO = 0;
	public static Integer INICIO_SESSION_TARDE_HORA = 13;
	public static Integer INICIO_SESSION_TARDE_MINUTO = 0;
	public static Integer INICIO_ALMOCO_HORA = 12;
	public static Integer INICIO_ALMOCO_MINUTO = 0;
	
	// Define QTD MAXIMA
	public static Integer MAXIMA_DURACAO_EM_MINUTOS_TRACK = 6 * 60; // Máximo 6 horas
	
}
