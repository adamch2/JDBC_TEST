<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 19/02/17
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*"%>
<%@ page import="org.apache.poi.ss.formula.functions.Na" %>
<%@ page import="Model.NamesDAO" %>

<html>
<head>
    <title>Tables</title>
</head>
<body>

<%

%>
<%
    NamesDAO nd = new NamesDAO();
    nd.setConnection();
    ResultSet rs = nd.getStatement().executeQuery("SELECT name,surname,age FROM test.test_names;");
%>


<table>
    <%while (rs.next()){%>
    <tr>
        <td><% out.println(rs.getString("Name")); %></td>
        <td><% out.println(rs.getString("Surname")); %></td>
        <td><% out.println(rs.getString("Age")); %></td>
        <td>
            <form method="post" action="doResultController">
                <input type="hidden" name="button">
                <input type="hidden" name="row">

                <button type="submit" name="deleteButton" onclick="row.value=this.parentNode.parentNode.parentNode.rowIndex; button.value=1">Delete</button>
            </form>
        </td>
    </tr>
    <%}%>

</table>
<%
nd.closeConnection();
%>

<form method="post" action="doResultController">
    <input type="hidden" name="button">
    <button type="submit" onclick="button.value=2">Export to CSV</button>
</form>


<a href="/JDBC_test_war_exploded/" >Send Me To Main Page!</a>

</body>
</html>
