
var maphelper = null;//封装操作对象
var infowindow = null;//信息窗口
var mapobj = null;//地图对象
var markerZoom = 12;//标记红绿灯地图所在级别
var mapClickEventListener = null;
var markers = [];
var initMarkers = [];
var markermsg = [];

var markersJson = '';
var user=null;
var numbers=[];
var options = "";
var areaid = 0;//当前区域
var ulimit = 10;//用户权限
var searchStrURL = decodeURI(location.search);


//无电缆联动
var markerids = [];
var linesmsg = [];
var dbclickable = true;
var linedbclickable = false;
var clickable = false;
var dots = Array();
var lineId = Date.parse(new Date());//时间做唯一标示表示当前线的ID

var poly = null;

google.maps.event.addDomListener(window, "load", initialize);

 function initialize() {
		if(searchStrURL!=null&&searchStrURL!="")
		{
			var areaidstr = searchStrURL.substring(searchStrURL.indexOf("=")+1,searchStrURL.length);
			areaid = parseInt(areaidstr);
		}
		AreaInit();
		console.log(searchStrURL);
	    var mapCanvas = document.getElementById("map_canvas");
		var myOptions = {
		zoom: markerZoom,   
		center: new Array(lng, lat),  
		disableDefaultUI: false, 
		disableDefaultUI: false,   
		navigationControl: true,   
		navigationControlOptions: {position: google.maps.ControlPosition.RIGHT_TOP},    
		mapTypeControl: false, //比例尺控件  
		scaleControl: true,   //启用/停用在双击时缩放并居中
		disableDoubleClickZoom:true, 
		//地图类型
		//NORMAL_MAP 标注地图
		//HYBRID_MAP 卫星混合
		//OFFLINE_MAP 离线地图
		//TERRAIN_MAP 地形图
		mapTypeId: OFFLINE_MAP,
		mapPath:"maptile/googlemaps/roadmap/",
		mapFileExt:"png"
    };

    maphelper = new mapHelper();
    mapobj = maphelper.initMap(mapCanvas, myOptions);
	if(ulimit<=0)
	{
	
		AreasInit();
	}
	MarkersInit();
	console.log("initialize ulimit:"+ulimit);
	GreenLinesInit();
	//google.maps.event.addListener(mapobj, "rightclick", reset);

}




function ClearPoly() {
	maphelper.clearInstanceEvent(mapobj, 'click');//删除实例其所有事件侦听器或指定侦听器.
}





	//初始化信号机
function initSignal(marker) {
   
    //标记动画
    if (marker.getAnimation() != null) {
        marker.setAnimation(null);
    } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }

    var contentString = setMarkerContent(marker);
	
	if(infowindow)
		infowindow.close();
    infowindow = new google.maps.InfoWindow({ //根据HTML初始化infowindow
        content: contentString
    });

    infowindow.open(mapobj, marker);


    var MapsEvent = new google.maps.event.addListener(infowindow, 'closeclick',
			    function(event) {
			    		marker.setAnimation(null);
			    });

    setMarkerEvents(marker);

}



function removeClickListener() {
    if (mapClickEventListener) {
      google.maps.event.removeListener(mapClickEventListener);
      mapClickEventListener = null;
    }
  }
  
//设置信号机标示的事件
function setMarkerEvents(marker)
{
	 maphelper.bindInstanceEvent(marker, 'click', function(event,map,marker) {
	 		if(clickable)
	 		{
				if($.inArray( marker.id, markerids)==-1)
				{
					console.log(marker.id);
					markerids.push(marker.id);
					for(var i=0;i<initMarkers.length;i++)
					{
						if(initMarkers[i].id==marker.id)
						{
							console.log(initMarkers[i]);
							dots.push(new Array(initMarkers[i].getPosition().kb,initMarkers[i].getPosition().jb));
						}
					}
				}
				console.log(dots);
				 poly = maphelper.polyline({
						dots:dots,						
						color:"#008000",
						weight:16,
						opacity:0.5,
						id:lineId
				});
	 			//$("#total_km").empty().text((poly.getLength()/1000).toFixed(3) + "km");  
	 		}
		});
		
		

	maphelper.bindInstanceEvent(marker, 'mouseover', function(event,map,marker) {
		
			//if(marker.isConnect&&!marker.isInitMarker)
			if(marker.initOver)
			{
			  	getMarkerWindow = new google.maps.InfoWindow({  content: getMarkerContent(marker) });
				getMarkerWindow.open(map, marker);
			}
			else
			{
				    var contentString = setMarkerContent(marker);

					if(infowindow)
						infowindow.close();
				    infowindow = new google.maps.InfoWindow({ //根据HTML初始化infowindow
				        content: contentString
				    });

				    infowindow.open(map, marker);

				    google.maps.event.addListener(infowindow, 'closeclick', function(event) {
		    			marker.setAnimation(null);
		    			});
			}
		 	
		 
		 
		});
		
	maphelper.bindInstanceEvent(marker, 'mouseout', function(event,map,marker) {
		 	if(getMarkerWindow)
		 	getMarkerWindow.close();
		 });

}

//初始化地图所有标志
function MarkersInit()
{
		$.ajax({   
	            url:'load',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            data: { "areaid":areaid},
	            error: function(msg)
	            { //失败   
	            	alert('加载失败,有可能是登陆时间过长用户名失效,请重新登陆');   
	            },   
	            success: function(msg)
	            { //成功
	            		encodeURI(msg);
	            		
	            	 	markermsg = msg;
	            	 	for(var i=0;i<markermsg.length;i++)
			    	    {
			    	    	numbers.push(markermsg[i].number);
				    	     var marker =  maphelper.markerPoint({
						  	    id:  markermsg[i].id,
								lat: markermsg[i].lat,
						        lng: markermsg[i].lng,
						        title: '红绿灯',
						        icon: "images/boot2.png"
				
						 	 });
						  marker.connectSuccess = true;
						  marker.initOver = true;
						  marker.number = markermsg[i].number;
						  marker.name = markermsg[i].name;
						  marker.address = markermsg[i].address;
						  setMarkerEvents(marker);
						  initMarkers.push(marker);
			    	    } 	   
			    	   
	            }  
    	    });  
}
//初始化当前区域
function AreaInit()
{
		$.ajax({   
	            url:'loadArea',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            data: { "areaid":areaid},
	            error: function(msg)
	            { //失败   
	            	alert('加载失败,有可能是登陆时间过长用户名失效,请重新登陆');  
	            },   
	            success: function(msg)
	            { //成功
	            		encodeURI(msg);
						$("#areaname").val(msg.areaname);
						lng = msg.lng;
						lat = msg.lat;
						ulimit = msg.ulimit;
						markerZoom = msg.size;
						areaid = msg.id;
						console.log("AreaInit areaid"+areaid,"AreaInit ulimit"+ulimit);
						if(ulimit==0)
						{
							$("#areasdiv").show();
						}else
	            		{
	            			lng = 119.71389770507812;
							lat = 31.336923737413848;
							markerZoom = 13;
	            		}
	            }  
    	    });  
}


//初始化当前用户的所有区域
function AreasInit()
{
		$.ajax({   
	            url:'loadAreas',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            error: function(msg)
	            { //失败   
	            	alert('加载失败,有可能是登陆时间过长用户名失效,请重新登陆'); 
	            },   
	            success: function(msg)
	            { //成功
	            	encodeURI(msg);
	            	$("#areaid option").remove();
					for(var i=0;i<msg.length;i++)
	            	{
	            		if(areaid==msg[i].id)
	            		{
	            				$("#areaid").append("<option value=" + msg[i].id + "  selected>" + msg[i].areaname + "</option>");
	            		}else
	            		{
	            				$("#areaid").append("<option value=" + msg[i].id + ">" + msg[i].areaname + "</option>");
	            		}
		            
	            	} 
	            }  
    	    });  
}

//初始化地图所有标志
function GreenLinesInit()
{
		$.ajax({   
	            url:'loadLines',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            error: function(msg)
	            { //失败   
	            	console.log('post失败');   
	            },   
	            success: function(msg)
	            { //成功
	            		encodeURI(msg);
	            		
	            	 	console.log(msg);
	            	 	linesmsg = msg;
	            	 	for(var i=0;i<linesmsg.length;i++)
			    	    {
			    	    	
			    	    	//处理mlids
			    	    	var sigmids = linesmsg[i].sigmids.split(",");
			    	    	for(var j=0;j<sigmids.length-1;j++)
			    	    	{
			    	    		for(var k=0;k<initMarkers.length;k++)
								{
									if(initMarkers[k].id==parseInt(sigmids[j]))
									{
										dots.push(new Array(initMarkers[k].getPosition().kb,initMarkers[k].getPosition().jb));
									}
								}
			    	    	}
			    	    	console.log("GreenLinesInit  dots"+dots);
			    	    	  var	 poly = maphelper.polyline({
										dots:dots,						
										color:"#008000",
										weight:16,
										opacity:0.5,
										id:linesmsg[i].marklineid
								});
								dots = Array();
								maphelper.bindInstanceEvent(poly, 'dblclick', function(event,map,poly) {
									self.location='greenroadAction!lbd?mklid='+poly.id; 
									
					        });
			    	    } 	   
	            }  
    	    });  
}



//获得信号机基本信息
function getMarkerContent(marker)
{
	return '<div  id="content"><h1 id="">当前信号机</h1><div id="bodyContent">' 
	+ '<br><div style="margin-top:0.8px">信号机编号：<input id="getnumber" value="'+marker.number+'" name="signal_number" type="text"  width="25px"/></div>' 
	+ '<br><div style="margin-top:0.8px">信号机地址：<input  id="address" value="'+marker.name+'" name="signal_address" type="text"    width="25px"/></div>' 
	+ '<br><div style="margin-top:0.8px">信号机名称：<input id="name" value="'+marker.address+'" name="signal_name" type="text"   width="25px"/></div>' 
	'</div>' ;
}



function Polyline() {
		//改变新增区域按钮状态
	if($("#addroad").css("background-image")!="none")
	{
		$("#addroad").css("background-image",'none').css("color","#000");
		alert("点击地图上的信号机");
		clickable = true;//单击启动
	}
	
}

//清除当前绿线
function ClearPoly() {
	maphelper.clearPoly();
	maphelper.clearLine();
	$("#total_km").text("");
	clickable = true;//单击恢复
	
}


function saveLine()
{
	if(markerids.length<2)
	{
		alert("当前没有可保存的无电缆联动,请点击添加无电缆联动按钮.");
	}else
	{
		console.log(poly);
		//改变新增区域按钮状态
		if($("#addroad").css("background-image")=="none")
		{
			$("#addroad").css("background-image",'url(images/topbtn02.fw.png)').css("color","#fff");
			 clickable = false;
		}
		var sids = "";
		for(var i=0;i<markerids.length;i++)
		{
			sids = sids +markerids[i]+",";
		}
		console.log(lineId,sids);
		$.ajax({   
            url:'addOrUpdateLine',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式     
 			data: { "mklid":lineId,"sids":sids},
            error: function(msg)
            { //失败   
            		alert("当前无电缆联动保存失败"); 
            },   
            success: function(msg)
            { //成功   
				alert("当前无电缆联动保存成功");  
				self.location.reload();  //刷新本页
            }  
   	    });   
	
	}
	
}

function changeArea()
{
	location.href = "greenmap.jsp?areaid="+parseInt($("#areaid").val());
}

