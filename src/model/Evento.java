package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Evento {

	private int id;
	private String descricaoEvento;
	private String email;
	private Date date;
	private boolean alarme;
	private Frequencia frequencia;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricaoEvento() {
		return descricaoEvento;
	}
	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isAlarme() {
		return alarme;
	}
	public void setAlarme(boolean alarme) {
		this.alarme = alarme;
	}
	public Frequencia getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}
	
	@Override
	public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        return "ID: " + id + "; " +
               sdf.format(date) + ";" +
               descricaoEvento + ";" +
               frequencia + ";" +
               email + ";" +
               (alarme ? "1" : "0");
    }
	
	
	
	
}
