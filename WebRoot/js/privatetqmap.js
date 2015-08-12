
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
var ulimit = 10;//用户权限
var searchStrURL = decodeURI(location.search);

var	 poly = new Object();
//特勤控制默认所有区域
var markerids = [];
var linesmsg = [];
var dbclickable = true;
var linedbclickable = false;
var clickable = false;
var dots = Array();
var lineId = Date.parse(new Date());//时间做唯一标示表示当前线的ID

var poly = new Object();
google.maps.event.addDomListener(window, "load", initialize);

 function initialize() {
	    var mapCanvas = document.getElementById("map_canvas");
		var myOptions = {
		zoom: 10,   
		center: new Array(119.82376098632812, 31.34542731758161),  
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
	MarkersInit();
	GreenLinesInit();

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
				poly = maphelper.polyline({
						dots:dots,						
						color:"#008000",
						weight:16,
						opacity:0.5,
						id:lineId
				});
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

//初始化当前所有信号机
function MarkersInit()
{
	console.log("加载信号机");
		$.ajax({   
	            url:'load',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            error: function(msg)
	            { //失败   
	            	alert('加载失败,有可能是登陆时间过长用户名失效,请重新登陆');   
	            },   
	            success: function(msg)
	            { //成功
	            	console.log(msg);
	            	if(msg!=null)
	            	{
	            		if(typeof(msg.length)=='undefined')//判断msg为错误提示还是正确数据
	            		{
	            			//错误提示
	            			alert(msg.message);
	            		}else
	            		{
	            			//正常数值
	            			console.log(msg);
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
							 	//  console.log(maphelper);  
							  marker.dbclickable = true;
							  marker.connectSuccess = true;
							  marker.initOver = true;
							  marker.number = markermsg[i].number;
							  marker.name = markermsg[i].name;
							  marker.address = markermsg[i].address;
							  setMarkerEvents(marker);
							  initMarkers.push(marker);
				    	    } 	 
	            		}
	            	}
            		
	            }  
    	    });  
}

//初始化所有特勤方案
function GreenLinesInit()
{
	console.log("开始加载特勤方案");
		$.ajax({   
	            url:'loadTqLines',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式   
	            dataType:'json', 
	            async:false,
	            error: function(msg)
	            { //失败   
	            	console.log('加载特勤方案失败');   
	            },   
	            success: function(msg)
	            { //成功
		            	console.log("line init");
		            	console.log(msg);
		            	if(msg==null||typeof(msg.length)=="undefined")
		            	{
		            		$("#tqid").append("<option  selected>" + '当前无任何特勤方案' + "</option>");
		            	}else
		            	{
		            		$("#tqid option").remove();
		            		$("#tqid").append("<option value='0' selected>"+'请选择特勤方案'+"</option>");
							for(var i=0;i<msg.length;i++)
			            	{
			            		$("#tqid").append("<option value=" + msg[i].id + ">" + msg[i].name + "</option>");
			            	} 
		            	
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


//删除单个信号机标记
function deleteMarker(id)
{
	for(var i=0;i<initMarkers.length;i++)
	{
		if(initMarkers[i].id == id)
		{
			$.ajax({   
	            url:'delete',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式     
	 			data: { "id":id},
	            error: function(msg)
	            { //失败   
	            	alert('信号机删除失败');   
	            },   
	            success: function(msg)
	            { //成功   
	            	alert('信号机删除成功');   
	            }  
    	    });   
    	   	 initMarkers[i].setMap(null);
	         initMarkers.splice(i,1);
		}
	}
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

//清楚当前绿线
function ClearPoly() {
	maphelper.clearPoly();
	maphelper.clearLine();
	$("#total_km").text("");
	clickable = true;//单击恢复
	
}

function deleteLine()
{
	poly = maphelper.getLine(lineId);//首先获得当前特勤线路
	//判断特勤线路
	if(typeof(poly)=='undefined')
	{
		alert("当前没有可删除的特勤方案,请选择特勤方案");
	}else
	{
		console.log(lineId);
		$.ajax({   
           url:'deleteLine',//这里是你的action或者servlert的路径地址   
           type:'post', //数据发送方式     
			data: { "mklid":lineId},
           error: function(msg)
           { //失败   
           		alert("当前特勤方案删除失败"); 
           },   
           success: function(msg)
           { //成功   
           }  
  	    });   
   	    self.location='stqmap.jsp'; 
	}
}

function saveAndUpdateLine()
{
	poly = maphelper.getLine(lineId);//首先获得当前特勤线路
	//判断特勤线路
	if(typeof(poly)=='undefined')
	{
		alert("当前没有可编辑的特勤方案,请选择特勤方案或添加特勤方案");
	}else
	{
		poly = maphelper.getLine(lineId);
		console.log(poly);
		//改变新增区域按钮状态
		if($("#addroad").css("background-image")=="none")
		{
			$("#addroad").css("background-image",'url(images/topbtn02.fw.png)').css("color","#fff");
			 clickable = false;
		}
		//通过markerids.length判断是新增特勤路线还是修改已存在的特勤路线		
		if(markerids.length>0)
		{
				console.log("新增");
				var sids = "";
				for(var i=0;i<markerids.length;i++)
				{
					sids = sids +markerids[i]+",";
				}
				$.ajax({   
	            url:'addOrUpdateTqLine',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式     
	 			data: { "mklid":lineId,"sids":sids},
	            error: function(msg)
	            { //失败   
	            		alert("当前特勤方案保存失败"); 
	            },   
	            success: function(msg)
	            { //成功   
	            }  
	   	    });   
		}
   	    self.location='greenroadAction!tq?mklid='+poly.id; 
	}
}

$(document).ready(function(){
	$("#tqid").on('change',function(){
		maphelper.clearLine();
		var selectedLineid = $(this).val();
		for(var i=0;i<linesmsg.length;i++)
  	    {
  	    	var lineObj = linesmsg[i];
  	    	if(lineObj.id==selectedLineid)
  	    	{
  	    		var sigmids = lineObj.sigmids.split(",");
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
	  	    	 poly = maphelper.polyline({
						dots:dots,						
						color:"#008000",
						weight:16,
						opacity:0.5,
						id:lineObj.marklineid
				});
				lineId = lineObj.marklineid;
				console.log(lineId);
				dots = Array();
				maphelper.bindInstanceEvent(poly, 'dblclick', function(event,map,poly) {
					self.location='greenroadAction!tq?mklid='+poly.id; 
	       		 });
  	    	}
  	  } 	   
	
	});


});


