//绿灯冲突
var updateCTs = {};
$(document).ready(function(){
	var imgsrc_new = "";//新的图片路径
	var img = null;
	var imgsrc="";
	var ids = {};
	var id = "";
			
	$("td img").click(function(event) {
			img = $(event.target);
			//console.log(img);
			imgsrc = img[0].currentSrc;
			id = img[0].id;
			if(imgsrc.indexOf("tick")==-1)
			{
				$("#"+id).attr("src","img/tick.png");
				updateCTs[id] = 0;
			}else
			{
				$("#"+id).attr("src","img/cross.png");
				updateCTs[id] = 1;
				
			}
		//	console.log(updateCTs);
		});
		
	
});


function saveGreen()
{
	//console.log(updateCTs);
	var msg = "";
	
	for(var prop in updateCTs){
    if(updateCTs.hasOwnProperty(prop)){
       // console.log('key is ' + prop +' and value is' + updateCTs[prop]);
        msg = msg + prop+":"+ updateCTs[prop]+",";
    	}
	}
	//console.log(msg);
		$.ajax({   
            url:'updateGreen',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            dataType:'json',
            data: { "dates":msg},  
            traditional: true,  
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
	            	alert("修改绿冲突表成功.");
	            }
				updateCTs = {};
				msg = "";
            }  
   	    });  
}