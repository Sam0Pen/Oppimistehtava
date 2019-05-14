<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<body>
<a href="http://localhost:8080/recordstore/">To Artistlist</a>
    <h1><c:out value="${ artists.getName() }" /></h1>


    <c:if test="${ !albums.isEmpty() }">
	    <table style="border-collapse: separate; border-spacing: 10px;">
	        <tr>
	        <th>Albums</th>
	        <th></th>
	        </tr>

		    <c:forEach var="album" items="${ albums }">
		        <tr>
		          <td><a href="album?id=${ album.getId() }"><c:out value="${ album.getTitle() }" /></a></td>
		          <td><form action = "album" method = "post">
                                        <input type="hidden" name="action" value="delete"/>
                          				<input type="hidden" name="albumId" value="${album.getId()}"/>


                          				<input type = "submit" value = "Delete" />
                          			</form></td>
		        </tr>
		    </c:forEach>
	    </table>
    </c:if>
    <c:if test="${ albums.isEmpty() }">
        <div>No albums for this artist :(</div>
    </c:if>
    </br>
    <form action = "album" method = "POST">
    				Add new album:</br>
    				Title: <input type = "text" name = "albumName">
    				<input type="hidden" name="artistId" value="${artists.id}"/>
    				<input type="hidden" name="action" value="add"/>


    				<input type = "submit" value = "Submit" />
    			</form>

</body>
</html>