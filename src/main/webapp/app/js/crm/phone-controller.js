mainModule.controller('phoneController', ['$scope', '$mdSidenav', 'Directory', 'dataService', 'callService', '$mdDialog',
function ($scope, $mdSidenav, Directory, dataService, callService, $mdDialog) {
    
    $scope.directory = Directory;
    $scope.callService = callService;
    $scope.currNum = null;
    
    $scope.clearCurrNum = function(){
        $scope.currNum = null;
    };
    $scope.pushDigit = function(d){
        $scope.currNum = ($scope.currNum||"")+d;
    };
    
    $scope.showClientsOnLine = function () {
        $mdDialog.show({
            controller: function($scope, $mdDialog, dataService, $rootScope, dataService) {
                
                $scope.clientsOnLine = [];
                
                $scope.initClOnLineCard = function(){
                    dataService.clientsOnline().success(function(data){
                        $scope.clientsOnLine = data;
                    });
                };
                
                $scope.openClient = function (id) {
                    $rootScope.openClient(id);
                    //$mdDialog.hide();
                };

                $scope.cancel = function () {
                    $mdDialog.cancel();
                };
            },
            templateUrl: './res/views/crm/client/clients_online_card.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                formatFunc: $scope.formatSeconds
            },
            fullscreen: true
        }).then(function (answer) {}, function () {});
    };
    
    $scope.close = function () {
      $mdSidenav('phone').close()
        .then(function () {});
    };
    
}]);