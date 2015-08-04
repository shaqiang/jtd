$(document).ready(function(){
  		//setInterval("suretime()",60*1000*60);
  })

function suretime()
{
	$.ajax({   
            url:'suretime',//这里是你的action或者servlert的路径地址   
            type:'get', //数据发送方式   
            error: function(msg)
            { //失败   
            	alert('校时失败');   
            },   
            success: function(msg)
            { //成功   
				alert('校时成功');   
            }  
   	    });   
}
