<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h2>Get All Food Data Here</h2>
   
   <c:forEach  items="${allFood}" var="f" >
   
 ID:    <c:out value="${f.id}"></c:out> <br>
 Name:  <c:out value="${f.name}"></c:out> <br>
 Price: <c:out value="${f.price}"></c:out> <br>
 Image : <img alt="" src="getImage?id=${f.id}" height="50px" width="50px">
 
 <br>
 <form action="updateImage"  method="post" enctype="Multipart/form-data">
 <input type="hidden" name="id" value="${f.id}"/>
 Image :: <input type="file" name="image"  required />
 
 <button>Update Image</button>
 
 </form>
   
   <hr><hr>
   </c:forEach>
   
</body>
</html>