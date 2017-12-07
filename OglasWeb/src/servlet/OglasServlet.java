package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.OglasBean;
import model.Ogla;
import model.OglasKorisnik;
import model.OglasPrijava;

/**
 * Servlet implementation class OglasServlet
 */
@WebServlet("/OglasServlet")
public class OglasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB
       OglasBean oglasBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OglasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text=request.getParameter("text");
		
		List<Ogla> rez=oglasBean.SearchOgla(text);
		
		request.setAttribute("oglasi", rez);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println(request.getParameter("idOglas"));
		int idOglasa=Integer.parseInt(request.getParameter("idOglas"));
		
		
		OglasKorisnik ok=(OglasKorisnik)request.getSession().getAttribute("user");
		
		boolean isOk=oglasBean.OglasPrijavljivanje(ok, idOglasa);
		
		System.out.println("javio se!");
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

}
