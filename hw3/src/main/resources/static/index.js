angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8888/market/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                name_product: $scope.filter ? $scope.filter.name_product : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
            $scope.viewDiv($scope.ProductsList.length);
        });
    };

    $scope.checkAuth = function (){
        $http.get('http://localhost:8888/market/check_auth')
            .then(function (response) {
                alert(response.data.value)
            })
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8888/market/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {
            });
    };

    if($localStorage.marketUser){
        try{
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime()/1000);
            if (currentTime > payload.exp){
                console.log("Токен просрочен!!!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        }catch (exp){

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
    }

    $scope.tryToLogout = function(){
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function (){
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function (){
        if ($localStorage.marketUser){
            return true;
        }else{
            return false;
        }
    };

    $scope.loadProductsBasket = function (pageIndex = 1) {
        $http({
            url: contextPath + '/cart/',
            method: 'GET',
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
                $scope.loadProductsBasket();
            })
    }

    $scope.aboutProduct = function (productId) {
        $http.get(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.ProductsList = response.data;
                $scope.viewDiv($scope.ProductsList.length);
            })
        console.log(contextPath + '/products/' + productId)
    }

    $scope.putBasket = function (productId) {
        $http.get(contextPath + '/cart/add/' + productId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }


    $scope.deleteProductBasket = function (productId) {

        $http({
            url: contextPath + '/cart/delete/' + productId,
            method: 'GET',
        })
            .then(function (response) {
                $scope.loadProductsBasket();
            })

    }


    $scope.changeCountProduct = function (productId, count) {

        $http({
            url: contextPath + '/cart/change',
            method: 'GET',
            params: {
                productId: productId,
                count: count
            }
        })
            .then(function (response) {
                $scope.loadProductsBasket();
            })

    }

    $scope.deletAllProductBasket = function () {

        $http({
            url: contextPath + '/cart/delete/',
            method: 'GET',
        })
            .then(function (response) {
                $scope.loadProductsBasket();
            })
    }

    $scope.viewDiv = function (length) {
        if (length > 1) {
            document.getElementById('div_return').hidden = true;
        } else {
            document.getElementById('div_return').hidden = false;
        }
    }


    $scope.returnProducts = function () {
        $scope.loadProducts();
    }

    $scope.loadProducts();
    $scope.loadProductsBasket();

    const form = document.getElementById('form');

    $scope.createProductJson = function () {
        $http.post(contextPath + '/products', $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            })
    }

});




