

function cleanAll()
{
	$(':input','#publicparamform')  
	 .not(':button, :submit, :reset, :hidden')  
	 .val('')  
}

$(document).ready(function(){

	//加载页面,初始化checkbox部分
	if (xyxrinit == 1) {
		$("#xyxr").val(1);
		$("#xyxr").attr("checked", "checked");
		$("#xr").show();
	} else {
		$("#xyxr").val(0);
		$("#xr").hide();
	}
	if (spetimeinit== 1) {
		$("#spetime").val(1);
		$("#spetime").attr("checked", "checked");
		$("#spe").show();
	} else {
		$("#spetime").val(0);
		$("#spe").hide();
	}
	if (suntimeinit == 1) {
		$("#suntime").val(1);
		$("#suntime").attr("checked", "checked");
		$("#sunt").show();
	} else {
		$("#suntime").val(0);
		$("#sunt").hide();
	}
	if (moninit == 1) {
		$("#mon").val(1);
		$("#mon").attr("checked", "checked");
	}else
	{
		$("#mon").val(0);
	}
	if (tueinit == 1) {
		$("#tue").val(1);
		$("#tue").attr("checked", "checked");
	}else
	{
		$("#tue").val(0);
	}
	if (wesinit == 1) {
		$("#wes").val(1);
		$("#wes").attr("checked", "checked");
	}else
	{
		$("#wes").val(0);
	}
	if (thirinit == 1) {
		$("#thir").val(1);
		$("#thir").attr("checked", "checked");
	}else
	{
		$("#thir").val(0);
	}
	if (frainit == 1) {
		$("#fra").val(1);
		$("#fra").attr("checked", "checked");
	}else
	{
		$("#fra").val(0);
	}
	if (satainit == 1) {
		$("#sata").val(1);
		$("#sata").attr("checked", "checked");
	}else
	{
		$("#sata").val(0);
	}
	if (suninit == 1) {
		$("#sun").val(1);
		$("#sun").attr("checked", "checked");
	}else
	{
		$("#sun").val(0);
	}
	
	
	$(".checkinputweek").click(function () {
		if ($(this).attr("checked") == "checked") {
			$(this).val(1);
		} else {
			$(this).val(0);
		}
	});
	
	$("#xyxr").click(function () {
		if ($("#xyxr").attr("checked") == "checked") {
			$("#xyxr").val(0);
			$("#xr").show();
		} else {
			$("#xyxr").val(1);
			$("#xr").hide();
		}
	});
	$("#spetime").click(function () {
		if ($("#spetime").attr("checked") == "checked") {
			$("#spetime").val(0);
			$("#spe").show();
		} else {
			$("#spetime").val(1);
			$("#spe").hide();
		}
	});
	$("#suntime").click(function () {
		if ($("#suntime").attr("checked") == "checked") {
			$("#suntime").val(0);
			$("#sunt").show();
		} else {
			$("#suntime").val(1);
			$("#sunt").hide();
		}
	});
	
	
	
	//checkbox部分
	


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

function diaoyueCS()
{
	executeCommand(5);
	self.location.reload();
}
