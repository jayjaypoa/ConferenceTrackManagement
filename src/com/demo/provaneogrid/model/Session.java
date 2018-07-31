package com.demo.provaneogrid.model;

import java.util.Date;

public class Session {

	private Talk talk;
	private Date horaInicio;
	
	public Session(Talk talk, Date horaInicio) {
		this.talk = talk;
		this.horaInicio = horaInicio;
	}
	
	public Session() {
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}
	
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Date getHoraInicio() {
		return this.horaInicio;
	}
	
	public Talk getTalk() {
		return this.talk;
	}	
	
}
