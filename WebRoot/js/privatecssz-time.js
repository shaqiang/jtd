//时间段参数
function changeTimeSelect()
{
	var timetype = $("#timetype").val();
	var orderid = $("#orderid").val();
	//console.log("sigtimeAction!sigtimes?timetype="+timetype+"&orderid="+orderid);
	location.href = "sigtimeAction!sigtimes?timetype="+timetype+"&orderid="+orderid;
}

function changeSoSelect()
{
	var timetype = $("#timetype").val();
	var orderid = $("#orderid").val();
	var soid = $("#soid").val();
	//console.log("sigtimeAction!sigtimes?timetype="+timetype+"&orderid="+orderid+"&soid="+soid);
	location.href = "sigtimeAction!sigtimes?timetype="+timetype+"&orderid="+orderid+"&soid="+soid;
}

function changeControl()
{
	var index = $("#control").val();
	if(index==3)
	{
		$("#seconds").removeAttr("readOnly");
	}else
	{
		$("#seconds").attr("readOnly","true");
		$(document).ready(function(){
			$("#seconds").val(0);
		});
	
	}
}

var msg = "";
function updateStepTimes()
{
	var timeinputs = $(".timeinput");
	var timetype = $("#timetype").val();
	var orderid = $("#orderid").val();
	var signid = $("#signid").val();
	for(var i=0;i<timeinputs.length;i++){
        msg = msg + timeinputs[i].name+":"+ timeinputs[i].value+",";
	}
	$.ajax({   
            url:'updateStepTimes',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "dates":msg,"timetype":timetype,"orderid":orderid,"signid":signid},  
            traditional: true,  
            async:false,
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
					return false;
				}
				msg = "";
				return true;
            }  
   	    });  
}


$(document).ready(function(){
	$(".dfinput").on("blur",function(){
			var	time = parseInt($(this).val());
				var id  = $(this).attr("id");
				if(id=='hour')
				{
					if(isNaN(time)||time>24||time<0)
					{
						alert("小时设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
					
				}if(id=='minute')
				{
					if(isNaN(time)||time>60||time<0)
					{
						alert("分钟设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
				}if(id=='seconds')
				{
					if(isNaN(time)||time>60||time<0)
					{
						alert("秒数设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
				}if(id=='lstime')
				{
					if(isNaN(time)||time>9||time<0)
					{
						alert("绿闪时间设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
				}if(id=='hdtime')
				{
					if(isNaN(time)||time>9||time<3)
					{
						alert("黄灯时间设置不正确,请重新设置.");
						$(this).val(3);//恢复原值
						return ;
					}
				}if(id=='qchdtime')
				{
					if(isNaN(time)||time>9||time<0)
					{
						alert("红灯时间设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
				}
				$(this).val(time);
		});
		
		$(".timeinput").on("blur",function(){
			var	time = parseInt($(this).val());
				if(isNaN(time)||time>90||time<10)
				{
					alert("相位执行时间设置不正确,请重新设置.");
					$(this).val(10);//恢复原值
					return ;
				}
				$(this).val(time);//恢复原值
		});
		

});