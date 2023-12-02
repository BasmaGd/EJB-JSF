
<%@page import="entities.Hotel"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        body {
            background: linear-gradient(to right, #7fff00, #00ced1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            height: 100vh;
            margin: 0;
        }

        form {
            width: 50%;
            margin-bottom: 20px;
            text-align: center;
        }

        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            border: 1px solid #000;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #98fb98;
        }

        td {
            background-color: #00fa9a;
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
            text-align: center;
            text-decoration: none;
            display: inline-block;
            border-radius: 5px;
            cursor: pointer;
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
    </style>
</head>
<body>

	<form action="HotelController">
		Nom : <input type="text" name="hotel" /> <br> 
		Adresse : <input type="text" name="adresse" /> <br>
		Téléphone : <input type="text" name="telephone" /> <br>
		Ville : <select name="villeId">
			<c:forEach items="${villes}" var="ville">
				<option value="${ville.id}">${ville.nom}</option>
			</c:forEach>
		</select><br>

		<input type="submit" class="btn" name="action" value="create">
	</form>



	<h1 class="hotels-heading">Liste des hôtels :</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Nom</th>
			<th>Adresse</th>
			<th>Téléphone</th>
			<th>Ville</th>
			<th>Actions</th>
			

		</tr>
		<c:forEach items="${hotels}" var="h">
			<tr>
				<td>${h.id}</td>
				<td>${h.nom}</td>
				<td>${h.adresse}</td>
				<td>${h.telephone}</td>
				<td>${h.ville.nom}</td>
				<td>
                    <form action="HotelController" method="post"  onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cet hôtel ?');">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${h.id}">
                        <input type="submit" class="btn" value="Supprimer">
                       
    
                    </form>
                    <form action="modifierHotel.jsp" method="post">
                        <input type="hidden" name="idHotel" value="${h.id}">
                        <input type="submit" class="btn" value="Modifier">
                    </form>
                </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
