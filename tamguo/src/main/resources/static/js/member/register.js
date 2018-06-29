// 校验
var validateCheckPass = (rule, value, callback) => {
  if (value !== vm.member.password) {
    callback(new Error('两次输入密码不一致!'));
  } else {
    callback();
  }
};
var validateUsername = (rule, value, callback) => {
  if(!/^(undefined|null|\s)$/.test(value)){
	  axios({method: 'get',url: mainHttp + 'checkUsername.html?username='+vm.member.username}).then(function(response){
 		   if(response.data.code == 201){
 			  callback(new Error('此用户名太受欢迎,请更换一个!'));
 		   }else{
 			  callback();
 		   }
 	  });
  }
}
var validateMobile = (rule, value, callback) => {
	  if(!/^(undefined|null|\s)$/.test(value)){
		  axios({method: 'get',url: mainHttp + 'checkMobile.html?mobile='+vm.member.mobile}).then(function(response){
	 		   if(response.data.code == 201){
	 			  callback(new Error('该手机号已经存在!'));
	 		   }else{
	 			  callback();
	 		   }
	 	  });
	  }
	}
var vm = new Vue({
	el: '#content',
    data() {
      return {
    	smsDisabled:false,
    	loading:false,
        member: {
          username: '',
          mobile:'',
          password:'',
          checkPass:'',
          verifyCode:''
        },
        courses: [],
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 6 , message: '长度大于6个字符', trigger: 'blur' },
            { validator: validateUsername, trigger: 'blur' }
          ],
          mobile: [
              { required: true, message: '请输入手机号', trigger: 'blur' },
              { pattern: /^1[345678]\d{9}$/, message: '手机号格式错误', trigger: 'blur' },
              { validator: validateMobile, trigger: 'blur' }
          ],
          password: [
        	  { required: true, message: '请输入密码', trigger: 'blur' },
          ],
          checkPass: [
        	  { required: true, message: '请输入确认密码', trigger: 'blur' },
              { validator: validateCheckPass, trigger: 'blur' }
          ],
          verifyCode:[
        	  { required: true, message: '请输入验证码', trigger: 'blur' },
          ],
          kemuId:[
        	  {required: true, message: '请选择科目', trigger: 'change'}
          ],
          email:[
        	  { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ]
        }
      };      
    },
    methods: {
      submitForm(formName) {
         this.$refs[formName].validate((valid) => {
	          if (valid) {
	        	vm.loading = true;
	        	vm.member.courseId = vm.member.kemuId[1];
	    		vm.member.subjectId = vm.member.kemuId[0];
	            axios({method: 'post',url: mainHttp + 'subRegister.html',data: vm.member}).then(function(response){
	      		    if(response.data.code == 200){
	      		    	vm.loading = false;
	      		    	vm.$message({message: "注册成功",duration:500,type: 'success',onClose:function(){
	      		    		window.location.href = "member/index.html";
	      		    	}});
					}else{
						vm.loading = false;
						vm.$message.error(response.data.message);
						vm.$refs[formName].validate();
					}
	      	  	});
	          } else {
	            console.log('error submit!!');
	            return false;
	          }
          });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getCourses:function(){
    	  axios.get(mainHttp + 'subject/getCourseCascaderTree.html').then(function(response){
    		  vm.courses = response.data.result;
    	  });  
      },
      sendSms:function(){
    	  // 校验成功才能发送短信
    	  vm.$refs['member'].validateField('mobile',function(message){
    		  if(message == ""){
    			  axios.get(mainHttp + 'sms/sendFindPasswordSms.html?mobile='+vm.member.mobile).then(function(response){
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
vm.getCourses();