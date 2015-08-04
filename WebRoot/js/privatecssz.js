
function initPublicParam() {
	//css部分
	$("#xyxr").click(function () {
		if ($("#xyxr").attr("checked") == "checked") {
			$("#xr").show();
		} else {
			$("#xr").hide();
		}
	});
	$("#spetime").click(function () {
		if ($("#spetime").attr("checked") == "checked") {
			$("#spe").show();
		} else {
			$("#spe").hide();
		}
	});
	$("#suntime").click(function () {
		if ($("#suntime").attr("checked") == "checked") {
			$("#sunt").show();
		} else {
			$("#sunt").hide();
		}
	});
	
	

	//checkbox部分
	if ($("#xyxr").attr("value") == 1) {
		$("#xyxr").attr("checked", "checked");
		$("#xr").show();
	} else {
		$("#xr").hide();
	}
	if ($("#spetime").attr("value") == 1) {
		$("#spetime").attr("checked", "checked");
		$("#spe").show();
	} else {
		$("#spe").hide();
	}
	if ($("#suntime").attr("value") == 1) {
		$("#suntime").attr("checked", "checked");
		$("#sunt").show();
	} else {
		$("#sunt").hide();
	}
	
	
	
	//checkbox部分
	if ($("#mon").attr("value") == 1) {
		$("#mon").attr("checked", "checked");
	}
	if ($("#tue").attr("value") == 1) {
		$("#tue").attr("checked", "checked");
	}
	if ($("#wes").attr("value") == 1) {
		$("#wes").attr("checked", "checked");
	}
	if ($("#thir").attr("value") == 1) {
		$("#thir").attr("checked", "checked");
	}
	if ($("#fra").attr("value") == 1) {
		$("#fra").attr("checked", "checked");
	}
	if ($("#sata").attr("value") == 1) {
		$("#sata").attr("checked", "checked");
	}
	if ($("#sun").attr("value") == 1) {
		$("#sun").attr("checked", "checked");
	}
}


function cleanAll()
{
	$(':input','#publicparamform')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
}

function changeSolution()
{
	$("#solutions").val();
	console.log($("#solutions").val());
	window.open("solutionAction!solutions?soid="+$("#solutions").val(),"rightFrame");
}


//相位方案
var updatedatas = {};
$(document).ready(function(){

			var datas = {};
			var imgsrc_new = "";//新的图片路径
			var img = "";
			
	$("div img").click(function(event) {
	
			img = $(event.target);
			var imgsrc = img[0].src;//获得当前焦点的src
			var lastnumber = imgsrc.substring(imgsrc.indexOf(".png")-1,imgsrc.indexOf(".png"));//灯的颜色
			var headnumber = imgsrc.substring(imgsrc.indexOf(".png")-3,imgsrc.indexOf(".png")-1);//路口方位及左转还是右转灯
			var headnumber1 = headnumber.substring(0,1);//路口方位
			var headnumber2 = headnumber.substring(1,2);//灯转向
			
			//根据灯的种类及当前颜色 来修改灯的颜色
			if(lastnumber == 0)
			{
				lastnumber = 1;
				imgsrc_new = "images/rod/l"+headnumber+"1.png";
			}else if(lastnumber == 1)
			{
				lastnumber = 3;
				imgsrc_new = "images/rod/l"+headnumber+"3.png";
			}else if(lastnumber == 3)
			{
				lastnumber = 0;
				imgsrc_new = "images/rod/l"+headnumber+"0.png";
			}
			
			if(headnumber2==3)
			{
				var imgclass= img[0].classList[0];
				
				updatedatas[imgclass] = lastnumber;
				
				$("."+imgclass).attr("src",imgsrc_new);
			}else
			{
				var imgid = img[0].id;
				
				updatedatas[imgid] = lastnumber;
				
				img.attr("src",imgsrc_new);
			}
			/*
			
			end = imgsrc.indexOf(".png")-38;
			
			if(img[0].id==null||img[0].id =="")
			{
				var imgclass= img[0].classList[0];
				
				start = imgclass.substr(imgclass.length-2,imgclass.length);
				
			}else
			{
				var imgid = img[0].id;
				start = imgid.substr(imgid.length-2,imgid.length);
			}
			start = imgsrc.indexOf(start);
			
			imgsrc_new = imgsrc.substr(start,end);
			console.log(imgsrc_new);
			var headnumber = imgsrc_new.substr(0,imgsrc_new.length-1);
			var lastnumber = imgsrc_new.substr(imgsrc_new.length-1,imgsrc_new.length);
			console.log(headnumber);   //路口方位及左转还是右转灯
			console.log(lastnumber);  //灯的颜色
			if(lastnumber<3)
			{
				lastnumber = parseInt(lastnumber)+1;
				imgsrc_new = "images/rod/l"+headnumber+lastnumber+".png";
			}else
			{
				imgsrc_new = "images/rod/l"+headnumber+"0"+".png";
			}
			
			img.attr("src",imgsrc_new);
			*/
		});
		
		
});




function allRed(id)
{
	
		$("."+id+"_2_3").attr("src","images/rod/l233.png");
		updatedatas[id+"_2_3"] = 3;	
									
		$("."+id+"_0_3").attr("src","images/rod/l033.png");
		updatedatas[id+"_0_3"] = 3;	
			
		$("."+id+"_3_3").attr("src","images/rod/l333.png");
		updatedatas[id+"_3_3"] = 3;	
		
		$("."+id+"_3_3").attr("src","images/rod/l333.png");
		updatedatas[id+"_3_3"] = 3;	
		
		
		$("#"+id+"_0_2").attr("src","images/rod/l023.png");
		updatedatas[id+"_0_2"] = 3;	
		
		$("#"+id+"_0_2").attr("src","images/rod/l023.png");
		updatedatas[id+"_0_2"] = 3;	
		
							
		$("#"+id+"_0_1").attr("src","images/rod/l013.png");
		updatedatas[id+"_0_1"] = 3;	
		
		$("#"+id+"_0_0").attr("src","images/rod/l003.png");
		updatedatas[id+"_0_0"] = 3;	
		
		$("#"+id+"_3_2").attr("src","images/rod/l323.png");
		updatedatas[id+"_3_2"] = 3;
		
		$("#"+id+"_3_1").attr("src","images/rod/l313.png");
		updatedatas[id+"_3_1"] = 3;
		
		$("#"+id+"_3_0").attr("src","images/rod/l303.png");
		updatedatas[id+"_3_0"] = 3;
		
		$("#"+id+"_1_0").attr("src","images/rod/l103.png");
		updatedatas[id+"_1_0"] = 3;
		
		$("#"+id+"_1_1").attr("src","images/rod/l113.png");
		updatedatas[id+"_1_1"] = 3;
		
		$("#"+id+"_1_2").attr("src","images/rod/l123.png");
		updatedatas[id+"_1_2"] = 3;
		
		$("#"+id+"_2_0").attr("src","images/rod/l203.png");
		updatedatas[id+"_2_0"] = 3;
		
		$("#"+id+"_2_1").attr("src","images/rod/l213.png");
		updatedatas[id+"_2_1"] = 3;
		
		$("#"+id+"_2_2").attr("src","images/rod/l223.png");
		updatedatas[id+"_2_2"] = 3;
		
		$("."+id+"_1_3").attr("src","images/rod/l133.png");
		updatedatas[id+"_1_3"] = 3;
		
		$("."+id+"_1_3").attr("src","images/rod/l133.png");
		updatedatas[id+"_1_3"] = 3;
		
		$("."+id+"_2_3").attr("src","images/rod/l233.png");
		updatedatas[id+"_2_3"] = 3;
		
		$("."+id+"_0_3").attr("src","images/rod/l033.png");
		updatedatas[id+"_0_3"] = 3;
	
}

function clearAllLight(id)
{
	$("."+id+"_2_3").attr("src","images/rod/l230.png");
		updatedatas[id+"_2_3"] = 0;	
									
		$("."+id+"_0_3").attr("src","images/rod/l030.png");
		updatedatas[id+"_0_3"] = 0;	
			
		$("."+id+"_3_3").attr("src","images/rod/l330.png");
		updatedatas[id+"_3_3"] = 0;	
		
		$("."+id+"_3_3").attr("src","images/rod/l330.png");
		updatedatas[id+"_3_3"] = 0;	
		
		
		$("#"+id+"_0_2").attr("src","images/rod/l020.png");
		updatedatas[id+"_0_2"] = 0;	
		
		$("#"+id+"_0_2").attr("src","images/rod/l020.png");
		updatedatas[id+"_0_2"] = 0;	
		
							
		$("#"+id+"_0_1").attr("src","images/rod/l010.png");
		updatedatas[id+"_0_1"] = 0;	
		
		$("#"+id+"_0_0").attr("src","images/rod/l000.png");
		updatedatas[id+"_0_0"] = 0;	
		
		$("#"+id+"_3_2").attr("src","images/rod/l320.png");
		updatedatas[id+"_3_2"] = 0;
		
		$("#"+id+"_3_1").attr("src","images/rod/l310.png");
		updatedatas[id+"_3_1"] = 0;
		
		$("#"+id+"_3_0").attr("src","images/rod/l300.png");
		updatedatas[id+"_3_0"] = 0;
		
		$("#"+id+"_1_0").attr("src","images/rod/l100.png");
		updatedatas[id+"_1_0"] = 0;
		
		$("#"+id+"_1_1").attr("src","images/rod/l110.png");
		updatedatas[id+"_1_1"] = 0;
		
		$("#"+id+"_1_2").attr("src","images/rod/l120.png");
		updatedatas[id+"_1_2"] = 0;
		
		$("#"+id+"_2_0").attr("src","images/rod/l200.png");
		updatedatas[id+"_2_0"] = 0;
		
		$("#"+id+"_2_1").attr("src","images/rod/l210.png");
		updatedatas[id+"_2_1"] = 0;
		
		$("#"+id+"_2_2").attr("src","images/rod/l220.png");
		updatedatas[id+"_2_2"] = 0;
		
		$("."+id+"_1_3").attr("src","images/rod/l130.png");
		updatedatas[id+"_1_3"] = 0;
		
		$("."+id+"_1_3").attr("src","images/rod/l130.png");
		updatedatas[id+"_1_3"] = 0;
		
		$("."+id+"_2_3").attr("src","images/rod/l230.png");
		updatedatas[id+"_2_3"] = 0;
		
		$("."+id+"_0_3").attr("src","images/rod/l030.png");
		updatedatas[id+"_0_3"] = 0;
}

function saveSolution()
{
	console.log(updatedatas);
	var msg = "";
	
	for(var prop in updatedatas){
    if(updatedatas.hasOwnProperty(prop)){
        console.log('key is ' + prop +' and value is' + updatedatas[prop]);
        msg = msg + prop+":"+ updatedatas[prop]+",";
    	}
	}
	console.log(msg);
		$.ajax({   
            url:'update',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "dates":updatedatas},  
            traditional: true,  
            error: function(msg)
            { //失败   
            	alert('发送命令失败');   
            },   
            success: function(msg)
            { //成功   
				//alert('发送命令成功');   
            }  
   	    });  
}