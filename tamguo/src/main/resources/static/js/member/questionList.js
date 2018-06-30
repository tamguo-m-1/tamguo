var vm = new Vue({
	el:'#container',
	data:{
		totalCount:null,
		currPage:1,
		questionTypes:[{id:'1',name:'单选题'},{id:'2',name:'多选项'},{id:'3',name:'判断题'},{id:'4',name:'填空题'},{id:'5',name:'简答题'}],
		q:{
			uid:null,
			questionType:null,
			reviewPoint:null,
			paperId:null
		},
		questionList:null
	},
	methods: {
		queryQuestionList:function(currPage){
			vm.q.paperId = $("#paperId").val();
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/queryQuestionList.html",
			    data: {paperId:vm.q.paperId,questionType:vm.q.questionType,reviewPoint:vm.q.reviewPoint,uid:vm.q.reviewPoint,
			    	page:currPage,limit:2},
			    dataType:"json",
			    success: function(r){
			    	vm.questionList = r.list;
			    	vm.totalCount = r.totalCount;
				}
			});
		},
		handleCurrentChange:function(val){
			vm.queryQuestionList(val);
		},
		deleteQuestion:function(uid){
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/deleteQuestion",
			    data: {"uid":uid},
			    success: function(r){
			    	if(r.code === 0){
			    		layer.alert(r.message, function(index){
			    			window.location.href = mainHttp + "member/questionList.html?paperId=" + question.paperId;
						});
					}else{
						layer.alert(r.message);
					}
				}
			});
		}
	}
});
vm.queryQuestionList(1);