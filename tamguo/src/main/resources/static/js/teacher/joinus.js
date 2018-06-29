function cardPhoto(rule, value, callback) {
    if (vm.cardPhotoList == undefined || vm.cardPhotoList.length == 0) {
      return callback(new Error('请上传身份证正面照'));
    }
    return callback();
};
function occupationPaper(rule, value, callback){
	if (vm.occupationPapersList == undefined || vm.occupationPapersList.length == 0) {
        return callback(new Error('请上传证件照'));
    }
	return callback();
}

var vm = new Vue({
  el: '#infoForm',
  data:{
	  	uploadUrl : mainHttp + 'uploadFile.html',
	  	mobileModel: {
          mobile: '',
          verifyCode:''
        },
        teacher:{
        	mobile:null
        },
        rules: {
        	mobile: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
                { pattern: /^1[345678]\d{9}$/, message: '手机号格式错误', trigger: 'blur' },
            ],
            verifyCode: [
            	{required: true, message: '请输入验证码', trigger: 'blur'},
            	{min:6, max:6, message: '验证码格式错误', trigger: 'blur' }
            ]
        },
        teacherRules: {
        	areaId:[{required: true, message: '请选择地区', trigger: 'change'}],
        	kemuId:[{required: true, message: '请选择科目', trigger: 'change'}],
        	name:[{required: true, message: '请输入姓名', trigger: 'blur'}],
        	motto:[{required: true, message: '请输入个人简介', trigger: 'blur'}],
        	cardId:[{required: true, message: '请输入身份证号', trigger: 'blur'}],
        	email:[{required: true, message: '请输入邮箱', trigger: 'blur'},
        	{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
        	cardPhoto:[{validator: cardPhoto, trigger: ['blur', 'change']}],
        	occupationPapers:[{validator: occupationPaper, trigger : ['blur', 'change']}]
        },
        active: 1,
        options: [],
        courses: [],
        cardPhotoList: [],
        occupationPapersList:[]
  },
  methods: {
      next:function(){
    	  // 第一步
    	  if(vm.active == 1){
    		  this.$refs.mobileForm.validate(function(valid){
	    		  if (valid) {
				      if(vm.active == 1){
			    		  axios.all([vm.getTeacher()]).then(axios.spread(function (tResponse) {
			    		     if(tResponse.data.code == 201 || tResponse.data.code == 202){
			    		    	  vm.$message({showClose:true,duration:1000,message:tResponse.data.message,type:'error',onClose:function(){
			    		    		  vm.mobileModel.verifyCode = "";
					    		      vm.$refs.mobileForm.validate('verifyCode');
			    		    	  }});
			    		     }else if(tResponse.data.code == 203){
			    		    	 vm.active = 2;
			    		    	 vm.teacher.mobile = vm.mobileModel.mobile;
			    		    	 
			    		    	 vm.getCourses();
			    		    	 vm.getArea();
			    		     }else if(tResponse.data.code == 0){
			    		    	 vm.active = 3;
			    		    	 vm.teacher = tResponse.data.result;
			    		     }
			    		  }));
			    	  }
    	          } else {
    	             console.log('error submit!!');
    	             return false;
    	          }
	          });
    	  }else if(vm.active == 2){ // 第二步
    		  this.$refs.teacherForm.validate(function(valid) {
	    		  if (valid) {
	    			 vm.teacher.provinceId = vm.teacher.areaId[0];
	    			 vm.teacher.cityId = vm.teacher.areaId[1];
	    			 vm.teacher.courseId = vm.teacher.kemuId[1];
	    			 vm.teacher.subjectId = vm.teacher.kemuId[0];
	    			 vm.teacher.cardPhoto = vm.cardPhotoList[0].path;
				     vm.teacher.occupationPapers = vm.occupationPapersList[0].path;
				     
				     axios.post('joinus.html', vm.teacher).then(function (response) {
				    	 console.log(response);
				    	 vm.active = 3;
				     }).catch(function (error) {console.log(error);});
    	          } else {
    	             console.log('error submit!!');
    	             return false;
    	          }
	          });
    	  }
      },
      getTeacher:function(){
    	  return axios.post(mainHttp + 'teacher/info.html',{mobile: vm.mobileModel.mobile,verifyCode: vm.mobileModel.verifyCode});
      },
      getCourses:function(){
    	  axios.get(mainHttp + 'subject/getCourseCascaderTree.html').then(function(response){
    		  vm.courses = response.data.result;
    	  });  
      },
      getArea:function(){
    	  axios.get(mainHttp + 'area/findAreaTree.html').then(function(response){
    		  vm.options = response.data.result;
    	  });
      },
      handleOccupationPapersRemove:function(file, fileList) {
          vm.occupationPapersList = [];
      },
      handleCardPhotoRemove:function(file, fileList){
    	  vm.cardPhotoList = [];
      },
      handleExceed:function(files, fileList) {
          this.$message.warning('当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件');
      },
      beforeRemove:function(file, fileList) {
          return this.$confirm('确定移除 ${ file.name }？');
      },
      uploadCardPhotoSuccess:function(response, file, fileList){
    	  var cardPhoto = {};
    	  cardPhoto.name = file.response.title;
    	  cardPhoto.url = mainHttp + file.response.url;
    	  cardPhoto.path = file.response.url;
    	  vm.cardPhotoList.push(cardPhoto);
    	  vm.$refs['teacherForm'].validateField('cardPhoto');
      },
      uploadOccupationPapersSuccess:function(response, file, fileList){
    	  var occupationPapers = {};
    	  occupationPapers.name = file.response.title;
    	  occupationPapers.url = mainHttp + file.response.url;
    	  occupationPapers.path = file.response.url;
    	  vm.occupationPapersList.push(occupationPapers);
    	  vm.$refs['teacherForm'].validateField('occupationPapers');
      },
      sendSms:function(){
    	  vm.mobileModel.verifyCode = "";
    	  vm.$refs['mobileForm'].validateField('mobile',function(message){
    		  if(message == ""){
    			  axios.get(mainHttp + 'sms/sendFindPasswordSms.html?mobile='+vm.mobileModel.mobile).then(function(response){
            		  if(response.data.code == 200){
            			  vm.$message({message: response.data.message,type: 'success'});
            		  }else{
            			  vm.$message.error(response.data.message);
            		  }
            	  });
    		  }
    	  });
      }
    }
});