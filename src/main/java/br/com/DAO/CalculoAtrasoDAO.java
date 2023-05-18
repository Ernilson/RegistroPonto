package br.com.DAO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.DTOs.ResultadoCalculoAtraso;
import br.com.Entity.CalculoAtraso;
import br.com.Entity.HorarioDeTrabalho;
import br.com.Entity.MarcacoesFeitas;
import br.com.Utils.CodigoErro;

public class CalculoAtrasoDAO {
	
	 private EntityManagerFactory entityManagerFactory;

   public CalculoAtrasoDAO() {
   	entityManagerFactory = Persistence.createEntityManagerFactory("RegistroPontoUnit"); 
   }

	// Fun��o que converte a string de tempo para LocalTime
	private static LocalTime stringToTime(String timeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return LocalTime.parse(timeString, formatter);
	}

	// Fun��o para calcular a diferen�a entre dois hor�rios
	public String calcularDiferenca(HorarioDeTrabalho ht, MarcacoesFeitas mf) {
		// Convertendo as strings para LocalTime
		LocalTime entradaTime = stringToTime(ht.getEntrada());
		LocalTime saidaTime = stringToTime(ht.getSaida());
		LocalTime marcacaoEntradaTime = stringToTime(mf.getEntrada());
		LocalTime marcacaoSaidaTime = stringToTime(mf.getSaida());

		// Se a marca��o de entrada � depois do hor�rio de entrada, o atraso � a
		// diferen�a
		if (marcacaoEntradaTime.isAfter(entradaTime)) {
			long minutosAtraso = ChronoUnit.MINUTES.between(entradaTime, marcacaoEntradaTime);
			return String.format("Atraso de %d minutos na entrada.", minutosAtraso);
		}

		// Se a marca��o de sa�da � antes do hor�rio de sa�da, o atraso � a diferen�a
		if (marcacaoSaidaTime.isBefore(saidaTime)) {
			long minutosAtraso = ChronoUnit.MINUTES.between(marcacaoSaidaTime, saidaTime);
			return String.format("Atraso de %d minutos na sa�da.", minutosAtraso);
		}

		// Se n�o houve atraso
		return "Sem atraso.";
	}
	
	public ResultadoCalculoAtraso calculoDeHorasExtras(String cpf) {
	    MarcacoesFeitasDAO mfdao = new MarcacoesFeitasDAO();
	    MarcacoesFeitas mf = mfdao.buscarPorCpf(cpf);
	    HoraDeTrabalhoDAO hdtdao = new HoraDeTrabalhoDAO();
	    HorarioDeTrabalho hdt = hdtdao.buscarPorCpf(cpf);

	    if (mf != null && hdt != null) {
	        LocalTime timeMf = LocalTime.parse(mf.getEntrada());
	        LocalTime timeHdt = LocalTime.parse(hdt.getEntrada());

	        long minutesBetween = ChronoUnit.MINUTES.between(timeMf, timeHdt);
	        long hours = minutesBetween / 60; // get the number of hours
	        long minutes = minutesBetween % 60; // get the remaining minutes

	        if (minutesBetween == 0) {
	            return new ResultadoCalculoAtraso("O horário está igual", null);
	        } else {
	            LocalTime periodoInicio = timeHdt.minusMinutes(minutesBetween);
	            String periodoAtraso = periodoInicio.toString() + " às " + timeHdt.toString();
	            return new ResultadoCalculoAtraso(hours + " horas e " + minutes + " minutos", periodoAtraso);
	        }
	    }

	    return new ResultadoCalculoAtraso(CodigoErro.INFORMACAO_INCOMPLETA, "Informação de horário incompleta");
	}

	public void salvar(CalculoAtraso registroPonto) {
       EntityManager entityManager = entityManagerFactory.createEntityManager();
       try {
           entityManager.getTransaction().begin();
           entityManager.persist(registroPonto);
           entityManager.getTransaction().commit();
       } catch (Exception e) {
           entityManager.getTransaction().rollback();
       } finally {
           entityManager.close();
       }
   }

	    
	    public void calcularEInserirAtraso(String cpf) {
	        ResultadoCalculoAtraso resultado = calculoDeHorasExtras(cpf);
	        
	        String atraso = resultado.getDiferenca();
	        // Remova a parte inicial da string para ficar apenas com "X horas e Y minutos"
	        atraso = atraso.replace("A diferenca e: ", "");
	        
	        // Agora podemos dividir a string em horas e minutos
	        String[] partes = atraso.split(" e ");
	        String horas = partes[0].replace(" horas", "");
	        String minutos = partes[1].replace(" minutos", "");
	        
	        // Obtenha o período de atraso do objeto ResultadoCalculoAtraso
	        String periodoAtraso = resultado.getPeriodoAtraso();
	        
	        // Agora temos as horas e os minutos como strings. Podemos criar uma string no formato TIME do SQL
	        String timeSql = horas + ":" + minutos + ":00";
	        
	        // Agora podemos criar um novo objeto CalculoAtraso e inseri-lo no banco de dados
	        CalculoAtraso calculoAtraso = new CalculoAtraso();
	        calculoAtraso.setCpf(cpf);
	        calculoAtraso.setEntrada(timeSql);
	        calculoAtraso.setSaida(timeSql);
	        
	        // Defina o campo 'periodoAtraso' do objeto CalculoAtraso com o valor adequado
	        calculoAtraso.setPeriodoAtraso(periodoAtraso);
	        
	        // Inserimos o atraso no banco de dados
	        CalculoAtrasoDAO atrasoDAO = new CalculoAtrasoDAO();
	        atrasoDAO.salvar(calculoAtraso);
	    }	   
	    
	    public List<CalculoAtraso> listarTodos() {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            String jpql = "SELECT m FROM CalculoAtraso m";
	            TypedQuery<CalculoAtraso> query = entityManager.createQuery(jpql, CalculoAtraso.class);
	            return query.getResultList();
	        } finally {
	            entityManager.close();
	        }
	    }


	    public HorarioDeTrabalho buscarPorCpf(String cpf) {
	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        try {
	            Query query = entityManager.createQuery("SELECT h FROM atraso h WHERE h.cpf = :cpf");
	            query.setParameter("cpf", cpf);
	            return (HorarioDeTrabalho) query.getSingleResult();
	        } finally {
	            entityManager.close();
	        }
	    }


	   



}
