//script for Burger Shop

var obj={
	'veg':'Veg Burger','nonveg':'Non-Veg Burger',
	'thin':10,'hard':10,'soft':12,
	'panner':100,'chicken':120,'turkey':130,
	'cucumber':25,'tomato':20,'cabbage':20,'meat':45
}
function resetValue(){
flag1=false;
flag2=false;
flag3=false;
type='';
amount=0;
toppingAmount=0;
curst='';
filling='';
offer=0;
toppings=[];
temp=[];
offerTopping='';
random=0;
}

var flag1=false;
var flag2=false;
var flag3=false;
var type='';
var amount=0;
var toppingAmount=0;
var curst='';
var filling='';
var offer=0;
var toppings=[];
var temp=[];
var offerTopping='';
var random=0;

function makeCall(toppingsSize){	
		setCurst();
		setFilling();
		setToppings(toppingsSize);
		checkFlag();
}

function burgerOfTheDay(){
	random= 1 + Math.floor(Math.random() * 6);
	type='nonveg';
	switch(random){
		case 1:{
			curst='hard',filling='turkey',toppings=['tomato','meat'];
			makeCall(toppings.length);
			break;
		}
		case 2:{
			curst='hard',filling='chicken',toppings=['cabbage','tomato','meat'];
			makeCall(toppings.length);
			break;
		}
		case 3:{
			type='veg';
			curst='soft',filling='panner',toppings=['cucumber','tomato'];
			makeCall(toppings.length);
			break;
		}
		case 4:{
			curst='thin',filling='turkey',toppings=['tomato','meat'];
			makeCall(toppings.length);
			break;
		}
		case 5:{
			curst='hard',filling='panner',toppings=['tomato','meat','cucumber'];
			makeCall(toppings.length);
			break;
		}
		case 6:{
			curst='soft',filling='turkey',toppings=['tomato','meat','cucumber','cabbage'];
			makeCall(toppings.length);
			break;
		}
		case 7:{
			type='nonveg';
			curst='thin',filling='panner',toppings=['tomato','cucumber','cabbage'];
			makeCall(toppings.length);
			break;
		}
	}
	
}


function appendElements(... elements){
	$('.contentDiv').append(elements);
}

function makeOrder(){
	var option1=$('<button></button>').attr({
		id:'ownBurger' ,onclick:'makeOwnBurger()'
		}).text('Create own Burger').css('margin-right','10px');
		
	var option2=$('<button></button>').attr({
		id:'burgerOfTheDay' ,onclick:'burgerOfTheDay()'
		}).text('Burger Of The Day').css('margin-right','10px');
	var backToHome=$('<button></button>').attr({
		onclick:'cancelOrder()'
		}).text('Back');
		
	$('.contentDiv').empty();
	appendElements(option1,option2,backToHome);
}

function makeOwnBurger(){
	var typeOption1=$('<button></button>').attr({
		id:'veg', onclick:'makeBurger("veg")'
		}).text('Veg Burger').css('margin-right','10px');
		
	var typeOption2=$('<button></button>').attr({
		id:'nonVeg', onclick:'makeBurger("nonveg")'
		}).text('Non-Veg Burger').css('margin-right','10px');
	var backToOrder=$('<button></button>').attr({
		onclick:'makeOrder()'
		}).text('Back');	
		
	$('.contentDiv').empty();
	appendElements(typeOption1,typeOption2,backToOrder);
}

function makeBurger(selectedType){
	type=selectedType;
	$('.contentDiv').empty();
	createCurstMenu();
	createFillingsMenu();		
	createToppingsMenu();
}

function createCurstMenu(){
	var heading1=$('<h2/>').text('Select curst');
	var curstOption1=$('<input>').attr({
		type:'radio',value:'thin',name:'curst',id:'curst1'
		});
	var curstLabel1=$('<label/>').attr('for','curst1').text('Thin - ₹10');
	
	var curstOption2=$('<input>').attr({
		type:'radio',value:'hard',name:'curst',id:'curst2'
		});
	var curstLabel2=$('<label/>').attr('for','curst2').text('Hard - ₹10');
	
	var curstOption3=$('<input>').attr({
		type:'radio',value:'soft',name:'curst',id:'curst3'
		});
	var curstLabel3=$('<label/>').attr('for','curst3').text('Soft - ₹12');
	var lineBreak=$('<br>');
	
	appendElements(heading1,lineBreak,curstOption1,curstLabel1,curstOption2,curstLabel2,curstOption3,curstLabel3,lineBreak);
}

function createFillingsMenu(){
	var heading2=$('<h2/>').text('Select Filling');
	var fillings1=$('<input>').attr({
			type:'radio',value:'panner',name:'filling'
			});
	var fillingLabel1=$('<label/>').text('Paneer Tikka - ₹100');
	
	var fillings2=$('<input>').attr({
			type:'radio',value:'chicken',name:'filling'
			});
	var fillingLabel2=$('<label/>').text('Chicken Tikka - ₹120');
	
	var fillings3=$('<input>').attr({
			type:'radio',value:'turkey',name:'filling'
			});
	var fillingLabel3=$('<label/>').text('Turkey Meat - ₹130');
	var lineBreak=$('<br>');
	
	if(type=='veg'){
		appendElements(heading2,lineBreak,fillings1,fillingLabel1,lineBreak);
	}
	else{
		appendElements(heading2,lineBreak,fillings1,fillingLabel1,fillings2,fillingLabel2,fillings3,fillingLabel3,lineBreak);
	}
}

function createToppingsMenu(){
	var heading3=$('<h2/>').text('Select Toppings');
	var toppings1=$('<input>').attr({
		type:'checkbox',value:'cucumber'
		});
	var label1=$('<label/>').text('Cucumber - ₹25');
	
	var toppings2=$('<input>').attr({
		type:'checkbox',value:'tomato'
		});
	var label2=$('<label/>').text('Tomato - ₹20');
	
	var toppings3=$('<input>').attr({
		type:'checkbox',value:'cabbage'
		});
	var label3=$('<label/>').text('Cabbage - ₹20');
	
	var toppings4=$('<input>').attr({
		type:'checkbox',value:'meat'
		});
	var label4=$('<label/>').text('Meat strip - ₹45');
	
	var backToPrevious=$('<button></button>').attr({
		onclick:'makeOwnBurger()'
		}).text('Back').css('margin-right','10px');
	
	var submitButton=$('<button></button>').attr({
		onclick:'placeOrder()'
		}).text('Next').css('margin-right','10px');
	var lineBreak=$('<br>');
	
	if(type=='veg'){
		appendElements(heading3,lineBreak,toppings1,label1,toppings2,label2,toppings3,label3,lineBreak,backToPrevious,submitButton);
	}
	else{
		appendElements(heading3,lineBreak,toppings1,label1,toppings2,label2,toppings3,label3,toppings4,label4,lineBreak,backToPrevious,submitButton);
	}
}

function setCurst(){
	amount+=obj[curst];
	flag1=true;
}
function setFilling(){
	amount+=obj[filling];
	flag2=true;
}

function setToppings(sizeOfMap){
	if(sizeOfMap==1){			
			toppingAmount+=obj[toppings[0]];
		}
		else{
			for(let i=0;i<sizeOfMap;i++){
				toppingAmount+=obj[toppings[i]];
				if(toppings[i]=='cabbage' || toppings[i]=='tomato'){
					temp.push(toppings[i]);
				}
			}
		}
		if(sizeOfMap==3){
			toppingAmount-=20;
			offer=20;
			offerTopping=temp[0];
		}
		flag3=true;
		amount+=toppingAmount;
}

function placeOrder(){
	curst=$('input[name="curst"]:checked').val();
	if(curst){
		setCurst();
	}
	else{
		alert("Select the curst");
	}
	filling=$('input[name="filling"]:checked').val();
	if(filling){
		setFilling();
	}
	else{
		alert("Select the Filling");
	}
	$(':checkbox:checked').each(function(i){
		toppings[i]=$(this).val();
	});
	let length=toppings.length;
	if(length>0 && length<4){
		setToppings(length);
	}
	else if(length==0){
		alert("Select toppings");
	}
	else{
		alert("Maximum three toppings only");
	}
	checkFlag();
}

function checkFlag(){
	if(flag1 && flag2 && flag3){
		displayInbox();
	}
}

function displayInbox(){	
	$('.contentDiv').empty();
	var outHeader=$('<h2/>').text('Your Order');
	var outType=$('<h4/>').text('You ordered '+obj[type]);
	var outCurst=$('<h4/>').text('Curst is '+curst+' 	 Price is '+obj[curst]);
	var outFilling=$('<h4/>').text('Filling is '+filling+'	  Price is ' +obj[filling]);
	if(offerTopping){
		var outToppings=$('<h4/>').text('Toppings is '+toppings+','+offerTopping+' is free		Price is '+toppingAmount);		
	}
	else{
		var outToppings=$('<h4/>').text('Toppings '+toppings+' 	 Price is '+toppingAmount);
	}
	var outTotal=$('<h4/>').text('Total Amount is   	'+amount);
	var submitButton=$('<button/>').attr({
		id:'submitButton',onclick:'confirmOrder()',
	}).text('Confirm');
	var cancelButton=$('<button/>').attr({
		class:'outButton',onclick:'cancelOrder()',
	}).text('Cancel');
	var lineBreak=$('<br>');
	appendElements(outHeader,outType,outCurst,outFilling,outToppings,outTotal,lineBreak,cancelButton,submitButton);
}

function cancelOrder(){
	resetValue();
	location.reload();
}
function confirmOrder(){
	$('#submitButton').hide();
	var success=$('<h4/>').text('Your Order is placed SuccessFully.').css("color",'green');
	$('.outButton').before(success).text('Home');
}