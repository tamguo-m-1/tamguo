var vm = new Vue({
	el:'#container',
	data:{
		loading:false,
		totalCount:null,
		currPage:1,
		questionTypes:[{id:'1',name:'单选题'},{id:'2',name:'多选项'},{id:'3',name:'判断题'},{id:'4',name:'填空题'},{id:'5',name:'简答题'}],
		q:{
			uid:null,
			questionType:null,
			content:null,
		},
		questionList:null
	},
	methods: {
		queryQuestionList:function(currPage){
			axios({method: 'post',url:mainHttp + "member/queryQuestionList" , data:{paperId:paperId,questionType:vm.q.questionType,content:vm.q.content,uid:vm.q.uid,page:vm.currPage,limit:2}}).then(function(response){
        	    	vm.questionList = response.data.list;
			    	vm.totalCount = response.data.totalCount;
			    	vm.loading = false;
      	    });
		},
		handleCurrentChange:function(val){
			vm.queryQuestionList(val);
		},
		deleteQuestion:function(uid){
			vm.$confirm('此操作将永久删除【'+uid+'】, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	            axios({method: 'post',url: mainHttp + "member/deleteQuestion.html?uid="+uid}).then(function(response){
	        	    if(response.data.code === 0){
	        	    	vm.$message({message: response.data.message ,duration:500,type: 'success',onClose:function(){
	        	    		window.location.href = mainHttp + "member/questionList.html?paperId=" + question.paperId;
	      		    	}});
					}else{
						vm.$message({message: response.data.message ,duration:500,type: 'success'});
					}
	      	    });
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		}
	}
});

setTimeout(vm.queryQuestionList(1),3000);//延时3秒 