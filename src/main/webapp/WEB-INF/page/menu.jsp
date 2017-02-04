<%-- 
    Document   : menu
    Created on : Nov 3, 2016, 7:11:52 PM
    Author     : oleh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./res/bower_components/angular-material/angular-material.css" rel="stylesheet" type="text/css"/>
        <link href="./res/css/mystyle.css" rel="stylesheet" type="text/css"/>
        
        <script src="./res/js/lib/jquery-2.2.4.min.js" type="text/javascript"></script>

        <script src="./res/bower_components/angular/angular.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-aria/angular-aria.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-animate/angular-animate.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-messages/angular-messages.js" type="text/javascript"></script>

        <script src="./res/bower_components/angular-material/angular-material.js" type="text/javascript"></script>
        <script type="text/javascript">
            angular.module('loginModule', ['ngMaterial']);
        </script>
        <style type="text/css">
            .mbox {
/*                padding: 100px;*/
                margin: 5px;
                width: 250px;
                height: 250px;
            }
            
            .my_grad{
/*                background: #999999;  Old browsers 
                background: -moz-radial-gradient(center, ellipse cover,  #999999 0%, #25445b 100%);  FF3.6-15 
                background: -webkit-radial-gradient(center, ellipse cover,  #999999 0%,#25445b 100%);  Chrome10-25,Safari5.1-6 
                background: radial-gradient(ellipse at center,  #999999 0%,#25445b 100%);  W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ 
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#999999', endColorstr='#25445b',GradientType=1 );  IE6-9 fallback on horizontal gradient */

            }
       </style>
    </head>
    <body ng-app="loginModule"
          ng-cloak
          layout="column" flex
          class="my_grad">
        <div layout="row"
             layout-fill
             layout-align="center center">
            <div class="md-whiteframe-5dp_"
                 style="padding: 20px;"
                 layout="row">
                <div layout="column">
                    <md-button href="./crm">
                        <div class="md-whiteframe-5dp mbox"
                             layout="column"
                             layout-align="center center">
                            <div>
                                <md-icon class="material-icons md-48"
                                     style="height: initial;width: initial;">people</md-icon>
                            </div>
                            <div>
                                <span>CRM</span>
                            </div>
                        </div>
                    </md-button>
                    <md-button href="./admin"
                               ng-disabled="true">
                        <div class="md-whiteframe-5dp mbox"
                             layout="column"
                             layout-align="center center">
                            <div>
                                <md-icon class="material-icons md-48"
                                     style="height: initial;width: initial;">call</md-icon>
                            </div>
                            <div>
                                <span>ADMIN</span>
                            </div>
                        </div>
                    </md-button>
                </div>
                <div layout="column">
                    <md-button href="./admin"
                               ng-disabled="!${isAdmin}">
                        <div class="md-whiteframe-5dp mbox"
                             layout="column"
                             layout-align="center center">
                            <div>
                                <md-icon class="material-icons md-48"
                                     style="height: initial;width: initial;">build</md-icon>
                            </div>
                            <div>
                                <span>ADMIN</span>
                            </div>
                        </div>
                    </md-button>
                    <md-button href="./login/logout">
                        <div class="md-whiteframe-5dp mbox"
                             layout="column"
                             layout-align="center center">
                            <div>
                                <md-icon class="material-icons md-48"
                                     style="height: initial;width: initial;">exit_to_app</md-icon>
                            </div>
                            <div>
                                <span>LOGOUT</span>
                            </div>
                        </div>
                    </md-button>
                </div>
            </div>

        </div>
    </body>
</html>
