<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*" %>
<html>
<head>
<title>Date JSP</title>
</head>
<% String message = "hello";
for(int i = 0; i < 3; i++) {
   message+= " coucou";
}
%>
<% Integer nombreChoisi = 0;
for(nombreChoisi = 0; nombreChoisi < 4; nombreChoisi++) {
    nombreChoisi ++;
}
%>
<% String newMessage = "Mais pourquoi faire ?";
for(int a = 0; a < 2; a++){
    newMessage+= " va savoir...";
}
%>

<body>
<h1 style="color: blue; font-size: 24px">Bienvenue sur une JSP !!!! Je dirai : <%= message %> </h1>
<ul><li style="color: green">Nombres : <%= nombreChoisi %> </li></ul>
<div><span style="color: red">Une span</span> au choix : <%= nombreChoisi %> </div>
<h2 style="color: turquoise; font-size: 20px; text-decoration: underline"><%= newMessage %></h2>


</body>
</html>