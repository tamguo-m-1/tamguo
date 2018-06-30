var validateArea = (rule, value, callback) => {
	if(/^(undefined|null|\s)$/.test(vm.paper.areaIdList[0])){
	 	  callback(new Error('请选择地区!'));
	}else{
	  callback();
	}
}
var validateCourse = (rule, value, callback) => {
	if(/^(undefined|null|\s)$/.test(vm.paper.areaIdList[0])){
	 	  callback(new Error('请选择科目!'));
	}else{
	  callback();
	}
}

// Vue
var vm = new Vue({
	el:'#pageShow',
	data:{
		q:{
			name
		},
		paperDialogFormVisible:false,
		paperInfoDialogFormVisible:false,
		areas:[],
		schools:[],
		courses:[],
		loading:false,
		title:null,
		totalCount:null,
		currPage:1,
		currPaperUid:null,
		paperList:null,
		paper:{
			courseId:null,
			schoolId:null,
			areaId:null,
			name:null,
			type:null,
			year:null,
			point:0,
			money:0
		},
		rules: {
          name: [
            { required: true, message: '请输入试卷名称', trigger: 'blur' },
            { min: 6 , message: '长度大于6个字符', trigger: 'blur' }
          ],
          areaIdList: [
        	{ required: true, message: '请选择所属地区', trigger: ['blur','change'] },,
            { validator: validateArea, trigger: ['blur','change'] }
          ],
          courseIdList: [
            {required: true, message: '请选择科目', trigger: 'change'},
            { validator: validateCourse, trigger: ['blur','change'] }
          ],
          year:[
        	  { required: true, message: '请输入年费', trigger: 'blur' }
          ],
          type:[
        	  { required: true, message: '请选择类型', trigger: 'blur' }
          ]
        },
        paperInfo:{
        	
        },
        pagerInfoRules:{
        	title:[
                { required: true, message: '请输入子试名称', trigger: 'blur' },
                { min: 6 , message: '长度大于6个字符', trigger: 'blur' }
            ],
            type:[
          	  { required: true, message: '请选择题目类型', trigger: 'blur' }
            ]
        }
	},
	methods: {
		reload: function (currPage) {
			vm.loading = true;
			axios({method: 'get',url: mainHttp + "/member/paper/list.html?name="+vm.q.name+"&page="+currPage+"&limit=6"}).then(function(response){
				vm.paperList = response.data.list;
				if(vm.paperList != null && vm.paperList.length > 0 ){
					vm.currPaperUid = vm.paperList[0].uid;
				}
				vm.totalCount = response.data.totalCount;
				vm.loading = false;
		 	});
		},
		handleCurrentChange:function(val){
			vm.currPage = val; 
			vm.reload(val);
		},
		queryAreaList:function(){
			axios.get(mainHttp + 'area/findAreaTree.html').then(function(response){
	    		vm.areas = response.data.result;
	    	});
		},
		// 获取地区学校
		querySchoolByAreaId:function(){
			axios.get(mainHttp + 'school/findByAreaId.html?areaId='+vm.paper.areaIdList).then(function(response){
	    		vm.schools = response.data;
	    	});
		},
		getCourses:function(){
    	    axios.get(mainHttp + 'subject/getCourseCascaderTree.html').then(function(response){
    		    vm.courses = response.data.result;
    	    });  
	    },
	    isFree:function(){
	    	if(paper.isFree){
	    		paper.point = 0;
	    		paper.money = 0;
	    	}
	    },
	    // 保存试卷
	    savePaper:function(){
	    	this.$refs['paper'].validate((valid) => {
		          if (valid) {
		        	vm.loading = true;
		        	var courseId = vm.paper.courseIdList[1];
		        	var subjectId = vm.paper.courseIdList[0];
		        	vm.paper.courseId = courseId;
		    		vm.paper.subjectId = subjectId;
		    		vm.paper.areaId = vm.paper.areaIdList.join(",");
		    		if(!/^(undefined|null|\s*)?$/.test(vm.paper.schoolIdList)){
			    		vm.paper.schoolId = vm.paper.schoolIdList.join(",");
		    		}
		    		var url = null;
		    		if(/^(undefined|null|\s*)?$/.test(vm.paper.uid)){
		    			url = mainHttp + 'member/paperList/addPaper.html';
		    		}else {
		    			url = mainHttp + 'member/paperList/updatePaper.html';
		    		}
		            axios({method: 'post',url: url,data: vm.paper}).then(function(response){
		      		    if(response.data.code == 0){
		      		    	vm.loading = false;
		      		    	vm.$message({message: response.data.message ,duration:500,type: 'success',onClose:function(){
		      		    		vm.paperDialogFormVisible = false;
		      		    		vm.$refs['paper'].resetFields();
		      		    		vm.paper.free = "0";
		      		    		vm.paper.type = null;
		      		    		vm.reload(vm.currPage);
		      		    	}});
						}else{
							vm.loading = false;
							vm.$message.error(response.data.message);
							vm.$refs['paper'].validate();
						}
		      	  	});
		          } else {
		             console.log('error submit!!');
		             return false;
		          }
	          });
	    	paperDialogFormVisible = false;
	    },
	    getPaper:function(uid){
	    	axios({method: 'get',url: mainHttp + 'member/findPaper.html?paperId=' + uid}).then(function(response){
      		    if(response.data.code == 0){
      		    	vm.paper = response.data.result;
      		    	vm.paper.areaIdList = vm.paper.areaId.split(",");
      		    	vm.querySchoolByAreaId();
      		    	var courseIdList = new Array();
      		    	courseIdList.push(vm.paper.subjectId);
      		    	courseIdList.push(vm.paper.courseId);
      		    	vm.paper.courseIdList = courseIdList;
      		    	Vue.set(vm.paper , 'schoolIdList' , vm.paper.schoolId.split(","));
				}
      	  	});
	    },
		updatePaper:function(uid , name){
			vm.getPaper(uid);
			
			vm.paperDialogFormVisible = true;
		},
		showPaperInfo:function(uid , queInfo){
			vm.paperInfo.flag = true;
			
			vm.paperInfo.uid = uid;
			
			vm.paperInfo.queInfo = queInfo;
			
			vm.paperInfoDialogFormVisible = true
		},
		// 保存子卷信息
		savePaperInfo:function(){
			this.$refs['paperInfo'].validate((valid) => {
		          if (valid) {
		        	  var url = null;
		        	  if(vm.paperInfo.flag){
		        		  url = mainHttp + 'member/paperList/addPaperQuestionInfo.html';
		        	  }else{
		        		  url = mainHttp + 'member/paperList/updatePaperQuestionInfo.html';
		        	  }
		        	  vm.loading = true;
		        	  axios({method: 'post',url: url,data: vm.paperInfo}).then(function(response){
			      		    if(response.data.code == 0){
			      		    	vm.loading = false;
			      		    	vm.$message({message: response.data.message ,duration:500,type: 'success',onClose:function(){
			      		    		vm.paperInfoDialogFormVisible = false;
			      		    		vm.$refs['paperInfo'].resetFields();
			      		    		vm.reload(vm.currPage);
			      		    	}});
							}else{
								vm.loading = false;
								vm.$message.error(response.data.message);
								vm.$refs['paperInfo'].validate();
							}
			      	  	});
		          } else {
		              console.log('error submit!!');
		              return false;
		          }
	        });
		},
		// 实现修改试卷
		showUpdatePaperInfo:function(uid , type , title , infoUid){
			vm.paperInfo.flag = false;
			vm.paperInfo.uid = uid;
			vm.paperInfoDialogFormVisible = true
			
			vm.paperInfo.name = name;
			Vue.set(vm.paperInfo , 'type' , type);
			vm.paperInfo.title = title;
			vm.paperInfo.infoUid = infoUid;
		},
		showDeleteDialog:function(paperId,paperName) {
	        vm.$confirm('此操作将永久删除【'+paperName+'】, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	            axios({method: 'get',url: mainHttp + 'member/paperList/deletePaper.html?paperId=' + paperId}).then(function(response){
	        	    vm.$message({type: 'info',message: response.data.message});
	        	    if(response.data.code == 0){
	      		    	vm.reload(vm.currPage);
					}
	      	    });
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
	    },
	    showPaperInfoDeleteDialog:function(paperId , name , uid){
	    	vm.$confirm('此操作将永久删除【'+name+'】, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	            axios({method: 'get',url: mainHttp + 'member/paperList/deletePaperQuestionInfoBtn?paperId=' + paperId+'&uid='+uid}).then(function(response){
	        	    vm.$message({type: 'info',message: response.data.message ,onClose:function(){
	        	    	if(response.data.code == 0){
		      		    	vm.reload(vm.currPage);
						}
    		    	}});
	      		    
	      	    });
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
	    },
	    openPaperInfo:function(uid){
	    	if(vm.currPaperUid == uid){
	    		vm.currPaperUid =  null
	    	}else{
		    	vm.currPaperUid = uid;
	    	}
	    },
		// 添加試題
		addPaperQuestionFn:function(paperId){
			window.location.href= mainHttp +'member/addQuestion.html?paperId='+paperId; 
		}
	}
});

// 查询页面数据
vm.reload(1);
// 获取地区信息
vm.queryAreaList();
// 获取科目信息
vm.getCourses();