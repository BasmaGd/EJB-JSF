<%@page import="entities.Ville"%>
<%@page import="entities.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotels par ville</title>
</head>
  <style>
        body {
            background: linear-gradient(to right, #7fff00, #00ced1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            width: 50%;
            text-align: center;
            margin-bottom: 20px;
        }

        .form-container input[type="text"],
        .form-container select {
            width: 100%;
            margin-bottom: 10px;
            padding: 5px;
            border-radius: 3px;
        }

        h1 {
            color: #228b22;
            text-shadow: 2px 2px 4px #000000;
            text-align: center;
            margin-bottom: 20px;
        }

        .btn {
            background-color: #00ff7f;
            border-color: #228b22;
            color: #228b22;
            padding: 5px 10px;
            text-decoration: none;
            display: inline-block;
            border-radius: 5px;
        }

        .btn:hover {
            background-color: #00ff00;
            border-color: #008000;
            color: #008000;
        }

        h1.hotels-heading {
            font-size: 2.2em;
            color: #228b22;
            text-shadow: 2px 2px 4px #000000;
            margin-right: 100px;
        }

        table {
            width: 50%;
            text-align: center;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
        }

        th {
            background-color: #98fb98;
        }

        td {
            background-color: #00fa9a;
        }
    </style>
<body>
    <h1>Hôtels par Ville</h1>
    
    
    
    <!-- Menu déroulant pour choisir la ville -->
    <form action="HotelController" method="get">
        <label for="villeSelect">Choisir une ville :</label>
        <select id="villeSelect" name="villeId">
            <option value="">Sélectionnez une ville</option>
            <!-- Boucle sur la liste des villes pour créer les options -->
            <c:forEach var="ville" items="${villes}">
                <option value="${ville.id}">${ville.nom}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="action" value="filterByVille">
            <button type="submit" class="btn btn-primary">Filter</button>
    </form>
    
    <%
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Hotel> filteredHotels = (List<Hotel>) request.getAttribute("hotels");
        
        if (filteredHotels != null && !filteredHotels.isEmpty()) {
            for (Hotel hotel : filteredHotels) {
    %>
                <div>
                    <h2><%= hotel.getNom() %></h2>
                    <p>Adresse: <%= hotel.getAdresse() %></p>
                    <p>Téléphone: <%= hotel.getTelephone() %></p>
                    <!-- Autres détails de l'hôtel que vous souhaitez afficher -->
                </div>
    <%
            }
        } else {
    %>
            <p>Aucun hôtel trouvé pour cette ville.</p>
    <%
        }
    %>
    
    <!-- Tableau des hôtels pour la ville sélectionnée -->
    <table>
        <thead>
            <tr>
                <th>Nom de l'hôtel</th>
                <th>Adresse</th>
                <th>Téléphone</th>
                <!-- Ajoutez d'autres colonnes si nécessaire -->
            </tr>
        </thead>
        <tbody>
</html>