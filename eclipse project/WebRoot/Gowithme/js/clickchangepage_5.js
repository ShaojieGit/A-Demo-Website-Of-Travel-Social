var current;  //全局变量

function changemenu(id) {
	var btn1 = document.getElementById("btn1");
    var btn2 = document.getElementById("btn2");
    var btn3 = document.getElementById("btn3");
    var btn4 = document.getElementById("btn4");
	var btn5 = document.getElementById("btn5");
	current = document.getElementById(id);
	btn1.style.color = '#999';
	btn2.style.color = '#999';
	btn3.style.color = '#999';
	btn4.style.color = '#999';
	btn5.style.color = '#999';
	current.style.color = '#F90';
	btn1.style.borderColor = '#999';
	btn2.style.borderColor = '#999';
	btn3.style.borderColor = '#999';
	btn4.style.borderColor = '#999';
	btn5.style.borderColor = '#999';
	current.style.borderColor = '#F90';
	switch(id) {
		case"btn1": changepage("page1");break;
		case"btn2": changepage("page2");break;
		case"btn3": changepage("page3");break;
		case"btn4": changepage("page4");break;
		case"btn5": changepage("page5");break;
	}
}

function changepage(page){
	document.getElementById("page1").style.display = 'none' ;
	document.getElementById("page2").style.display = 'none' ;
	document.getElementById("page3").style.display = 'none' ;
	document.getElementById("page4").style.display = 'none' ;
	document.getElementById("page5").style.display = 'none' ;
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
