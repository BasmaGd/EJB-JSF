package controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.IDaoLocale;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */
public class VilleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(beanName = "kenza")
	private IDaoLocale<Ville> ejb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VilleController() {
        super();
       // TODO Auto-generated constructor stub
   }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String nom = request.getParameter("ville");
//		ejb.create(new Ville(nom));
//		
//		request.setAttribute("villes", ejb.findAll());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
//		
//		dispatcher.forward(request, response);
//	}
	@PermitAll
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    String nom = request.getParameter("ville");
                    ejb.create(new Ville(nom));
                    
                    break;
                case "delete":
                    int idToDelete = Integer.parseInt(request.getParameter("id"));
                    Ville villeToDelete = ejb.findById(idToDelete);
                    ejb.delete(villeToDelete);
                    break;
                case "update":
                    int idToUpdate = Integer.parseInt(request.getParameter("id"));
                    String updatedNom = request.getParameter("ville");
                    Ville villeToUpdate = ejb.findById(idToUpdate);
                    villeToUpdate.setNom(updatedNom);
                    ejb.update(villeToUpdate);
                    break;
                // Ajoutez d'autres actions au besoin
            }
        }

        request.setAttribute("villes", ejb.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");

        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@PermitAll
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
