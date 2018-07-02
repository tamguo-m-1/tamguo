 var validatePass = (rule, value, callback) => {
    if (value === '') {
      callback(new Error('请输入确认密码！'));
    } else if (value !== vm.member.nowPassword) {
      callback(new Error('两次输入密码不一致！'));
    } else {
      callback();
    }
};

var vm = new Vue({
	el:'#app',
	data:{
		smsDisabled:false,
		loading:false,
		member:{
			
		},
		rules:{
			password:[{ required: true, message: '请输入旧密码', trigger: 'blur' }],
			nowPassword:[
				{ required: true, message: '请输入新密码', trigger: 'blur' },
				{ min: 6 , message: '长度大于6个字符', trigger: 'blur' }
			],
			confirmPassword:[
				{ required: true, message: '请输入确认密码', trigger: 'blur' },
				{ min: 6 , message: '长度大于6个字符', trigger: 'blur' },
				{ validator: validatePass, trigger: 'blur' }
			],
			verifyCode:[{ required: true, message: '请输入验证码', trigger: 'blur' }]
		}
	},
	methods:{
		onSubmit:function(){
			loading = true;
	    	this.$refs['member'].validate((valid) => {
		          if (valid) {
		            axios({method: 'post',url: mainHttp + 'member/password/update.html',data: vm.member}).then(function(response){
		      		    if(response.data.code == 0){
		      		    	vm.loading = false;
		      		    	vm.$message({message: "修改成功",duration:500,type: 'success',onClose:function(){
		      		    		window.location.reload();
		      		    	}});
						}else if(response.data.code == 501){
							vm.loading = false;
							vm.member.password = "";
							vm.$message.error(response.data.message);
							vm.$refs['member'].validate();
						}else if(response.data.code == 502){
							vm.loading = false;
							vm.member.verifyCode = "";
							vm.$message.error(response.data.message);
							vm.$refs['member'].validate();
						}else{
							vm.loading = false;
							vm.$message.error(response.data.message);
						}
		      	  	});
		          } else {
		            console.log('error submit!!');
		            return false;
		          }
	          });
		},
		getMember:function(){
	    	  axios.get(mainHttp + 'member/findCurrMember.html').then(function(response){
	    		  vm.member = response.data.result;
	    		  var kemuId = [];
	    		  kemuId.push(vm.member.subjectId);
	    		  kemuId.push(vm.member.courseId);
	    		  vm.member.kemuId = kemuId;
	    		  vm.member.imageUrl = mainHttp + vm.member.avatar;
	    	  });  
	     },
	     sendSms:function(){
	    	  // 校验成功才能发送短信
    		  if(vm.member.mobile != ""){
    			  axios.get(mainHttp + 'sms/sendFindPasswordSms.html?mobile='+vm.member.mobile).then(function(response){
            		  if(response.data.code == 200){
            			  vm.$message({message: response.data.message,type: 'success'});
            		  }else{
            			  vm.$message.error(response.data.message);
            		  }
            	  });
    		  }else{
    			  vm.$message.error("未绑定手机号！");
    		  }
	     }
	}
});


vm.getMember();