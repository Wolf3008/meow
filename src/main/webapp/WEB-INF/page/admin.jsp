<%-- 
    Document   : admin
    Created on : Nov 3, 2016, 7:11:38 PM
    Author     : oleh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./res/bower_components/angular-material/angular-material.css" rel="stylesheet" type="text/css"/>

        <link href="./res/css/mystyle.css" rel="stylesheet" type="text/css"/>

        <script src="./res/js/lib/jquery-2.2.4.min.js" type="text/javascript"></script>

        <script src="./res/bower_components/angular/angular.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-aria/angular-aria.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-animate/angular-animate.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-messages/angular-messages.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-route/angular-route.js" type="text/javascript"></script>
        <script src="./res/bower_components/angular-ui-router/release/angular-ui-router.js" type="text/javascript"></script>

        <script src="./res/bower_components/angular-material/angular-material.js" type="text/javascript"></script>

        <script src="./res/js/admin/admin-main.js" type="text/javascript"></script>

        <style type="text/css">
            .mbox {
                padding: 100px;
                margin: 20px;
            }
        </style>
    </head>
    <body ng-app="adminModule" 
          layout="column"
          ng-cloak>
    <md-toolbar class="main_header"
                layout-align=" center">
        <div class="md-toolbar-tools">
            <img src="./res/img/meow1.svg" 
                 alt="icon"
                 class="meow_icon"/>
            <h3> Meow-ADMIN</h3>
            <div flex>
            </div>
            <md-icon class="material-icons md-avatar-icon"
                     style="color: cornflowerblue;">account_box</md-icon>
            <span>${userName}</span>
            <md-button href="./menu" class="md-fab md-mini">
                <md-icon class="material-icons md-avatar-icon">apps</md-icon>
            </md-button>
        </div>
    </md-toolbar>
    <div ng-controller="adminController"
         layout="row"
         flex>
<!--        <md-content class="md-padding"
                    flex>-->
        <div flex layout="row"
             ng-init="init()">
            <md-tabs md-border-bottom class="md-accent_ stretch-height"
                     flex>
                <md-tab id="tab1">
                    <md-tab-label>Менеджери</md-tab-label>
                    <md-tab-body>
                        <div layout-fill layout="row">
                            <div flex>
                                <md-list ng-cloak>
                                    <md-subheader class="md-no-sticky">Менеджери 
                                        <md-button class="md-icon-button"
                                                   ng-click="showManagerDialog({}, reloadManagers)">
                                            <md-tooltip md-direction="right">Створити</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                    </md-subheader>
                                    <md-list-item ng-repeat="manager in managers" 
                                                  ng-click="showManagerDialog(manager, reloadManagers)" 
                                                  class="noright md-2-line">
                                        <md-icon class="material-icons md-avatar-icon">person</md-icon>
                                        <div class="md-list-item-text">
                                            <h3>{{ manager.name }}</h3>
                                            <p>Login: {{ manager.login }}</p>
                                        </div>
                                        <md-icon class="md-secondary md-icons"
                                                 ng-click="delManager(manager.id)"
                                                 aria-label="Delete">delete</md-icon>
                                    </md-list-item>
                                </md-list>
                            </div>
                            
                        </div>
                    </md-tab-body>
                </md-tab>
                <md-tab id="tab2" ng-disabled="data.secondLocked">
                    <md-tab-label>{{data.secondLabel}}</md-tab-label>
                    <md-tab-body>
                        <div layout-fill>
                            <md-list ng-cloak>
                                <md-subheader class="md-no-sticky">Типи клієнтів
                                    <md-button class="md-icon-button"
                                               ng-click="showEntityDialog({}, saveClientType)">
                                        <md-tooltip md-direction="right">Створити</md-tooltip>
                                        <md-icon class="material-icons">add</md-icon>
                                    </md-button>
                                </md-subheader>
                                <md-list-item ng-repeat="clType in clientTypes"
                                              ng-click="showEntityDialog(clType, saveClientType)"
                                              class="md-2-line">
                                    <md-button class="md-icon-button"
                                               ng-click="showAdvanced(clType, saveClientType)">
                                        <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                        <md-icon class="material-icons" ng-if="clType.icon">{{clType.icon}}</md-icon>
                                        <md-icon class="material-icons" ng-if="!clType.icon">block</md-icon>
                                    </md-button>
                                    <div class="md-list-item-text">
                                        <h3>{{clType.name}}</h3>
                                        <p>{{clType.description}}</p>
                                    </div>
                                    <span flex></span>
                                    <md-icon class="md-secondary md-icons"
                                             ng-click="delClientType(clType.id)"
                                             aria-label="Delete">delete</md-icon>
                                </md-list-item>
                                <md-divider></md-divider>
                                <md-subheader class="md-no-sticky">Статуси клієнтів
                                    <md-button class="md-icon-button"
                                               ng-click="showEntityDialog({}, saveClientState)">
                                        <md-tooltip md-direction="right">Створити</md-tooltip>
                                        <md-icon class="material-icons">add</md-icon>
                                    </md-button>
                                </md-subheader>
                                <md-list-item ng-repeat="clState in clientStates"
                                              ng-click="showEntityDialog(clState, saveClientState)"
                                              class="md-2-line">
                                    <md-button class="md-icon-button"
                                               ng-click="showAdvanced(clState, saveClientState)">
                                        <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                        <md-icon class="material-icons" ng-if="clState.icon">{{clState.icon}}</md-icon>
                                        <md-icon class="material-icons" ng-if="!clState.icon">block</md-icon>
                                    </md-button>
                                    <div class="md-list-item-text">
                                        <h3>{{clState.name}}</h3>
                                        <p>{{clState.description}}</p>
                                    </div>
                                    <span flex></span>
                                    <md-icon class="md-secondary md-icons"
                                             ng-click="delClientState(clState.id)"
                                             aria-label="Delete">delete</md-icon>
                                    <!--<md-switch class="md-secondary" ng-model="setting.enabled"></md-switch>-->
                                </md-list-item>
                                <md-divider></md-divider>
                                <md-subheader class="md-no-sticky">Типи контактів
                                    <md-button class="md-icon-button"
                                               ng-click="showEntityDialog({}, saveContactType, './res/views/admin/contact_type_dialog.html')">
                                        <md-tooltip md-direction="right">Створити</md-tooltip>
                                        <md-icon class="material-icons">add</md-icon>
                                    </md-button>
                                </md-subheader>
                                <md-list-item ng-repeat="cntType in contactTypes"
                                              ng-click="showEntityDialog(cntType, saveContactType, './res/views/admin/contact_type_dialog.html')"
                                              class="md-2-line">
                                    <md-button class="md-icon-button"
                                               ng-click="showAdvanced(cntType, saveContactType)">
                                        <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                        <md-icon class="material-icons" ng-if="cntType.icon">{{cntType.icon}}</md-icon>
                                        <md-icon class="material-icons" ng-if="!cntType.icon">block</md-icon>
                                    </md-button>
                                    <div class="md-list-item-text">
                                        <h3>{{cntType.name}}</h3>
                                        <p>{{cntType.description}}</p>
                                    </div>
                                    <span flex></span>
                                    <md-icon class="md-secondary md-icons"
                                             ng-click="delContactType(cntType.id)"
                                             aria-label="Delete">delete</md-icon>
                                </md-list-item>
                            </md-list>
                        </div>
                    </md-tab-body>
                </md-tab>
                <md-tab id="tab3">
                    <md-tab-label>Задачі</md-tab-label>
                    <md-tab-body>
                        <div layout-fill layout="row">
                            <div flex>
                                <md-list ng-cloak>
                                    <md-subheader class="md-no-sticky">Типи задач
                                        <md-button class="md-icon-button"
                                                   ng-click="showEntityDialog({}, saveTaskType)">
                                            <md-tooltip md-direction="right">Створити</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                    </md-subheader>
                                    <md-list-item ng-repeat="tskType in taskTypes"
                                                  ng-click="showEntityDialog(tskType, saveTaskType)"
                                                  class="md-2-line">
                                        <md-button class="md-icon-button"
                                                   ng-click="showAdvanced(tskType, saveTaskType)">
                                            <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                            <md-icon class="material-icons" ng-if="tskType.icon">{{tskType.icon}}</md-icon>
                                            <md-icon class="material-icons" ng-if="!tskType.icon">block</md-icon>
                                        </md-button>
                                        <div class="md-list-item-text">
                                            <h3>{{tskType.name}}</h3>
                                            <p>{{tskType.description}}</p>
                                        </div>
                                        <span flex></span>
                                        <md-icon class="md-secondary md-icons"
                                                 ng-click="delTaskType(tskType.id)"
                                                 aria-label="Delete">delete</md-icon>
                                    </md-list-item>
                                    <md-divider></md-divider>
                                    <md-subheader class="md-no-sticky">Статуси задач
                                        <md-button class="md-icon-button"
                                                   ng-click="showEntityDialog({}, saveTaskState)">
                                            <md-tooltip md-direction="right">Створити</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                    </md-subheader>
                                    <md-list-item ng-repeat="tskState in taskStates"
                                                  ng-click="showEntityDialog(tskState, saveTaskState)"
                                                  class="md-2-line">
                                        <md-button class="md-icon-button"
                                                   ng-click="showAdvanced(tskState, saveTaskState)">
                                            <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                            <md-icon class="material-icons" ng-if="tskState.icon">{{tskState.icon}}</md-icon>
                                            <md-icon class="material-icons" ng-if="!tskState.icon">block</md-icon>
                                        </md-button>
                                        <div class="md-list-item-text">
                                            <h3>{{tskState.name}}</h3>
                                            <p>{{tskState.description}}</p>
                                        </div>
                                        <span flex></span>
                                        <md-icon class="md-secondary md-icons"
                                                 ng-click="delTaskState(tskState.id)"
                                                 aria-label="Delete">delete</md-icon>
                                        <!--<md-switch class="md-secondary" ng-model="setting.enabled"></md-switch>-->
                                    </md-list-item>
                                </md-list>
                            </div>
                        </div>
                    </md-tab-body>
                </md-tab>
                <md-tab id="tab4">
                    <md-tab-label>Угоди</md-tab-label>
                    <md-tab-body>
                        <div layout-fill layout="row">
                            <div flex>
                                <md-list ng-cloak>
                                    <md-subheader class="md-no-sticky"
                                                  style="//background-color: #add2f3;">Типи договорів
                                        <md-button class="md-icon-button"
                                                   ng-click="showEntityDialog({}, saveDealType)">
                                            <md-tooltip md-direction="right">Створити</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                    </md-subheader>
                                    <md-list-item ng-repeat-start="dealType in dealTypes"
                                                  ng-click="showEntityDialog(dealType, saveDealType)"
                                                  class="md-2-line"
                                                  layout='row'>
                                        <md-button class="md-icon-button"
                                                   ng-click="showAdvanced(dealType, saveDealType)">
                                            <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                            <md-icon class="material-icons" ng-if="dealType.icon">{{dealType.icon}}</md-icon>
                                            <md-icon class="material-icons" ng-if="!dealType.icon">block</md-icon>
                                        </md-button>
                                        <div class="md-list-item-text">
                                            <h3>{{dealType.name}}</h3>
                                            <p>{{dealType.description}}</p>
                                        </div>
                                        <span flex></span>
                                        <span class="" style="margin-right: 15px;">Показати сутності</span>
                                        <md-switch class="" ng-model="showThingTypeFlags[dealType.id]">
                                            <md-tooltip md-direction="left">Показати сутності</md-tooltip>
                                        </md-switch>
                                        <md-button class="md-icon-button"
                                                   ng-click="showEntityDialog(decoratedThing({}, dealType), saveThingType, './res/views/admin/thing_type_dialog.html')">
                                            <md-tooltip md-direction="left">Створити сутність</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                        <md-button class="md-icon-button" ng-click="delDealType(dealType.id)">
                                            <md-icon class="md-icons"
                                                     aria-label="Delete">delete</md-icon>
                                        </md-button>
                                        
                                    </md-list-item>
                                    <md-list-item ng-repeat-end ng-repeat ="thingType in dealType.thingTypes"
                                                  ng-click="showEntityDialog(decoratedThing(thingType, dealType), saveThingType, './res/views/admin/thing_type_dialog.html')"
                                                  style="margin-left: 40px;"
                                                  class="md-2-line"
                                                  layout='row'
                                                  ng-show="showThingTypeFlags[dealType.id]">
                                        <md-button class="md-icon-button"
                                                   ng-click="showAdvanced(decoratedThing(thingType, dealType), saveThingType, './res/views/admin/thing_type_dialog.html')">
                                            <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                            <md-icon class="material-icons" ng-if="thingType.icon">{{thingType.icon}}</md-icon>
                                            <md-icon class="material-icons" ng-if="!thingType.icon">block</md-icon>
                                        </md-button>
                                        <div class="md-list-item-text">
                                            <h3>{{thingType.name}}</h3>
                                            <p>{{thingType.description}}</p>
                                        </div>
                                        <span flex></span>
                                        <md-button>
                                            <md-tooltip md-direction="left">Вартість</md-tooltip>
                                            <span>{{thingType.cost}}</span>
                                        </md-button>
                                        <md-button class="md-icon-button" ng-click="delThingType(thingType.id, thingType.dealType.id)">
                                            <md-icon class="md-icons"
                                                     aria-label="Delete">delete</md-icon>
                                        </md-button>
                                    </md-list-item>
                                    <md-divider></md-divider>
                                    <!--<md-divider></md-divider>-->
                                    <md-subheader class="md-no-sticky">Статуси договорів
                                        <md-button class="md-icon-button"
                                                   ng-click="showEntityDialog({}, saveDealState)">
                                            <md-tooltip md-direction="right">Створити</md-tooltip>
                                            <md-icon class="material-icons">add</md-icon>
                                        </md-button>
                                    </md-subheader>
                                    <md-list-item ng-repeat="dealState in dealStates"
                                                  ng-click="showEntityDialog(dealState, saveDealState)"
                                                  class="md-2-line"
                                                  layout='row'>
                                        <md-button class="md-icon-button"
                                                   ng-click="showAdvanced(dealState, saveDealState)">
                                            <md-tooltip md-direction="top">Вибрати іконку</md-tooltip>
                                            <md-icon class="material-icons" ng-if="dealState.icon">{{dealState.icon}}</md-icon>
                                            <md-icon class="material-icons" ng-if="!dealState.icon">block</md-icon>
                                        </md-button>
                                        <div class="md-list-item-text">
                                            <h3>{{dealState.name}}</h3>
                                            <p>{{dealState.description}}</p>
                                        </div>
                                        <span flex></span>
                                        <md-icon class="md-secondary md-icons"
                                                 ng-click="delDealState(dealState.id)"
                                                 aria-label="Delete">delete</md-icon>
                                    </md-list-item>
                                    <md-divider></md-divider>
                                </md-list>
                            </div>
                        </div>
                    </md-tab-body>
                </md-tab>
            </md-tabs>
        </div>
    </div>
</body>
</html>
