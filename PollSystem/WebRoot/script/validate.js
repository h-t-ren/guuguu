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
			titlewarning.innerHTML = "���ⲻ��Ϊ��!";
			object.focus();
		}else if(x.length>30){
			titlewarning.innerHTML = "���ⲻ�ܳ���30���ַ���";
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
			descriptionwarning.innerHTML = "�������ܳ���50���ַ���";
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
			namewarning.innerHTML = "��������Ϊ�գ�";
			object.focus();
		}else if(x.length > 25){
			namewarning.innerHTML = "�������ܳ���25���ַ���";
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
			emailwarning.innerHTML = "�����ַ���ܳ���50���ַ���";
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
			optionwarning.innerHTML = "������ѡһ��ѡ�";
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