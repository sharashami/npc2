package controlador.chapa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.chapa.Chapa;
import modelo.chapa.ChapaRN;
import modelo.chapa.Integrante;

import org.apache.commons.lang3.StringUtils;

import controlador.util.Formata;

/**
 * Servlet implementation class ServletChapa
 */
public class ServletChapa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.RequestDispatcher dispatcher = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletChapa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if("form".equals(acao)){
			
			//cadastrar chapa
			if(StringUtils.trimToEmpty(request.getParameter("botao")).equals("Salvar")){
				Chapa chapa = new Chapa();
				 String[][] obrigatorios = {{"nomechapa","Obrigatorio"},
						 					{"presidentematricula","Obrigatorio"},{"presidentenome","Obrigatorio"},{"presidentecurso","Obrigatorio"},
						 					{"secretariomatricula","Obrigatorio"},{"secretarionome","Obrigatorio"},{"secretariocurso","Obrigatorio"},
						 					{"tesoureiromatricula","Obrigatorio"},{"tesoureironome","Obrigatorio"},{"tesoureirocurso","Obrigatorio"}};
				 
				if(isValidoForm(request, response, obrigatorios, chapa)){
					ChapaRN rn = new ChapaRN();
					rn.salvar(chapa);
					index(request, response);
					return ;
				}
				
			}else if(StringUtils.trimToEmpty(request.getParameter("botao")).equals("Editar")){
			//alterar chapa
			
			}else if(StringUtils.trimToEmpty(request.getParameter("botao")).equals("Excluir")){
			//excluir chapa
				
			}
			
			dispatcher = request.getRequestDispatcher("view/chapa/formulario.jsp");
			
		}else{
			index(request, response);
			return ;
		}
		
    	dispatcher.forward(request, response);
		
	}
	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChapaRN rn = new ChapaRN();
		List<Chapa> lista = rn.listarChapas();
		request.setAttribute("chapas", lista);
		dispatcher = request.getRequestDispatcher("view/chapa/lista.jsp");
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private boolean isObrigatorio(HttpServletRequest request,String[][] obrigatorios){
		boolean valido = true;
		if(obrigatorios!=null){
			Map<String,String[]> m =Collections.synchronizedMap((Map<String,String[]>) new HashMap<String, String[]>(request.getParameterMap()));
			for (int i = 0; i <obrigatorios.length; i++) {
				for (Map.Entry<String,String[]> e:m.entrySet()) {
					
					if(e.getKey().equals(obrigatorios[i][0]) && StringUtils.trimToNull(e.getValue()[0])==null ){
						request.setAttribute(obrigatorios[i][0]+"_msg", obrigatorios[i][1]);
						valido =(valido==true)?false:false;
					}
				}
			}
		}
	
		return valido;
	}
	private boolean isValidoForm(HttpServletRequest request, HttpServletResponse response,String[][] obrigatorios,Chapa chapa){
		chapa.setId(Formata.parseLong(request.getParameter("id")));
		chapa.setNome(Formata.trimToEmpty(request.getParameter("nomechapa")));
		
		List<Integrante> integrantes = new ArrayList<Integrante>();
		Integrante i = new Integrante();
		
		
		i.setFuncao("presidente");
		i.setCurso(Formata.trimToEmpty(request.getParameter("presidentecurso")));
		i.setMatricula(Formata.trimToEmpty(request.getParameter("presidentematricula")));
		i.setNome(Formata.trimToEmpty(request.getParameter("presidentenome")));
		i.setChapa(chapa);
		integrantes.add(i);

		i = new Integrante();
		i.setFuncao("tesoureiro");
		i.setCurso(Formata.trimToEmpty(request.getParameter("tesoureirocurso")));
		i.setMatricula(Formata.trimToEmpty(request.getParameter("tesoureiromatricula")));
		i.setNome(Formata.trimToEmpty(request.getParameter("tesoureironome")));
		i.setChapa(chapa);
		integrantes.add(i);
		

		i = new Integrante();
		i.setFuncao("secretario");
		i.setCurso(Formata.trimToEmpty(request.getParameter("secretariocurso")));
		i.setMatricula(Formata.trimToEmpty(request.getParameter("secretariomatricula")));
		i.setNome(Formata.trimToEmpty(request.getParameter("secretarionome")));
		i.setChapa(chapa);
		integrantes.add(i);
		
		chapa.setIntegrante(integrantes);
		return isObrigatorio(request,obrigatorios);
		
	}
}
