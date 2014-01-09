package com.android.correnegada;

public class Meta {
	
	private int id;
	private String treino;
	private long aquecimento;
	private long caminhada;
	private long trote;
	private long corrida;
	private long tempoTotal;
		
	public Meta(int id, String treino, long aquecimento, long caminhada,
			long trote, long corrida, long tempoTotal) {
		super();
		this.id = id;
		this.treino = treino;
		this.aquecimento = aquecimento;
		this.caminhada = caminhada;
		this.trote = trote;
		this.corrida = corrida;
		this.tempoTotal = tempoTotal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTreino() {
		return treino;
	}
	public void setTreino(String treino) {
		this.treino = treino;
	}
	public long getAquecimento() {
		return aquecimento;
	}
	public void setAquecimento(long aquecimento) {
		this.aquecimento = aquecimento;
	}
	public long getCaminhada() {
		return caminhada;
	}
	public void setCaminhada(long caminhada) {
		this.caminhada = caminhada;
	}
	public long getTrote() {
		return trote;
	}
	public void setTrote(long trote) {
		this.trote = trote;
	}
	public long getCorrida() {
		return corrida;
	}
	public void setCorrida(long corrida) {
		this.corrida = corrida;
	}
	public long getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(long tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

	@Override
	public String toString(){
		return treino + ": - aquecimento " + aquecimento + "min" +
				" - caminhada" + caminhada + "min" +
				" - caminhada" + trote + "min" +
				" - caminhada" + corrida + "min" +
				" - tempo de treino" + tempoTotal + "min -";
	}
}
