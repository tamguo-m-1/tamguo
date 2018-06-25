$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'teacher/list.html',
        datatype: "json",
        colModel: [		
            { label: '教师ID', name: 'uid', width: 45, key: true , hidden:true},       	
			{ label: '真实名称', name: 'name', width: 45},
			{ label: '手机号', name: 'mobile', width: 45 },
			{ label: '考试', name: 'subjectName', width: 45 },
			{ label: '科目', name: 'courseName', width: 45 },
			{ label: '省份', name: 'provinceName', width: 45 },
			{ label: '城市', name: 'cityName', width: 45 },
			{ label: '创建时间', name: 'createTime', width: 45 },
			{ label: '状态', name: 'status', width: 25,formatter: function(value, options, row){
				if(value === "apply"){
					return '<span class="label label-danger">申请中</span>';
				}else if(value === "pass"){
					return '<span class="label label-success">通过</span>';
				}else if(value === "unpass"){
					return '<span class="label label-danger">未通过</span>';
				}
			}},
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
			name: null
		},
		showList: true,
		title:null,
		teacher:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'mobile': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		audit: function(event){
			var teacherId = getSelectedRow();
			if(teacherId == null){
				return ;
			}
			axios.get(mainHttp + 'teacher/find/'+teacherId+'.html').then(function (response) {
				vm.teacher.name = response.data.result.name;
				confirm('您是希望【'+vm.teacher.name+'】成为探果题库的老师？', function(){
					vm.pass(teacherId);
				});
			}).catch(function (error) {
			    console.log(error);
			});
		},
		edit:function(event){
			var teacherId = getSelectedRow();
			if(teacherId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getTeacher(teacherId);
		},
		saveOrUpdate:function(event){
			var url = mainHttp + "teacher/update.html";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.teacher),
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
		getTeacher:function(teacherId){
			axios.get(mainHttp + 'teacher/find/'+teacherId+'.html').then(function (response) {
				vm.teacher = response.data.result;
				vm.teacher.cardPhoto = mainHttp + vm.teacher.cardPhoto;
				vm.teacher.occupationPapers = mainHttp + vm.teacher.occupationPapers;
			}).catch(function (error) {
			    console.log(error);
			});
		},
		pass:function(teacherId){
			axios.get(mainHttp + 'teacher/pass/'+teacherId+'.html').then(function (response) {
				alert(response.data.message);
			}).catch(function (error) {
			    console.log(error);
			});
		},
		del:function(teacherId){
			var teacherIds = getSelectedRows();
			if(teacherIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "teacher/delete.html",
				    data: JSON.stringify(teacherIds),
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
		}
	}
});