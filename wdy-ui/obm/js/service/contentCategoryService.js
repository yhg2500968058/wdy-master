//服务层
app.service('contentCategoryService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('http://localhost:8081/tbContentCategory/findAll');
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('http://localhost:8081/tbContentCategory/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findById=function(id){
		return $http.get('http://localhost:8085/tbContentCategory/findById/'+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('http://localhost:8085/tbContentCategory/save',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.put('http://localhost:8081/tbContentCategory/update',entity );
	}
	//删除
	this.delete=function(ids){
		return $http.delete('http://localhost:8085/tbContentCategory/delete/'+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('http://localhost:8085/tbContentCategory/search?page='+page+"&rows="+rows,searchEntity);
	}

});
