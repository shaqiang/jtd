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
			console.log(img);
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
			console.log(updateCTs);
		});
});


function saveGreen()
{
	console.log(updateCTs);
	var msg = "";
	for(var prop in updateCTs){
    if(updateCTs.hasOwnProperty(prop)){
        console.log('key is ' + prop +' and value is' + updateCTs[prop]);
        msg = msg + prop+":"+ updateCTs[prop]+",";
    	}
	}
	console.log(msg);
		$.ajax({   
            url:'updateGreen',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "dates":msg},  
            traditional: true,  
            error: function(msg)
            { //失败   
            	alert('发送命令失败');   
            },   
            success: function(msg)
            { //成功   
				alert('修改绿冲突成功');   
				updateCTs = {};
				msg = "";
            }  
   	    });  
}


function check()
{
	  var   reg= /^[0-9]*[1-9][0-9]*$/;//正整数
	  if( !reg.test($("#centerPort").val()) )
	  {	
	  	alert("输入端口号不正确.");
	  	return false;
	  }else
	  {
	  	var centerIp = $("#centerIp").val();
	  	var centerPort = $("#centerPort").val();
	  	$.ajax({   
            url:'promotionSig',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "centerIp":centerIp,"centerPort":centerPort},  
            traditional: true,  
            error: function(msg)
            { //失败   
            	alert('发送命令失败');   
            },   
            success: function(msg)
            { //成功   
				alert('升级成功');   
            }  
   	    });  
	  	
	  	
	  	
	  }
}







