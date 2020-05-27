app.service('tbItemCatService',function ($http) {
    
    this.findAll=function () {
        return $http.get('http://localhost:8081/tbItemCat/list');
    }

    this.findById=function (id) {
        return $http.get('http://localhost:8081/tbItemCat/findById/'+id);
    }

    this.findPage=function (page,rows,classify) {
        return $http.post('http://localhost:8081/tbItemCat/findPage?page='+page+'&rows='+rows,classify);
    }

    this.add=function (entity,parentId) {
        return $http.post('http://localhost:8081/tbItemCat/save?parentId='+parentId,entity);
    }

    this.update=function (entity) {
        return $http.post('http://localhost:8081/tbItemCat/update',entity);
    }

    this.del=function (ids) {
        return $http.get('http://localhost:8081/tbItemCat/delete/'+ids);

    }

    this.findParentId=function (parentId) {
        return $http.get('http://localhost:8081/tbItemCat/findByParentId?parentId='+parentId);

    }

    this.findSonId=function (id) {
        return $http.get('http://localhost:8081/tbItemCat/findBySonId/'+id);
    }

})