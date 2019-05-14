<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
<a href="http://localhost:8080/recordstore/">To Artistlist</a>
	<h1><c:out value="${ album.getTitle() }" /></h1>
	<h4><c:out value="By: ${ artist.getName()}"/></h4>
    <h3>Tracks:</h3>
	<table style="border-collapse: separate; border-spacing: 10px;">
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Genre</th>
			<th>Length</th>
		</tr>
		<c:forEach items="${ tracks }" var="track" varStatus="index">

			<tr>
				<td>${index.index+1 }</td>
				<td>${ track.getName() }</td>
				<td>${ track.getGenreName() }</td>
				<td>${ track.getMilliseconds() }</td>

			</tr>
		</c:forEach>
	</table>
	 </br>


        			<button onclick='window.location.href = "track?id=${ album.getId()}";'>Add new song?</button>

</body>
</html>