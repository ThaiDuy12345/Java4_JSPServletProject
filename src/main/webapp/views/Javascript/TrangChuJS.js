function loadDisplay(d){
	alert(d);
	if(d === 0){
		changeQuanLyComponent(document.getElementById('mainButton'));
	}else{
		changeQuanLyComponent(document.getElementById('userButton'));
	}
}
function categoryPress(){
	document.getElementById("bodyCategory").classList.add("peaceOut");
	var bt = document.getElementsByClassName("displayButton");
	for(var i = 0;i < bt.length ;i++){
		bt[i].style.transition = "0.3s color 0.3s";
		bt[i].classList.add("peaceOutButton");
	}
}
function categoryOut(){
	document.getElementById("bodyCategory").classList.remove("peaceOut");
	var bt = document.getElementsByClassName("displayButton");
	for(var i = 0;i < bt.length ;i++){
		bt[i].style.transition = "0.1s";
		bt[i].classList.remove("peaceOutButton");
	}
}
function categoryButtonPress(bt){
	bt.classList.add("pressingButton");
}
function categoryButtonOut(bt){
	bt.classList.remove("pressingButton");
}
function changeQuanLyComponent(bt){
	var val = bt.value;
	if(val === "user"){
		document.getElementById("userComponent").style.display = "block";
		document.getElementById("mainComponent").style.display = "none";
		document.getElementById("videoComponent").style.display = "none";
	}else if(val === "video"){
		document.getElementById("videoComponent").style.display = "block";
		document.getElementById("userComponent").style.display = "none";
		document.getElementById("mainComponent").style.display = "none";
	}else {
		document.getElementById("mainComponent").style.display = "block";
		document.getElementById("videoComponent").style.display = "none";
		document.getElementById("userComponent").style.display = "none";
	}
}