//一般参数
function initPublicParam() {
	//css部分
	$("#xyxr").click(function () {
		if($("#xyxr").val()==0)
  		{
  			$("#xyxr").val(1);
  		}else
  		{
  			$("#xyxr").val(0);
  		}
		if ($("#xyxr").attr("checked") == "checked") {
			$("#xr").show();
		} else {
			$("#xr").hide();
		}
	});
	$("#spetime").click(function () {
		if($("#spetime").val()==0)
  		{
  			$("#spetime").val(1);
  		}else
  		{
  			$("#spetime").val(0);
  		}
		if ($("#spetime").attr("checked") == "checked") {
			$("#spe").show();
		} else {
			$("#spe").hide();
		}
	});
	$("#suntime").click(function () {
		if($("#suntime").val()==0)
  		{
  			$("#suntime").val(1);
  		}else
  		{
  			$("#suntime").val(0);
  		}
		if ($("#suntime").attr("checked") == "checked") {
			$("#sunt").show();
		} else {
			$("#sunt").hide();
		}
	});
	

	//checkbox部分
	if ($("#xyxr").attr("value") == 1) {
		$("#xyxr").attr("checked", "checked");
		$("#xr").show();
	} else {
		$("#xr").hide();
	}
	if ($("#spetime").attr("value") == 1) {
		$("#spetime").attr("checked", "checked");
		$("#spe").show();
	} else {
		$("#spe").hide();
	}
	if ($("#suntime").attr("value") == 1) {
		$("#suntime").attr("checked", "checked");
		$("#sunt").show();
	} else {
		$("#sunt").hide();
	}
	
	
	
	//checkbox部分
	if ($("#mon").attr("value") == 1) {
		$("#mon").attr("checked", "checked");
	}
	if ($("#tue").attr("value") == 1) {
		$("#tue").attr("checked", "checked");
	}
	if ($("#wes").attr("value") == 1) {
		$("#wes").attr("checked", "checked");
	}
	if ($("#thir").attr("value") == 1) {
		$("#thir").attr("checked", "checked");
	}
	if ($("#fra").attr("value") == 1) {
		$("#fra").attr("checked", "checked");
	}
	if ($("#sata").attr("value") == 1) {
		$("#sata").attr("checked", "checked");
	}
	if ($("#sun").attr("value") == 1) {
		$("#sun").attr("checked", "checked");
	}
}


function cleanAll()
{
	$(':input','#publicparamform')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
}

$(document).ready(function(){
	$(".dfinput").on("blur",function(){
			var	time = parseInt($(this).val());
				var id  = $(this).attr("id");
				if(id=='kjhs')
				{
					if(isNaN(time)||time>30||time<0)
					{
						alert("开机黄闪设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
							return ;
					}
				}if(id=='qchd')
				{
					if(isNaN(time)||time>9||time<0)
					{
						alert("清场红灯设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
							return ;
					}
				}if(id=='gmin')
				{
					if(isNaN(time)||time>90||time<10)
					{
						alert("最短绿灯设置不正确,请重新设置.");
						$(this).val(10);//恢复原值
						return ;
					}
				}if(id=='bc')
				{
					if(isNaN(time)||time>15||time<3)
					{
						alert("步长时间设置不正确,请重新设置.");
						$(this).val(3);//恢复原值
						return ;
					}
				}if(id=='gmax')
				{
					if(isNaN(time)||time>90||time<parseInt($("#gmin").val()))
					{
						alert("最长绿灯设置不正确,请重新设置.");
						$(this).val(10);//恢复原值
							return ;
					}
				}if(id=='xrfx')
				{
					if(isNaN(time)||time>90||time<30)
					{
						alert("行人请求设置不正确,请重新设置.");
						$(this).val(30);//恢复原值
						return ;
					}
				}if(id=='cycle')
				{
					if(isNaN(time)||time>255||time<0)
					{
						alert("周期设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}
				}
				$(this).val(time);
		});
		
		
		
		$(".ydfinput").on("blur",function(){
				var	time = parseInt($(this).val());
					if(isNaN(time)||time>12||time<0)
					{
						alert("月份设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}				
		});
		
		$(".rdfinput").on("blur",function(){
				var	time = parseInt($(this).val());
					if(isNaN(time)||time>31||time<0)
					{
						alert("日期设置不正确,请重新设置.");
						$(this).val(0);//恢复原值
						return ;
					}	
		});
	

});


