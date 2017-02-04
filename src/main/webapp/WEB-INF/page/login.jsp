<%-- 
    Document   : login
    Created on : Nov 3, 2016, 7:12:21 PM
    Author     : oleh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../res/bower_components/angular-material/angular-material.css" rel="stylesheet" type="text/css"/>

        <script src="../res/js/lib/jquery-2.2.4.min.js" type="text/javascript"></script>

        <script src="../res/bower_components/angular/angular.js" type="text/javascript"></script>
        <script src="../res/bower_components/angular-aria/angular-aria.js" type="text/javascript"></script>
        <script src="../res/bower_components/angular-animate/angular-animate.js" type="text/javascript"></script>
        <script src="../res/bower_components/angular-messages/angular-messages.js" type="text/javascript"></script>

        <script src="../res/bower_components/angular-material/angular-material.js" type="text/javascript"></script>
        <script type="text/javascript">
            angular.module('loginModule', ['ngMaterial']);
        </script>
    </head>
    <body ng-app="loginModule" 
          layout="column" flex
          class="my_grad">
        <div layout="row"
             layout-fill
             layout-align="center center">
            <div class="md-whiteframe-5dp"
                 style="padding: 20px;">
                <c:if test="${filed}"><div style="color: red;"><h4>Не вірні авторизаційні дані!</h4><p>спробуйте ще</p></div></c:if>
                <form action="../j_spring_security_check" method="post">
                    <md-input-container class="md-block">
                        <label>Логін</label>
                        <input type="text" required name="login"/>
                    </md-input-container>
                    <md-input-container class="md-block">
                        <label>Пароль</label>
                        <input type="password" required name="password"/>
                    </md-input-container>
                    <div flex layout="row" layout-alighn="center center">
                        <md-button type="submit">Увійти</md-button>
                    </div>
                    
                </form>
            </div>

        </div>
    </body>
</html>
