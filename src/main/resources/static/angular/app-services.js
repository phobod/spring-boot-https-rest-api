angular.module('app-services', [ 'ngResource' ])
.service("objectService",[ '$resource', '$http', function($resource, $http) {
	return {
		listAllObjects : function() {
			return $resource('/object').query();
		},
		findObjectById : function(id) {
			return $resource('/object/:id',{id:id}).get();
		},
		saveObject : function(object) {
			return $resource('/object').save({},object);
		},
		updateObject : function(object) {			
			return $resource('/object/:id',{id:object.id}).save({},object);
		},
		deleteObject : function(id) {
			return $resource('/object/:id',{id:id}).remove({});
		}
	}
}])
.service("editService", ['$resource', function($resource){
	return {
	}
}])
.service("authService", ['$http', function($http){
	return {
		login : function(credentials){
			var config = {
					params: {
						username: credentials.username,
						password: credentials.password
				    }
			};
			return $http.post('/login', '', config);
		},
		logout : function(){
			return $http.post('/logout', {});
		}
	}
}]);