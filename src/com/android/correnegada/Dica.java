package com.android.correnegada;

public class Dica {
	private int imagemID;
	private String categoria;
	private String dica;

	public Dica(int imagemID, String categoria, String dica) {
		super();
		this.imagemID = imagemID;
		this.categoria = categoria;
		this.dica = dica;
	}
	
	public int getImagemID() {
		return imagemID;
	}

	public void setImagemID(int imagemID) {
		this.imagemID = imagemID;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}



}
