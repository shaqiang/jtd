var maphelper = null;//封装操作对象
var infowindow = null;//信息窗口
var mapobj = null;//地图对象
var markerZoom = 13;//标记红绿灯地图所在级别
var mapClickEventListener = null;
var markers = [];
var initMarkers = [];
var markermsg = [];
var markerId = Date.parse(new Date());//时间做唯一标示
var markersJson = '';
var user=null;
var numbers=[];
var options = "";




google.maps.event.addDomListener(window, "load", initialize);
 function initialize() {

	   var mapCanvas = document.getElementById("map_canvas");
		var myOptions = {
		zoom: markerZoom,   
		center: new Array(119.69663500785828, 31.369760901943426),  
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
    
    

	maphelper.bindInstanceEvent(mapobj, 'zoom_changed', function(event){
		$("#ZOOM").val(maphelper.getZoom());
	});		
	
	maphelper.bindInstanceEvent(mapobj, 'dblclick', function(event){
		if(dbclick)
		{
			$("#CLAT").val(event.latLng.lat());
			$("#CLNG").val(event.latLng.lng());
		}

	});
	
	//MarkersInit();

	//google.maps.event.addListener(mapobj, "rightclick", reset);

}



function addArea() {
	//改变新增区域按钮状态
	if($("#addarea").css("background-image")!="none")
	{
		$("#addarea").css("background-image",'none').css("color","#000");
		dbclick = true;
	 	alert("双击地图位置作为区域中心点.");
	}
 }



//添加单个信号机标记
function saveArea()
{
	//改变新增区域按钮状态
	if($("#addarea").css("background-image")!="none")
	{
		alert("请点击 添加区域 按钮.");
	}else
	{
		var areaname = $('#areaname').val();
		var lat = $("#CLAT").val();
		var lng = $("#CLNG").val();
		var zoom = $("#ZOOM").val();
		if(lng==""||isNaN(parseFloat(lng)))
		{
			alert("请正确填写经度.");
			return ;
		}
		if(lat==""||isNaN(parseFloat(lat)))
		{
			alert("请正确填写纬度.");
			return ;
		}if(areaname=="")
		{
			alert("请填写区域名称..");
			return ;
		}
		console.log(parseInt(zoom));
		if(zoom==""||zoom==0||isNaN(parseInt(zoom)))
		{
			alert("当前地图级别不正确..");
			return ;
		}
		//改变新增区域按钮状态
		if($("#addarea").css("background-image")=="none")
		{
			$("#addarea").css("background-image",'url(images/topbtn02.fw.png)').css("color","#fff");
			dbclick = false;
		}
		$.ajax({   
	           url:'addArea',//这里是你的action或者servlert的路径地址   
	           type:'post', //数据发送方式     
				data: { "lat":lat,"lng":lng,"zoom":parseInt(zoom),"uareaname":areaname},
	           error: function(msg)
	           { //失败   
	           		alert('区域添加失败');   
	           },   
	           success: function(msg)
	           { //成功   
					alert('区域添加成功');  
					self.location.reload();  //刷新本页
	           }  
	  	    });   
    	    
	
	}
}

$(document).ready(function(){

	$("#areaname").on("blur",function(){
	
			var uareaname = $(this).val();
			$.ajax({   
			            url:'checkAreaname',//这里是你的action或者servlert的路径地址   
			            type:'post', //数据发送方式   
			            async:false,
			            data: { "uareaname":uareaname},
			            dataType:'json',
			            error: function(msg)
			            { //失败   
			            	console.log('post失败');   
			            },   
			            success: function(msg)
			            { //成功
							 if(msg!=null)
							 {
							 	alert(msg.message);
						 		$(document).ready(function(){ 
						 			$("#areaname").val('');
						 		});
							 }
						}
					});
	
	
	});

});






