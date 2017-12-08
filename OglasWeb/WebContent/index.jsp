<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="OglasServlet" method="get">


		search text <input type="text" name="text"></br> <input
			type="submit" value="pretrazi">
	</form>
	
	<form action="CreateOglasServlet" method="post">

</br>
		tekst oglasa <input type="text" name="textOglasa"></br> <input
			type="submit" value="Dodaj oglas">
	</form>

	<table style="width: 100%">
		<tr>
			<th>Oglas</th>
			<th>Prijavi se</th>
		</tr>
		<c:forEach items="${oglasi}" var="oglas">
			<tr>
				<td>${oglas.text}</td>
				<td>
					<form action="OglasServlet" method="post">
						<input type="hidden" name="idOglas" value="${oglas.idOglas}" />
						<button type="submit">Prijavi me</button>
				</td>

			</tr>
		</c:forEach>
		
		<table>
</body>



<body>
	aaaaaaaaaaaaaaaaaa
	<form action="BlogServlet" method="POST"></br>
	teext: <input type="text" name="text"></br>
	telikesext: <input type="text" name="likes">
	<select  name="katId"></br>
		<c:forEach items="${kategorije}" var="i">
			<option value="${i.id}">${i.naziv}</option>


		</c:forEach>
		</select>
		<input type="submit" value="Save">
	</form>
	</br>
	<a href="/BlogWEB/pretrazi.jsp"> Pretrazi blogove</a>
</body>
</html>