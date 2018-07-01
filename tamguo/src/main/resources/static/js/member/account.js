var vm = new Vue({
	el:'#app',
	data() {
	      return {
	    	loading:false,
	        courses:[],
	        member:{
	        	name:null
	        },
	        rules: {
	        	username: [
	              { required: true, message: '请输入用户名', trigger: 'blur' }
	            ],
	        	nickName: [
	              { required: true, message: '请输入用户名', trigger: 'blur' }
	            ],
	            kemuId:[
	            	{ required: true, message: '请输入用户名', trigger: 'blur' }
	            ]
	        }
	      };
	    },
	    methods: {
		      handleAvatarSuccess(res, file) {
		    	  vm.member.avatar = res.filePath;
		    	  vm.member.imageUrl = res.fileDomain + res.filePath;
		      },
		      beforeAvatarUpload(file) {
			        const isJPG = file.type === 'image/jpeg';
			        const isLt2M = file.size / 1024 / 1024 < 2;
		
			        if (!isJPG) {
			          this.$message.error('上传头像图片只能是 JPG 格式!');
			        }
			        if (!isLt2M) {
			          this.$message.error('上传头像图片大小不能超过 2MB!');
			        }
			        return isJPG && isLt2M;
		      },
		      onSubmit:function(){
		    	  loading = true;
		    	  this.$refs['member'].validate((valid) => {
			          if (valid) {
			        	vm.member.courseId = vm.member.kemuId[1];
			    		vm.member.subjectId = vm.member.kemuId[0];
			            axios({method: 'post',url: mainHttp + 'member/account/update.html',data: vm.member}).then(function(response){
			      		    if(response.data.code == 0){
			      		    	vm.loading = false;
			      		    	vm.$message({message: "修改成功",duration:500,type: 'success',onClose:function(){
			      		    		window.location.reload();
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
		      getCourses:function(){
		    	  axios.get(mainHttp + 'subject/getCourseCascaderTree.html').then(function(response){
		    		  vm.courses = response.data.result;
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
		      }
	    }
});
vm.getCourses();
vm.getMember();