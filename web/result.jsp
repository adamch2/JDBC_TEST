<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 19/02/17
  Time: 09:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*"%>
<%@ page import="Model.NamesDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tables</title>
</head>
<body>

<%

%>
<%
    NamesDAO nd = new NamesDAO();
    NamesDAO nd2 = new NamesDAO();
    nd.setConnection();
    nd2.setConnection();
    ResultSet rs = nd.getStatement().executeQuery("SELECT name,surname,age FROM test.test_names;");
    ResultSet rs2 = nd2.getStatement().executeQuery("SELECT select_name,select_age FROM test.select_names;");

%>

<style>
    .col-1 {
        float: left;
        width: 20%;
    }
    .col-2 {
        float: left;
        width: 20%;
    }
</style>


<div class="col-1">

<table class="table table-striped">
    <%while (rs.next()){%>
    <tr>
        <td><% out.println(rs.getString("Name")); %></td>
        <td><% out.println(rs.getString("Surname")); %></td>
        <td><% out.println(rs.getString("Age")); %></td>
        <td>
            <form method="post" action="doResultController">
                <input type="hidden" name="button">
                <input type="hidden" name="row">
                <input type="hidden" name="table">

                <button type="submit" name="deleteButton" onclick="row.value=this.parentNode.parentNode.parentNode.rowIndex; button.value=1; table.value=1;">Delete</button>
                <button type="submit" name="selectButton" onclick="row.value=this.parentNode.parentNode.parentNode.rowIndex; button.value=2;">Add</button>
            </form>
        </td>
    </tr>
    <%}%>
</table>

    <form method="post" action="doResultController">
        <input type="hidden" name="button">
        <button type="submit" onclick="button.value=3">Export to CSV</button>
    </form>


</div>

<div class="col-2">

    <table class="table table-striped">
        <%while (rs2.next()){%>
        <tr>
            <td><% out.println(rs2.getString("select_name")); %></td>
            <td><% out.println(rs2.getString("select_age")); %></td>
            <td>
                <form method="post" action="doResultController">
                    <input type="hidden" name="button">
                    <input type="hidden" name="row">
                    <input type="hidden" name="table">

                    <button type="submit" name="deleteButton" onclick="row.value=this.parentNode.parentNode.parentNode.rowIndex; button.value=1; table.value=2;">Delete</button>
                </form>
            </td>
        </tr>
        <%}%>

    </table>
<%
nd.closeConnection();
%>
</div>

<a href="/JDBC_test_war_exploded/" >Send Me To Main Page!</a>

</body>
</html>
