package controlador.votacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.mysql.fabric.xmlrpc.base.Array;

import modelo.aluno.Aluno;
import modelo.chapa.Chapa;
import modelo.chapa.ChapaRN;
import modelo.votacao.Resultado;
import modelo.votacao.Votacao;
import modelo.votacao.VotacaoRN;
import controlador.login.ServletLogin;
import controlador.util.Formata;

/**
 * Servlet implementation class ServletVotacao
 */
public class ServletVotacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.RequestDispatcher dispatcher = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVotacao() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletLogin.verificaUsuarioLogado(request,response)){
			dispatcher = request.getRequestDispatcher("view/login/login.jsp");
	    	dispatcher.forward(request, response);
	    	return ;
		}
		
		String acao = request.getParameter("acao");

		if("votar".equals(acao)){
			
			if(StringUtils.trimToEmpty(request.getParameter("botao")).equals("votar")){
				Aluno usuario = ServletLogin.usuarioLogado(request);
				VotacaoRN rnVotacao = new VotacaoRN();
				
				if(rnVotacao.jaVotou(usuario.getId()) != null){
					request.setAttribute("erro", "Não é possível votar mais de uma vez.");
					dispatcher = request.getRequestDispatcher("view/votacao/resultado.jsp");
					
				}else {

					Votacao v = new Votacao();
					v.setEleitor(usuario);
					
					ChapaRN rnChapa = new ChapaRN();
					v.setChapa(rnChapa.getChapa(Formata.convetNmeroLon(request.getParameter("chapa"))));
					
					
					rnVotacao.votar(v);
					request.setAttribute("msg", "voto computado com sucesso");
					resultado(request, response);return ;
				}
				
				
			}else{
				ChapaRN rn = new ChapaRN();
				request.setAttribute("chapas", rn.listarChapas());
				dispatcher = request.getRequestDispatcher("view/votacao/votacao.jsp");
			}
		}else if("resultado".equals(acao)){
			resultado(request, response);return ;
		}
    	dispatcher.forward(request, response);
	}

	private void resultado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VotacaoRN rn = new VotacaoRN();
		
		List<Resultado> lista = new ArrayList<Resultado>();

		List<Chapa> l = new ChapaRN().listarChapas();
		long totalVotos = rn.quantidadeVotos();
		for (int i = 0; i < l.size(); i++) {
			List<Votacao> votos = rn.listarVotos(l.get(i).getId());
			Resultado r = new Resultado();
			r.setChapa(l.get(i));
			r.setVotos(votos.size());
			r.setPorcentagem((r.getVotos()/(totalVotos*1.0))*100.0);
			lista.add(r);
		}
		
		
		
		request.setAttribute("resultado", lista);
		dispatcher = request.getRequestDispatcher("view/votacao/resultado.jsp");	
		dispatcher.forward(request, response);
		
	}
	
}
