<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tittle page</title>
        <!--<link rel="stylesheet" type="text/css" href="../stylesheets/tooltip.css" />-->
        <script type="text/javascript" src="scripts/jquery.js"></script>
        <script type="text/javascript" src="scripts/tipper.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheets/taxi.css">
    </head>
    <body>
        <div id="header"><h1>Welcome!</h1></div>
        <div align="center">
            <a href="navigationRequest.jsp">request list</a>
            &nbsp<span tip="Просмотр и редактирование заявок">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
            <a href="navigationType.jsp">type list</a>
            &nbsp<span tip="Просмотр и редактирование типов машин">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
            <a href="navigationCar.jsp">car list</a>
            &nbsp<span tip="Просмотр и редактирование машин">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
            <a href="createRequest.jsp">create new request</a>
            &nbsp<span tip="Создание новой заявки">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
            <a href="createType.jsp">create new type</a>
            &nbsp<span tip="Создание нового типа">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
            <a href="createCar.jsp">create new car</a>
            &nbsp<span tip="Создание новой машины">
                 <img src="pictures/Help_and_Support.gif" alt="help" style="width: 15px;height: 15px"></span><br><br>
        </div>
        <div id="footer">&copy; DreamJTeam</div>
    </body>
</html>
