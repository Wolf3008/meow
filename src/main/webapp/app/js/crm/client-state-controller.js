mainModule.controller('ClientsListCnt', ['$scope', '$http', '$compile','$document','$mdSidenav',
    '$log', '$stateParams', '$state', 'ClientsListState', 'Directory', '$timeout', 'dataService',
    '$mdDialog', 'taskService', 'dealService',
function ($scope, $http, $compile, $document, $mdSidenav, $log, $stateParams, $state, ClientsListState, 
Directory, $timeout, dataService, $mdDialog, taskService, dealService) {
//    $scope.taskService = taskService;
//    $scope.dealService = dealService;
    $scope.state = ClientsListState;
    $scope.state.selectedClients = [];
    $scope.directory = Directory;
    
    
    //$scope.clientsListNeedRefresh = false;
    //$scope.state.enableFilter = false;
    
    $scope.clients = [
        {id:1, name: "client1"},
        {id:2, name: "client2e"},
        {id:3, name: "client3"}
    ];
    $scope.filterChips = [];
    $scope.changeFilter = function(){
        $scope.filterChips = [];
        $scope.filterChips = $scope.state.filter.type.map(function(ent){ent.field = "type";return ent;});
        $scope.filterChips = $scope.filterChips.concat($scope.state.filter.state.map(function(ent){ent.field = "state";return ent;}));
        $scope.filterChips = $scope.filterChips.concat($scope.state.filter.manager.map(function(ent){ent.field = "manager";return ent;}));
        console.log("CHIPS ", $scope.filterChips);
        $scope.reloadList();
    };
    $scope.deleteChips = function(ent, index){
        angular.forEach($scope.state.filter[ent.field], function(e, id){
            if(ent.id === e.id){
                $scope.state.filter[ent.field].splice(id, 1);
            }
        });
        $scope.reloadList();
    };
    
    
    $scope.closeFilter = function () {
        // Component lookup should always be available since we are not using `ng-if`
        //$scope.state.clientPanelIsOpen = false;
        $mdSidenav('left').close()
                .then(function () {
                    $log.debug("close Left is done");
                });
    };
    
    $scope.selectClient = function(client){
        var index = $scope.state.selectedClients.map(function(v){return v.id;}).indexOf(client.id);
        //console.log("selected index = ",)
        if(index===-1){
            $scope.state.selectedClients.push(client);
        }else{
            $scope.state.selectedClients.splice(index, 1);
        }
    };
    
    $scope.getFilter = function(p, s){
        return {
            page : p,
            items : s,
            filter: $scope.state.filter.filter,
            type:$scope.state.filter.type.map(function (e){return e.id;}),
            state:$scope.state.filter.state.map(function (e){return e.id;}),
            managers:$scope.state.filter.manager.map(function (e){return e.id;})
        };
    };
    $scope.dynamicItems = new DynamicItems(dataService.filterClients, $scope.getFilter);
    $scope.reloadList = function(){
        $scope.dynamicItems.loadedPages = {};
    };
    
    $scope.init = function(){
//        if($scope.state.clientPanelIsOpen){
//            $scope.openClient(-1);
//        }
    };
    //$scope.init();
}]);