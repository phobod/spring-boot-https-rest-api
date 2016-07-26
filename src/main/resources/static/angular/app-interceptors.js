angular.module('app-interceptors', [])
.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('httpResponseInterceptor');
}])
.factory("httpResponseInterceptor", ['$q','$location', '$rootScope', function($q, $location, $rootScope){
	$rootScope.principal = {
			auth : false,
			name : ''
	};
	$rootScope.errorMessage = {
			message : '',
			description : '', 
			code : ''
	};
	return {
		response: function(response){
			var name = response.headers('PrimcipalName');
			if(name != undefined) {
        		$rootScope.principal = {
                	auth : true,
        			name : name
                };
        	} else{
        		$rootScope.principal = {
        			auth : false,
        			name : ''
        		};
        	}
            return response || $q.when(response);
        },
        responseError: function(rejection) {
            if (rejection.status === 401) {
                $location.path('/login').search('returnTo', $location.path());
            } else {
            	$rootScope.errorMessage = {
            			message : rejection.data.message,
            			description : rejection.data.description, 
            			code : rejection.status
            	};
            	$location.path('/error');
            }
            return $q.reject(rejection);
        }
    }
}]);