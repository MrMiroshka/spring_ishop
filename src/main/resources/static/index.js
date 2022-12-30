angular.module('market', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8888/market/api/v1';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.aboutProduct = function (productId) {
        $http.get(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.ProductsList = response.data;
                $scope.viewDiv($scope.ProductsList.length);
            })
        console.log(contextPath + '/products/' + productId)
    }

    $scope.deleteProductById = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
                $scope.loadProductsBasket();
            })
    }

    $scope.loadProducts();
});