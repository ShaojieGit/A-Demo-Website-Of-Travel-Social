function checkblank() {
	var username = document.getElementById("username");
	var userpassw = document.getElementById("userpassw");
	if(username.value == "" || userpassw.value == "") {
		document.write("信息不完整！");
		return;
	}
}

function checkregister() {
	var username = document.getElementById("username");
	var userpassw = document.getElementById("userpassw");
	var userpasswagain = document.getElementById("userpasswagain");
	var usernameerror = document.getElementById("usernameerror");
	var userpasswerror = document.getElementById("userpasswerror");
	var userpasswagainerror = document.getElementById("userpasswagainerror");
	if(username.value == ""||username.value.length>20) {
		usernameerror.style.display = 'block';
		userpasswerror.style.display = 'none';
		userpasswagainerror.style.display = 'none';
		return false;
	}
	else if(userpassw.value == ""||username.length>20) {
		usernameerror.style.display = 'none';
		userpasswerror.style.display = 'block';
		userpasswagainerror.style.display = 'none';
		return false;
	}
	else if(userpassw.value != userpasswagain.value) {
		usernameerror.style.display = 'none';
		userpasswerror.style.display = 'none';
		userpasswagainerror.style.display = 'block';
		return false;
	}
	else {
		usernameerror.style.display = 'none';
		userpasswerror.style.display = 'none';
		userpasswagainerror.style.display = 'none';
		return true;
	}
}

function allow() {
	if(checkregister() == false)
	  return false;
	else
	  return true;
}
