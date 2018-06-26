$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysUser/queryPage.html',
        type:"post",
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'uid', width: 45, key: true , hidden:true},
			{ label: '用户名', name: 'userName', width: 75 },
			{ label: '昵称', name: 'nickName', width: 75 },
			{ label: '角色名称', name: 'roleName', width: 75 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === "locked" ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}} 
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "list",
            page: "currPage",
            total: "totalPage",
            records: "totalCount"
        },
        prmNames : {
            page:"current", 
            rows:"size", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			userName: null
		},
		showList: true,
		title:null,
		roleList:{},
		subjectList:null,
		courseList:null,
		user:{
			status:"normal",
			roleIdList:[]
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:'normal',roleIds:null};
			vm.courseList = [];
			
			axios.all([this.getRoleList() , vm.getSubjectList()]).then(axios.spread(function (rResponse , sResponse) {
				vm.roleList = rResponse.data.result;
				
				vm.subjectList = sResponse.data.result;
            }));
		},
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
            vm.roleList = {};
            
            axios.all([vm.getUser(userId),vm.getRoleList(), vm.getSubjectList()]).then(axios.spread(function (uResponse , rResponse , sResponse) {
            	vm.user = uResponse.data.result;
            	vm.roleList = rResponse.data.result;
            	vm.subjectList = sResponse.data.result;
            	
            	axios.all([vm.getUser(userId),vm.getCouseList()]).then(axios.spread(function (uResponse , cResponse) {
            		vm.user = uResponse.data.result;
                	vm.courseList = cResponse.data.result;
                }));
            }));
            
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sysUser/delete",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.message);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.user.uid == null ? "../sysUser/save" : "../sysUser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		getUser: function(userId){
			return axios.get("../sysUser/info/"+userId);
		},
		getRoleList: function(){
			return axios.get("../sysRole/all");
		},
		getCompanyList:function(){
			return axios.get("../sysCompany/findAll.html");
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'userName':vm.q.userName},
                page:page
            }).trigger("reloadGrid");
		},
		getCouseList: function(){
			return axios.get(mainHttp + "course/findBySubjectId.html?subjectId="+vm.user.subjectId);
		},
		getSubjectList: function(){
			return axios.get(mainHttp + "subject/getSubject.html");
		},
		changeSubject:function(){
			axios.all([this.getCouseList()]).then(axios.spread(function (cResponse) {
				vm.courseList = cResponse.data.result;
            }));
		}
	},
	watch:{
		  // 数据修改时触发
	      subjectList: function() {
	        this.$nextTick(function(){
		        $('#subjectId').selectpicker('refresh');
	        })
	      },
	      courseList: function() {
		        this.$nextTick(function(){
			        $('#courseId').selectpicker('refresh');
		        })
	      }
	}
});