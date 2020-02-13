angular.module("login_page",[])
    .controller("LoginCtrl", ["$scope", "$http", function ($scope, $http) {
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    console.log("userCtrl success")
                },
                function(error) {
                    console.log("userCtrl error")
                }
            );
        }
    }]);