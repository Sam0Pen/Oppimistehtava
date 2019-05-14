<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
    <a href="http://localhost:8080/recordstore/">To Artistlist</a>
    <h1>Music catalog search</h1>
	<form method="get" action="search">
			<input type="text" name="keyword" />
			<input type="submit" value="Search" />
	</form>
	<span>Results for keyword "${keyword}"</span>
	<h3>Artists</h3>
    	<ul>
    	    <c:forEach var="artist" items="${ artists }">
            	<li><a href="artist?id=${ artist.getId() }"><c:out value="${ artist.getName() }" /></a></li>
            </c:forEach>
    	</ul>
    	<c:if test="${ artists.isEmpty() }">
                    <div>No artists found for this keyword</div>
                </c:if>

	<h3>Albums</h3>
	<ul>
		<c:forEach var="album" items="${ albums }">
			<li><a href="album?id=${ album.getId() }"><c:out value="${ album.getTitle() }" /></a></li>
		</c:forEach>
	
	</ul>
    <c:if test="${ albums.isEmpty() }">
            <div>No albums found for this keyword</div>
        </c:if>

</body>
</html>