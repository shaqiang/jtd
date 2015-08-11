//pageKit.js
//登陆页面，验证码
function changeImage()
{
			var v=document.getElementById("im");
			var anum = Math.random()*100;
			v.innerHTML="<img src=toolkitAction!validateCode?id="+anum+" width=80 height=32  />";
}

//分页显示
function jumpNullPage(url,page){
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

function jumpIserrorPage(url,page,iserror){
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&iserror='+iserror;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示
function jumpPage(url,page,con,convalue,status,pid){
console.log("enter1");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&con='+con+'&convalue='+convalue+'&status='+status+'&pkid='+pid;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}
//分页显示用户管理
function jumpPage(url,page,convalue){
console.log("userManage----------------");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&convalue='+convalue;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示片区管理
function jumpSonPage(url,page,userid,uareaname){
console.log("userPianqu----------------");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&userid='+userid+'&uareaname='+uareaname;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示用户管理
function jumpFlowPage(url,page,sigid,time1,time2){
console.log("jumpFlowPage----------------");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&sigid='+sigid+'&time1='+time1+'&time2='+time2;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示用户管理
function jumpFlowLinePage(url,page,sigid,time1,time2,interval,nextstarttime){
console.log("jumpFlowPage----------------");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&sigid='+sigid+'&time1='+time1+'&time2='+time2+'&interval='+interval+'&nextstarttime='+nextstarttime;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示片区管理
function jumpAllPage(url,page,uareaname){
console.log("pianqu----------------");
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&uareaname='+uareaname;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}


//分页显示操作日志管理
function jumpOplogPage(url,page,uid,opevent,startdate,enddate){
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&uid='+uid+'&opevent='+opevent+'&startdate='+startdate+'&enddate='+enddate;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}

//分页显示设备日志管理
function jumpDevlogPage(url,page,sigid,devevent,startdate,enddate){
	var page=page;
	if(isNaN(page)){
		var page2=document.getElementById(page).value;
		page=parseInt(page2);
	}
	
	var url=url+'?page='+page+'&sigid='+sigid+'&devevent='+devevent+'&startdate='+startdate+'&enddate='+enddate;
	url=encodeURI(url);
	url=encodeURI(url);
	window.location=url;
}
function deleteBatch(url){
				var aa=document.all.chek;
				var loc=url+"!deleteBatch?ids=0";
				if(aa.value>0){
					loc=url+"!delete?id="+aa.value;
				}
				if(aa.length>0){
					for(var i=0;i<aa.length;i++){
						if(aa[i].checked==true){
							var cpid=aa[i].value;
							loc=loc+","+cpid;
						}
					}
				}
				
				window.location=loc;
}


//全选
function selectAll(){
	var aa=document.getElementsByName("chek");
	if(aa.length>0){
		for(var i=0;i<aa.length;i++){
			aa[i].checked=true;
		}
	}

}
//反选
function reverse(){
	var aa=document.getElementsByName("chek");
	if(aa.length>0){
		for(var i=0;i<aa.length;i++){
			if(aa[i].checked==false){
				aa[i].checked=true;
			}else{
				aa[i].checked=false;
			}
		}
	}

}
//禁止使用退格键backspace
function keyDown(){
            // 禁止使用backspace键
            if(window.event.keyCode == 8){
                alert("该文本框为只读");
                event.returnValue=false;
            }
            // 后面还可以禁止其它键，照着上面的方法写就行了
            // 比如：if(event.shiftKey&&event.keyCode == 121) // 屏蔽shift+F10
}
//公文字典多选择删除shoufatype_manage.jsp


function checkNum(obj){
    //定义正则表达式部分
    var num=obj.value;
    if(isNaN(num)){
        alert("密码只能输入数字");
        obj.value="";
        obj.focus();
    }
}



function checkNum2(){
    //定义正则表达式部分
    var num=obj.value;
    if(isNaN(num)){
        alert("密码只能输入数字");
        obj.value="";
        obj.focus();
    }
}
function executeCommand(id)
{
	$.ajax({   
            url:'doCommand',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            data: { "commandId":id},   
            dataType:'json',
            error: function(msg)
            { //失败   
            	alert('命令发送失败');   
            },   
            success: function(msg)
            { //成功   
				//alert('发送命令成功');   
				if(msg!=null)
				{
					alert(msg.message);
				}else
				{
					alert('命令发送成功');  
				}
            }  
   	    });   
}


function clearFlow()
{
	if(confirm("您确定要清除车流量信息吗？"))
	{
		executeCommand(34);
		$.ajax({   
	            url:'clearFlow',//这里是你的action或者servlert的路径地址   
	            type:'post', //数据发送方式  
	            dataType:'json',
	            traditional: true,  
	            error: function(msg)
	            { //失败   
	            	alert('发送命令失败');   
	            },   
	            success: function(msg)
	            { //成功   
					if(msg!=null)
					{
						//alert(msg.message);
					}else
					{
						alert("该信号机的车流量信息已清除.");
					}
	            }  
	   	    });  
   	  }
}

function clearErrorcode()
{
	if(confirm("您确定要清除故障信息吗？"))
	{
		executeCommand(37);
		$.ajax({   
            url:'clearErrorcode',//这里是你的action或者servlert的路径地址   
            type:'post', //数据发送方式  
            dataType:'json',
            traditional: true,  
            error: function(msg)
            { //失败   
            	alert('发送命令失败');   
            },   
            success: function(msg)
            { //成功   
				if(msg!=null)
				{
					//alert(msg.message);
				}else
				{
					alert("该信号机的故障信息已清除.");
				}
            }  
   	    });  
	
	}
	
}

