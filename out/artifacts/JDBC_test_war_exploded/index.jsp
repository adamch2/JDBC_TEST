<%--
  Created by IntelliJ IDEA.
  User: adam
  Date: 16/02/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>JDBC TEST</title>
  </head>
  <body>

  <form action="doNamesController" method="post" role="form">
    <legend>Names List</legend>

    <div class="form-group">
      <label for="1"></label>

      <input type="text" class="form-control" name="param1" id="1" placeholder="name..."><br>
      <input type="text" class="form-control" name="param2" id="2" placeholder="surname..."><br>
      <input type="text" class="form-control" name="param3" id="3" placeholder="age..."><br>
      <input type="hidden" name="button">
    </div>

    <button type="submit" class="btn btn-primary" onclick="{button.value=1}">ADD</button>
    <button type="submit" class="btn btn-primary" onclick="{button.value=2}">SHOW</button>
  </form>

  </body>
</html>
