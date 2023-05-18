package br.com.TestesIntegration;

import java.util.List;

import br.com.DAO.HoraDeTrabalhoDAO;
import br.com.Entity.HorarioDeTrabalho;

public class HorarioDeTrabalhoTeste {

	public static void main(String[] args) {

		HorarioDeTrabalho ht = new HorarioDeTrabalho();
		HoraDeTrabalhoDAO cdao = new HoraDeTrabalhoDAO();
		
		ht.setCpf("6977984000");
		ht.setEntrada("08:00");
		ht.setIntervaloInicio("12:15");
		ht.setIntervaloFim("13:20");
		ht.setSaida("16:00");
		
		cdao.salvar(ht);

	
	
	//---------------------------------------Metodo para Listar todos os registros-----------------------------------------------------
//	
	try {
		List<HorarioDeTrabalho> horarios = cdao.listarTodos();

		for (HorarioDeTrabalho hts : horarios) {
			System.out.println("CPF: " + ht.getCpf() + "Entrada: " + ht.getEntrada() + ", Saï¿½da: " + hts.getSaida() + 
					", IntervaloInicio: " + hts.getIntervaloInicio() + ", IntervaloFim: " + hts.getIntervaloFim());
		}

	} catch (Exception e) {
		System.out.println(e);
	}

	}
}
