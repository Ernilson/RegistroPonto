package br.com.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CalculoAtraso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cpf;
	private String entrada;
	private String intervaloInicio;
    private String intervaloFim;
    private String saida;
    private String periodoAtraso;
    

   public CalculoAtraso() {
	// TODO Auto-generated constructor stub
}
   
	public CalculoAtraso(Long id, String cpf, String entrada, String intervaloInicio, String intervaloFim, String saida, String periodoAtraso) {
	super();
	this.id = id;
	this.cpf = cpf;
	this.entrada = entrada;
	this.intervaloInicio = intervaloInicio;
	this.intervaloFim = intervaloFim;
	this.saida = saida;
	this.periodoAtraso = periodoAtraso;
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

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getIntervaloInicio() {
		return intervaloInicio;
	}

	public void setIntervaloInicio(String intervaloInicio) {
		this.intervaloInicio = intervaloInicio;
	}

	public String getIntervaloFim() {
		return intervaloFim;
	}

	public void setIntervaloFim(String intervaloFim) {
		this.intervaloFim = intervaloFim;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	 public String getPeriodoAtraso() {
	        return periodoAtraso;
	    }

	    public void setPeriodoAtraso(String periodoAtraso) {
	        this.periodoAtraso = periodoAtraso;
	    }
}

