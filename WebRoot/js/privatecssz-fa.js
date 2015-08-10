//相位方案
function changeSolution()
{
	$("#solutions").val();
	//console.log($("#solutions").val());
	window.open("solutionAction!solutions?soid="+$("#solutions").val(),"rightFrame");
}

 //3：红 2：黄 1：绿 0：灭 null：未知
var updateFang = {};
$(document).ready(function(){

			var datas = {};
			var imgsrc_new = "";//新的图片路径
			var img = "";
			var conflictStart = "";
			var conflictStr = "";
		$(".xwlb img").click(function(event) {
	
			var img = $(event.target);
			var imgsrc = img[0].src;//获得当前焦点的src
			
			//console.log("imgsrc:-----------------------"+imgsrc);
			var lastnumber = imgsrc.substring(imgsrc.indexOf(".png")-1,imgsrc.indexOf(".png"));//当前焦点的灯的颜色
			
			//console.log("lastnumber:-----------------------"+lastnumber);
			
			var headnumber = imgsrc.substring(imgsrc.indexOf(".png")-3,imgsrc.indexOf(".png")-1);//当前焦点的路口方位及左转还是右转灯	
			//console.log("headnumber:-----------------------"+headnumber);
			
			var headnumber1 = headnumber.substring(0,1);//当前焦点的路口方位
			var headnumber2 = headnumber.substring(1,2);//当前焦点的灯转向
			
			//根据灯的种类及当前颜色 来修改灯的颜色
			if(lastnumber == 0)
			{
					//当前灯色要改为绿色的时候要进行绿冲突判断
					
					//第一步：判断是那个路口的那个灯进行设置为绿色，则获得当前绿冲突的情况 放在conflictStr中
					if(headnumber=="00")
					{
						//console.log("获取了这里的00");
						conflictStr = $("#c1").val();
						//console.log("东左的冲突"+conflictStr);
					}
					else if(headnumber=="01")
					{
						//console.log("获取了这里的01");
						conflictStr = $("#c2").val();
					}
					else if(headnumber=="02")
					{
						//console.log("获取了这里的02");
						conflictStr = $("#c3").val();
					}
					else if(headnumber=="03")
					{
						//console.log("获取了这里的03");
						conflictStr = $("#c4").val();
					}
					else if(headnumber=="10")
					{
						//console.log("获取了这里的10");
						conflictStr = $("#c5").val();
					}
					else if(headnumber=="11")
					{
							//console.log("获取了这里的11");
						conflictStr = $("#c6").val();
					}
					else if(headnumber=="12")
					{
						//console.log("获取了这里的12");
						conflictStr = $("#c7").val();
					}
					else if(headnumber=="13")
					{
						//console.log("获取了这里的13");
						conflictStr = $("#c8").val();
					}
					else if(headnumber=="20")
					{
						//console.log("获取了这里的20");
						conflictStr = $("#c9").val();
					}
					else if(headnumber=="21")
					{
						//console.log("获取了这里的21");
						conflictStr = $("#c10").val();
					}else if(headnumber=="22")
					{
						//console.log("获取了这里的22");
						conflictStr = $("#c11").val();
					}else if(headnumber=="23")
					{
						//console.log("获取了这里的23");
						conflictStr = $("#c12").val();
					}else if(headnumber=="30")
					{
						//console.log("获取了这里的30");
						conflictStr = $("#c13").val();
					}else if(headnumber=="31")
					{
						//console.log("获取了这里的31");
						conflictStr = $("#c14").val();
					}else if(headnumber=="32")
					{
						//console.log("获取了这里的32");
						conflictStr = $("#c15").val();
					}else if(headnumber=="33")
					{
						//console.log("获取了这里的33");
						conflictStr = $("#c16").val();
					}
					//console.log("冲突:"+conflictStr);
					//第二部：分两种情况，一种为 id 一种为 class 解析conflictStr 并逐一找出冲突的灯当前的颜色是否为绿色如果为绿色则返回
					//console.log("img[0].id"+img[0].id);
					if(img[0].id==null||img[0].id =="")
					{
						//console.log("this is class");
						//class
						var imgclass= img[0].classList[0];
						conflictStart = imgclass.substring(0,imgclass.indexOf("_"));
						if(typeof(conflictStr) != "undefined"&&conflictStr!="")
						{
							var strs= conflictStr.split(","); //字符分割 
							//console.log("strs:-------------------------"+strs);
							for (i=0;i<strs.length-1;i++) 
							{ 
								var lightKind = strs[i].substring(strs[i].length-1,strs[i].length);
								console.log("lightKind:-------------------------"+lightKind);
								var imgNow = null;//当前与之比较的灯
								var currentLightNumber = 0;//当前与之比较的灯 的种类
								var srcNow = "";
								if(lightKind==3)
								{
									 imgNow = $("."+conflictStart+strs[i]);
									 console.log("lightKind==3=========",imgNow);
									 console.log("imgNow:---------------------------"+"#"+conflictStart+strs[i]);
									 srcNow = imgNow[0].currentSrc;
									 currentLightNumber = srcNow.substring(srcNow.indexOf(".png")-1,srcNow.indexOf(".png"));//当前比较灯的颜色
								}else
								{
									imgNow = $("#"+conflictStart+strs[i]);
									console.log("lightKind!=3=========",imgNow);
									console.log("imgNow:---------------------------"+"#"+conflictStart+strs[i]);
									srcNow = imgNow[0].currentSrc;
								    currentLightNumber = srcNow.substring(srcNow.indexOf(".png")-1,srcNow.indexOf(".png"));//当前比较灯的颜色
								}
								if(currentLightNumber==1)
								{
									alert(imgNow[0].alt+"与当前灯绿冲突");
									return;
								}
							}
							
						}
						
					}else
					{
						//id
						//console.log("this is id");
						var imgid = img[0].id;
						//console.log("imgid:-------------------------"+imgid);
						conflictStart = imgid.substring(0,imgid.indexOf("_"));
						//console.log("conflictStart:-------------------------"+conflictStart);
						if(typeof(conflictStr) != "undefined"&&conflictStr!="")
						{
							var strs= conflictStr.split(","); //字符分割 
							//console.log("strs:-------------------------"+strs);
							for (i=0;i<strs.length-1;i++) 
							{ 
								var lightKind = strs[i].substring(strs[i].length-1,strs[i].length);
								//console.log("lightKind:-------------------------"+lightKind);
								var imgNow = null;//当前与之比较的灯
								var currentLightNumber = 0;//当前与之比较的灯 的种类
								var srcNow = "";
								if(lightKind==3)
								{
									 imgNow = $("."+conflictStart+strs[i]);
									 //console.log("imgNow:---------------------------"+"."+conflictStart+strs[i]);
									 srcNow = imgNow[0].currentSrc;
									 currentLightNumber = srcNow.substring(srcNow.indexOf(".png")-1,srcNow.indexOf(".png"));//当前比较灯的颜色
								}else
								{
									imgNow = $("#"+conflictStart+strs[i]);
									srcNow = imgNow[0].currentSrc;
								    currentLightNumber = srcNow.substring(srcNow.indexOf(".png")-1,srcNow.indexOf(".png"));//当前比较灯的颜色
								}
								if(currentLightNumber==1)
								{
									alert(imgNow[0].alt+"与当前灯绿冲突");
									return;
								}
							} 
						}
					}
				
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
				
				updateFang[imgclass] = lastnumber;
				
				$("."+imgclass).attr("src",imgsrc_new);
			}else
			{
				var imgid = img[0].id;
				
				updateFang[imgid] = lastnumber;
				
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
			//console.log(imgsrc_new);
			var headnumber = imgsrc_new.substr(0,imgsrc_new.length-1);
			var lastnumber = imgsrc_new.substr(imgsrc_new.length-1,imgsrc_new.length);
			//console.log(headnumber);   //路口方位及左转还是右转灯
			//console.log(lastnumber);  //灯的颜色
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
		updateFang[id+"_2_3"] = 3;	
									
		$("."+id+"_0_3").attr("src","images/rod/l033.png");
		updateFang[id+"_0_3"] = 3;	
			
		$("."+id+"_3_3").attr("src","images/rod/l333.png");
		updateFang[id+"_3_3"] = 3;	
		
		$("."+id+"_3_3").attr("src","images/rod/l333.png");
		updateFang[id+"_3_3"] = 3;	
		
		
		$("#"+id+"_0_2").attr("src","images/rod/l023.png");
		updateFang[id+"_0_2"] = 3;	
		
		$("#"+id+"_0_2").attr("src","images/rod/l023.png");
		updateFang[id+"_0_2"] = 3;	
		
							
		$("#"+id+"_0_1").attr("src","images/rod/l013.png");
		updateFang[id+"_0_1"] = 3;	
		
		$("#"+id+"_0_0").attr("src","images/rod/l003.png");
		updateFang[id+"_0_0"] = 3;	
		
		$("#"+id+"_3_2").attr("src","images/rod/l323.png");
		updateFang[id+"_3_2"] = 3;
		
		$("#"+id+"_3_1").attr("src","images/rod/l313.png");
		updateFang[id+"_3_1"] = 3;
		
		$("#"+id+"_3_0").attr("src","images/rod/l303.png");
		updateFang[id+"_3_0"] = 3;
		
		$("#"+id+"_1_0").attr("src","images/rod/l103.png");
		updateFang[id+"_1_0"] = 3;
		
		$("#"+id+"_1_1").attr("src","images/rod/l113.png");
		updateFang[id+"_1_1"] = 3;
		
		$("#"+id+"_1_2").attr("src","images/rod/l123.png");
		updateFang[id+"_1_2"] = 3;
		
		$("#"+id+"_2_0").attr("src","images/rod/l203.png");
		updateFang[id+"_2_0"] = 3;
		
		$("#"+id+"_2_1").attr("src","images/rod/l213.png");
		updateFang[id+"_2_1"] = 3;
		
		$("#"+id+"_2_2").attr("src","images/rod/l223.png");
		updateFang[id+"_2_2"] = 3;
		
		$("."+id+"_1_3").attr("src","images/rod/l133.png");
		updateFang[id+"_1_3"] = 3;
		
		$("."+id+"_1_3").attr("src","images/rod/l133.png");
		updateFang[id+"_1_3"] = 3;
		
		$("."+id+"_2_3").attr("src","images/rod/l233.png");
		updateFang[id+"_2_3"] = 3;
		
		$("."+id+"_0_3").attr("src","images/rod/l033.png");
		updateFang[id+"_0_3"] = 3;
	
}

function clearAllLight(id)
{
	$("."+id+"_2_3").attr("src","images/rod/l230.png");
		updateFang[id+"_2_3"] = 0;	
									
		$("."+id+"_0_3").attr("src","images/rod/l030.png");
		updateFang[id+"_0_3"] = 0;	
			
		$("."+id+"_3_3").attr("src","images/rod/l330.png");
		updateFang[id+"_3_3"] = 0;	
		
		$("."+id+"_3_3").attr("src","images/rod/l330.png");
		updateFang[id+"_3_3"] = 0;	
		
		
		$("#"+id+"_0_2").attr("src","images/rod/l020.png");
		updateFang[id+"_0_2"] = 0;	
		
		$("#"+id+"_0_2").attr("src","images/rod/l020.png");
		updateFang[id+"_0_2"] = 0;	
		
							
		$("#"+id+"_0_1").attr("src","images/rod/l010.png");
		updateFang[id+"_0_1"] = 0;	
		
		$("#"+id+"_0_0").attr("src","images/rod/l000.png");
		updateFang[id+"_0_0"] = 0;	
		
		$("#"+id+"_3_2").attr("src","images/rod/l320.png");
		updateFang[id+"_3_2"] = 0;
		
		$("#"+id+"_3_1").attr("src","images/rod/l310.png");
		updateFang[id+"_3_1"] = 0;
		
		$("#"+id+"_3_0").attr("src","images/rod/l300.png");
		updateFang[id+"_3_0"] = 0;
		
		$("#"+id+"_1_0").attr("src","images/rod/l100.png");
		updateFang[id+"_1_0"] = 0;
		
		$("#"+id+"_1_1").attr("src","images/rod/l110.png");
		updateFang[id+"_1_1"] = 0;
		
		$("#"+id+"_1_2").attr("src","images/rod/l120.png");
		updateFang[id+"_1_2"] = 0;
		
		$("#"+id+"_2_0").attr("src","images/rod/l200.png");
		updateFang[id+"_2_0"] = 0;
		
		$("#"+id+"_2_1").attr("src","images/rod/l210.png");
		updateFang[id+"_2_1"] = 0;
		
		$("#"+id+"_2_2").attr("src","images/rod/l220.png");
		updateFang[id+"_2_2"] = 0;
		
		$("."+id+"_1_3").attr("src","images/rod/l130.png");
		updateFang[id+"_1_3"] = 0;
		
		$("."+id+"_1_3").attr("src","images/rod/l130.png");
		updateFang[id+"_1_3"] = 0;
		
		$("."+id+"_2_3").attr("src","images/rod/l230.png");
		updateFang[id+"_2_3"] = 0;
		
		$("."+id+"_0_3").attr("src","images/rod/l030.png");
		updateFang[id+"_0_3"] = 0;
}

function saveSolution()
{
	////console.log(updateFang);
	var msg = "";
	var currentsoid = $("#solutions").val();
	for(var prop in updateFang){
    if(updateFang.hasOwnProperty(prop)){
      //  //console.log('key is ' + prop +' and value is' + updateFang[prop]);
        msg = msg + prop+":"+ updateFang[prop]+",";
    	}
	}
		$.ajax({   
            url:'updateSolution',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "dates":msg,"soid":currentsoid},  
            traditional: true,  
            dataType:'json',
            error: function(msg)
            { //失败   
            	alert('发送命令失败');   
            },   
            success: function(msg)
            { //成功   
				if(msg!=null)
				{
					alert(msg.message);
				}else
				{
					alert('修改相位方案命令发送成功.');  
				}
				updateFang = {};
				msg = "";
				self.location.reload();
            }  
   	    });  
}