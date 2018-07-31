package com.demo.provaneogrid.model;

import java.util.ArrayList;
import java.util.List;

public class Track {

	private List<Session> sessao;

	public Track() {
		sessao = new ArrayList<>();
	}
	
	public void addSession (Session sessao) {
		this.sessao.add(sessao);
	}
	
	public void setSessions(List<Session> sessao) {
		this.sessao.addAll(sessao);
	}
	
	public List<Session> getSessions() {
		return this.sessao;
	}
	
}
