var currentfocus;
var currnetfocuscomment;

function focusin(id) {
	currentfocus = document.getElementById(id);
	currentfocus.style.borderColor = '#F90';
}

function focusout(id) {
	currentfocus = document.getElementById(id);
	currentfocus.style.borderColor = '#999';
	currentfocus = '';
}

function overbordercolor(id) {
	var id = document.getElementById(id);
	id.style.borderColor = '#F90';
}

function outbordercolor(id) {
	var id = document.getElementById(id);
	if(id != currentfocus) {
	id.style.borderColor = '#999';
	}
}



/*评论*/
function focusincomment(name) {
	currnetfocuscomment = document.getElementsByName(name);
	currnetfocuscomment.style.borderColor = '#F90';
}

function focusoutcomment(name) {
	currnetfocuscomment = document.getElementsByName(name);
	currnetfocuscomment.style.borderColor = '#999';
	currnetfocuscomment = '';
}

function overbordercolorcomment(name) {
	var name = document.getElementsByName(name);
	name.style.borderColor = '#F90';
}

function outbordercolorcomment(name) {
	var name = document.getElementsByName(name);
	if(name != currnetfocuscomment) {
	name.style.borderColor = '#999';
	}
}