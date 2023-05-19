package br.com.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.DAO.CalculoAtrasoDAO;
import br.com.DTOs.ResultadoCalculoAtraso;
import br.com.Entity.CalculoAtraso;


@WebServlet("/CalculoAtrasoServlet")
public class CalculoAtrasoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private CalculoAtrasoDAO calculoAtrasoDAO;
   
    public void init() {
        calculoAtrasoDAO = new CalculoAtrasoDAO();        
          listaratrasos();
}
    
    private void listaratrasos() {
      	 List<CalculoAtraso> at = calculoAtrasoDAO.listarTodos();
      	 getServletContext().setAttribute("at", at);
      }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //Teste
    	System.out.println("Iniciando doPost() em CalculoAtrasoDAO");
    	String action = request.getParameter("action");

        try {
            switch (action) {
                case "calcularAtraso":
                	String cpf = calculoAtrasoDAO.buscarUltimoRegistro();
                	request.getParameter("cpf");
                    String entrada = request.getParameter("entrada");
                    String saida = request.getParameter("saida");
                    
                    adicionarAtraso(cpf, entrada, saida, request, response);
                    break;

                default:
                    listarAtrasos(request, response);
                    break;
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Ocorreu um erro ao processar a solicitação: " + e.getMessage());
        }

        // Chama o método listarAtrasos para exibir a lista após a execução do POST
        listarAtrasos(request, response);
    }
    
    public void adicionarAtraso(String cpf, String entrada, String saida, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//String cpfd = calculoAtrasoDAO.buscarUltimoRegistro();    	
    	
        calculoAtrasoDAO.calcularEInserirAtraso(cpf);
        listarAtrasos(request, response);        
    }

    private void listarAtrasos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	 
    	List<CalculoAtraso> atraso = calculoAtrasoDAO.listarTodos();
        request.setAttribute("atraso", atraso);
        request.getRequestDispatcher("controleDeHora.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        	//http://localhost:8080/RegisterPoint/HoraDeTrabalhoServlet?action=list
        if (action != null) {
            switch (action) {
            case "list":
				   	listarAtrasos(request, response);
                    break;                
                default:
                	listarAtrasos(request, response);
                    break;
            }
        } else {
        	listarAtrasos(request, response);
        }
    }
}
