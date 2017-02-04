mainModule.factory('toastService', ['$mdToast', function ($mdToast) {

        var serviceInstance = {};
        
        serviceInstance.permissions = {
            basic:true
        };
        serviceInstance.hideDelay = 3000;
        serviceInstance.pos = {
            top: false,
            left: false
        };
        serviceInstance.getPos = function(){
            return (serviceInstance.pos.top?"top ":"bottom ")+(serviceInstance.pos.left?"left":"right");
        };

        serviceInstance.simpleShow = function (permission, mess) {
            if(serviceInstance.permissions[permission]){
                $mdToast.show(
                        $mdToast.simple()
                        //.toastClass("my_toast")
                        .textContent(mess)
                        .position(serviceInstance.getPos())
                        .hideDelay(serviceInstance.hideDelay)
                        );
            }
        };
        
        serviceInstance.actionShow = function (permission, mess, callback) {
            if(serviceInstance.permissions[permission]){
                var toast = $mdToast.simple()
                        .textContent(mess)
                        .action('UNDO')
                        .highlightAction(true)
                        .highlightClass('md-accent')// Accent is used by default, this just demonstrates the usage.
                        .position(serviceInstance.getPos());
                $mdToast.show(toast).then(function (response) {
                    if (response == 'ok') {
                        alert('You clicked the \'UNDO\' action.');
                        if(isFunction(callback)){
                            callback();
                        }
                    }
                });
            }
        };
        
        return serviceInstance;
    }]);