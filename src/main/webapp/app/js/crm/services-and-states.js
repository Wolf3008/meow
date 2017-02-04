mainModule.factory('ClientsListState', [function () {
    return {
        enableFilter : true,
        filter : {
            type:[],
            state:[],
            manager:[]
        },
        currClient:null,
        //clientPanelIsOpen: false
        selectedClients:[]
    };
}]);
mainModule.factory('TasksListState', [function () {
    return {
        enableFilter : true,
        filter : {
            type:[],
            state:[],
            manager:[]
        },
        currTask:null,
        taskPeriod: 'all'
        //clientPanelIsOpen: false
    };
}]);
mainModule.factory('CallsListState', [function () {
    return {
        enableFilter : true,
        filter : {
            numbers:[],
            manager:[]
        }
        //clientPanelIsOpen: false
    };
}]);
mainModule.factory('DealsListState', [function () {
    return {
        enableFilter : true,
        filter : {
            type:[],
            state:[],
            manager:[]
        },
        selectedDeals:[]
    };
}]);
mainModule.factory('ReportsListState', [function () {
    return {
        enableFilter : true,
        filter : {
            manager:[]
        },
        chartType: "pie",
        chartData: null,
        currTitle : "Клієнти"
    };
}]);
mainModule.factory('Directory', ['dataService', function (dataService) {
    return {
        contactTypes : [],
        states: [],
        types: [],
        taskStates: [],
        taskTypes: [],
        managers: [],
        chartTypes: ['bar', 'pie']
    };
}]);
mainModule.factory('dataService', ['$http', function ($http) {

        var serviceInstance = {};

        serviceInstance.getClientTypes = function () {
            return $http.get("./client/type/list");
        };
        
        serviceInstance.getClientStates = function () {
            return $http.get("./client/state/list");
        };
        
        serviceInstance.filterClients = function (filter) {
            return $http.get("./client/filter",{params:filter});
        };
        
        serviceInstance.filterClientsByName = function (filter) {
            return $http.get("./client/byname",{params:filter});
        };
        
        serviceInstance.getClient = function (id) {
            return $http.get("./client/"+id);
        };
        
        serviceInstance.addClient = function (client) {
            return $http.put("./client", client);
        };
        
        serviceInstance.updateClient = function (client) {
            return $http.post("./client", client);
        };
        
        serviceInstance.delClient = function (id) {
            return $http.delete("./client/"+id);
        };
        
        serviceInstance.getContactTypes = function () {
            return $http.get("./contact/type/list");
        };
        
        //task
        serviceInstance.addTask = function (task) {
            return $http.put("./task", task);
        };
        serviceInstance.delTask = function (id) {
            return $http.delete("./task/"+id);
        };
        serviceInstance.filterTasks = function (filter) {
            return $http.get("./task/filter",{params:filter});
        };
        serviceInstance.addTaskType = function (type) {
            return $http.put("./task/type", type);
        };
        serviceInstance.getTaskTypes = function () {
            return $http.get("./task/type/list");
        };
        serviceInstance.addTaskState = function (state) {
            return $http.put("./task/state", state);
        };
        serviceInstance.getTaskStates = function () {
            return $http.get("./task/state/list");
        };
        
        //client notice
        serviceInstance.addClientNotice = function (clId, notice) {
            return $http.put("./client/"+clId+"/notice", notice);
        };
        serviceInstance.updateClientNotice = function (notice) {
            return $http.post("./client/notice", notice);
        };
        serviceInstance.delClientNotice = function (id) {
            return $http.delete("./client/notice/"+id);
        };
        serviceInstance.getClientNoticeList = function (clId) {
            return $http.get("./client/"+clId+"/notice/list");
        };
        
        //deal
        serviceInstance.addDeal = function (deal) {
            return $http.put("./deal", deal);
        };
        serviceInstance.editDeal = function (deal) {
            return $http.post("./deal", deal);
        };
        serviceInstance.delDeal = function (id) {
            return $http.delete("./deal/"+id);
        };
        serviceInstance.getDealById = function (id) {
            return $http.get("./deal/"+id);
        };
        serviceInstance.filterDeals = function (filter) {
            return $http.get("./deal/filter",{params:filter});
        };
        serviceInstance.addDealType = function (type) {
            return $http.put("./deal/type", type);
        };
        serviceInstance.getDealTypes = function () {
            return $http.get("./deal/type/list");
        };
        serviceInstance.addDealState = function (state) {
            return $http.put("./deal/state", state);
        };
        serviceInstance.getDealStates = function () {
            return $http.get("./deal/state/list");
        };
        serviceInstance.addDealThingType = function (type) {
            return $http.put("./deal/thing/type", type);
        };
        serviceInstance.getDealThingTypes = function () {
            return $http.get("./deal/thing/type/list");
        };
        
        //cdr
        serviceInstance.filterCalls = function (filter) {
            return $http.get("./cdr/list",{params:filter});
        };
        
        //call
        serviceInstance.callTo = function (num, context) {
            var cont = "";
            if(context){
                cont = "&context="+context;
            }
            return $http.put("./call/to?number="+num+cont);
        };
        serviceInstance.hangup = function (num) {
            return $http.delete("./call/hangup");
        };
        serviceInstance.clientsOnline = function () {
            return $http.get("./call/online");
        };
        serviceInstance.redirectTo = function (num) {
            return $http.post("./call/redirect?number="+num);
        };
        
        //report
        serviceInstance.getClientReport = function(filter){
            return $http.get("./report/client",{params:filter});
        };
        serviceInstance.getTaskReport = function(filter){
            return $http.get("./report/task",{params:filter});
        };
        serviceInstance.getDealReport = function(filter){
            return $http.get("./report/deal",{params:filter});
        };
        
        serviceInstance.getAllManagers = function (user) {
            return $http.get("./user/list", user);
        };
        
        return serviceInstance;
    }]);
mainModule.factory('taskService', ['dataService', '$mdDialog', function (dataService, $mdDialog) {
    function DialogController($scope, $mdDialog, $rootScope, Directory, item, $q, $log, dataService, closeCallBack, dealService) {
        $scope.directory = Directory;
        $scope.dealService = dealService;
        //  console.log("item", item);
        $scope.task = angular.copy(item) || {clients:[]};
        if($scope.task.date){
            $scope.task.date = new Date($scope.task.date);
        }
        if(!$scope.task.clients){
            $scope.task.clients = [];
        }
        //---
        $scope.addcl = {};
        $scope.addcl.isDisabled    = false;
        $scope.addcl.querySearch   = querySearch;
        $scope.addcl.selectedItemChange = selectedItemChange;
        $scope.addcl.searchTextChange   = searchTextChange;
        // ******************************
        // Internal methods
        // ******************************
        function querySearch (query) {
            var deferred = $q.defer();
            dataService.filterClientsByName({items:10, filter:query}).success(function (data){
                deferred.resolve( data );
            });
            return deferred.promise;
        }
        function searchTextChange(text) {
          $log.info('Text changed to ' + text);
        }
        function selectedItemChange(item) {
            //$log.info('Item changed to ' + JSON.stringify(item));
            if($scope.task.clients.map(function(v){return v.id;}).indexOf(item.id)===-1){
                $scope.task.clients.push(item);
            }
        }
        //---
        //---
        $scope.adddeal = {
            isDisabled: false,
            querySearch: function (query) {
                var deferred = $q.defer();
                dataService.filterDeals({page:1, items:10, filter:query}).success(function (data){
                    deferred.resolve( data.rows );
                });
                return deferred.promise;
            },
            searchTextChange : function searchTextChange(text) {
                $log.info('Text changed to ' + text);
            },
            selectedItemChange : function selectedItemChange(item) {
                //$log.info('Item changed to ' + JSON.stringify(item));
                if($scope.task.deals.map(function(v){return v.id;}).indexOf(item.id)===-1){
                    $scope.task.deals.push(item);
                }
            }
        };
        
        $scope.openClient = function (id) {
            $rootScope.openClient(id);
            //$mdDialog.hide();
        };
        
        $scope.removeClient = function(index){
            $scope.task.clients.splice(index, 1);
        };
        
        $scope.hide = function () {
            $mdDialog.hide();
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.answer = function () {
            if ($scope.taskForm.$valid) {
                dataService.addTask($scope.task).success(function (data) {
                    //alert("task added, id:" + data);
                    if (closeCallBack) {
                        try {
                            closeCallBack();
                        } catch (e) {

                        };
                    }
                });
                $mdDialog.hide();
            }
            
        };
        
        $scope.delTask = function(id){
            var confirm = $mdDialog.confirm()
                    .title('Увага!')
                    .textContent('Задача буде видалена! Ви впевнені?')
                    .ariaLabel('Delete')
                    .ok('Видалити!')
                    .cancel('Відмінити');
            $mdDialog.show(confirm).then(function () {
                dataService.delTask(id).success(function (data) {
                    if (closeCallBack) {
                        try {
                            closeCallBack();
                        } catch (e) {};
                    }
                    $mdDialog.hide();
                });
            }, function () {

            });
        };
        
        $scope.removeDeal = function(id){
            $scope.task.deals.splice(id,1);
        };
    }
    return {
        showAddNewTaskDialog : function (existTask, call) {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: './res/views/crm/task/edit_task_card.html',
                parent: angular.element(document.body),
    //            targetEvent: ev,
                clickOutsideToClose: false,
                fullscreen: true,//useFullScreen
                locals: {
                    item: existTask||{},
                    closeCallBack: call
                }
            }).then(function (result) {
                
            }, function () {
                //$scope.status = 'You cancelled the dialog.';
            });
        }
    };
}]);
mainModule.factory('dealService', ['dataService', '$mdDialog', function (dataService, $mdDialog) {
    
    return {
        showAddDealDialog : function (entity, closeCallBack, reload) {
            $mdDialog.show({
                controller: function($scope, $mdDialog, $q, $log, entity, dataService, taskService, Directory, $rootScope) {
                    $scope.directory = Directory;
                    $scope.taskService = taskService;
                    $scope.closeCallBack = closeCallBack;
                    $scope.deal = angular.copy(entity)||{things:[]};
                    if($scope.deal.ddate){
                        $scope.deal.ddate = new Date($scope.deal.ddate);
                    }
                    $scope.openClient = function (id){
                        $rootScope.openClient(id);
                        //$mdDialog.hide();
                    };
                    
                    $scope.currThing = {};
                    $scope.costDisabled = true;

                    $scope.hide = function () {
                        $mdDialog.hide();
                    };
                    $scope.cancel = function () {
                        $mdDialog.cancel();
                    };
                    $scope.answer = function (answer) {
                        $mdDialog.hide(answer);
                    };

                    $scope.calculateAllCost = function(){
                        $scope.deal.cost = 0;
                        angular.forEach($scope.deal.things, function(e){
                            $scope.deal.cost+=e.cost;
                        });
                    }
                    $scope.calculateThingCost = function(){
                        if($scope.currThing.count){
                            $scope.currThing.cost = $scope.currThing.type.cost*$scope.currThing.count;
                        }else{
                            $scope.currThing.cost = 0;
                        }
                    };
                    $scope.resetCurrThing = function(){
                        $scope.currThing = {};
                    };
                    $scope.addThing = function(){
                        if(!$scope.deal.things)
                            $scope.deal.things = [];
                        if($scope.currThing.cost===undefined||$scope.currThing.cost===null){
                            $scope.calculateThingCost();
                        }
                        if($scope.thingForm.$valid){
                            if(!$scope.currThing.exist){
                                $scope.deal.things.push($scope.currThing);
                            }
                            $scope.resetCurrThing();
                        }
                        $scope.calculateAllCost();
                    };
                    $scope.editThing = function(thing){
                        $scope.currThing = thing;
                        $scope.currThing.exist = true;
                    };
                    $scope.delThing = function(index){
                        $scope.deal.things.splice(index, 1);
                    };

                    $scope.saveEntity = function(){
                        if ($scope.dealForm.$valid) {
                            dataService.addDeal($scope.deal).success(function(data){
                                //$scope.reloadDeal(data);
                                if (closeCallBack) {
                                    try {
                                        closeCallBack();
                                    } catch (e) {

                                    };
                                }
                                $mdDialog.hide();
                            });
                        }
                    };
                    $scope.delDeal = function(id){
                        var confirm = $mdDialog.confirm()
                                .title('Увага!')
                                .textContent('Угода та її задачі будуть видалені! Ви впевнені?')
                                .ariaLabel('Delete')
                                .ok('Видалити!')
                                .cancel('Відмінити');
                        $mdDialog.show(confirm).then(function () {
                            dataService.delDeal(id).success(function (data) {
                                if (closeCallBack) {
                                    try {
                                        closeCallBack();
                                    } catch (e) {

                                    }
                                    ;
                                }
                                $mdDialog.hide();
                            });
                        }, function () {

                        });
                    };
                        
                    $scope.reloadDeal = function(id){
                        if (id) {
                            dataService.getDealById(id).success(function (data) {
                                $scope.deal = data;
                                if($scope.deal.ddate){
                                    $scope.deal.ddate = new Date($scope.deal.ddate);
                                }
                            });
                        }
                    };
                    
                    $scope.removeTask = function(id){
                        $scope.deal.tasks.splice(id,1);
                    };
                    
                    $scope.initDealCard = function(){
                        if(reload){
                            $scope.reloadDeal($scope.deal.id);
                        }
                    };
                    
                },
                templateUrl: './res/views/crm/deal/edit_deal_card.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                locals: {
                    entity: entity
                },
                fullscreen: true
            })
            .then(function (answer) {}, function () {});
        }
    };
}]);
mainModule.factory('callService', ['dataService', function (dataService) {
    
    return {
        callTo : function (num, cont) {
            dataService.callTo(num, cont);
        },
        
        hangup : function () {
            dataService.hangup();
        },
        
        redirectTo : function(num){
            dataService.redirectTo(num);
        }
    };
}]);