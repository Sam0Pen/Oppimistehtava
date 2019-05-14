<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Record Store / all artists</title>
</head>
<body>
    <form method="get" action="search">
    			Search for artist or album: <input type="text" name="keyword" />
    			<input type="submit" value="Search" />
    	</form>
        <br>



	<h1>Welcome to music catalog</h1>
	<%--ADD ARTIST --%>
        	<form action = "" method = "POST">

                				Add new artist: <input type = "text" name = "Name">
                				<input type="hidden" name="action" value="add"/>


                				<input type = "submit" value = "Submit" />
                			</form>
                			<br>
	<p><c:out value="All ${ artists.size() } artists:" /></p>

	<ol>
		<c:forEach var="artist" items="${ artists }">
				<li><a href="artist?id=${ artist.getId() }"> <c:out
							value="${ artist.getName() }" /> 
				</a></li>
		</c:forEach>
	</ol>
</body>
</html>

