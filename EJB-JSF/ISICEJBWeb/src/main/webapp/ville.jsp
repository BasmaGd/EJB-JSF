<%@page import="entities.Ville"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste des Villes</title>
    <style>
        body {
            background: linear-gradient(to right, #7fff00, #00ced1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        table {
            border-collapse: collapse;
            width: 50%;
            margin-top: 20px;
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
        }

        .btn:hover {
            background-color: #00ff00;
            border-color: #008000;
            color: #008000;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="VilleController">
            Nom : <input type="text" name="ville" /> <br>
            <input type="submit"  class="btn" name="action" value="create">
        </form>

        <h1>Liste des villes : </h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${villes}" var="v">
                <tr>
                    <td>${v.id}</td>
                    <td>${v.nom}</td>
                    <td>
                        <form action="VilleController" method="post"  onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette ville ?');">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${v.id}">
                            <input type="submit" class="btn" value="Supprimer">
                        </form>
                        <form action="modifierVille.jsp" method="get">
                            <input type="hidden" name="idVille" value="${v.id}">
                            <input type="submit" class="btn" value="Modifier">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
