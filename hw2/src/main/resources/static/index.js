angular.module('market', []).controller('indexController', function ($scope, $http) {
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

    $scope.loadProductsBasket = function (pageIndex = 1) {
        $http({
            url: contextPath + '/cart/',
            method: 'GET',
        }).then(function (response) {
            $scope.BasketList = response.data.content;
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
        $http.get(contextPath + '/cart/' + productId)
            .then(function (response) {
                $scope.BasketList = response.data.content;
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




