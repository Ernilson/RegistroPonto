package br.com.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CalculoHoraExtra  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cpf;
    private String data;
    private String entradaPrevista;
    private String entradaRealizada;
    private String saidaPrevista;
    private String saidaRealizada;
    private int horaExtraEntrada;
    private int horaExtraSaida;
    
   public CalculoHoraExtra() {
	// TODO Auto-generated constructor stub
   }
   
	public CalculoHoraExtra(Long id, String cpf, String data, String entradaPrevista, String entradaRealizada,
		String saidaPrevista, String saidaRealizada, int horaExtraEntrada, int horaExtraSaida) {
	super();
	this.id = id;
	this.cpf = cpf;
	this.data = data;
	this.entradaPrevista = entradaPrevista;
	this.entradaRealizada = entradaRealizada;
	this.saidaPrevista = saidaPrevista;
	this.saidaRealizada = saidaRealizada;
	this.horaExtraEntrada = horaExtraEntrada;
	this.horaExtraSaida = horaExtraSaida;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEntradaPrevista() {
		return entradaPrevista;
	}

	public void setEntradaPrevista(String entradaPrevista) {
		this.entradaPrevista = entradaPrevista;
	}

	public String getEntradaRealizada() {
		return entradaRealizada;
	}

	public void setEntradaRealizada(String entradaRealizada) {
		this.entradaRealizada = entradaRealizada;
	}

	public String getSaidaPrevista() {
		return saidaPrevista;
	}

	public void setSaidaPrevista(String saidaPrevista) {
		this.saidaPrevista = saidaPrevista;
	}

	public String getSaidaRealizada() {
		return saidaRealizada;
	}

	public void setSaidaRealizada(String saidaRealizada) {
		this.saidaRealizada = saidaRealizada;
	}

	public int getHoraExtraEntrada() {
		return horaExtraEntrada;
	}

	public void setHoraExtraEntrada(int horaExtraEntrada) {
		this.horaExtraEntrada = horaExtraEntrada;
	}

	public int getHoraExtraSaida() {
		return horaExtraSaida;
	}

	public void setHoraExtraSaida(int horaExtraSaida) {
		this.horaExtraSaida = horaExtraSaida;
	}   
}