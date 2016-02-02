package controlador.login;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.aluno.Aluno;
import modelo.aluno.AlunoRN;

import org.hibernate.HibernateException;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private javax.servlet.RequestDispatcher dispatcher = null;

       
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

				if(a != null && a.getId() > 0){
					System.out.println("aluno encontrado");
					dispatcher = request.getRequestDispatcher("paginainicial.jsp");
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
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
