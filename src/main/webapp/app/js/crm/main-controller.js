mainModule.controller('mainCtr', ['$scope', '$http', '$compile','$document', '$state', 'Directory',
    '$mdSidenav', '$log', 'dataService', 'taskService', 'dealService', '$rootScope', '$mdDialog',
function ($scope, $http, $compile, $document, $state, Directory, $mdSidenav, $log, dataService, 
          taskService, dealService, $rootScope, $mdDialog) {
    
    $scope.taskService = taskService;
    $scope.dealService = dealService;
    
    $scope.btmActiveState = [false, false, false, false, false];
    $scope.lastBtnIndex = 1;
    $scope.btmStateNames = ['calls', 'clients', 'deals', 'tasks', 'reports'];
    
    //set active state
    $scope.first = true;
    function getRootState(){
        return $scope.btmStateNames.filter(function(st, index){
            return $scope.btmActiveState[index];
        })[0]||"";
    }
    $scope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
        // toState === $state.current
        console.log("call STATE: ",toState.name);
        if($scope.first){
            $scope.first = false;
            try{
                console.log("STATE: ",toState.name);
                var name = toState.name.split(".")[0];
                $scope.btmActiveState[$scope.btmStateNames.indexOf(name)] = true;
                $scope.lastBtnIndex = $scope.btmStateNames.indexOf(name);
            }catch (e){
                $log.debug("no state :"+e);
            }
        }
        
    });
//    $scope.setCheckCurrState = function(){
//        try{
//            console.log("STATE: ",$state.current.name)
//            $scope.btmActiveState[$scope.btmStateNames.indexOf($state.current.name)] = true;
//        }catch (e){
//            $log.debug("no state :"+e);
//        }
//    };
    
    $scope.refreshDirectory = function(){
        dataService.getClientStates().success(function(data){
            Directory.states = data;
        });
        dataService.getClientTypes().success(function(data){
            Directory.types = data;
        });
        dataService.getContactTypes().success(function(data){
            Directory.contactTypes = data;
        });
        dataService.getAllManagers().success(function(data){
            Directory.managers = data;
        });
        dataService.getTaskTypes().success(function(data){
            Directory.taskTypes = data;
        });
        dataService.getTaskStates().success(function(data){
            Directory.taskStates = data;
        });
        
        //deal things
        dataService.getDealTypes().success(function(data){
            Directory.dealTypes = data;
        });
        dataService.getDealStates().success(function(data){
            Directory.dealStates = data;
        });
        dataService.getDealThingTypes().success(function(data){
            Directory.dealThingTypes = data;
        });
    };
    
    //calling functionality
//    $rootScope.phoneRegex0 = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
//    $rootScope.phoneRegex1 = /(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]‌​)\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)([2-9]1[02-9]‌​|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})\s*(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+)\s*)?$/i;
    
    $scope.showCallingDialog = function (contacts) {
        $mdDialog.show({
            controller: function($scope, $mdDialog, callService) {

                $scope.originContacts = angular.copy(contacts)||[];
                $scope.contacts = [];
                
                $scope.cancel = function () {
                    $mdDialog.cancel();
                };

                $scope.callTo = function (num){
                    callService.callTo(num);
                    $mdDialog.cancel();
                };
                
                
                $scope.initCallingCard = function(){
                    $scope.contacts = $scope.originContacts.filter(function(c){
                        return c.type.callable;
                    });
                };
                
                $scope.initCallingCard();
            },
            templateUrl: './res/views/crm/client/call_card.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {},
            fullscreen: true
        }).then(function (answer) {}, function () {});
    };
    
    
    //----things-for-open-client----
    $scope.openClient = function(id){
        //console.log("is open "+$mdSidenav('right').isOpen());
        $state.transitionTo(getRootState()+'.client', {clientId:id});
        if(!$scope.isOpenRight()){
            $scope.toggleRight();
        }
        //$scope.state.clientPanelIsOpen = true;
    };
    
    //for dialog services
    $rootScope.openClient = $scope.openClient;
    $rootScope.getRootState = getRootState;
    
    function buildToggler(navID) {
      return function() {
        // Component lookup should always be available since we are not using `ng-if`
        $mdSidenav(navID)
          .toggle()
          .then(function () {
            $log.debug("toggle " + navID + " is done");
          });
      };
    }
    $scope.toggleRight = buildToggler('right');
    $scope.isOpenRight = function(){
      return $mdSidenav('right').isOpen();
    };
    //=-----------------------------
    
    var self = this;
    $scope.directory = Directory;
    $scope.setState = function(state){
        $state.transitionTo(state);
        var index = $scope.btmStateNames.indexOf(state);
        $scope.btmActiveState[$scope.lastBtnIndex] = false;
        $scope.btmActiveState[index] = true;
        $scope.lastBtnIndex = index;
    };
    
    $scope.togglePhone = buildToggler('phone');
    function buildToggler(navID) {
      return function() {
        // Component lookup should always be available since we are not using `ng-if`
        $mdSidenav(navID)
          .toggle()
          .then(function () {
            $log.debug("toggle " + navID + " is done");
          });
      };
    };
    
    $scope.isOpen = false;
    $scope.demo = {
        isOpen: false,
        count: 0,
        selectedDirection: 'left'
    };
    $scope.refreshDirectory();
    
    var originatorEv;
    self.openMenu = function($mdOpenMenu, ev) {
      originatorEv = ev;
      $mdOpenMenu(ev);
    };
    self.openClient = $scope.openClient;
    return self;
}]);