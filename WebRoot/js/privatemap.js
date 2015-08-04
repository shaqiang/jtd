var maphelper = null;//封装操作对象
var infowindow = null;//信息窗口
var mapobj = null;//地图对象
var markerZoom = 12;//标记红绿灯地图所在级别
var lng = "";//经度
var lat = "";//维度
var mapClickEventListener = null;
var markers = [];
var initMarkers = [];
var markermsg = [];
var markerId = Date.parse(new Date());//时间做唯一标示
var markersJson = '';
var user=null;
var numbers=[];
var options = "";
var areaid = 0;//当前区域
var ulimit = 10;//用户权限
var searchStrURL = decodeURI(location.search);
var deleteable = false;    	

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
	
	//google.maps.event.addListener(mapobj, "rightclick", reset);

}

//初始化select
function addOption(){  

	for(var i=0;i<numbers.length;i++){   
	  	options = options+"+<option value=" +numbers[i] + ">" + numbers[i] + "</option>"
	}   
}  


function reset() {

    for (var i=0;i<markers.length;i++) {
      markers[i].setMap(null);
    }
    markers = [];
 }

function ClearPoly() {
		self.location.reload();  //刷新本页
}

function deleteSig()
{
		//删除按钮
		if($("#addsig").css("background-image")=="none")
		{
			$("#addsig").css("background-image",'url(images/topbtn02.fw.png)').css("color","#fff");
			maphelper.clearInstanceEvent(mapobj, 'click');//取消新增
			if (mapClickEventListener) {
		      google.maps.event.removeListener(mapClickEventListener);
		      mapClickEventListener = null;
		    }
		}
		if($("#delsig").css("background-image")!="none")
		{
			$("#delsig").css("background-image",'none').css("color","#000");
			alert("点击地图上的信号机进行删除");
			deleteable = true;//可以删除
		}
		
}

function addClickEventListener() {
		if($("#addsig").css("background-image")!="none")
		{
			$("#addsig").css("background-image",'none').css("color","#000");
			alert("单击地图添加信号机..");
		}
		if($("#delsig").css("background-image")=="none")
		{
			$("#delsig").css("background-image",'url(images/topbtn02.fw.png)').css("color","#fff");
			deleteable = false;//取消删除
		}
        if (!mapClickEventListener) {
          mapClickEventListener = google.maps.event.addListener(mapobj, 'click', function (event) {
            addMarker(event.latLng, true);
          });
        }
      }

	function addMarker(latlng, doQuery) {
		  var marker =  maphelper.markerPoint({
		  	    id:  markerId++,
				lat: latlng.lat(),
		        lng: latlng.lng(),
		        title: '红绿灯',
		        icon: "images/boot2.png"

		  });
		  marker.connectSuccess = false;
		  marker.initOver = false;
		  marker.name = '';
		  marker.address = '';
		  marker.dbclickable = false;
		  initSignal(marker);  
		  initMarkers.push(marker);
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
		
		maphelper.bindInstanceEvent(marker, 'dblclick', function(event,map,marker) {
					 if(!deleteable)
        			{
						window.open("sigAction!toTraffic?mkid="+marker.id,"rightFrame");
					}
	        });
	        
	      
	        	  maphelper.bindInstanceEvent(marker, 'click', function(event,map,marker) {
	        	    if(deleteable)
        			{
        				console.log("delete");
						deleteMarker(marker.id);
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

	maphelper.bindInstanceEvent(marker, 'dragend', function(event,map,marker) {
		 	marker.setPosition(event.latLng);
		 });

	
}

//初始化地图所有标志
function MarkersInit()
{
	console.log("Markers    Init........");
		console.log("areaid...."+areaid);
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
	            		console.log(msg);
	            		numbers = [];
	            		if(msg!=null)
	            		{
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
							 	  console.log(maphelper);  
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
    	    });  
    	    addOption();//初始化select
}


//初始化当前区域
function AreaInit()
{
		console.log("Area  Init........");
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
	            		console.log(msg);
	            		if(msg!=null)
	            		{
	            			$("#areaname").val(msg.areaname);
							lng = msg.lng;
							lat = msg.lat;
							ulimit = msg.ulimit;
							markerZoom = msg.size;
							areaid = msg.id;
							if(ulimit==0)
							{
								$("#areasdiv").show();
							}
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

//获得信号机基本信息
function getMarkerContent(marker)
{
	return '<div  id="content"><h1 id="">当前信号机</h1><div id="bodyContent">' 
	+ '<br><div style="margin-top:0.8px">信号机编号：<input id="getnumber" value="'+marker.number+'" name="signal_number" type="text"  width="25px"/></div>' 
	+ '<br><div style="margin-top:0.8px">信号机地址：<input  id="address" value="'+marker.name+'" name="signal_address" type="text"    width="25px"/></div>' 
	+ '<br><div style="margin-top:0.8px">信号机名称：<input id="name" value="'+marker.address+'" name="signal_name" type="text"   width="25px"/></div>' 
	'</div>' ;
}

//绑定信号机并设置基本信息
function setMarkerContent(marker)
{
	
	return '<div  id="content"><h1 id="">绑定远程信号机</h1><div id="bodyContent">'
	+ '<div style="margin-top:10px; float:left; width:300px;">信号机编号：<select id="numberSelect"  name="numberSelect"  style="padding-bottom:1px;border:1px solid #cfdfe4" width="25px">'
	+options+'</select></div>' 
	+ '<br><div style="margin-top:5px; float:left; width:300px;">信号机地址：<input id="address" class="setAddress" value="" name="signal_address" type="text"   style="padding-bottom:1px;border:1px solid #cfdfe4"  width="25px"/></div>' 
	+ '<br><div style="margin-top:5px; float:left; width:300px;">信号机名称：<input id="name" class="setName" value="" name="signal_name" type="text"   style="padding-bottom:1px;border:1px solid #cfdfe4"  width="25px"/></div>' 
	+ '<br><div class="maptip"><btn><a href="javascript:saveMarker('+marker.id+')" target="rightFrame" onclick="return checkMarker();">绑定</a></btn></div></div>'

	'</div>' ;
}


//添加单个信号机标记
function saveMarker(id)
{
	for(var i=0;i<initMarkers.length;i++)
	{
		if(initMarkers[i].id == id)
		{
			var number = $('#numberSelect').val();
			var address = $('#address').val();
			var name = $('#name').val();
			var lat = initMarkers[i].getPosition().jb;
			var lng = initMarkers[i].getPosition().kb;
			
			$.ajax({   
	            url:'addOrUpdate',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式     
	 			data: {"mkid":id,"number":number,"address":address,"name":name,"lat":lat,"lng":lng,"areaid":areaid},
	            error: function(msg)
	            { //失败   
	            	alert('信号机增加失败');   
	            },   
	            success: function(msg)
	            { //成功   
	          		if(infowindow)
					infowindow.close();
					alert('信号机绑定成功');  
	            }  
    	    });   
    	    initMarkers[i].dbclickable = true;
    	    initMarkers[i].initOver = true;
    	    initMarkers[i].setAnimation(null);
    	    initMarkers[i].name = name;
    	    initMarkers[i].address = address;
    	    initMarkers[i].number = number;
		}
	}
}


//删除单个信号机标记
function deleteMarker(id)
{
	if(confirm("相关联动设置也会一并删除,您确定要删除该信号机么?"))
	{
		for(var i=0;i<initMarkers.length;i++)
		{
	
			if(initMarkers[i].id == id)
			{
				$.ajax({   
		            url:'deleteMarker',//这里是你的action或者servlert的路径地址   
		            type:'post', //数据发送方式     
		 			data: { "mkid":id},
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
	
}





function changeArea()
{
	location.href = "map.jsp?areaid="+parseInt($("#areaid").val());
}

function checkMarker()
{
	var nameError = false;
	var address = $(".setAddress").val();
	var name = $(".setName").val();
	if(address==null||address=="")
	{
		alert("信号机所在地址不能为空");
		return false;
	}
	$.ajax({   
			            url:'checkMarkerName',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: { "name":name},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('post失败');   
			            },   
			            success: function(msg)
			            { //成功
							 if(msg!=null)
							 {
						 		nameError = true;
							 }
						}
					});
	if(name==null||name=="")
	{
		alert("信号机名称不能为空");
		return false;
	}
	if(nameError)
	{
		alert("信号机名称已存在，请重新输入");
		return false;
	}
}



