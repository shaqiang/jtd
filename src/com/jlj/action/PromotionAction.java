package com.jlj.action;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mina.TimeServerHandler;

import org.apache.mina.core.session.IoSession;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.vo.AjaxMsgVO;
import com.opensymphony.xwork2.ActionSupport;

@Component("promotionAction")
@Scope("prototype")
public class PromotionAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	
	private String centerIp;
	private String centerPort;
	private String sigNumber;
	
	
	/**
	 * 跳转至升级界面
	 * @return
	 * @throws UnknownHostException 
	 */
	public String promotion() throws UnknownHostException
	{
		if(this.getCurrrenSession(sigNumber)!=null){
			String address = InetAddress.getLocalHost().getHostAddress();   
			System.out.println(address);
			if(address!=null&&!address.equals(""))
			{
				centerIp = address;
			}
			return "cssz-sj";
		}else
		{
			String errorMsg="信号机["+sigNumber+"]连接异常,检查信号机是否断开.";
			request.put("errorMsg", errorMsg);
			return "index";
		}
	}
	
	
	/**
	 * 升级及下发
	 * @return
	 */
	public String promotionSig()
	{
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		IoSession currrenSession =this.getCurrrenSession(sigNumber);
		/*System.out.println("正在升级.....");
		System.out.println(centerIp);
		System.out.println(centerPort);*/
		int port = Integer.valueOf(centerPort);
		byte send_byte[] = new byte[14];
		send_byte[0] = (byte) 0xff;
		send_byte[1] = (byte) 0xff;
		send_byte[2] = (byte) 0xff;
		send_byte[3] = (byte) 0xff;
		send_byte[4] = (byte) 0x01;
		send_byte[5] = (byte) 0xF0;
		send_byte[6] = (byte) 0xA2;
		send_byte[7] = (byte) 0x17;
		send_byte[8] = (byte) 0x00;
		send_byte[9] = (byte) 0x0A;
		send_byte[10] = (byte) (port>>8);
		send_byte[11] = (byte) (port&0xff);
		
		 int k = 0;
		 for( int i = 4; i < send_byte.length-2; i++){
			 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
			//System.out.println();
		  k += send_byte[i]&0xFF;
		 }
		 
	       for (int i = 0; i < 2; i++) {  
	    	   send_byte[send_byte.length-i-1]  = (byte) (k >>> (i * 8));  
	       }  
		
	   	System.out.println("=======================下发升级命令========================================");
		
		for (int i3 = 0; i3 < send_byte.length; i3++) {
			System.out.print(send_byte[i3]);
		}
		System.out.println("");
		System.out.println("========================下发升级命令=======================================");
		
		currrenSession.write(send_byte);
	       
		//send_byte[12] = (byte) 0xff;
		//send_byte[13] = (byte) 0xff;
		return null;
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


	public String getCenterIp() {
		return centerIp;
	}


	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}


	public String getCenterPort() {
		return centerPort;
	}


	public void setCenterPort(String centerPort) {
		this.centerPort = centerPort;
	}


	public String getSigNumber() {
		return sigNumber;
	}


	public void setSigNumber(String sigNumber) {
		this.sigNumber = sigNumber;
	}

	
	
	
}
