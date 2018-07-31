package com.demo.provaneogrid.model;

public class Talk implements Comparable<Talk> {

	private String titulo;
	private int duracao;
	
	public Talk(String titulo, int duracao) {
		this.titulo = titulo;
		this.duracao = duracao;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setDuracao(int minDur) {
		this.duracao = minDur;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public int getDuracao() {
		return this.duracao;
	}

	
	@Override
	public int compareTo(Talk talk) {
		if (this.duracao > talk.duracao) {
			return -1;
		} else if (this.duracao < talk.duracao) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
