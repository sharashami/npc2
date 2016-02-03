package controlador.chapa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.aluno.Aluno;
import modelo.aluno.AlunoRN;
import modelo.chapa.Chapa;
import modelo.chapa.ChapaRN;
import modelo.chapa.Integrante;
import modelo.votacao.VotacaoRN;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;

import com.sun.org.apache.xpath.internal.axes.AxesWalker;

import controlador.login.ServletLogin;
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
		if(!ServletLogin.verificaUsuarioLogado(request,response)){
			dispatcher = request.getRequestDispatcher("view/login/login.jsp");
	    	dispatcher.forward(request, response);
	    	return ;
		}
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
					chapa.setCriador(ServletLogin.usuarioLogado(request));
					ChapaRN rn = new ChapaRN();
					StringBuilder erros = new StringBuilder();

					for (int i = 0; i < chapa.getIntegrante().size(); i++) {
						//verifica se é aluno
						Aluno aExiste = new AlunoRN().existeAluno(chapa.getIntegrante().get(i).getMatricula());
						if(aExiste == null || aExiste.getId() <= 0){
							erros.append("Aluno matrícula "+chapa.getIntegrante().get(i).getMatricula()+" não existe. <br>");
						
						}else{
							//verifica se aluno ja esta em outra chapa
							Chapa cDoAluno = rn.jaEstaEmChapa(aExiste.getId());
							if(cDoAluno != null && cDoAluno.getId() > 0){
								erros.append("Aluno matrícula "+chapa.getIntegrante().get(i).getMatricula()+" já está na chapa : "+cDoAluno.getNome()+" <br>");	
							}
						}
					}
					
					if(!erros.toString().isEmpty()){
						request.setAttribute("erro", erros.toString());
						request.setAttribute("chapa", chapa);
						dispatcher = request.getRequestDispatcher("view/chapa/formulario.jsp");

				    	dispatcher.forward(request, response);
				    	return ;
					}
					try {
						rn.salvar(chapa);
					} catch (HibernateException e) {
						//CHAPA DUPLICADA
						if(e.getMessage().contains("Duplicate")){
							request.setAttribute("erro", "Não é possível cadastrar chapa duplicada.");
						}
						e.printStackTrace();
					}
					index(request, response);
					return ;
				}
				
			}else if(StringUtils.trimToEmpty(request.getParameter("botao")).equals("Editar")){
				Chapa chapa = new Chapa();
				 String[][] obrigatorios = {{"nomechapa","Obrigatorio"},
						 					{"presidentematricula","Obrigatorio"},{"presidentenome","Obrigatorio"},{"presidentecurso","Obrigatorio"},
						 					{"secretariomatricula","Obrigatorio"},{"secretarionome","Obrigatorio"},{"secretariocurso","Obrigatorio"},
						 					{"tesoureiromatricula","Obrigatorio"},{"tesoureironome","Obrigatorio"},{"tesoureirocurso","Obrigatorio"}};
				 
				if(isValidoForm(request, response, obrigatorios, chapa)){
					chapa.setCriador(ServletLogin.usuarioLogado(request));
					ChapaRN rn = new ChapaRN();
					StringBuilder erros = new StringBuilder();

					for (int i = 0; i < chapa.getIntegrante().size(); i++) {
						//verifica se é aluno
						Aluno aExiste = new AlunoRN().existeAluno(chapa.getIntegrante().get(i).getMatricula());
						if(aExiste == null || aExiste.getId() <= 0){
							erros.append("Aluno matrícula "+chapa.getIntegrante().get(i).getMatricula()+" não existe. <br>");
						
						}else{
							//verifica se aluno ja esta em outra chapa
							Chapa cDoAluno = rn.jaEstaEmChapa(aExiste.getId());
							if(cDoAluno != null && cDoAluno.getId() > 0 ){
								if(chapa.getId() == 0 || cDoAluno.getId() != chapa.getId()){
									erros.append("Aluno matrícula "+chapa.getIntegrante().get(i).getMatricula()+" já está na chapa : "+cDoAluno.getNome()+" <br>");
								}
							}
						}
					}
					
					if(!erros.toString().isEmpty()){
						request.setAttribute("erro", erros.toString());
						request.setAttribute("chapa", chapa);
						dispatcher = request.getRequestDispatcher("view/chapa/formulario.jsp");

				    	dispatcher.forward(request, response);
				    	return ;
					}
					try {
						rn.alterar(chapa);
					} catch (HibernateException e) {
						//CHAPA DUPLICADA
						if(e.getMessage().contains("Duplicate")){
							request.setAttribute("erro", "Não é possível cadastrar chapa duplicada.");
						}
						e.printStackTrace();
					}
					index(request, response);
					return ;
				}
			}
			
			dispatcher = request.getRequestDispatcher("view/chapa/formulario.jsp");
		
		//alterar chapa da lista
		}else if("editar".equals(acao)){
		
			long id =Formata.parseLong(request.getParameter("id"));
			if(id > 0){
				ChapaRN rn = new ChapaRN();
				Chapa c = rn.getChapa(id);
				
				if (c !=null && c.getId() > 0){
					if(c.getCriador().getId() == ServletLogin.usuarioLogado(request).getId()){
						request.setAttribute("chapa", c);
						dispatcher = request.getRequestDispatcher("view/chapa/formulario.jsp");	
					}else{
						request.setAttribute("erro","Você só pode alterar chapas que você criou");
						index(request, response);						return ;
						
					}
				}else{
					index(request, response);return ;
				}
			}
			
		//excluir chapa da lista
		}else if("excluir".equals(acao)){
		//excluir chapa
			long id =Formata.parseLong(request.getParameter("id"));
			ChapaRN rn = new ChapaRN();
			Chapa c = rn.getChapa(id);
			
			if (c !=null && c.getId() > 0){
				System.out.println(c.getCriador().getId() + " "+ ServletLogin.usuarioLogado(request).getId());
				if(c.getCriador().getId() == ServletLogin.usuarioLogado(request).getId()){
					//remove votacao
					VotacaoRN rnVotacao =  new VotacaoRN();
					rnVotacao.removeVotacao();
					rn.remover(c);
					request.setAttribute("msg","Chapa removida com sucesso");
					index(request, response);return ;
					
				}else{
					request.setAttribute("erro","Você só pode remover chapas que você criou");
					index(request, response);						return ;
					
				}
			}else{
				index(request, response);return ;
			}
			
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
