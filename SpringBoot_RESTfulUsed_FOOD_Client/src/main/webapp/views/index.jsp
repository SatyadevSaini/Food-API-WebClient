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

<h1 style="background: orange; color: black; font: italic;">Food WebPage</h1>
  
  <a href="/">HomePage</a> <br> <br> 
  <a href="getAllFood">GetAllFood</a> <br> 
  
   <p>${add} </p>    
  <h2 style="background: orange; color: black;">Add Food Here WithImage Here</h2>
    <hr>
    <form action="addFood" method="post" enctype="multipart/form-data">
     
    ID : <input type="number" name="id" placeholder="Enter Id Here" required/> <br> <br>
    NAME :<input name="name" type="text" placeholder="Enter Name Here " required/>  <br> <br>
    PRICE :<input type="number" name="price" placeholder="Enter Price Here" required/>  <br> <br>
    IMAGE :<input name="image"  type="file">  <br> <br>
    
    <button style="background: green; ">Add Book</button>
       <hr>
    </form>
    
    <form action="update" method="post">
    
    <select></select>
    
    
    </form>
    
</body>
</html>