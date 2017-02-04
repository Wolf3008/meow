var mainModule = angular.module('mainModule',
        [
            'ngMaterial',
            'ngMessages',
            'ui.bootstrap',
            'ngRoute',
            'ui.router',
            'mdPickers',
            'angularCharts'
        ]).config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('docs-dark', 'default')
            //.primaryPalette('yellow')
            //.dark();
            .primaryPalette('grey', {
                'default': '400', // by default use shade 400 from the pink palette for primary intentions
                'hue-1': '100', // use shade 100 for the <code>md-hue-1</code> class
                'hue-2': '600', // use shade 600 for the <code>md-hue-2</code> class
                'hue-3': 'A100' // use shade A100 for the <code>md-hue-3</code> class
            })
            // If you specify less than all of the keys, it will inherit from the
            // default shades
            .accentPalette('blue-grey', {
                'default': '200' // use shade 200 for default, and keep all other shades the same
            }).dark();

    $mdThemingProvider.theme('dark-grey').backgroundPalette('grey').dark();
    $mdThemingProvider.theme('dark-orange').backgroundPalette('orange').dark();
    $mdThemingProvider.theme('dark-purple').backgroundPalette('deep-purple').dark();
    $mdThemingProvider.theme('dark-blue').backgroundPalette('blue').dark();
});//'ui.bootstrap'

//401
mainModule.config(['$locationProvider', '$httpProvider', '$provide', function ($locationProvider, $httpProvider, $provide) {
        
        $provide.factory('myHttpInterceptor', function ($q, $injector) {
            return {
                // optional method
                'request': function (config) {
                    // do something on success
                    return config;
                },
                // optional method
                'requestError': function (rejection) {
                    // do something on error
                    if (rejection.status == 401) {
                        //return;
                    }
                    return $q.reject(rejection);
                },
                // optional method
                'response': function (response) {
                    // do something on success
                    return response;
                },
                // optional method
                'responseError': function (rejection) {
                    function showCallingDialog() {
                        $injector.get("$mdDialog").show({
                            controller: function($scope, $mdDialog, $http) {
                                
                                $scope.ok = function () {
//                                    $http.post("./j_spring_security_check",{login:$scope.login, password:$scope.pass})
//                                            .success(function(){
//                                                $mdDialog.cancel();
//                                            });
                                    $http.post(["./j_spring_security_check?login=",$scope.login, "&password=",$scope.pass].join(""))
                                            .success(function(){
                                                $mdDialog.cancel();
                                            });
                                    
                                };
                            },
                            templateUrl: './res/views/crm/basic/relogin_card.html',
                            parent: angular.element(document.body),
                            clickOutsideToClose: false,
                            locals: {},
                            fullscreen: true
                        }).then(function (answer) {}, function () {});
                    };
                    // do something on error
                    if (rejection.status == 401) {
                        window.location = './';
                        //showCallingDialog();
                        //return;
                    }
                    return $q.reject(rejection);
                }
            };
        });

        $httpProvider.interceptors.push('myHttpInterceptor');
    }]);

mainModule.config(function($stateProvider, $urlRouterProvider) {
  // For any unmatched url, redirect to..
  $urlRouterProvider.otherwise("/clients");
  
  var clientCardState = {
        url: "/client",
        params: {
            clientId: null
        },
        views: {
            body: {
                controller: 'ClientCnt',
                templateUrl: './res/views/crm/client/client_card.html'
            }
        }
  };
  
  $stateProvider
    .state('clients', {
        url: "/clients",
        views: {
            'body': {
                controller: 'ClientsListCnt as ctrl',
                templateUrl: './res/views/crm/client/clients_state.html'
            },
            'filter@clients': {
                //controller: 'ClientsListCnt',
                templateUrl: './res/views/crm/client/client_list_filter.html'
            }
        }
    })
    .state('calls', {
        url: "/calls",
        views: {
            'body': {
                controller: 'CdrStateController',
                templateUrl: './res/views/crm/cdr/cdr_state.html'
            },
            'filter@calls': {
                templateUrl: './res/views/crm/cdr/cdr_state_filter.html'
            }
        }
    })
    .state('tasks', {
        url: "/tasks",
        views: {
            'body': {
                controller: 'TaskStateController as ctrl',
                templateUrl: './res/views/crm/task/task_state.html'
            },
            'filter@tasks': {
                //controller: 'ClientsListCnt',
                templateUrl: './res/views/crm/task/task_state_filter.html'
            }
        }
    })
    .state('deals', {
        url: "/deals",
        views: {
            'body': {
                controller: 'DealStateController as ctrl',
                templateUrl: './res/views/crm/deal/deal_state.html'
            },
            'filter@deals': {
                templateUrl: './res/views/crm/deal/deal_state_filter.html'
            }
        }
    })
    .state('reports', {
        url: "/reports",
        views: {
            'body': {
                controller: 'ReportStateController as ctrl',
                templateUrl: './res/views/crm/report/report_state.html'
            },
            'filter@reports': {
                templateUrl: './res/views/crm/report/report_state_filter.html'
            }
        }
    })
    
    .state('clients.client', angular.copy(clientCardState))
    .state('calls.client', angular.copy(clientCardState))
    .state('tasks.client', angular.copy(clientCardState))
    .state('deals.client', angular.copy(clientCardState))
    .state('reports.client', angular.copy(clientCardState));
});