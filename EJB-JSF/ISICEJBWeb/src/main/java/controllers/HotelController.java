package controllers;


import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Hotel;
import entities.Ville;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HotelController
 */

public class HotelController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = "basma")
    private IDaoLocale<Hotel> ejb;
    @EJB(beanName = "kenza")
    private IDaoLocale<Ville> vejb;

    public HotelController() {
        super();
    }

    
    @PermitAll
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    String nom = request.getParameter("hotel");
                    String adresse = request.getParameter("adresse");
                    String telephone = request.getParameter("telephone");
                 // Retrieve the villeId from the request parameter
    				String villeId = request.getParameter("villeId");
    				System.out.println("---------------------------------------------------");
    				System.out.println(villeId);
    				System.out.println("---------------------------------------------------");
    				if (villeId != null) {
    					Ville ville = vejb.findById(Integer.parseInt(villeId));
    					System.out.println("---------------------------------------------------");
System.out.println(ville);
System.out.println("---------------------------------------------------");
    					ejb.create(new Hotel(nom, adresse, telephone, ville));
    				} else {
    					Ville ville = null;
    					ejb.create(new Hotel(nom, adresse, telephone, ville));
    				}
//                    String idVilleParameter = request.getParameter("ville");
//            
//                    int idVille = Integer.parseInt(idVilleParameter);
//                    Ville selectedVille = vejb.findById(idVille);
//                    ejb.create(new Hotel(nom, adresse, telephone,selectedVille));
                    break;
                case "delete":
                    int idToDelete = Integer.parseInt(request.getParameter("id"));
                    Hotel hotelToDelete = ejb.findById(idToDelete);
                    ejb.delete(hotelToDelete);
                    break;
//                case "modi":
//                    int idHotel2 = Integer.parseInt(request.getParameter("idHotel")); // Use "idHotel" instead of "id"
//                    System.out.println("------------------------------");
//                    System.out.println("ID: " + idHotel2);
//                    System.out.println("------------------------------");
//                    
//                    // Forwarding the request to "modifierHotel.jsp"
//                    RequestDispatcher dispatcher1 = request.getRequestDispatcher("/modifierHotel.jsp");
//                    dispatcher1.forward(request, response);
//                    break;

                case "update":
                	List<Ville> villesList = vejb.findAll();
                    request.setAttribute("villes", villesList);
                    int idToUpdate = Integer.parseInt(request.getParameter("id"));

                    String updatedNom = request.getParameter("hotel");
                    String updatedAdresse = request.getParameter("adresse");
                    String updatedTelephone = request.getParameter("telephone");
//                    int ville_id = Integer.parseInt(request.getParameter("villeId"));
//                   Ville ville = vejb.findById(ville_id);
                    Hotel hotelToUpdate = ejb.findById(idToUpdate);
                    hotelToUpdate.setNom(updatedNom);
                    hotelToUpdate.setAdresse(updatedAdresse);
                    hotelToUpdate.setTelephone(updatedTelephone);
                    //hotelToUpdate.setVille(ville);
                    
                
                    
                    ejb.update(hotelToUpdate);
                   
                    break;
                case "filterByVille":
                    String filterVilleParam = request.getParameter("villeId");

                    if (filterVilleParam != null && !filterVilleParam.isEmpty()) {
                        try {
                            int idVille = Integer.parseInt(filterVilleParam);
                            List<Hotel> hotelList;

                            if (idVille != 0) {
                                hotelList = ejb.findByVille(idVille);
                            } else {
                                hotelList = ejb.findAll(); // Obtenir tous les hôtels si aucun filtre n'est sélectionné
                            }

                            List<Ville> villeList = vejb.findAll(); // Récupérer la liste de toutes les villes
                            request.setAttribute("hotels", hotelList); // Stocker la liste filtrée d'hôtels
                            request.setAttribute("villes", villeList); // Stocker la liste de toutes les villes
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // Gérer l'exception si la conversion de l'ID de ville échoue
                        }
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/hotelByVille.jsp");
                    dispatcher.forward(request, response);
                    break;
                    
//                case "filterByCity":
//                    int villeIdToFilter = Integer.parseInt(request.getParameter("ville_id"));
//                    request.setAttribute("hotels", ejb.findByVille(villeIdToFilter));
//                    RequestDispatcher filterDispatcher = request.getRequestDispatcher("hotelByVille.jsp");
//                    filterDispatcher.forward(request, response);
//                    return;
//                // Ajoutez d'autres actions au besoin
            }
        }

        List<Hotel> hotelList = ejb.findAll();
        List<Ville> villesList = vejb.findAll();
        request.setAttribute("hotels", hotelList);
        request.setAttribute("villes", villesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel.jsp");
        dispatcher.forward(request, response);
    }

    @PermitAll
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
