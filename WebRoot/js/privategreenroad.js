 $(document).ready(function(){
 			
 			alert("起始点调整与坐标点一致.");
			var searchStrURL = decodeURI(location.search);
		    var searchStrArray = searchStrURL.slice(1).split("&");
			var cirleTime = 0;
			var timetype = 0;
			var orderid = 0;
			var begintime = '';
			var searchStr = '';
			if(searchStrArray.length>1)
			{
				console.log(searchStrArray);
				searchStr = searchStrArray[0].split("=")[1];
				cirleTime = parseInt(searchStrArray[1].split("=")[1]);
				timetype =  parseInt(searchStrArray[2].split("=")[1]);
				orderid =  parseInt(searchStrArray[3].split("=")[1]);
				begintime =  searchStrArray[4].split("=")[1];
			}
			else
			{
				searchStr = searchStrArray[0].split("=")[1];
			}
			var jsonlist = searchStr.substring(searchStr.indexOf("=")+1,searchStr.length);
			var jsonSigs = JSON.parse(jsonlist);
			
			var jsonsigs = [];
            var sigs = [];//信号机数组
            var canvas =document.getElementById("canvas");
            var context =canvas.getContext("2d");
            var dots = [];//坐标点
            var dotsbak = [];//原坐标点
			

            var pixdiffX = 38;//像素 X轴差值
            var pixdiffY = 768;//像素 Y轴差值
            var pixDot = 8;//像素 点位差值
            var pixinitsigWidth = 1080/3;//初始化信号机像素的 三分之一 为一个周期的长度

            initSigs();//设置信号机
            createDivs();//初始化div
            setDivs(sigs);//设置 信号机 div


            setDots();//初始化坐标点  dots[]
            setSpeedDivs(dots);//设置 速度

            console.log(dots);
            dotsbak = dots.slice();//复制js数组

            for(var i=0;i<sigs.length;i++)
            {
            	var timeX = getSigBeginTime(sigs[i]);
                if(i!=0)
                {
                    setSigDraggable(i,timeX);//设置信号机拖动事件
                }
                $("#sigtime"+i).css("margin-left",timeX+pixdiffX);
                $("#sigtime"+i).css('margin-top',pixdiffY+35);
                $("#sigdistance"+i).css('margin-top',dots[i].y-20);
                $("#sigdistance"+i).text( parseInt((pixdiffY-dots[i].y)*10+200));//显示与基准点的距离差
                $("#sigtime"+i).text( parseInt(timeX));//显示与基准点的时间差
            }


                drawLine();


                function setSigDraggable(index,timeX)
                {
                    $("#sig"+index).draggable({axis:"x"});
                    $( "#sig"+index ).on( "dragstop", function( event, ui ) {

                        context.clearRect(0,0,canvas.width,canvas.height);

                        //一个信号机 移动 两个点改变 正向的点和反向的点
                        dots[index] =   new Dot(dotsbak[index].x+ui.offset.left-pixDot,dots[index].y,"t"+index);
                        dots[index+dots.length/2] =   new Dot(dotsbak[index+dots.length/2].x+ui.offset.left-pixDot,dots[index+dots.length/2].y,"t"+index);
						
						
						
						
                        $("#sigtime"+index).css('margin-left',timeX+pixdiffX+ui.offset.left);

                        changeSpeed(index);



                        if(index<2&&index>0)
                        {

                            console.log(dots[index+1].x);
                            if(dots[index].x>dots[index+1].x || dots[index].x<dots[index-1])
                            {
                                alert("操作错误,请返回");

                            }else
                            {

                                $("#sigtime"+index).text( parseInt(timeX+ui.offset.left));


                                $("#sigdistance"+index).css('margin-top',dots[index].y-20);
                                $("#sigdistance"+index).text( parseInt((pixdiffY-dots[index].y)*10+200));
                                drawLine() ;
                            }

                        }
                        else
                        {
                            if(index==0)
                            {
                                if(dots[index].x>=dots[index+1].x)
                                {
                                    alert("操作错误,请返回");
                                }else
                                {
                                    $("#sigtime"+index).text( parseInt(timeX+ui.offset.left));


                                    $("#sigdistance"+index).css('margin-top',dots[index].y-20);
                                    $("#sigdistance"+index).text( parseInt((pixdiffY-dots[index].y)*10+200));
                                    drawLine() ;
                                }
                            }
                            if(index==2)
                            {
                                if(dots[index].x<=dots[index-1])
                                {
                                    alert("操作错误,请返回");
                                }else
                                {
                                    $("#sigtime"+index).text( parseInt(timeX+ui.offset.left));


                                    $("#sigdistance"+index).css('margin-top',dots[index].y-20);
                                    $("#sigdistance"+index).text( parseInt((pixdiffY-dots[index].y)*10+200));
                                    drawLine() ;
                                }
                            }

                        }

                    } );

                }

                function drawLine()
                {
                    if(dots.length>1)
                    {
                        /**
                         *  0-1-2
                         */
                        for(var i=0;i<dots.length/2-1;i++)
                        {
                            context.beginPath();
                            context.moveTo(dots[i].x,dots[i].y);
                            context.lineTo(dots[i+1].x,dots[i+1].y);
                            context.stroke();
                        }
                        /**
                         *  3-4-5
                         */
                        for(var i=dots.length/2;i<dots.length-1;i++)
                        {
                            context.beginPath();
                            context.moveTo(dots[i].x,dots[i].y);
                            context.lineTo(dots[i+1].x,dots[i+1].y);
                            context.stroke();
                        }
                    }
                }

            /**
             * 根据信号机对象的位置，设置点坐标
             * 正向
             * 默认 周期a 相位0 开始
             * # 0_a_0 0-信号机编号 a-信号机周期    0-相位编号
             *
             * 反向
             * 默认周期a开始
             * # 0_a_4  0-信号机编号  a-信号机周期  4-相位编号(末尾)
             *
             */
            function setDots()
            {

                //正向坐标点
                for(var i=0;i<sigs.length;i++)
                {

                    var dot = new Dot($("#"+sigs[i].number+sigs[i].zhengxiang).offset().left-pixDot,$("#"+sigs[i].number+sigs[i].zhengxiang).offset().top-pixDot,"t"+i);
                    dots.push(dot);
                }


                var dotCommon = null;
                //反向坐标点
                for(var i=0;i<sigs.length;i++)
                {
                    if(i==0)
                    {
                        var dot = new Dot($("#"+sigs[i].number+sigs[i].fanxiang).offset().left-pixDot,$("#"+sigs[i].number+sigs[i].fanxiang).offset().top-pixDot,"t"+i);
                        dotCommon = dot;
                        dots.push(dot);
                	}
                    else
                    {
                        var time = getSigBackTime(sigs[i]);
                        var dot = new Dot(dotCommon.x-time, pixdiffY-sigs[i].distance/10+20);//相位本身存在高度
                        dots.push(dot);
                    }
                }

            }

                /**
                 * 坐标点构造方法
                 */
                function Dot(x,y,name)
                {
                    this.x = x;
                    this.y = y;
                    this.name  = name;
                }

                /**
                 * 信号机构造方法
                 * id:编号
                 * number:顺序编号
                 * name:信号机名称
                 * circle:周期
                 * speedzx:正向速度
                 * speedfx:反向速度
                 * distance:距离(与基准点)
                 * pharsenum:相位数组
                 * zhengxiang:周期 相位
                 * fanxiang:周期 相位
                 */
                function Sig(id,number,name,circle,speedzx,speedfx,distance,phasenum,zhengxiang,fanxiang)
                {
                	this.id = id;
                    this.number = number;
                    this.name = name;
                    this.circle = circle;
                    this.speedzx = speedzx*10/36;//公里/h 换算为 m/s
                    this.speedfx = speedfx*10/36;
                    this.distance = distance;
                    this.phasenum = phasenum;
                    this.zhengxiang = zhengxiang;// _a_  0 : 周期  相位
                    this.fanxiang = fanxiang;// _c_  0 : 周期  相位
                }


                function initSigs()
                {
                    console.log("----------------initSigs----------");
                    /*
                    var sig1 = new Sig(1,0,'test1',20,40,50,0,[0.33,0.33,0.33],"_a_0","_c_0");//基准点
                    sigs.push(sig1);
                    var sig2 = new Sig(2,1,'test2',22,60,60,500,[0.25,0.25,0.25,0.25],"_a_0",null);
                    sigs.push(sig2);
                    var sig3 = new Sig(3,2,'test3'23,,0,0,1500,[0.166,0.166,0.166,0.166,0.166,0.166],"_a_0",null);//最后一个信号机没有正向反向速度
                    sigs.push(sig3);
                    */
                    for(var i =0;i<jsonSigs.length;i++)
                    {
                    	var jsonSig = jsonSigs[i];
                    	var sig = new Sig(jsonSig.sigid,parseInt(jsonSig.signumber),jsonSig.signame,parseInt(jsonSig.sigcircle),jsonSig.zxspeed,jsonSig.fxspeed,jsonSig.distance,jsonSig.pharseArray,jsonSig.zxpharse,jsonSig.fxpharse);//基准点
                    	sigs.push(sig);
                    }
                    console.log(sigs);
                }

            //构建sig div
             function createDivs()
             {

               // console.log("------------------createDiv----------");
                 for(var i=0;i<sigs.length;i++)
                 {

                     var sigdiv=$('<div>'+sigs[i].name+'</div>');             //信号机 div
                     var sigtimediv=$('<div></div>');                      //信号机开始时间 div
                     var sigdistance=$('<div></div>');                      //信号机离基准路口距离

                     sigdiv.addClass('initsig');
                     sigtimediv.addClass('initsigtime');
                     sigdistance.addClass('initsigdistance');

                     sigdiv.attr('id','sig'+i);
                     sigtimediv.attr('id','sigtime'+i);
                     sigdistance.attr('id','sigdistance'+i);

                     //相位周期a div
                    for(var j=0;j<sigs[i].phasenum.length;j++)
                    {
                        var tdiv=$('<div>'+'t'+j+'</div>');            //创建一个子div
                        tdiv.addClass("initsigt");
                        tdiv.attr('id',sigs[i].number+'_a_'+j);//给子div设置id
                        //childdiv.addclass('childdiv');          //添加css样式
                        tdiv.appendTo(sigdiv);            //将子div添加到父div中
                    }

                     //相位周期b div
                     for(var j=0;j<sigs[i].phasenum.length;j++)
                     {
                         var tdiv=$('<div>'+'t'+j+'</div>');            //创建一个子div
                         tdiv.addClass("initsigt");
                         tdiv.attr('id',sigs[i].number+'_b_'+j);//给子div设置id
                         //childdiv.addclass('childdiv');          //添加css样式
                         tdiv.appendTo(sigdiv);            //将子div添加到父div中
                     }
                     
                     //相位周期c div
                     for(var j=0;j<sigs[i].phasenum.length;j++)
                     {
                         var tdiv=$('<div>'+'t'+j+'</div>');            //创建一个子div
                         tdiv.addClass("initsigt");
                         tdiv.attr('id',sigs[i].number+'_c_'+j);//给子div设置id
                         //childdiv.addclass('childdiv');          //添加css样式
                         tdiv.appendTo(sigdiv);            //将子div添加到父div中
                     }

                     sigdiv.appendTo($("#topdiv"));              //将父div添加到body中
                     sigtimediv.appendTo($("#topdiv"));
                     sigdistance.appendTo($("#topdiv"));
                 }


                 /**
                  * 正向速度
                  */
                 for(var i=0;i<sigs.length-1;i++)
                 {
                     var sigspeed =  $('<div></div>');
                     sigspeed.addClass('initsigspeed');
                     sigspeed.attr('id','sigspeed'+i);//给子div设置id
                     sigspeed.appendTo($("#topdiv"));
                 }

                 for(var i=sigs.length;i<(sigs.length)*2-1;i++)
                 {
                     var sigspeed =  $('<div></div>');
                     sigspeed.addClass('initsigspeed');
                     sigspeed.attr('id','sigspeed'+i);//给子div设置id
                     sigspeed.appendTo($("#topdiv"));
                 }

             }

            //设置div
            function setDivs(sigs)
            {
                //console.log("------------------setDivs----------");
                for(var i=0;i<sigs.length;i++)
                {
                    //初始化信号机div 在坐标轴的位置
                    //基准点坐标  x-pixdiffX y-pixdiffY(测试下来正好对应 坐标轴的左下角0的位置s)
                    if(i==0)
                    {
                        $("#sig"+i).css("margin-left",pixdiffX);//sig 横坐标
                        $("#sig"+i).css("margin-top",pixdiffY);//sig 纵坐标
                        $("#sigdistance"+i).css("margin-top",pixdiffY);
                        $("#sigtime"+i).css("margin-left",pixdiffX);
                    }else
                    {
                        var timeX = getSigBeginTime(sigs[i]);
                        $("#sig"+i).css("margin-left",timeX+pixdiffX);//sig 横坐标
                        $("#sig"+i).css("margin-top",pixdiffY-sigs[i].distance/10);//sig 纵坐标
                        $("#sigdistance"+i).css("margin-top",pixdiffY-sigs[i].distance/10);
                        $("#sigtime"+i).css("margin-left",timeX+pixdiffX);
                    }


                    for(var j=0;j<sigs[i].phasenum.length;j++)
                    {
                        var phasewidth = sigs[i].phasenum[j]*pixinitsigWidth;
                        if(j%2==0)
                        {
                            $("#"+sigs[i].number+"_a_"+j).css("background","#07FCE5");
                            $("#"+sigs[i].number+"_a_"+j).css("width",phasewidth);
                            $("#"+sigs[i].number+"_b_"+j).css("background","#07FCE5");
                            $("#"+sigs[i].number+"_b_"+j).css("width",phasewidth);
                            $("#"+sigs[i].number+"_c_"+j).css("background","#07FCE5");
                            $("#"+sigs[i].number+"_c_"+j).css("width",phasewidth);
                        }else
                        {
                            $("#"+sigs[i].number+"_a_"+j).css("width",phasewidth);
                            $("#"+sigs[i].number+"_b_"+j).css("width",phasewidth);
                            $("#"+sigs[i].number+"_c_"+j).css("width",phasewidth);
                        }
                    }
                }

            }

            function setSpeedDivs(dots)
            {
               // console.log("------------------setSpeedDivs----------");
                /**
                 * 正向速度
                 */
                for(var i=0;i<dots.length/2-1;i++)
                {
                    var mleft = (dots[i].x+dots[i+1].x)/2;
                    var mtop = (dots[i].y+dots[i+1].y)/2;

                    //console.log("正向速度"+(dots[i].y-dots[i+1].y)*10/(dots[i+1].x-dots[i].x));

                    $("#sigspeed"+i).css("margin-top", mtop);
                    $("#sigspeed"+i).css("margin-left",mleft);
                     if(dots[i].y-dots[i+1].y<0)
                     {
                     	alert("距离设置不正确,请重新调整距离");
                     }else
                     {
                     	   if(dots[i+1].x-dots[i].x<0)
                    {
                    	alert("相位对应冲突,请重新调整相位");
	                    }else
	                    {
	                    	  $("#sigspeed"+i).text("正:"+ parseInt((dots[i].y-dots[i+1].y)*10/(dots[i+1].x-dots[i].x)*36/10));
	                    }
                     	
                     }
                }

                /**
                 * 反向速度
                 */
                for(var i=dots.length/2;i<dots.length-1;i++)
                {
                    var mleft = (dots[i].x+dots[i+1].x)/2;
                    var mtop = (dots[i].y+dots[i+1].y)/2;


                    $("#sigspeed"+i).css("margin-top", mtop);
                    $("#sigspeed"+i).css("margin-left",mleft);
                    
                    if(dots[i].y-dots[i+1].y<0)
                     {
                     	alert("距离设置不正确,请重新调整距离");
                     }else
                     {
                     	   if(dots[i].y-dots[i+1].y<0||dots[i].x-dots[i+1].x<0)
		                    {
		                    	alert("相位对应冲突,请重新调整相位");
		                    }else
		                    {
		                    	 $("#sigspeed"+i).text("反:"+  parseInt((dots[i].y-dots[i+1].y)*10/(dots[i].x-dots[i+1].x)*36/10)     );
		                    }
                     }
                }
                return;

            }

            /**
             * 获得信号机开始时间在坐标轴的显示的位置
             * @param i
             * @returns {number}
             */
            function getSigBeginTime(sig)
            {
                var time = 0;
                for(var j=1;j<=sig.number;j++)//信号机编号
                {
                   // console.log( "距离："+ sigs[j].distance,"正向速度:"+ sigs[j-1].speedzx,"时间:"+sigs[j].distance/sigs[j-1].speedzx);
                    time = time + (sigs[j].distance-sigs[j-1].distance)/sigs[j-1].speedzx;// 当前的距离 除去 上一个信号机设置的正向速度
                }

                var phasenumber = sig.zhengxiang.substring(sig.zhengxiang.length-1,sig.zhengxiang.length);//相位编号
				// console.log("phasenumber:"+phasenumber);
                var timeT = 0;//相位时间
                for(var z=1;z<=parseInt(phasenumber);z++)
                {
                    timeT = timeT+sig.phasenum[z-1]*cirleTime;
                }
                /*
                console.log("sig"+sig.number+":",time,timeT,cirleTime,time-timeT);
                time = (time-timeT+cirleTime)%cirleTime;
                var circleNumber = (time-timeT+cirleTime)/cirleTime;*/
           	    time = time-timeT;
                //（距离/速度 - 前几个相位的执行时间 + 周期时间）%周期；
                return time;
            }

            /**
             * 获得信号机反向时坐标轴的显示的位置
             * @param sig
             * @returns {number}
             */
            function getSigBackTime(sig)
            {
                var time = 0;
                for(var j=1;j<=sig.number;j++)//信号机编号
                {
                    // console.log( "距离："+ sigs[j].distance,"正向速度:"+ sigs[j-1].speedzx,"时间:"+sigs[j].distance/sigs[j-1].speedzx);
                    time = time + (sigs[j].distance-sigs[j-1].distance)/sigs[j-1].speedfx;// 当前的距离 除去 上一个信号机设置的正向速度
                }
                return time;
            }

            /**
             * 改变速度
             * @param index
             */
            function changeSpeed(index)
            {
          //    console.log("------------------changeSpeed----------");
                if(index==sigs.length-1)
                {
                    /**
                     * 正速度
                     */
                    var  i = index-1;
                    var mleft = (dots[i].x+dots[i+1].x)/2;
                    var mtop = (dots[i].y+dots[i+1].y)/2;

                    $("#sigspeed"+i).css("margin-top", mtop);
                    $("#sigspeed"+i).css("margin-left",mleft);
                    $("#sigspeed"+i).text("正:"+parseInt((dots[i].y-dots[i+1].y)*10/(dots[i+1].x-dots[i].x)*36/10));


                    /**
                     * 反向速度
                     */
                     i = index-1+dots.length/2;
                    var mleft = (dots[i].x+dots[i+1].x)/2;
                    var mtop = (dots[i].y+dots[i+1].y)/2;


                    $("#sigspeed"+i).css("margin-top", mtop);
                    $("#sigspeed"+i).css("margin-left",mleft);
                    $("#sigspeed"+i).text("反:"+parseInt((dots[i].y-dots[i+1].y)*10/(dots[i].x-dots[i+1].x)*36/10));
                }else
                {
                    //下部分
                    /**
                     * 正速度
                     */
                    var  i = index-1;
                    var mleftDown_zx = (dots[i].x+dots[i+1].x)/2;
                    var mtopDown_zx = (dots[i].y+dots[i+1].y)/2;


                    $("#sigspeed"+i).css("margin-top", mtopDown_zx);
                    $("#sigspeed"+i).css("margin-left",mleftDown_zx);
                    $("#sigspeed"+i).text("正:"+parseInt((dots[i].y-dots[i+1].y)*10/(dots[i+1].x-dots[i].x)*36/10));

                    /**
                     * 反向速度
                     */
                    i = index-1+dots.length/2;
                    var mleftDown_fx = (dots[i].x+dots[i+1].x)/2;
                    var mtopDown_fx = (dots[i].y+dots[i+1].y)/2;



                    $("#sigspeed"+i).css("margin-top", mtopDown_fx);
                    $("#sigspeed"+i).css("margin-left",mleftDown_fx);
                    $("#sigspeed"+i).text("反:"+parseInt((dots[i].y-dots[i+1].y)*10/(dots[i].x-dots[i+1].x)*36/10));


                    //上部分
                    /**
                     * 正速度
                     */
                    var  j = index;
                    var mleftUp_zx = (dots[j].x+dots[j+1].x)/2;
                    var mtopUp_zx = (dots[j].y+dots[j+1].y)/2;

                    $("#sigspeed"+j).css("margin-top", mtopUp_zx);
                    $("#sigspeed"+j).css("margin-left",mleftUp_zx);
                    $("#sigspeed"+j).text("正:"+parseInt((dots[j].y-dots[j+1].y)*10/(dots[j+1].x-dots[j].x)*36/10));



                    /**
                     * 反向速度
                     */
                    j = index+dots.length/2;
                    var mleftUp_fx = (dots[j].x+dots[j+1].x)/2;
                    var mtopUp_fx = (dots[j].y+dots[j+1].y)/2;


                    $("#sigspeed"+j).css("margin-top", mtopUp_fx);
                    $("#sigspeed"+j).css("margin-left",mleftUp_fx);
                    $("#sigspeed"+j).text("反:"+parseInt((dots[j].y-dots[j+1].y)*10/(dots[j].x-dots[j+1].x)*36/10));

                }


            }
            
            $("#setPharseTime").click(function(){
            
            	
            	var sids = "";
            	var msg = "";
            	
            	for(var i=0;i<sigs.length;i++)
            	{
            		if(!isNaN($("#sigtime"+i).text()))
            		{
            			 msg = msg + sigs[i].id+":"+  parseInt($("#sigtime"+i).text())+",";
            		}
            	}
            	//console.log(orderid,timetype,begintime,msg);
            	$.ajax({   
		            url:'setPharseTime',//这里是你的action或者servlert的路径地址   
		            type:'post', //数据发送方式  
		            dataType:'json',
		            data: { "orderid":orderid,"timetype":timetype,"begintime":begintime,"dates":msg},  
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
		            		alert('无电缆联动设置成功');   
		            	}
		            }  
		   	    });  
            	
            	
            });

        });
        
     
        
        
