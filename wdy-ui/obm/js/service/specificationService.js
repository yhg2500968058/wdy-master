//服务层
app.service('specificationService', function ($http) {

    //读取列表数据绑定到表单中
    this.findAll = function () {
        return $http.get('http://localhost:8081/tbSpecification/findAll.do');
    }
    //分页
    this.findPage = function (page, rows) {
        return $http.get('http://localhost:8081/tbSpecification/findPage.do?page=' + page + '&rows=' + rows);
    }
    //查询实体
    this.findOptionById = function (id) {
        return $http.post('http://localhost:8081/tbSpecification/findOptionById?id=' + id);
    }
    //增加
    this.add = function (pojoSpecificationName,optionNameAndOrderArray) {
        return $http.post('http://localhost:8081/tbSpecification/add?name='+pojoSpecificationName+"&optionName="+optionNameAndOrderArray);
    }
    //修改
    this.update = function (id,name,optionArray) {
        return $http.post('http://localhost:8081/tbSpecification/updateSpecOption?id='+id+'&name='+name+'&optionArray='+optionArray);
    }
    //删除
    this.dele = function (ids) {
        return $http.get('http://localhost:8081/tbSpecification/delete/' + ids);
    }
    //搜索
    this.search = function (page, rows, searchEntity) {
        return $http.post('http://localhost:8081/tbSpecification/search?page=' + page + "&rows=" + rows, searchEntity);
    }


    //规格的下拉列表
    this.selectSpecificationOptionList = function () {
        return $http.get("http://localhost:8081/tbSpecification/selectOptionList");
    }
});
