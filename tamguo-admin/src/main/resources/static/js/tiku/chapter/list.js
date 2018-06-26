$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'chapter/list.html',
        datatype: "json",
        colModel: [			
			{ label: '章节ID', name: 'uid', width: 40, key: true , hidden:true},
			{ label: '章节名称', name: 'name', width: 60 },
			{ label: '父章节名称', name: 'parentName', width: 60 },
			{ label: '问题数量', name: 'questionNum', width: 60 },
			{ label: '知识点', name: 'pointNum', width: 60 }              
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

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "uid",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	}
};
var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		subjectList:null,
		q:{
			name:null
		},
		chapter:{
			
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.chapter = {};
			vm.getChapterTree();
		},
		update: function (event) {
			var chapterId = getSelectedRow();
			if(chapterId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			axios.all([this.getChapter(chapterId)]).then(axios.spread(function (cResponse) {
				vm.chapter = cResponse.data.result;
            	vm.getChapterTree();
            }));
		},
		del: function (event) {
			var courseIds = getSelectedRows();
			if(courseIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "course/delete.html",
				    data: JSON.stringify(courseIds),
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
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.chapter.uid == null ? mainHttp + "chapter/save.html" : mainHttp + "chapter/update.html";
			// 获取章节
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.chapter),
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		openChapterTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#chapterLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单x
					Vue.set(vm.chapter, 'parentId', node[0].uid);
					Vue.set(vm.chapter, 'parentName', node[0].name);
					layer.close(index);
	            }
			});
		},
		getChapterTree:function(courseId){
			//加载菜单树
			axios.get(mainHttp + "chapter/getChapterTree.html").then(function(cResponse){
				// 构建菜单
				ztree = $.fn.zTree.init($("#chapterTree"), setting, cResponse.data.result);
			});
		},
		getChapter:function(chapterId){
			return axios.get(mainHttp + "chapter/info/"+chapterId+".html");
		}
	}
});