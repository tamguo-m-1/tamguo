$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'teacher/list.html',
        datatype: "json",
        colModel: [		
            { label: '教师ID', name: 'uid', width: 45, key: true , hidden:true},       	
			{ label: '真实名称', name: 'name', width: 45},
			{ label: '手机号', name: 'mobile', width: 45 },
			{ label: '身份证号', name: 'cardId', width: 45 },
			{ label: 'QQ号', name: 'qq', width: 45 }
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
			vm.showList = false;
			vm.title = "审核";
			vm.teacher = {};
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
			var url = vm.teacher.uid == null ? mainHttp + "teacher/audit.html" : mainHttp + "teacher/update.html";
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
			}).catch(function (error) {
			    console.log(error);
			});
		}
	}
});