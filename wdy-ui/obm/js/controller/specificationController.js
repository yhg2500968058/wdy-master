//进入页面初始化页面数据
app.controller("specificationController",function ($scope, $controller,specificationService) {

    $controller('baseController', {$scope: $scope});//继承

    //搜索
   $scope.search = function (page, rows) {
        specificationService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.result.rows;
                $scope.paginationConf.totalItems = response.result.total;//更新总记录数
            }
        );
    }

    $scope.entity = {specificationOptionList: []};

    $scope.newSpecification = function () {
        //清空hidden
        $scope.updateSpecificationLogoModel = "";
        document.getElementById("updateSpecificationLogoId").value = "";

        $scope.specName = "";
        $scope.optionListInfo = [];
    }
    //添加规格选项
    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({});
    }

    //删除规格选项行
    $scope.deleTableRow = function (index) {
        $scope.optionListInfo.splice(index,1);
    }
    //删除新增规格行
    $scope.deleTableRow1 = function (index) {
        $scope.entity.specificationOptionList.splice(index, 1);
    }

    //保存
    $scope.saveSpecificationInfo = function () {
        //标记，如果获取到的element对象数组中有值为空则标记为false
        var flag = true;

        //获取页面hidden的id
        var specHiddenId = document.getElementById("updateSpecificationLogoId").value;
        //获取的规格名称
        var pojoSpecificationName = document.getElementById("specificationName").value;
        //获取的规格选项名称dom对象集合
        var pojoSpecificationOptionName = document.getElementsByName("optionName1");
        //获取的规格选项排序dom对象集合
        var pojoSpecificationOptionOrders = document.getElementsByName("orders1");

        //标记，如果获取到的element对象数组中有值为空则标记为false
        for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
            var pojoSpecificationOptionNameElement = pojoSpecificationOptionName[i];
            var pojoSpecificationOptionOrdersElement = pojoSpecificationOptionOrders[i];
            if (pojoSpecificationOptionNameElement.value == "") {
                flag = false;
            }
            if (pojoSpecificationOptionOrdersElement.value == "") {
                flag = false;
            }
        }

        //获得隐藏域，如果值为空则代表点击了新增按钮
        if(specHiddenId == "") {
            if (pojoSpecificationName != "") {
                if (pojoSpecificationOptionName.length > 0) {
                    if (flag == true) {
                        //将dom对象集合转成数组
                        var optionNameAndOrderArray = [];


                        for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
                            optionNameAndOrderArray.push(pojoSpecificationOptionName[i].value + "-" + pojoSpecificationOptionOrders[i].value)
                        }
                            specificationService.add(pojoSpecificationName,optionNameAndOrderArray).success(
                            function (response) {
                                if (response.code == "200"){
                                    layer.msg(response.message)
                                $scope.reloadList();

                                }
                                //window.location.reload();
                            }
                        ).error(
                            function (response) {
                                layer.msg("添加失败,请检查您的网络后重试");
                               // window.location.reload();

                            }
                        );
                    } else {
                        layer.msg("规格选项的名称或排序不能为空")
                    }
                } else {
                    layer.msg("规格必须有且至少有一个规格选项")
                }
            } else {
                layer.msg("规格名称不能为空")
            }

        }else{
            //规格id不为空，代表修改
            if (pojoSpecificationName != "") {
                if (pojoSpecificationOptionName.length > 0) {
                    if (flag == true) {
                        //将dom对象集合转成数组
                        var optionNameAndOrderArray = [];

                        for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
                            optionNameAndOrderArray.push(pojoSpecificationOptionName[i].value + "-" + pojoSpecificationOptionOrders[i].value)
                        }

                       specificationService.update(specHiddenId,pojoSpecificationName,optionNameAndOrderArray).success(
                            function (response) {
                                $scope.reloadList();
                                //window.location=location;
                            }
                        ).error(
                            function (response) {
                                layer.msg("添加失败");
                                //window.location=location;
                            }
                        );
                    } else {
                        layer.msg("规格选项的名称或排序不能为空")
                    }
                } else {
                    layer.msg("规格必须有且至少有一个规格选项")
                }
            } else {
                layer.msg("规格名称不能为空")
            }


        }
    }

    $scope.showSpecificationData = function (id, specName) {
        //清空hidden
        $scope.updateSpecificationLogoModel = "";
        document.getElementById("updateSpecificationLogoId").value = "";

        //清空页面的规格选项列表
        $scope.entity.specificationOptionList = [];

        //清空规格选项数组
        $scope.foundOptionArray = [];

        document.getElementById("updateSpecificationLogoId").value = id;

        $scope.specName = specName;
            specificationService.findOptionById(id).success(
            function (response) {
                var optionArray = response.result;
                $scope.optionListInfo = response.result;
            }
        )
    }

    $scope.deleteByIds = function () {
        if ($scope.selectIds.length == 0) {
            layer.msg("请选择要删除的规格")
            return;
        }
        if (confirm("删除规格同时将会删除该规格对应的规格选项，确定要删除吗?")) {
            specificationService.dele($scope.selectIds).success(
                function (response) {
                   // window.location.reload();
                    layer.msg("操作")
                    $scope.reloadList();
                }
            ).error(
                function (response) {
                    layer.msg(response.message);
                    $scope.reloadList();;
                }
            );
        }

    }

});
/*
app.controller("myController", function ($scope, $http) {
        $scope.findList = function () {
            $http.get("http://localhost:8081/tbSpecification/findAllSpecification").success(
                function (response) {
                    $scope.list = response.result;
                }
            ).error(
                function (response) {
                    layer.msg(response.message);
                }
            );
        }

        $scope.entity = {specificationOptionList: []};

        //增加规格选项行
        $scope.addTableRow = function () {
            $scope.entity.specificationOptionList.push({});
        }

        //新建按钮事件
        $scope.newSpecification = function(){
            $scope.entity.specificationOptionList = [];
            $scope.optionListInfo = [];
            $scope.specName = "";
            $scope.updateSpecificationLogoModel = "";
            document.getElementById("updateSpecificationLogoId").value = "";
        }

        //删除规格选项行
        $scope.deleTableRow = function (index) {
            $scope.entity.specificationOptionList.splice(index, 1);
            $scope.optionListInfo.splice(index,1);
        }

        $scope.findByName = function () {
            var a = document.getElementById("specName").value;
            if (a == "") {
                $scope.findList();
            } else {
                $http.get("http://localhost:8081/tbSpecification/findSpecificationByName/" + a).success(
                    function (response) {
                        $scope.list = response.result;
                    }
                );
            }
        }

        //用户勾选的ID集合
        $scope.selectIds = [];
        //用户勾选复选框
        $scope.updateSelection = function ($event, id) {
            if ($event.target.checked) {
                $scope.selectIds.push(id);
            } else {
                var index = $scope.selectIds.indexOf(id);
                $scope.selectIds.splice(index, 1);
            }
        }
        //删除
        $scope.deleteByIds = function () {
            if ($scope.selectIds.length == 0) {
                layer.msg("请选择要删除的规格")
                return;
            }
            if (confirm("删除规格同时将会删除该规格对应的规格选项，确定要删除吗?")) {
                $http.get("http://localhost:8081/tbSpecification/deleteById/" + $scope.selectIds).success(
                    function (response) {
                        window.location.reload();
                    }
                ).error(
                    function (response) {
                        layer.msg(response.message);
                        window.location.reload();
                    }
                );
            }

        }

        //保存
        $scope.saveSpecificationInfo = function () {
            //标记，如果获取到的element对象数组中有值为空则标记为false
            var flag = true;

            //获取页面hidden的id
            var specHiddenId = document.getElementById("updateSpecificationLogoId").value;
            //获取的规格名称
            var pojoSpecificationName = document.getElementById("specificationName").value;
            //获取的规格选项名称dom对象集合
            var pojoSpecificationOptionName = document.getElementsByName("optionName1");
            //获取的规格选项排序dom对象集合
            var pojoSpecificationOptionOrders = document.getElementsByName("orders1");

            //标记，如果获取到的element对象数组中有值为空则标记为false
            for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
                var pojoSpecificationOptionNameElement = pojoSpecificationOptionName[i];
                var pojoSpecificationOptionOrdersElement = pojoSpecificationOptionOrders[i];
                if (pojoSpecificationOptionNameElement.value == "") {
                    flag = false;
                }
                if (pojoSpecificationOptionOrdersElement.value == "") {
                    flag = false;
                }
            }

            //获得隐藏域，如果值为空则代表点击了新增按钮
            if(specHiddenId == "") {
                if (pojoSpecificationName != "") {
                    if (pojoSpecificationOptionName.length > 0) {
                        if (flag == true) {
                            //将dom对象集合转成数组
                            var optionNameAndOrderArray = [];

                            for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
                                optionNameAndOrderArray.push(pojoSpecificationOptionName[i].value + "-" + pojoSpecificationOptionOrders[i].value)
                            }

                            $http.get("http://localhost:8081/tbSpecification/addSpecification/" + pojoSpecificationName + "/" + optionNameAndOrderArray).success(
                                function (response) {
                                    window.location.reload();
                                }
                            ).error(
                                function (response) {
                                    layer.msg("添加失败,请检查您的网络后重试");
                                    window.location.reload();
                                }
                            );
                        } else {
                            layer.msg("规格选项的名称或排序不能为空")
                        }
                    } else {
                        layer.msg("规格必须有且至少有一个规格选项")
                    }
                } else {
                    layer.msg("规格名称不能为空")
                }

            }else{
                //规格id不为空，代表修改
                if (pojoSpecificationName != "") {
                    if (pojoSpecificationOptionName.length > 0) {
                        if (flag == true) {
                            //将dom对象集合转成数组
                            var optionNameAndOrderArray = [];

                            for (var i = 0; i < pojoSpecificationOptionName.length; i++) {
                                optionNameAndOrderArray.push(pojoSpecificationOptionName[i].value + "-" + pojoSpecificationOptionOrders[i].value)
                            }

                            $http.get("http://localhost:8081/tbSpecification/updateSpecification/" + specHiddenId + "/" + pojoSpecificationName + "/" + optionNameAndOrderArray).success(
                                function (response) {
                                    window.location=location;
                                }
                            ).error(
                                function (response) {
                                    layer.msg("添加失败");
                                    window.location=location;
                                }
                            );
                        } else {
                            layer.msg("规格选项的名称或排序不能为空")
                        }
                    } else {
                        layer.msg("规格必须有且至少有一个规格选项")
                    }
                } else {
                    layer.msg("规格名称不能为空")
                }


            }
        }

        //修改,自动根据传入的数据的长度增加相应数量的新增规格行
        $scope.showSpecificationData = function (id, specName) {
            //清空hidden
            $scope.updateSpecificationLogoModel = "";
            document.getElementById("updateSpecificationLogoId").value = "";

            //清空页面的规格选项列表
            $scope.entity.specificationOptionList = [];

            //清空规格选项数组
            $scope.foundOptionArray = [];

            document.getElementById("updateSpecificationLogoId").value = id;

            $scope.specName = specName;
            $http.get("http://localhost:8081/tbSpecification/initDataById/" + id).success(
                function (response) {
                    var optionArray = response.result;
                    $scope.optionListInfo = response.result;
                }
            )
        }
    }
)*/
