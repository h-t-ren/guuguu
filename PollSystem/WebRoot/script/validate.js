	var titlewarning;
	var descriptionwarning;
	var namewarning;
	var emailwarning;
	

	function check(){
	
		
		var title = document.form1.title.value;
		var name = document.form1.initialname.value;
		//alert((titlewarning.innerHTML!="" && namewarning.innerHTML != ""));
		if(title != "" && name != ""){
			if(namewarning.innerHTML == "" && namewarning.innerHTML == "" && descriptionwarning.innerHTML == ""&& titlewarning.innerHTML == "" )
				return true;
		}
		//alert(namewarning.innerHTML == "" && namewarning.innerHTML == "" && descriptionwarning.innerHTML == ""&& titlewarning.innerHTML == "" )
		return false;
		
	}
	function checkTitle(object){
		titlewarning = document.getElementById("titlewarning");
		var x = object.value;
		x = trim(x);
		if(x == ""){
			titlewarning.innerHTML = "标题不能为空!";
			object.focus();
		}else if(x.length>30){
			titlewarning.innerHTML = "标题不能超过30个字符！";
			object.focus();
		}else{
			titlewarning.innerHTML = "";
		}
		
	}
	
	function checkDesc(object){
		 descriptionwarning = document.getElementById("descriptionwarning");
		var x = object.value;
		x = trim(x);
		if(x.length>50){
			descriptionwarning.innerHTML = "描述不能超过50个字符！";
			object.focus();
		}else{
			descriptionwarning.innerHTML = "";
		}
	}
	function checkName(object){
		 namewarning = document.getElementById("namewarning");
		var x = object.value;
		x = trim(x);
		if(x == ""){
			namewarning.innerHTML = "姓名不能为空！";
			object.focus();
		}else if(x.length > 25){
			namewarning.innerHTML = "姓名不能超过25个字符！";
			object.focus();
		}else{
			namewarning.innerHTML = "";
		}
	}
	function checkEmail(object){
		 emailwarning = document.getElementById("emailwarning");
		var x = object.value;
		x = trim(x);
		if(x.length > 50){
			emailwarning.innerHTML = "邮箱地址不能超过50个字符！";
			object.focus();
		}else{
			emailwarning.innerHTML = "";
		}
	}
	
	function checkOptions(){
		var x = document.all.optionid;
		var juge = 0;
		for(var i=0;i<x.length;i++){
			var value = trim(x[i].value);
			if(value!=""){
				juge = 1;
				break;
			}
		}
		if(juge == 1){;
			return true;
		}else{
			var optionwarning = document.getElementById("optionwarning");
			optionwarning.innerHTML = "至少填选一个选项！";
			return false;
		}
	}
	
	function trim(str){
	
		if(str == null){
			return "";
		}else{
			str = str.replace(/^\s*\s$/g,"");
			return str;
		}
	}