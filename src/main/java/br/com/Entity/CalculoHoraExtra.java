package br.com.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    private HorarioDeTrabalho horarioTrabalho;
    
   public CalculoHoraExtra() {
	// TODO Auto-generated constructor stub
   }

public CalculoHoraExtra(Long id, String cpf, String data, String entradaPrevista, String entradaRealizada,
		String saidaPrevista, String saidaRealizada, int horaExtraEntrada, int horaExtraSaida,
		HorarioDeTrabalho horarioTrabalho) {
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
	this.horarioTrabalho = horarioTrabalho;
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

public HorarioDeTrabalho getHorarioTrabalho() {
	return horarioTrabalho;
}

public void setHorarioTrabalho(HorarioDeTrabalho horarioTrabalho) {
	this.horarioTrabalho = horarioTrabalho;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CalculoHoraExtra other = (CalculoHoraExtra) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

@Override
public String toString() {
	return "CalculoHoraExtra [id=" + id + ", cpf=" + cpf + ", data=" + data + ", entradaPrevista=" + entradaPrevista
			+ ", entradaRealizada=" + entradaRealizada + ", saidaPrevista=" + saidaPrevista + ", saidaRealizada="
			+ saidaRealizada + ", horaExtraEntrada=" + horaExtraEntrada + ", horaExtraSaida=" + horaExtraSaida
			+ ", horarioTrabalho=" + horarioTrabalho + "]";
}
   
	
}