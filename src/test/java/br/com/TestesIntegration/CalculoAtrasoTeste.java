package br.com.TestesIntegration;

import java.util.List;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.Entity.CalculoAtraso;


public class CalculoAtrasoTeste {

	public static void main(String[] args) {
		CalculoAtraso cal = new CalculoAtraso();
		CalculoAtrasoDAO cdao = new CalculoAtrasoDAO();
		
		cal.setCpf("6977984000");
		cal.setEntrada("08:00");
		cal.setIntervaloInicio("12:15");
		cal.setIntervaloFim("13:20");
		cal.setSaida("16:00");
		
		//cdao.salvar(cal);
		
		//---------------------------------------Metodo para Listar todos os registros-----------------------------------------------------
	//	
		try {
			List<CalculoAtraso> horarios = cdao.listarTodos();

			for (CalculoAtraso cals : horarios) {
				System.out.println("CPF: " + cal.getCpf() + "Entrada: " + cal.getEntrada() + ", Saï¿½da: " + cals.getSaida() + 
						", IntervaloInicio: " + cals.getIntervaloInicio() + ", IntervaloFim: " + cals.getIntervaloFim());
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
