package controlador.login;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.aluno.Aluno;
import modelo.aluno.AlunoRN;

import org.apache.catalina.connector.Request;
import org.hibernate.HibernateException;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.RequestDispatcher dispatcher = null;

       
	public static Aluno usuarioLogado(HttpServletRequest request){
		return (Aluno) request.getSession().getAttribute("usuario");
	}

	public static boolean verificaUsuarioLogado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Aluno a = (Aluno) session.getAttribute("usuario");
		return !(a == null);
			
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		criaBaseAluno();
		
		if("login".equals(acao)){
			Aluno a = new Aluno();
			a.setSenha(request.getParameter("senha"));
			a.setMatricula(request.getParameter("matricula"));
			
			AlunoRN rn = new AlunoRN();
			try {
				a = rn.autenticarAluno(a);
				HttpSession session = request.getSession(true);
				session.setAttribute("usuario",a);
				
				a = (Aluno) session.getAttribute("usuario");
				if(a != null && a.getId() > 0){
					System.out.println("aluno encontrado");
					dispatcher = request.getRequestDispatcher("paginainicial.jsp");
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}else	if("sair".equals(acao)){
			HttpSession session = request.getSession();
			session.removeAttribute("usuario");
			request.setAttribute("msg", "Logout efetuado com sucesso.");
			dispatcher = request.getRequestDispatcher("view/login/login.jsp");
		}else{
			dispatcher = request.getRequestDispatcher("view/login/login.jsp");
		}
		
		
    	dispatcher.forward(request, response);
		
		
	}

	private void criaBaseAluno() {
		AlunoRN rn = new AlunoRN();
		java.util.List<Aluno> lista= rn.listarAlunos();
		if(lista == null || lista.isEmpty()){
			Aluno a = new Aluno();
			a.setMatricula("1234");
			a.setSenha("1234");
			rn.salvar(a);
			a = new Aluno();
			a.setMatricula("4321");
			a.setSenha("1234");
			rn.salvar(a);
			a = new Aluno();
			a.setMatricula("5678");
			a.setSenha("1234");
			rn.salvar(a);
			a = new Aluno();
			a.setMatricula("8765");
			a.setSenha("1234");
			rn.salvar(a);
		}
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

}
