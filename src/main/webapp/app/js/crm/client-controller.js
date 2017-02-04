mainModule.controller('ClientCnt', ['$scope', '$mdSidenav','$log', '$stateParams', 
    '$mdDialog', 'ClientsListState', '$state', 'dataService', 'toastService', 'Directory', '$rootScope', 'callService',
function ($scope, $mdSidenav, $log, $stateParams, $mdDialog, ClientsListState, 
$state, dataService, toastService, Directory, $rootScope, callService) {
    $scope.state = ClientsListState;
    $scope.callService = callService;
    //var self = this;
    $scope.client = {
        contacts:[]
    };
    $scope.clone = {
        contacts:[]
    };
    $scope.hasUnsavedChanges = false;
    
    $scope.notices = [];
    
    $scope.clientId = $stateParams.clientId;//cc
    
    
    $scope.showAddNewContactDialog = function (ev, index) {
//        var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
        $mdDialog.show({
            controller: DialogController,
            templateUrl: './res/views/crm/client/edit_contact_card.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: false,
            fullscreen: false,//useFullScreen
            locals: index>=0?{
                item: angular.copy($scope.client.contacts[index]),
                index: index
            }:{
                item: null,
                index: null
            }
        }).then(function (result) {
            if(result.index!==null){
                $scope.client.contacts[index] = result.item;
            }else{
                $scope.client.contacts.push(result.item);
            }
                
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });
    };
    $scope.deleteContact = function(index){
        $scope.client.contacts.splice(index,1);
    };
    
    //notices
    $scope.loadNotices = function(){
        dataService.getClientNoticeList($scope.client.id).success(function(data){
            $scope.notices = data;
        });
    };
    $scope.addNotice = function(text){
        dataService.addClientNotice($scope.client.id, {text:text, client:{id:$scope.client.id}})
                .success(function(){
                    $scope.loadNotices();
                    $scope.currComment = null;
                });
    };
    $scope.delNotice = function(id){
        dataService.delClientNotice(id).success(function(){
            $scope.loadNotices();
        });
    };
    
    //calls DUBLICATE CODE
    $scope.showCalls = false;
    $scope.selectedChainId = null;
    $scope.recordInChain = function(chainId){
        return $scope.selectedChainId === chainId;
    };
    $scope.selectChain = function(chainId){
        $scope.selectedChainId = chainId;
    };
    $scope.showCallsFun = function(){
        $scope.showCalls = true;
    };
    $scope.getCallFilter = function(p, s){
        var nums = $scope.client.contacts.filter(function (c) {
            return c.type.callable;
        }).map(function (c) {
            return c.contact;
        });
        if(nums.length===0)
            nums = ["-1"];
        return {
            page : p,
            items : s,
            numbers: nums
        };
    };
    $scope.reloadCallList = function(){
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
    $scope.initCallsList = function(){
        $scope.dynamicCallItems = new DynamicItems(dataService.filterCalls, $scope.getCallFilter, 20);
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
    
    
    $scope.saveClient = function(){
        var id = $scope.client.id;
        //TO-DO need separating by save and update
        dataService.addClient($scope.client).success(function (data){
            toastService.simpleShow("basic","Зміни збережено!");
            $scope.loadClient(data);
            //angular.element(window).triggerHandler('resize');
            $scope.$parent.dynamicItems.clientsListNeedRefresh = true;
            if(!id){
                $scope.$parent.dynamicItems.loadedPages = {};
            }
            //$scope.$parent.ctrl.dynamicItems.getItemAtIndex();
        });
    };
    $scope.loadClient = function(id){
        dataService.getClient(id).success(function (data){
            $scope.client = data;
            $scope.clone = angular.copy(data);
            $scope.hasUnsavedChanges = false;
            
            $scope.loadNotices();
            $scope.reloadCallList();
        });
    };
    $scope.reloadClient = function(){
        if($scope.client.id){
            dataService.getClient($scope.client.id).success(function (data){
                $scope.client = data;
                $scope.clone = angular.copy(data);
                $scope.hasUnsavedChanges = false;
                $scope.loadNotices();
            });
            
        }
        
    };
    $scope.delClient = function (){
        var confirm = $mdDialog.confirm()
                  .title('Увага!')
                  .textContent('Клієнт, його задачі та угоди будуть видалені! Ви впевнені?')
                  .ariaLabel('Lucky day')
//                  .targetEvent(ev)
                  .ok('Видалити!')
                  .cancel('Відмінити');
            $mdDialog.show(confirm).then(function() {
                dataService.delClient($scope.client.id).success(function (data){
                    console.log("deleted");
                    $scope.$parent.dynamicItems.clientsListNeedRefresh = true;
                    $mdSidenav('right').close()
                        .then(function () {
                            $state.transitionTo('clients');
                            $log.debug("close RIGHT is done");
                        });
                });
            }, function() {
                
            });
        
    };
    $scope.$watch('client', function(val) {
        if(!$scope.hasUnsavedChanges){
            if(!angular.equals(val, $scope.clone)){
                $scope.hasUnsavedChanges = true;
                console.log("hasUnsavedCahange = true");
            }
        }
    }, true);
    $scope.initClient = function(){
        if($scope.clientId>0){
            $scope.loadClient($scope.clientId);
        }
        $scope.hasUnsavedChanges = false;
    };
    
    
    
    $scope.close = function (ev) {//cc
        // Component lookup should always be available since we are not using `ng-if`
        //$scope.state.clientPanelIsOpen = false;
        if($scope.hasUnsavedChanges){
            // Appending dialog to document.body to cover sidenav in docs app
            var confirm = $mdDialog.confirm()
                  .title('Є незбережені зміни!')
                  .textContent('Всеодно закрити? Ато не збережеться...')
                  .ariaLabel('Lucky day')
                  .targetEvent(ev)
                  .ok('Ага, закриваєм!')
                  .cancel('Нєєє');
            $mdDialog.show(confirm).then(function() {
                $mdSidenav('right').close()
                .then(function () {
                    $state.transitionTo('clients');
                    $log.debug("close RIGHT is done");
                });
            }, function() {
                
            });
        }else
        $mdSidenav('right').close()
                .then(function () {
                    $state.transitionTo($rootScope.getRootState());
                    $log.debug("close RIGHT is done");
                });
    };
    //return self;
    //console.log("par ", $scope.$parent);
}]);
function DialogController($scope, $mdDialog, $rootScope, Directory, item, index) {
  $scope.directory = Directory;
//  console.log("item", item);
  $scope.contact = item||{};
  $scope.confirmText = $scope.contact.id?"Зберегти зміни":"Додати";
  $scope.hide = function() {
    $mdDialog.hide();
  };
  $scope.cancel = function() {
    $mdDialog.cancel();
  };
  $scope.answer = function(answer) {
    var result = {
        index: index,
        item: answer
    };
    $mdDialog.hide(result);
  };
}