mainModule.controller('DealStateController', ['$scope', '$http', '$compile','$document','$mdSidenav',
    '$log', '$stateParams', '$state', 'DealsListState', 'Directory', '$timeout', 'dataService',
    '$mdDialog', 'taskService',
function ($scope, $http, $compile, $document, $mdSidenav, $log, $stateParams, $state, DealsListState, 
Directory, $timeout, dataService, $mdDialog, taskService) {
    $scope.taskService = taskService;
    $scope.state = DealsListState;
    $scope.directory = Directory;
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
    $scope.dynamicItems = new DynamicItems(dataService.filterDeals, $scope.getFilter, 20);
    $scope.reloadList = function(){
        $scope.dynamicItems.loadedPages = {};
    };
    
    
    $scope.selectDeal = function(deal){
        var index = $scope.state.selectedDeals.map(function(v){return v.id;}).indexOf(deal.id);
        //console.log("selected index = ",)
        if(index===-1){
            $scope.state.selectedDeals.push(deal);
        }else{
            $scope.state.selectedDeals.splice(index, 1);
        }
    };
    
}]);