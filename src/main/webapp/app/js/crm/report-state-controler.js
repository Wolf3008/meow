mainModule.controller('ReportStateController', ['$scope', 'Directory', 'dataService', 'ReportsListState',
function ($scope, Directory, dataService, ReportsListState) {
    $scope.state = ReportsListState;
    $scope.directory = Directory;
    
    $scope.filterChips = [];
    
    //charts data
    $scope.chartConfig = {
        title: "",//$scope.state.currTitle,
        tooltips: true,
        labels: true,
        mouseover: function () {},
        mouseout: function () {},
        click: function () {},
        legend: {
            display: true,
            //could be 'left, right'
            position: 'right'
        }
    };
    $scope.clientDataT = {series: ['Клієнти'],data: []};
    $scope.clientDataS = {series: ['Клієнти'],data: []};
    $scope.taskDataT = {series: ['Задачі'],data: []};
    $scope.taskDataS = {series: ['Задачі'],data: []};
    $scope.dealDataT = {series: ['Угоди'],data: []};
    $scope.dealDataS = {series: ['Угоди'],data: []};
    
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
    
    $scope.getFilter = function(){
        var manIds = $scope.state.filter.manager.map(function(m){
            return m.id;
        });
        return {
            from: $scope.state.filter.from,
            to: $scope.state.filter.to,
            managers: manIds
        };
    };
    
    $scope.reloadList = function(){
        dataService.getClientReport($scope.getFilter()).success(function (data){
            $scope.clientDataT.data = data.types.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
            $scope.clientDataS.data = data.states.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
        });
        
        dataService.getTaskReport($scope.getFilter()).success(function (data){
            $scope.taskDataT.data = data.types.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
            $scope.taskDataS.data = data.states.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
        });
        
        dataService.getDealReport($scope.getFilter()).success(function (data){
            $scope.dealDataT.data = data.types.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
            $scope.dealDataS.data = data.states.map(function(arr){
                    return {x:arr[1], y:[arr[0]]};
                });
        });
    };
    
    $scope.initReports = function(){
        if(!$scope.state.chartData){
            $scope.state.chartData = $scope.clientDataT;
        }
        //$scope.state.chartType = 'pie';
        $scope.reloadList();
    };
    
}]);