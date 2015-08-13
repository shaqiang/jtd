//时间段参数
function changeTimeSelect()
{
	var timetype = $("#timetype").val();
	var orderid = $("#orderid").val();
	var mklid = $("#mklid").val();
	location.href = "greenroadAction!tq?timetype="+timetype+"&orderid="+orderid+"&mklid="+mklid;
}


function deleteTqLine()
{
	var mklid = $("#mklid").val();
	$.ajax({   
	            url:'deleteTqLine',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式     
	 			data: { "mklid":mklid},
	            error: function(msg)
	            { //失败   
	            	alert('删除失败');   
	            },   
	            success: function(msg)
	            { //成功   
	            	alert('删除成功');   
	            	location.reload();  //刷新本页
	            }  
    	    });   	
}

function test()
{
	console.log("test");
}


$(document).ready(function(){

	
	//设置相位执行时间
		$(".dfinput_tqtime").on("blur",function(){
				var	time = parseInt($(this).val());
				var id  = $(this).attr("id");
				if(id.indexOf('gtime')!=-1)
				{
					if(isNaN(time)||time>99||time<1)
					{
						alert("绿灯时间设置不正确,请重新设置.");
						$(this).val(1);//恢复原值
						return ;
					}
					
				}if(id.indexOf('ytime')!=-1)
				{
					if(isNaN(time)||time>9||time<3)
					{
						alert("黄灯时间设置不正确,请重新设置.");
						$(this).val(3);//恢复原值
						return ;
					}
				}if(id.indexOf('rtime')!=-1)
				{
					if(isNaN(time)||time>9||time<3)
					{
						alert("红灯时间设置不正确,请重新设置.");
						$(this).val(3);//恢复原值
						return ;
					}
				}
				$(this).val(time);
		});
		
});



//3：红 2：黄 1：绿 0：灭 null：未知
var updateFang = {};
$(document).ready(function(){

			var datas = {};
			var imgsrc_new = "";//新的图片路径
			var conflictVO = null;
			var img = "";
			var conflictStart = "";
			var conflictStr = "";
			for(var i=0;i<sigVOs.length;i++)
			{
				allRed(sigVOs[i].number);
			}
		$(".picbox img").click(function(event) {
			var img = $(this);
			console.log(img);
			
			var imgsrc = img[0].src;//获得当前焦点的src
			
			var lastnumber = imgsrc.substring(imgsrc.indexOf(".png")-1,imgsrc.indexOf(".png"));//当前焦点的灯的颜色
			var headnumber = imgsrc.substring(imgsrc.indexOf(".png")-3,imgsrc.indexOf(".png")-1);//当前焦点的路口方位及左转还是右转灯	
			
			var headnumber1 = headnumber.substring(0,1);//当前焦点的路口方位
			var headnumber2 = headnumber.substring(1,2);//当前焦点的灯转向
			
			//根据灯的种类及当前颜色 来修改灯的颜色
			if(lastnumber == 0)
			{
					//当前灯色要改为绿色的时候要进行绿冲突判断
					//第二部：分两种情况，一种为 id 一种为 class 解析conflictStr 并逐一找出冲突的灯当前的颜色是否为绿色如果为绿色则返回
					if(img[0].id==null||img[0].id =="")
					{
						//class
						var imgclass= img[0].classList[0];
						conflictStart = imgclass.substring(0,imgclass.indexOf("_"));
						for(var i=0;i<conflictVOs.length;i++)
						{
							if(conflictVOs[i].sid==conflictStart)
							{
								//console.log("冲突:"+conflictVOs[i].c_00);
								 if(headnumber=="00")
								{
									//console.log("获取了这里的00");
									conflictStr = conflictVOs[i].c_00;
								}
								else if(headnumber=="01")
								{
									//console.log("获取了这里的01");
									conflictStr = conflictVOs[i].c_01;
								}
								else if(headnumber=="02")
								{
									//console.log("获取了这里的02");
									conflictStr = conflictVOs[i].c_02;
								}
								else if(headnumber=="03")
								{
									//console.log("获取了这里的03");
									conflictStr = conflictVOs[i].c_03;
								}
								else if(headnumber=="10")
								{
									//console.log("获取了这里的10");
									conflictStr = conflictVOs[i].c_10;
								}
								else if(headnumber=="11")
								{
									//console.log("获取了这里的11");
									conflictStr = conflictVOs[i].c_11;
								}
								else if(headnumber=="12")
								{
									//console.log("获取了这里的12");
									conflictStr = conflictVOs[i].c_12;
								}
								else if(headnumber=="13")
								{
									//console.log("获取了这里的13");
									conflictStr = conflictVOs[i].c_13;
								}
								else if(headnumber=="20")
								{
									//console.log("获取了这里的20");
									conflictStr = conflictVOs[i].c_20;
								}
								else if(headnumber=="21")
								{
									//console.log("获取了这里的21");
									conflictStr = conflictVOs[i].c_21;
								}else if(headnumber=="22")
								{
									//console.log("获取了这里的22");
									conflictStr = conflictVOs[i].c_22;
								}else if(headnumber=="23")
								{
									//console.log("获取了这里的23");
									conflictStr = conflictVOs[i].c_23;
								}else if(headnumber=="30")
								{
									//console.log("获取了这里的30");
									conflictStr = conflictVOs[i].c_30;
								}else if(headnumber=="31")
								{
									//console.log("获取了这里的31");
									conflictStr = conflictVOs[i].c_31;
								}else if(headnumber=="32")
								{
									//console.log("获取了这里的32");
									conflictStr = conflictVOs[i].c_32;
								}else if(headnumber=="33")
								{
									//console.log("获取了这里的33");
									conflictStr = conflictVOs[i].c_33;
								}
							}
						}
							
							
						if(typeof(conflictStr) != "undefined"&&conflictStr!="")
						{
							var strs= conflictStr.split(","); //字符分割 
							for (i=0;i<strs.length-1;i++) 
							{ 
								var lightKind = strs[i].substring(strs[i].length-1,strs[i].length);
								var imgNow = null;//当前与之比较的灯
								var currentLightNumber = 0;//当前与之比较的灯 的种类
								var srcNow = "";
								if(lightKind==3)
								{
									 imgNow = $("."+conflictStart+strs[i]);
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
					}else
					{
						//id
						
						
						var imgid = img[0].id;
						conflictStart = imgid.substring(0,imgid.indexOf("_"));
						
						console.log(conflictStart);
						
						
						for(var i=0;i<conflictVOs.length;i++)
						{
							if(conflictVOs[i].number==conflictStart)
							{
								//console.log("冲突:"+conflictVOs[i].c_00);
								 if(headnumber=="00")
								{
									//console.log("获取了这里的00");
									conflictStr = conflictVOs[i].c_00;
								}
								else if(headnumber=="01")
								{
									//console.log("获取了这里的01");
									conflictStr = conflictVOs[i].c_01;
								}
								else if(headnumber=="02")
								{
									//console.log("获取了这里的02");
									conflictStr = conflictVOs[i].c_02;
								}
								else if(headnumber=="03")
								{
									//console.log("获取了这里的03");
									conflictStr = conflictVOs[i].c_03;
								}
								else if(headnumber=="10")
								{
									//console.log("获取了这里的10");
									conflictStr = conflictVOs[i].c_10;
								}
								else if(headnumber=="11")
								{
									//console.log("获取了这里的11");
									conflictStr = conflictVOs[i].c_11;
								}
								else if(headnumber=="12")
								{
									//console.log("获取了这里的12");
									conflictStr = conflictVOs[i].c_12;
								}
								else if(headnumber=="13")
								{
									//console.log("获取了这里的13");
									conflictStr = conflictVOs[i].c_13;
								}
								else if(headnumber=="20")
								{
									//console.log("获取了这里的20");
									conflictStr = conflictVOs[i].c_20;
								}
								else if(headnumber=="21")
								{
									//console.log("获取了这里的21");
									conflictStr = conflictVOs[i].c_21;
								}else if(headnumber=="22")
								{
									//console.log("获取了这里的22");
									conflictStr = conflictVOs[i].c_22;
								}else if(headnumber=="23")
								{
									//console.log("获取了这里的23");
									conflictStr = conflictVOs[i].c_23;
								}else if(headnumber=="30")
								{
									//console.log("获取了这里的30");
									conflictStr = conflictVOs[i].c_30;
								}else if(headnumber=="31")
								{
									//console.log("获取了这里的31");
									conflictStr = conflictVOs[i].c_31;
								}else if(headnumber=="32")
								{
									//console.log("获取了这里的32");
									conflictStr = conflictVOs[i].c_32;
								}else if(headnumber=="33")
								{
									//console.log("获取了这里的33");
									conflictStr = conflictVOs[i].c_33;
								}
							}
						}
						
						console.log("conflictStr"+conflictStr);
						if(typeof(conflictStr) != "undefined"&&conflictStr!="")
						{
						
							var strs= conflictStr.split(","); //字符分割 
							for (i=0;i<strs.length-1;i++) 
							{ 
								var lightKind = strs[i].substring(strs[i].length-1,strs[i].length);
								var imgNow = null;//当前与之比较的灯
								var currentLightNumber = 0;//当前与之比较的灯 的种类
								var srcNow = "";
								if(lightKind==3)
								{
									 imgNow = $("."+conflictStart+strs[i]);
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
		});
		
		
});

function saveControl()
{
	var msg = "";
	for(var prop in updateFang){
    if(updateFang.hasOwnProperty(prop)){
        //console.log('key is ' + prop +' and value is' + updateFang[prop]);
        msg = msg + prop+":"+ updateFang[prop]+",";
    	}
	}
	var sigSize = sigVOs.length;
	//console.log("sigSize:"+sigSize);
	var time = $(".dfinput_tqtime");
	var gtime = '';
	var ytime = '';
	var rtime = '';
	
	time.each(function(){
		for(var i=0;i<sigSize;i++)
		{
			var number = sigVOs[i].number;//获得当前信号机id
			var thisid = $(this).attr("id");//获得当前time id
			//console.log(thisid);
			var gid = number+"_gtime";//组装id
			var yid = number+"_ytime";//组装id
			var rid = number+"_rtime";//组装id
			if(thisid==gid)
			{
				gtime = gtime + number+"_"+$(this).val()+",";
			}
			if(thisid==yid)
			{
				ytime = ytime + number+"_"+$(this).val()+",";
			}
			if(thisid==rid)
			{
				rtime = rtime + number+"_"+$(this).val()+",";
			}
		}
	
	});
	//console.log(msg);
	//console.log(gtime);
	//console.log(rtime);
	//console.log(ytime);
	var tqname = $("#tqname").val();
	if(tqname==null||tqname=='')
	{
		alert("特勤方案名称不能为空.");
		return;
	}
		$.ajax({   
            url:'saveControl',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: {"dates":msg,"gtime":gtime,"rtime":rtime,"ytime":ytime,"tqname":tqname,"marklineid":marklineid},  
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
            		alert('特勤控制保存成功');   
            		location.href ='tqmap.jsp'; 
            	}
            }  
   	    });  
}

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