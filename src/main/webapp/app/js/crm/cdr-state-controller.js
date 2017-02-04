mainModule.controller('CdrStateController', ['$scope', '$http', '$compile','$document','$mdSidenav',
    '$log', '$stateParams', '$state', 'CallsListState', 'Directory', '$timeout', 'dataService',
    '$mdDialog', 'taskService',
function ($scope, $http, $compile, $document, $mdSidenav, $log, $stateParams, $state, CallsListState, 
Directory, $timeout, dataService, $mdDialog, taskService) {
    $scope.taskService = taskService;
    $scope.state = CallsListState;
    $scope.directory = Directory;
    $scope.selectedChainId = null;
    $scope.filterChips = [];
    
    $scope.recordInChain = function(chainId){
        return $scope.selectedChainId === chainId;
    };
    $scope.selectChain = function(chainId){
        $scope.selectedChainId = chainId;
    };
    
    $scope.changeFilter = function(){
        $scope.filterChips = [];
        $scope.filterChips = $scope.state.filter.manager.map(function(ent){ent.field = "manager";return ent;});
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
        var nums = $scope.state.filter.manager.map(function(m){
            return m.extNum;
        });
        if($scope.state.filter.filter){
            nums.push($scope.state.filter.filter);
        }
        return {
            page : p,
            items : s,
            from: $scope.state.filter.from,
            to: $scope.state.filter.to,
            numbers: nums//$scope.state.filter.filter?[$scope.state.filter.filter]:[]
        };
    };
    $scope.dynamicItems = new DynamicItems(dataService.filterCalls, $scope.getFilter, 20);
    //$scope.dynamicItems = new DynamicItems();
    $scope.reloadList = function(){
        $scope.dynamicItems.loadedPages = {};
    };
    
    $scope.formatSeconds = function(secs){
        var hours   = Math.floor(secs / 3600);
        var minutes = Math.floor((secs - (hours * 3600)) / 60);
        var seconds = secs - (hours * 3600) - (minutes * 60);

        if (hours   < 10) {hours   = "0"+hours;}
        if (minutes < 10) {minutes = "0"+minutes;}
        if (seconds < 10) {seconds = "0"+seconds;}
        return hours+':'+minutes+':'+seconds;
    };
    
    $scope.getManagerByNum = function (num) {
        var res = $scope.directory.managers.filter(function (m) {
            return num === m.extNum;
        })[0];
        return res?res.name:num;
    };
    
    $scope.showDetailDialog = function (call) {
        $mdDialog.show({
            controller: function($scope, $mdDialog, dataService, $rootScope, formatFunc) {

                $scope.formatSeconds = formatFunc;

                $scope.call = angular.copy(call)||{};
                
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
            },
            templateUrl: './res/views/crm/cdr/cdr_detail_card.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                formatFunc: $scope.formatSeconds
            },
            fullscreen: true
        }).then(function (answer) {}, function () {});
    };
    
    $scope.init = function(){
    };
    //$scope.init();
}]);