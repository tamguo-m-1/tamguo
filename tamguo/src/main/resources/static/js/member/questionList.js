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
		}
	}
});
vm.queryQuestionList(1);