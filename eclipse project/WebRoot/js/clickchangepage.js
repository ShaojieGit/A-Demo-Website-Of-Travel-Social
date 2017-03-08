var current = document.getElementById("btn1") ;  //全局变量

function changemenu(id) {
	var btn1 = document.getElementById("btn1");
    var btn2 = document.getElementById("btn2");
    var btn3 = document.getElementById("btn3");
    var btn4 = document.getElementById("btn4");
	current = document.getElementById(id);
	btn1.style.color = '#999';
	btn2.style.color = '#999';
	btn3.style.color = '#999';
	btn4.style.color = '#999';
	current.style.color = '#F90';
	btn1.style.borderColor = '#999';
	btn2.style.borderColor = '#999';
	btn3.style.borderColor = '#999';
	btn4.style.borderColor = '#999';
	current.style.borderColor = '#F90';
	switch(id) {
		case"btn1": changepage("page1");break;
		case"btn2": changepage("page2");break;
		case"btn3": changepage("page3");break;
		case"btn4": changepage("page4");break;
	}
}

function changepage(page){
	document.getElementById("page1").style.display = 'none' ;
	document.getElementById("page2").style.display = 'none' ;
	document.getElementById("page3").style.display = 'none' ;
	document.getElementById("page4").style.display = 'none' ;
	document.getElementById(page).style.display = 'block' ;
}

function overcolor(id) {
	var id = document.getElementById(id);
	id.style.color = '#F90';
	id.style.borderColor = '#F90';
}

function outcolor(id) {
	var id = document.getElementById(id);
	if(id != current){
	id.style.color = '#999';
	id.style.borderColor = '#999';
	}
}

function changemore(){
	var idcontent = document.getElementById("idcontent");
	var more = document.getElementById("more");
	if(idcontent.style.overflow == 'hidden') {
		idcontent.style.overflow = 'visible' ;
		idcontent.style.height = 'auto' ;
		more.value = '收起' ;
	}
	else {
		idcontent.style.overflow = 'hidden' ;
		idcontent.style.height = '250px' ;
		more.value = '更多' ;
	}
}
