package com.jlj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mina.DataConvertor;
import mina.TimeServerHandler;
import net.sf.json.JSONObject;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.model.Issuedcommand;
import com.jlj.model.Sig;
import com.jlj.model.Signpublicparam;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.util.Commands;
import com.jlj.vo.AjaxMsgVO;
import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.converters.basic.DateConverter;

@Component("sigpublicparamAction")
@Scope("prototype")
public class SignpublicparamAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	
	private ISigService sigService;
	private ISignpublicparamService sigpubparamService;
	private IIssuedcommandService issuedcommandService;
	
	private Signpublicparam sigpubparam;
	private Sig sig;
	private int sigpubid;//信号机公共参数Id
	//private String sigIp;//2015-6-24 修改项目 改为使用signumber（信号机编号）来标识信号机唯一性
	private String sigNumber;
	
	private Integer spetimeable;
	private Integer suntimeable;
	
	public String suretime(){
		//System.out.println("1-获取当前时间的各个数据--------------------------------");
		/*
		 * 项目调整
		 * sigIp 改为 sigNumber 2015-06-24
		 */
		/*	sigIp = (String) session.get("sigIp");
		if(sigIp==null){
			String errorMsg="IP地址失效,请重新进去信号机,进行设置";
			request.put("errorMsg", errorMsg);
			return "index";
		}*/
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		if(this.getCurrrenSession(sigNumber)==null)
		{
			AjaxMsgVO msgVO = new AjaxMsgVO();
			String message = "信号机["+sigNumber+"]连接异常,检查信号机是否断开.";
			msgVO.setMessage(message);
			JSONObject jsonObj = JSONObject.fromObject(msgVO);
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(jsonObj.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return NONE;
		}
		//System.out.println("2-获取数据库数据，下发命令--------------------------------");
		updateJiaoShiBytes(sigNumber,getCurrrenSession(sigNumber));
		//System.out.println("3-调阅新命令和新数据，更新数据库--------------------------------");
		//Commands.executeCommand(28,this.getCurrrenSession(sigIp));//编号28 校时调阅
		return NONE;
	}
	
	public String suretimeSometime()
	{
		
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		if(this.getCurrrenSession(sigNumber)!=null){
			Timer timer = new Timer("tq",true);  
	        timer.schedule(new TimerTask() {  
	  
	            @Override  
	            public void run() {  
	            	
	            }
	              
	        },  0,  1*1000); 
			return NONE;
		}
		else
		{
			String errorMsg="信号机["+sigNumber+"]连接异常,检查信号机是否断开.";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		
	}
	
	
	
	//跳转 一般参数页面  
	public String publicParam() {
		/*
		 * 项目调整
		 * sigIp 改为 sigNumber 2015-06-24
		 */
		/*	sigIp = (String) session.get("sigIp");
		if(sigIp==null){
			String errorMsg="IP地址失效,请重新进去信号机,进行设置";
			request.put("errorMsg", errorMsg);
			return "index";
		}*/
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		//根据sigNumber来查询信号机公共参数
		sigpubparam = sigpubparamService.getPublicparamByNumber(sigNumber);
		
		if(sigpubparam!=null&&sigpubparam.getNumber()!=null)
		{
			//判断信号机公共参数中的signid是否为空，如果为空则设置公共参数中的signid对应sig
			if(sigpubparam.getSig()==null)
			{
				sig = sigService.querySigByNumber(sigNumber);
				sigpubparam.setSig(sig);
				sigpubparamService.update(sigpubparam);//公共参数中设置信号机id
			}
			session.put("sigNumber", sigNumber);//从地图中进入信号机，将信号机sigNumber传入session
			initPublicParamJSP(sigpubparam.getWorkingset());
			//判断是否首次进入一般参数,如果首次进入一般参数则需设置公共参数中的signid对应sig
			return "cssz-cs";
		}else
		{
			String errorMsg="未获得一般参数信息，请连接信号机并初始化所有参数.";
			request.put("errorMsg", errorMsg);
			return "index";
		}
				
	}
	
	//前台ajax初始化所有参数前判断当前信号机iosession是否为空
	public String checkIOSession()
	{
		
		if(this.getCurrrenSession(sigNumber)==null)
		{
			AjaxMsgVO msgVO = new AjaxMsgVO();
			String message = "信号机["+sigNumber+"]连接异常,检查信号机是否断开.";
			msgVO.setMessage(message);
			JSONObject jsonObj = JSONObject.fromObject(msgVO);
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(jsonObj.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	//工作日设置处理
	private void initPublicParamJSP(int workingset)
	{

		switch(workingset)
		{
			case 0:
				spetimeable = 0;
				suntimeable = 0;
				break;
			case 1:
				spetimeable = 1;
				suntimeable = 0;
				break;
			case 2:
				spetimeable = 0;
				suntimeable = 1;
				break;
			case 3:
				spetimeable = 1;
				suntimeable = 1;
				break;
			default:
				spetimeable = 0;
				suntimeable = 0;
					
		}
		
	}

	
	
	//处理一般参数jsp显示
	private void setPublicParamJSP()
	{
		//行人请求处理
		if(sigpubparam.getXyxr()==null)
		{
			sigpubparam.setXyxr(0);
		}
		
		System.out.println("周日"+suntimeable);
		System.out.println("特殊日"+spetimeable);
		//工作日设置处理
		if(spetimeable==null)
		{
			spetimeable = 0;
		}
		if(suntimeable==null)
		{
			suntimeable = 0;
		}
		if(spetimeable==1&&suntimeable==1)
		{
			sigpubparam.setWorkingset(3);
		}
		if(spetimeable==1&&suntimeable!=1)
		{
			sigpubparam.setWorkingset(2);
		}
		if(spetimeable!=1&&suntimeable==1)
		{
			sigpubparam.setWorkingset(1);
		}
		if(spetimeable!=1&&suntimeable!=1)
		{
			sigpubparam.setWorkingset(0);
		}
		
		//周日 处理
		if(sigpubparam.getMon()==null)
		{
			sigpubparam.setMon(0);
		}
		if(sigpubparam.getTue()==null)
		{
			sigpubparam.setTue(0);
		}
		if(sigpubparam.getWes()==null)
		{
			sigpubparam.setWes(0);
		}
		if(sigpubparam.getThir()==null)
		{
			sigpubparam.setThir(0);
		}
		if(sigpubparam.getFra()==null)
		{
			sigpubparam.setFra(0);
		}
		if(sigpubparam.getSata()==null)
		{
			sigpubparam.setSata(0);
		}
		if(sigpubparam.getSun()==null)
		{
			sigpubparam.setSun(0);
		}
	}
	
	
	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		return SUCCESS;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		setPublicParamJSP();
		/*
		 * 项目调整
		 * sigIp 改为 sigNumber 2015-06-24
		 */
		/*	sigIp = (String) session.get("sigIp");
		if(sigIp==null){
			String errorMsg="IP地址失效,请重新进去信号机,进行设置";
			request.put("errorMsg", errorMsg);
			return "index";
		}*/
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		System.out.println("行人请求"+sigpubparam.getXyxr());
		if(this.getCurrrenSession(sigNumber)!=null){
			//System.out.println("1-获取界面数据，更新数据库--------------------------------");
			//sigpubparamService.update(sigpubparam);//修改-from lq
			//System.out.println("2-获取数据库数据，下发命令--------------------------------");
			updateSigPublicparamBytes(sigNumber,getCurrrenSession(sigNumber));
			Thread.sleep(2000);//线程等待2秒让信号机进行调阅
			//System.out.println("3-调阅新命令和新数据，更新数据库--------------------------------");//("+newDatas+")
			Commands.executeCommand(5,this.getCurrrenSession(sigNumber));//编号5 公共参数调阅
			String errorMsg="修改一般参数成功.";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		else
		{
			String errorMsg="信号机["+sigNumber+"]连接异常,检查信号机是否断开.";
			request.put("errorMsg", errorMsg);
			return "index";
		}
	}
	
	private void updateSigPublicparamBytes(String sigNumber,IoSession currrenSession) {
		Sig sig1 = sigService.querySigByNumber(sigNumber);
		if(sig1!=null){
			Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig1.getId(),5);//编号5
			if(issuedcommand!=null){
				//0-获取新数据
				int qchdtime = sigpubparam.getQchdtime();//清场红灯Red_Clearance_Time//data[11]
				int kjhstime = sigpubparam.getKjhstime();//开机黄闪Yellow_Flash_Time//data[12]
				String number = sigpubparam.getNumber();//number//data[13]
				String comparam = sigpubparam.getComparam();//comparam//data[15]
				int checkflow = sigpubparam.getCheckflow();//checkflow//data[16]
				String innermark = sigpubparam.getInnermark();//innermark//data[17]
				int workingset = sigpubparam.getWorkingset();//Workingset//data[18]
				Integer[] days = sigpubparam.getDays();//SigSunTime[]
				for (int i = 0; i < days.length; i++) {
					System.out.println(days[i]);
				}
				int gmintime = sigpubparam.getGmintime();//gmintime//data[26]
				int gmaxtime = sigpubparam.getGmaxtime();//gmaxtime//data[27]
				int zdbctime = sigpubparam.getZdbctime();//zdbctime//data[28]
				int countdownmode = sigpubparam.getCountdownmode();//countdownmode//data[29]
				int xrfxtime = sigpubparam.getXrfxtime();//xrfxtime//data[42]
				System.out.println("下发的行人请求："+	sigpubparam.getXyxr());
				int cycle = sigpubparam.getCycle();//cycle//data[43]
				int xyxr = sigpubparam.getXyxr();//xyxr//data[44]
				Integer[] getSpecialmonths = sigpubparam.getSpecialmonths();//SigSpecialTime[][]
				Integer[] specialdays = sigpubparam.getSpecialdays();//SigSpecialTime[][]
				
				//1-获取数据库中保存的命令
				String datastr= issuedcommand.getDatas();//普通参数-原始命令
				//System.out.println("SigPublicparam数据库中datas================================"+datastr);
				//2-获取的新数据，包装成新命令，并修改数据库“命令表issuedCommand”-from jlj
				
				byte[] msendDatas = DataConvertor.decode(datastr,140);
				
				msendDatas[6] = (byte) 0x82;
				msendDatas[7] = (byte) 0x01;
				msendDatas[11] = (byte)qchdtime;
				msendDatas[12] = (byte)kjhstime;
				System.out.println("the number is "+number +"test is "+Integer.valueOf(number)+"test1 is"+(Integer.valueOf(number)&0xff));
				
				msendDatas[13] = (byte) (Integer.valueOf(number)>>8);
				msendDatas[14] = (byte) (Integer.valueOf(number)&0xff);
				msendDatas[15] = Byte.parseByte(comparam, 16);
				msendDatas[16] = (byte)checkflow;
				//msendDatas[17] = Byte.parseByte(innermark, 16);
				msendDatas[18] = (byte)workingset;
				
				msendDatas[19] = 0;
				
				for (int i = 0; i < 7; i++) {
					msendDatas[19] |= days[i]<<(6-i);
				}
				
				msendDatas[26] = (byte) gmintime;
				msendDatas[27] = (byte) gmaxtime;
				msendDatas[28] = (byte) zdbctime;
				msendDatas[29] = (byte) countdownmode;
				msendDatas[42] = (byte) xrfxtime;
				msendDatas[43] = (byte) cycle;
				msendDatas[44] = (byte) xyxr;
				
				for( int j =0 ;j < 24;j++){
					msendDatas[58+j*2]   =	getSpecialmonths[j].byteValue();
					msendDatas[58+j*2+1] = specialdays[j].byteValue() ;
				}
				
				 int k = 0;
				 for( int i = 4; i < msendDatas.length-2; i++){
					 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
					//System.out.println();
				  k += msendDatas[i]&0xFF;
				 }
				 
			 
		         
			       for (int i = 0; i < 2; i++) {  
			    	   msendDatas[msendDatas.length-i-1]  = (byte) (k >>> (i * 8));  
			       }  
				
				//System.out.println("=======================公共参数下发========================================");
			       System.out.print(DataConvertor.bytesToHexString(msendDatas));
				for (int i = 37; i < 41; i++) {
					System.out.print((msendDatas[i]&0xff)+" ");
				}
				//System.out.println("");
				//System.out.println("========================公共参数下发=======================================");
				//System.out.println("SigPublicparam the send str is "+DataConvertor.bytesToHexString(msendDatas));
				//3-命令下发-from sl
				
				byte sendtest[] = new byte[msendDatas.length+1];
				sendtest[0] = 0x01;
				for (int i = 0; i < msendDatas.length; i++) {
					sendtest[i+1] = msendDatas[i];
				}
				currrenSession.write(sendtest);
				
				//currrenSession.write(IoBuffer.wrap(msendDatas));
			}
		}
		
		
		
	}

	private void updateJiaoShiBytes(String sigNumber,IoSession currrenSession) {
		Sig sig1 = sigService.querySigByNumber(sigNumber);
		if(sig1!=null){
//			Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig1.getId(),28);//编号28
//			if(issuedcommand!=null){
//				//0-获取新数据
//				
//				//1-获取数据库中保存的命令
//				String datastr= issuedcommand.getDatas();//普通参数-原始命令
//				System.out.println("Jiaoshi数据库中datas================================"+datastr);
				//byte[] msendDatas = DataConvertor.decode(datastr,20);
				byte[] msendDatas = new byte[20];
				msendDatas[0] = (byte) 0xff;
				msendDatas[1] = (byte) 0xff;
				msendDatas[2] = (byte) 0xff;
				msendDatas[3] = (byte) 0xff;
				msendDatas[4] = (byte) 0x01; //有问题
				msendDatas[5] = (byte) 0xf0; //有问题
				msendDatas[6] = (byte)0x81 ;
				msendDatas[7] = 0x00;
				msendDatas[8] = 0x00;
				msendDatas[9] = 0x10;
				Calendar nowdate = Calendar.getInstance();
					
				msendDatas[10] = (byte) (nowdate.get(Calendar.YEAR)%2000);
			    msendDatas[11] = (byte) (nowdate.get(Calendar.MONTH)+1);
			    msendDatas[12] = (byte) nowdate.get(Calendar.DAY_OF_MONTH);
			    if(nowdate.get(Calendar.DAY_OF_WEEK )<2){
			    	msendDatas[13] = 0x07;
			    }else if(nowdate.get(Calendar.DAY_OF_WEEK )>1){
			    	msendDatas[13] = (byte)( nowdate.get(Calendar.DAY_OF_WEEK)-1);
			    }
			    
			    
			    msendDatas[14] = (byte) nowdate.get(Calendar.HOUR_OF_DAY);
			    msendDatas[15] = (byte) nowdate.get(Calendar.MINUTE);
			    msendDatas[16] = (byte) nowdate.get(Calendar.SECOND);
				
				System.out.println("本地时间是"+msendDatas[10]+"年"+msendDatas[11]+"月"+msendDatas[12]+"日"+msendDatas[14]+"时"+msendDatas[15]+"分"+msendDatas[16]+"秒"+"星期"+msendDatas[13]);
			    
			    int k = 0;
				 for( int i1 = 4; i1 < msendDatas.length-2; i1++){
					 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
					//System.out.println();
				  k += msendDatas[i1]&0xFF;
				 }
				 
			       for (int i2 = 0; i2 < 2; i2++) {  
			    	   msendDatas[msendDatas.length-i2-1]  = (byte) (k >>> (i2 * 8));  
			       }  
				
				System.out.println("=======================校时下发========================================");
				
				for (int i3 = 0; i3 < msendDatas.length; i3++) {
					System.out.print(msendDatas[i3]);
				}
				System.out.println("");
				System.out.println("========================校时下发=======================================");
				
				
				
				//2-获取的新数据，包装成新命令，并修改数据库“命令表issuedCommand”-from jlj
				
				//3-命令下发-from sl
				currrenSession.write(IoBuffer.wrap(msendDatas));
//			}
		}
		
		
		
	}
	
	public IoSession getCurrrenSession(String sigNumber)
	{
		for(IoSession session : TimeServerHandler.iosessions)
		{
			if(session.getAttribute("number")!=null)
			{
				if(session.getAttribute("number").equals(sigNumber))
				{
					return session;
				}
			}
		}
		return null;
	}
	

	// get、set-------------------------------------------

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Sig getSig() {
		return sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	public ISigService getSigService() {
		return sigService;
	}

	@Resource
	public void setSigService(ISigService sigService) {
		this.sigService = sigService;
	}


	public ISignpublicparamService getSigpubparamService() {
		return sigpubparamService;
	}

	@Resource
	public void setSigpubparamService(ISignpublicparamService sigpubparamService) {
		this.sigpubparamService = sigpubparamService;
	}


	public Signpublicparam getSigpubparam() {
		return sigpubparam;
	}


	public void setSigpubparam(Signpublicparam sigpubparam) {
		this.sigpubparam = sigpubparam;
	}

	public int getSpetimeable() {
		return spetimeable;
	}

	public void setSpetimeable(int spetimeable) {
		this.spetimeable = spetimeable;
	}

	public int getSuntimeable() {
		return suntimeable;
	}

	public void setSuntimeable(int suntimeable) {
		this.suntimeable = suntimeable;
	}

	/*public String getSigIp() {
		return sigIp;
	}

	public void setSigIp(String sigIp) {
		this.sigIp = sigIp;
	}*/
	
	
	public int getSigpubid() {
		return sigpubid;
	}

	public void setSigpubid(int sigpubid) {
		this.sigpubid = sigpubid;
	}

	public IIssuedcommandService getIssuedcommandService() {
		return issuedcommandService;
	}

	@Resource
	public void setIssuedcommandService(IIssuedcommandService issuedcommandService) {
		this.issuedcommandService = issuedcommandService;
	}
	public String getSigNumber() {
		return sigNumber;
	}
	public void setSigNumber(String sigNumber) {
		this.sigNumber = sigNumber;
	}
	
	
}
