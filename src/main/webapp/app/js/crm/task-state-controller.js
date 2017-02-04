mainModule.controller('TaskStateController', ['$scope', '$http', '$compile','$document','$mdSidenav',
    '$log', '$stateParams', '$state', 'TasksListState', 'Directory', '$timeout', 'dataService',
    '$mdDialog',
function ($scope, $http, $compile, $document, $mdSidenav, $log, $stateParams, $state, TasksListState, 
Directory, $timeout, dataService, $mdDialog) {
    //$scope.taskService = taskService;
    $scope.state = TasksListState;
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
    $scope.getDateBounds = function(selector){
        var res = {from:null, to: null};
        var now = new Date();
        switch (selector){
            case "day":
                res.from = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                res.to = new Date(now.getFullYear(), now.getMonth(), now.getDate()+1);
                break;
            case "week":
                res.from = new Date(now.getFullYear(), now.getMonth(), now.getDate()-now.getDay()+1);
                res.to = new Date(now.getFullYear(), now.getMonth(), now.getDate()-now.getDay()+7);
                break;
            case "mounth":
                res.from = new Date(now.getFullYear(), now.getMonth());
                res.to = new Date(now.getFullYear(), now.getMonth()+1);
                break;
            default :
                break;
        }
        return res;
    };
    $scope.getFilter = function(p, s){
        var dateObj = $scope.getDateBounds($scope.state.taskPeriod);
        return {
            page : p,
            items : s,
            filter: $scope.state.filter.filter,
            type:$scope.state.filter.type.map(function (e){return e.id;}),
            state:$scope.state.filter.state.map(function (e){return e.id;}),
            managers:$scope.state.filter.manager.map(function (e){return e.id;}),
            from : dateObj.from,
            to : dateObj.to
        };
    };
    $scope.dynamicItems = new DynamicItems(dataService.filterTasks, $scope.getFilter, 10);
    $scope.reloadList = function(){
        $scope.dynamicItems.loadedPages = {};
    };
    
    $scope.$watch('state.taskPeriod', function(val) {
        console.log("date bounds changed!");
        $scope.reloadList();
    }, true);
    
    //calc color from red to green
    function getColor(value){
        //value from 0 to 1
        var hue=((1-value)*120).toString(10);
        return ["hsl(",hue,",100%,50%)"].join("");
    }
    $scope.calcColor = function(date1, date2){
        try{
            if(date1>date2)
                return getColor(1);
            var now = (new Date()).getTime();
            if(now>date2)
                return getColor(1);
            if(now<date1)
                return getColor(0);
            var l = date2-date1;
            var l1 = now-date1;
            return getColor(l1*1.0/l);
        }catch (e){
            
        }
    };
    
    $scope.init = function(){
//        if($scope.state.clientPanelIsOpen){
//            $scope.openClient(-1);
//        }
    };
    //$scope.init();
}]);