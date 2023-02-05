angular.module('market', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1';
    const contextPathCarts = 'http://localhost:5555/cart/api/v1';


    var cont = document.getElementById("pagination");
    var pageIndex = 1;
    var countPages = 1;
    var head = document.getElementById("head");
    var fine = document.getElementById("fine");


    $scope.generatePagesList = function () {
        $scope.pagesList = [];
        for (let i = 0; i < countPages; i++) {
            $scope.pagesList.push(i + 1);
        }
    };

    cont.addEventListener("click", function (event) {
         if (event.target.innerText == "Следующая") {
            if (pageIndex < countPages) {
                pageIndex++;
                $scope.loadProducts();
            }
        } else if (event.target.innerText == "Предыдущая") {
            if (pageIndex > 1) {
                pageIndex--;
                $scope.loadProducts();
            }
        } else {
            if( event.target.innerText.length==1) {
                pageIndex = event.target.innerText;
                $scope.loadProducts();
            }
        }

    });

    $scope.isPaginationHeadOrFine = function () {
        if (pageIndex == 1) {
            head.hidden=true;
            fine.hidden=false;
        } else if (pageIndex == countPages) {
            head.hidden=false;
            fine.hidden=true;
        }else{
            head.hidden=false;
            fine.hidden=false;
        }
    };


    $scope.loadProducts = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
                name_product: $scope.filter ? $scope.filter.name_product : null,
                p: pageIndex,
                pageSize: 6
            }
        }).then(function (response) {
            countPages = response.data.totalPages;
            $scope.generatePagesList();
            $scope.ProductsList = response.data.content;
            $scope.viewDiv($scope.ProductsList.length);
            $scope.isPaginationHeadOrFine();
        });
    };

    $scope.loadOrders = function (pageIndex = 1) {
        $http.get(contextPath + '/orders', $scope.user)
        $http({
            url: contextPath + '/orders',
            method: 'GET',
        }).then(function (response) {
            $scope.OrderList = response.data;
        });
    };


    $scope.saveOrder = function () {
        $http.post(contextPath + '/orders', $scope.user)
            .then(function (response) {
                $scope.loadProductsBasket();
                $scope.loadOrders();
            })
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:5555/auth/auth', $scope.user)
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

    if ($localStorage.marketUser) {
        try {
            let jwt = $localStorage.marketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("User not authorization!!!");
                delete $localStorage.marketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (exp) {

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
    }

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadProductsBasket = function (pageIndex = 1) {
        $http({
            url: contextPathCarts + '/cart/',
            method: 'GET',
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
                $scope.loadProductsBasket();
            })
    }

    $scope.aboutProduct = function (productId) {
        $http.get(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.ProductsList = [response.data];
                $scope.viewDiv($scope.ProductsList.length);
            })
        console.log(contextPath + '/products/' + productId)
    }

    $scope.putBasket = function (productId) {
        $http.get(contextPathCarts + '/cart/add/' + productId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    }


    $scope.deleteProductBasket = function (productId) {

        $http({
            url: contextPathCarts + '/cart/delete/' + productId,
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
            url: contextPathCarts + '/cart/delete/',
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
    $scope.loadOrders();
    $scope.loadProductsBasket();

    const form = document.getElementById('form');

    $scope.createProductJson = function () {
        $http.post(contextPath + '/products', $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            })
    }

});




