var adminModule = angular.module('adminModule',
        [
            'ngMaterial',
            'ngMessages',
            'ngRoute',
            'ui.router'
        ]);

adminModule.factory('dataService', ['$http', function ($http) {

        var serviceInstance = {};


        serviceInstance.addClientType = function (type) {
            return $http.put("./client/type", type);
        };
        serviceInstance.delClientType = function (id) {
            return $http.delete("./client/type/"+id);
        };
        serviceInstance.getClientTypes = function () {
            return $http.get("./client/type/list");
        };
        
        serviceInstance.addClientState = function (state) {
            return $http.put("./client/state", state);
        };
        serviceInstance.delClientState = function (id) {
            return $http.delete("./client/state/"+id);
        };
        serviceInstance.getClientStates = function () {
            return $http.get("./client/state/list");
        };
        
        serviceInstance.addContactType = function (type) {
            return $http.put("./contact/type", type);
        };
        serviceInstance.delContactType = function (id) {
            return $http.delete("./contact/type/"+id);
        };
        serviceInstance.getContactTypes = function () {
            return $http.get("./contact/type/list");
        };
        
        serviceInstance.getIcons = function () {
            return $http.get("./res/js/admin/grid.json");
        };
        
        serviceInstance.addManager = function (user) {
            return $http.put("./user", user);
        };
        serviceInstance.delManager = function (id) {
            return $http.delete("./user/"+id);
        };
        serviceInstance.getAllManagers = function (user) {
            return $http.get("./user/list", user);
        };
        
        //task
        serviceInstance.addTaskType = function (type) {
            return $http.put("./task/type", type);
        };
        serviceInstance.delTaskType = function (id) {
            return $http.delete("./task/type/"+id);
        };
        serviceInstance.getTaskTypes = function () {
            return $http.get("./task/type/list");
        };
        
        serviceInstance.addTaskState = function (state) {
            return $http.put("./task/state", state);
        };
        serviceInstance.delTaskState = function (id) {
            return $http.delete("./task/state/"+id);
        };
        serviceInstance.getTaskStates = function () {
            return $http.get("./task/state/list");
        };
        
        //deal
        serviceInstance.addDealType = function (type) {
            return $http.put("./deal/type", type);
        };
        serviceInstance.delDealType = function (id) {
            return $http.delete("./deal/type/"+id);
        };
        serviceInstance.getDealTypes = function () {
            return $http.get("./deal/type/list");
        };
        
        serviceInstance.addDealState = function (state) {
            return $http.put("./deal/state", state);
        };
        serviceInstance.delDealState = function (id) {
            return $http.delete("./deal/state/"+id);
        };
        serviceInstance.getDealStates = function () {
            return $http.get("./deal/state/list");
        };
        
        serviceInstance.addDealThingType = function (type) {
            return $http.put("./deal/thing/type", type);
        };
        serviceInstance.delDealThingType = function (id) {
            return $http.delete("./deal/thing/type/"+id);
        };
        serviceInstance.getDealThingTypes = function () {
            return $http.get("./deal/thing/type/list");
        };
        
        return serviceInstance;
    }]);
        
adminModule.controller('adminController', ['$scope', '$http', '$compile','$document','$mdSidenav',
    '$log', '$stateParams', '$state', '$timeout', 'dataService', '$mdDialog',
function ($scope, $http, $compile, $document, $mdSidenav, $log, $stateParams, $state, $timeout, dataService, $mdDialog) {
    
    $scope.testFunc = function (text){
        alert(text);
    };
    
    $scope.clientTypes = [];
    $scope.clientStates = [];
    $scope.contactTypes = [];
    $scope.taskTypes = [];
    $scope.taskStates = [];
    $scope.managers = [];
    $scope.grid = {};
    $scope.dealTypes = [];
    $scope.dealStates = [];
    $scope.dealThingTypes = [];
    
    $scope.showThingTypeFlags = {};
    
    $scope.init = function(){
        dataService.getClientTypes().success(function(data){
            $scope.clientTypes = data;
        });
        dataService.getClientStates().success(function(data){
            $scope.clientStates = data;
        });
        dataService.getContactTypes().success(function(data){
            $scope.contactTypes = data;
        });
        dataService.getAllManagers().success(function(data){
            $scope.managers = data;
        });
        dataService.getTaskTypes().success(function(data){
            $scope.taskTypes = data;
        });
        dataService.getTaskStates().success(function(data){
            $scope.taskStates = data;
        });
        
        //deal things
        dataService.getDealTypes().success(function(data){
            $scope.dealTypes = data;
        });
        dataService.getDealStates().success(function(data){
            $scope.dealStates = data;
            angular.forEach(data, function(val){
                $scope.showThingTypeFlags[val] = false;
            });
        });
        dataService.getDealThingTypes().success(function(data){
            $scope.dealThingTypes = data;
        });
        dataService.getIcons().success(function(data){
            $scope.grid = data;
            //console.log($scope.grid);
        });
        
    };
    
    $scope.reloadManagers = function(){
        dataService.getAllManagers().success(function(data){
            $scope.managers = data;
        });
    };
    $scope.delManager = function (id) {
        dataService.delManager(id).success(function (data) {
            $scope.reloadManagers();
        });
    };
    
    $scope.saveClientType = function(type){
        dataService.addClientType(type).success(function(data){
            console.log("save cl type with id:",data);
            dataService.getClientTypes().success(function(data){
                $scope.clientTypes = data;
            });
        });
    };
    $scope.delClientType = function(id){
        dataService.delClientType(id).success(function(data){
            dataService.getClientTypes().success(function(data){
                $scope.clientTypes = data;
            });
        });
    };
    
    $scope.saveClientState = function(state){
        dataService.addClientState(state).success(function(data){
            console.log("save cl state with id:",data);
            dataService.getClientStates().success(function(data){
                $scope.clientStates = data;
            });
        });
    };
    $scope.delClientState = function(id){
        dataService.delClientState(id).success(function(data){
            dataService.getClientStates().success(function(data){
                $scope.clientStates = data;
            });
        });
    };
    
    $scope.saveTaskType = function(type){
        dataService.addTaskType(type).success(function(data){
            console.log("save tsk type with id:",data);
            dataService.getTaskTypes().success(function(data){
                $scope.taskTypes = data;
            });
        });
    };
    $scope.delTaskType = function(id){
        dataService.delTaskType(id).success(function(data){
            dataService.getTaskTypes().success(function(data){
                $scope.taskTypes = data;
            });
        });
    };
    
    $scope.saveTaskState = function(state){
        dataService.addTaskState(state).success(function(data){
            console.log("save tsk state with id:",data);
            dataService.getTaskStates().success(function(data){
                $scope.taskStates = data;
            });
        });
    };
    $scope.delTaskState = function(state){
        dataService.delTaskState(state).success(function(data){
            console.log("save tsk state with id:",data);
            dataService.getTaskStates().success(function(data){
                $scope.taskStates = data;
            });
        });
    };
    
    $scope.saveContactType = function(type){
        dataService.addContactType(type).success(function(data){
            console.log("save cl type with id:",data);
            dataService.getContactTypes().success(function(data){
                $scope.contactTypes = data;
            });
        });
    };
    $scope.delContactType = function(id){
        dataService.delContactType(id).success(function(data){
            dataService.getContactTypes().success(function(data){
                $scope.contactTypes = data;
            });
        });
    };
    
    //deal
    $scope.saveDealState = function(state){
        dataService.addDealState(state).success(function(data){
            console.log("save deal state with id:",data);
            dataService.getDealStates().success(function(data){
                $scope.dealStates = data;
            });
        });
    };
    $scope.delDealState = function(id){
        dataService.delDealState(id).success(function(data){
            dataService.getDealStates().success(function(data){
                $scope.dealStates = data;
            });
        });
    };
    $scope.saveDealType = function(type){
        dataService.addDealType(type).success(function(data){
            console.log("save deal type with id:",data);
            dataService.getDealTypes().success(function(data){
                $scope.dealTypes = data;
            });
        });
    };
    $scope.delDealType = function(id){
        dataService.delDealType(id).success(function(data){
            dataService.getDealTypes().success(function(data){
                $scope.dealTypes = data;
            });
        });
    };
    $scope.saveThingType = function(type){
        dataService.addDealThingType(type).success(function(data){
            console.log("save thing type with id:",data);
            dataService.getDealTypes().success(function(data){
                $scope.dealTypes = data;
                $scope.showThingTypeFlags[type.dealType.id] = true;
            });
        });
    };
    $scope.delThingType = function(id, dealTypeId){
        dataService.delDealThingType(id).success(function(data){
            dataService.getDealTypes().success(function(data){
                $scope.dealTypes = data;
                $scope.showThingTypeFlags[dealTypeId] = true;
            });
        });
    };
    
    $scope.decoratedThing = function(thing, dealType){
        thing.dealType = {
            id: dealType.id
        };
        return thing;
    };
    $scope.showAdvanced = function (entity, saveCallback) {
        $mdDialog.show({
            controller: function($scope, $mdDialog, grid, entity) {
                $scope.grid = grid;
                $scope.entity = entity;
                $scope.hide = function () {
                    $mdDialog.hide();
                };
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
                $scope.answer = function (answer) {
                    $mdDialog.hide(answer);
                };
                $scope.saveEntity = function(icon){
                    $scope.entity.icon = icon;
                    saveCallback($scope.entity);
                    $mdDialog.hide();
                };
            },
            templateUrl: './res/views/admin/icon_dialog.html',
            parent: angular.element(document.body),
            //targetEvent: ev,
            clickOutsideToClose: true,
            locals: {
                    grid: $scope.grid, 
                    entity: entity
                },
            fullscreen: true//$scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
                .then(function (answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function () {
                    $scope.status = 'You cancelled the dialog.';
                });
    };
    $scope.showManagerDialog = function (entity, closeCallback) {
        $mdDialog.show({
            controller: function($scope, $mdDialog, entity, dataService) {
                $scope.entity = angular.copy(entity);
                $scope.changePass = false;
                if(entity.id===null||entity.id===undefined){
                    $scope.changePass = true;
                }
                $scope.hide = function () {
                    $mdDialog.hide();
                };
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
                $scope.answer = function (answer) {
                    $mdDialog.hide(answer);
                };
                $scope.saveManager = function(){
                    dataService.addManager($scope.entity).success(function(data){
                        try{
                            closeCallback();
                        }catch(e){}
                        $mdDialog.hide("asd");
                    });
                };
                $scope.changePassw = function () {
                    $scope.changePass = true;
                };
            },
            templateUrl: './res/views/admin/manager_dialog.html',
            parent: angular.element(document.body),
            //targetEvent: ev,
            clickOutsideToClose: true,
            locals: {
                entity: entity
            },
            fullscreen: true//$scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
        .then(function (answer) {}, function () {});
    };
    $scope.showEntityDialog = function (entity, saveCallback, view) {
        $mdDialog.show({
            controller: function($scope, $mdDialog, entity, dataService) {
                $scope.entity = angular.copy(entity);
                $scope.hide = function () {
                    $mdDialog.hide();
                };
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
                $scope.answer = function (answer) {
                    $mdDialog.hide(answer);
                };
                $scope.saveEntity = function(){
                    saveCallback($scope.entity);
                    $mdDialog.hide();
                };
            },
            templateUrl: view?view:'./res/views/admin/entity_dialog.html',
            parent: angular.element(document.body),
            //targetEvent: ev,
            clickOutsideToClose: true,
            locals: {
                entity: entity
            },
            fullscreen: true//$scope.customFullscreen // Only for -xs, -sm breakpoints.
        })
        .then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });
    };
    
    $scope.data = {
        selectedIndex: 0,
        secondLocked: false,
        secondLabel: "Клієнти",
        bottom: false
    };
    $scope.next = function () {
        $scope.data.selectedIndex = Math.min($scope.data.selectedIndex + 1, 2);
    };
    $scope.previous = function () {
        $scope.data.selectedIndex = Math.max($scope.data.selectedIndex - 1, 0);
    };
    
}]);