<%-- 
    Document   : index
    Created on : Nov 3, 2016, 7:11:29 PM
    Author     : oleh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Meow</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link href="./res/bower_components/angular-material/angular-material.css" rel="stylesheet" type="text/css"/>
        <link href="./res/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="./res/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="./res/bower_components/mdPickers/dist/mdPickers.css" rel="stylesheet" type="text/css"/>
       
        
        <link href="./res/css/mystyle.css" rel="stylesheet" type="text/css"/>
        
        <script src="./res/js/lib/jquery-2.2.4.min.js" type="text/javascript"></script>
        <script src="./res/js/lib/bootstrap.min.js" type="text/javascript"></script>
        
        <script src="./res/bower_components/angular/angular.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-aria/angular-aria.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-animate/angular-animate.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-messages/angular-messages.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-route/angular-route.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-ui-router/release/angular-ui-router.js" type="text/javascript"></script>
        <script src="./res/bower_components/moment/moment.js" type="text/javascript"></script>
        <script src="./res/bower_components/moment/locale/uk.js" type="text/javascript"></script>
        <script src="./res/bower_components/mdPickers/dist/mdPickers.js" type="text/javascript"></script>
        
        <script src="./res/bower_components/d3/d3.min.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-charts/dist/angular-charts.js" type="text/javascript"></script>
        
        <script src="./res/bower_components/angular-material/angular-material.js" type="text/javascript"></script>
        
        <script src="./res/js/lib/ui-bootstrap-tpls-1.3.3.min.js" type="text/javascript"></script>
        
        <script src="./res/js/basic/di.js" type="text/javascript"></script>
        <script src="./res/js/crm/main-module.js" type="text/javascript"></script>
        <script src="./res/js/basic/toast-service.js" type="text/javascript"></script>
        
        <script src="./res/js/crm/main-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/client-state-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/client-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/services-and-states.js" type="text/javascript"></script>
        
        <script src="./res/js/crm/task-state-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/cdr-state-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/deal-state-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/phone-controller.js" type="text/javascript"></script>
        <script src="./res/js/crm/report-state-controler.js" type="text/javascript"></script>
        
    </head>
    <body ng-app="mainModule"
          ng-controller="mainCtr as main"
          ng-cloak
          layout="column">
        <md-toolbar class="main_header"
                    layout-align=" center">
            <div class="md-toolbar-tools">
                <img src="./res/img/meow1.svg" 
                     alt="icon"
                     class="meow_icon"/>
                <h3> Meow-CRM</h3>
                <div flex>
                </div>
                <md-icon class="material-icons md-avatar-icon"
                         style="color: cornflowerblue;">account_box</md-icon>
                <span>${userName}</span>
                <md-button href="./menu" class="md-fab md-mini">
                    <md-icon class="material-icons md-avatar-icon">apps</md-icon>
                </md-button>
                <md-button class="md-fab md-mini" 
                           aria-label="Phone"
                           ng-click="togglePhone()">
                    <md-icon class="material-icons">phone</md-icon>
                </md-button>
            </div>
        </md-toolbar>
        <div layout="row" flex>
<!--menu-->
            <md-content layout="column" layout-align=" stretch"
                 class="meow_left_panel_"
                 md-theme="docs-dark">
                <md-button class="md-grid-item-content main_btn"
                           ng-click="setState('clients')"
                           ng-class="btmActiveState[1]?'main_btn_on':null">
                    <md-icon class="material-icons">people</md-icon>
                </md-button>
                <md-button class="md-grid-item-content main_btn"
                           ng-click="setState('tasks')"
                           ng-class="btmActiveState[3]?'main_btn_on':null">
                    <md-icon class="material-icons">alarm</md-icon>
                </md-button>
                <md-button class="md-grid-item-content main_btn"
                           ng-click="setState('calls')"
                           ng-class="btmActiveState[0]?'main_btn_on':null">
                    <md-icon class="material-icons">settings_phone</md-icon>
                </md-button>
                
                <md-button class="md-grid-item-content main_btn"
                           ng-click="setState('deals')"
                           ng-class="btmActiveState[2]?'main_btn_on':null">
                    <md-icon class="material-icons">attach_money</md-icon>
                </md-button>
                
                <md-button class="md-grid-item-content main_btn"
                           ng-click="setState('reports')"
                           ng-class="btmActiveState[4]?'main_btn_on':null">
                    <md-icon class="material-icons">trending_up</md-icon>
                </md-button>
                <span flex></span>
            </md-content>
<!--content-->
            <div ui-view="body" 
                 layout="column" 
                 flex
                 style="
                    position: relative;
                    ">
                        
            </div>
            <md-sidenav class="md-sidenav-right md-whiteframe-3dp" 
                        md-component-id="phone"
                        md-disable-backdrop="">
                <div ng-controller="phoneController" 
                            layout-padding
                            layout="column"
                            layout-fill>
                    <div layout="column"
                         flex>
                        <div layout="row">
                            <md-button ng-click="close()" class="md-icon-button">
                                <md-icon>clear</md-icon>
                            </md-button>
                            <md-button ng-click="showClientsOnLine()" 
                                       class="md-icon-button">
                                <md-icon>contact_phone</md-icon>
                            </md-button>
                        </div>
                        <!--<md-divider></md-divider>-->
                        <md-input-container>
                            <label for="testInput">Номер</label>
                            <input type="text"
                                   ng-model="currNum"/>
                            <md-icon ng-click="clearCurrNum()">delete</md-icon>
                        </md-input-container>
                        <div layout="row">
                            <md-button ng-click="callService.hangup()"
                                       class="phone_red_btn md-raised"
                                       flex>
                                <md-icon>call_end</md-icon>
                            </md-button>
<!--                            <md-button ng-click="callService.redirectTo(currNum)"
                                       ng-disabled="!currNum"
                                       flex>
                                <md-icon>phone_forwarded</md-icon>
                            </md-button>-->
<div flex></div>
                            <md-button ng-click="callService.callTo(currNum)"
                                       ng-disabled="!currNum"
                                       class="phone_green_btn md-raised"
                                       flex>
                                <md-icon>call</md-icon>
                            </md-button>
                        </div>
                        <md-input-container>
                            <table class="phone_keyboard">
                            <tbody>
                                <tr>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(1)">1</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(2)">2</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(3)">3</md-button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(4)">4</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(5)">5</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(6)">6</md-button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(7)">7</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(8)">8</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(9)">9</md-button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit('*')">*</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit(0)">0</md-button>
                                    </td>
                                    <td>
                                        <md-button class="md-raised" ng-click="pushDigit('#')">#</md-button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </md-input-container>
                        <md-content flex>
                            <md-list>
                                <md-divider></md-divider>
                                <md-subheader class="md-no-sticky">Внутрішні номери:</md-subheader>
                                <md-list-item class="md-2-line" 
                                              ng-repeat="man in directory.managers" 
                                              ng-click="callService.callTo(man.extNum, 'from-internal')"
                                              ng-show="man.extNum"
                                              layout="row">
                                    <md-icon class="material-icons md-avatar-icon">person</md-icon>
                                    <div class="md-list-item-text">
                                        <h3>{{ man.name}}</h3>
                                        <p>{{ man.extNum }}</p>
                                    </div>
                                    <div flex></div>
<!--                                    <md-button class='md-icon-button'
                                               ng-click="callService.redirectTo(man.extNum)">
                                        <md-icon>phone_forwarded</md-icon>
                                    </md-button>-->
                                </md-list-item>
                            </md-list>
                        </md-content>
                    </div>
                    
                </div>
            </md-sidenav>
        </div>
    </body >
</html>
