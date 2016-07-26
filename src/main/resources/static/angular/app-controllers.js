angular.module('app-controllers', ['ngRoute'])
	.config(['$routeProvider', function($routeProvider){
		$routeProvider.when('/main', {
			templateUrl : '/html/main.html',
			controller : 'objectController'
		});
		$routeProvider.when('/edit/:id', {
			templateUrl : '/html/edit.html',
			controller : 'editController'
		});
		$routeProvider.when('/login', {
			templateUrl : '/html/login.html',
			controller : 'loginController'
		});
		$routeProvider.when('/error', {
			templateUrl : '/html/error.html'
		});
		$routeProvider.otherwise({
			redirectTo : '/main'
		});
	}])
	.controller('objectController', ['$scope', 'objectService', function($scope, objectService){
		$scope.objects = objectService.listAllObjects();
		$scope.deleteObject = function(id, index){
			objectService.deleteObject(id).$promise.then(function(resp){
				$scope.objects.splice(index,1);					
			});
		}
	}])
	.controller('editController', ['$scope', '$routeParams', '$location', 'objectService', function($scope, $routeParams, $location, objectService){
		$scope.index = $routeParams.id;
		if ($routeParams.id > -1) {
			$scope.object = objectService.findObjectById($routeParams.id);
		}
		$scope.saveObject = function(){
			objectService.saveObject($scope.object).$promise.then(function(resp){
				$location.path("/main");
			},function(error){
				$scope.error = true;
				$scope.errorMessage = error.data.description;
			})
		}
		$scope.updateObject = function(){
			objectService.updateObject($scope.object).$promise.then(function(resp){
				$location.path("/main");
			},function(error){
				$scope.error = true;
				$scope.errorMessage = error.data.description;
			})
		}
	}])
	.controller('loginController', ['$rootScope', '$scope', '$location', 'authService', function($rootScope, $scope, $location, authService){
			$scope.credentials = {
					username :'',
					password :''
			};	
			$scope.loginFormSubmit = function (){
				 authService.login($scope.credentials).then(function(data){
					 $scope.error = false;
					 $location.path("/main");
				 }, function(){
					 $location.path("/login");
                     $scope.error = true;
				 });
			};
			$scope.logout = function(){
				authService.logout().then(function(){
					 $location.path("/login");
				});
			};
	}]);
