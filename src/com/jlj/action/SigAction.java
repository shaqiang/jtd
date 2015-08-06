package com.jlj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mina.TimeServerHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.model.Greenconflict;
import com.jlj.model.Sig;
import com.jlj.model.SigPara;
import com.jlj.model.Usero;
import com.jlj.service.IDevlogService;
import com.jlj.service.IFlowService;
import com.jlj.service.IGreenconflictService;
import com.jlj.service.ISigService;
import com.jlj.util.Commands;
import com.jlj.vo.AjaxMsgVO;
import com.jlj.vo.ConflictVO;
import com.jlj.vo.SigStatus;
import com.opensymphony.xwork2.ActionSupport;

@Component("sigAction")
@Scope("prototype")
public class SigAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	public static int[][] trafficlights = new int[4][5];
	private static int[][] trafficlights_next = new int[4][5];
	public static int[] Countdown = new int[4];
	private ISigService sigService;
	private Sig sig;
	private int id;
	//public static String curruntSigIp;//2015-6-24 修改项目 改为使用signumber（信号机编号）来标识信号机唯一性
	public static String curruntSigNumber;
	public int curruntCommandId;
	private SigPara sigParas;
	private Long mkid;
	
	/*
	 * 绿冲突
	 */
	private List<Greenconflict> greens;
	private ConflictVO conflictVO;
	private IGreenconflictService greenService;
	/*
	 * json messsage
	 */
	private String dates;
	private int gltime;
	private int rltime;
	private int yltime;
	
	/*
	 * 其他service
	 */
	private IDevlogService devlogService;
	private IFlowService flowService;
	
	public String sigStatus() {
		Usero usero = (Usero)session.get("usero");
		 int userid = usero.getId();
		 List<Sig> usersigs = new ArrayList<Sig>();
		 if(usero.getUlimit()==0)
		 {
			 usersigs = sigService.getAllSigs();
		 }else
		 {
			 usersigs = sigService.querySigsByUser(userid);
		 }
		if (usersigs != null && usersigs.size() > 0) {
			List<SigStatus> sigstatuses = new ArrayList<SigStatus>();
			for (int i = 0; i < usersigs.size(); i++) {
				StringBuffer thestatus=new StringBuffer();
				Sig sigobj = usersigs.get(i);
//				if(sigobj.getMkid()!=null){
//					thestatus.append("<a href='sigAction!toTraffic?mkid="+sigobj.getMkid()+"' target='rightFrame'>");
//				}else{
//					thestatus.append("<a href=# onClick=alert('当前信号机异常') >");
//				}
				IoSession theSession =this.getCurrrenSession(sigobj.getNumber());
				if(theSession==null){
					thestatus.append("<font color=red>断开</font>");
				}else if(usersigs.get(i).getIserror()==1){
					thestatus.append("<font color=red>故障</font>");
				}else{
					thestatus.append("正常");
				}
//				thestatus.append("</a>");
				String url="#";
				if(sigobj.getMkid()!=null){
					url="sigAction!toTraffic?mkid="+sigobj.getMkid();
				}
				SigStatus sigstatus = new SigStatus(usersigs.get(i)
						.getName(), thestatus.toString(),url);
				if(sigobj.getLat()!=null&&!sigobj.getLat().equals("")&&sigobj.getLng()!=null&&!sigobj.getLng().equals("")){
					sigstatuses.add(sigstatus);
				}
				
			}
			// 将list转化成JSON对象
			JSONArray jsonArray = JSONArray.fromObject(sigstatuses);
			//System.out.println(jsonArray.toString());
			PrintWriter out;
			try {
				response.setCharacterEncoding("UTF-8"); 
				out = response.getWriter();
				out.print(jsonArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	public IoSession getCurrrenSession(String sigNumber)
	{
		/*System.out.println("SigAction 链接数=========================="+TimeServerHandler.iosessions.size());
		System.out.println("当前的编号是："+sigNumber);
		for(IoSession session : TimeServerHandler.iosessions)
		{
			System.out.println("所有session的编号是："+session.getAttribute("number"));
		}*/
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
	
	public String toTraffic() {
		//System.out.println(mkid);
		sig = sigService.loadByMkid(mkid);
		//System.out.println("sig================="+sig);
		if (sig != null) {
			
			//System.out.println(sig.getId());
			//curruntSigIp = sig.getIp();
			//session.put("sigIp", sig.getIp());// 从地图中进入信号机，将信号机ip传入session
			curruntSigNumber = sig.getNumber();
			setGreenConflict(sig.getId());
			session.put("sigNumber", curruntSigNumber);// 从地图中进入信号机，将信号机number传入session
		}
		return "traffic";
	}

	// 发送简单命令
	public String doCommand() {
		
		if(this.getCurrrenSession(curruntSigNumber)==null)
		{
			AjaxMsgVO msgVO = new AjaxMsgVO();
			String message = "信号机["+curruntSigNumber+"]连接异常,检查信号机是否断开.";
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
			return null;
		}else
		{
			String commandIdStr = req.getParameter("commandId");
			System.out.println("执行命令编号：" + commandIdStr + "   " + "信号机链接对象："
					+ TimeServerHandler.iosessions);
			if (commandIdStr != null) {
				curruntCommandId = Integer.parseInt(commandIdStr);
				if (curruntSigNumber != null && TimeServerHandler.iosessions != null
						&& TimeServerHandler.iosessions.size() > 0) {
					Commands.executeCommand(curruntCommandId, curruntSigNumber,
							TimeServerHandler.iosessions);
				}
			}
			return null;
		}
		
		
	}
	
	//获得绿冲突对象
	private void setGreenConflict(int sigid) {
		//System.out.println("sigid:"+sigid);
		greens = greenService.loadBySid(sigid);
		//System.out.println("greens:"+greens);
		if(greens!=null&&greens.size()==16)
		{
			conflictVO = new ConflictVO();
			for(int i=0;i<greens.size();i++)
			{
				String conflictname="";
				
				if(greens.get(i).getL00()!=null&&greens.get(i).getL00()==1)
				{
					conflictname = conflictname+"_0_0,";
				} if(greens.get(i).getL01()!=null&&greens.get(i).getL01()==1)
				{
					conflictname = conflictname+"_0_1,";
				} if(greens.get(i).getL02()!=null&&greens.get(i).getL02()==1)
				{
					conflictname = conflictname+"_0_2,";
				} if(greens.get(i).getL03()!=null&&greens.get(i).getL03()==1)
				{
					conflictname = conflictname+"_0_3,";
				} if(greens.get(i).getL10()!=null&&greens.get(i).getL10()==1)
				{
					conflictname = conflictname+"_1_0,";
				} if(greens.get(i).getL11()!=null&&greens.get(i).getL11()==1)
				{
					conflictname = conflictname+"_1_1,";
				} if(greens.get(i).getL12()!=null&&greens.get(i).getL12()==1)
				{
					conflictname = conflictname+"_1_2,";
				} if(greens.get(i).getL13()!=null&&greens.get(i).getL13()==1)
				{
					conflictname = conflictname+"_1_3,";
				} if(greens.get(i).getL20()!=null&&greens.get(i).getL20()==1)
				{
					conflictname = conflictname+"_2_0,";
				} if(greens.get(i).getL21()!=null&&greens.get(i).getL21()==1)
				{
					conflictname = conflictname+"_2_1,";
				} if(greens.get(i).getL22()!=null&&greens.get(i).getL22()==1)
				{
					conflictname = conflictname+"_2_2,";
				} if(greens.get(i).getL23()!=null&&greens.get(i).getL23()==1)
				{
					conflictname = conflictname+"_2_3,";
				} if(greens.get(i).getL30()!=null&&greens.get(i).getL30()==1)
				{
					conflictname = conflictname+"_3_0,";
				} if(greens.get(i).getL31()!=null&&greens.get(i).getL31()==1)
				{
					conflictname = conflictname+"_3_1,";
				} if(greens.get(i).getL32()!=null&&greens.get(i).getL32()==1)
				{
					conflictname = conflictname+"_3_2,";
				} if(greens.get(i).getL33()!=null&&greens.get(i).getL33()==1)
				{
					conflictname = conflictname+"_3_3,";
				}
				if(conflictname!=""&&conflictname.contains(","))
				{
					conflictname = conflictname.substring(0, conflictname.length()-1);
				}
				switch (i) {
				case 0:
					conflictVO.setC_00(conflictname);
					break;
				case 1:
					conflictVO.setC_01(conflictname);
					break;
				case 2:
					conflictVO.setC_02(conflictname);
					break;
				case 3:
					conflictVO.setC_03(conflictname);
					break;
				case 4:
					conflictVO.setC_10(conflictname);
					break;
				case 5:
					conflictVO.setC_11(conflictname);
					break;
				case 6:
					conflictVO.setC_12(conflictname);
					break;
				case 7:
					conflictVO.setC_13(conflictname);
					break;
				case 8:
					conflictVO.setC_20(conflictname);
					break;
				case 9:
					conflictVO.setC_21(conflictname);
					break;
				case 10:
					conflictVO.setC_22(conflictname);
					break;
				case 11:
					conflictVO.setC_23(conflictname);
					break;
				case 12:
					conflictVO.setC_30(conflictname);
					break;
				case 13:
					conflictVO.setC_31(conflictname);
					break;
				case 14:
					conflictVO.setC_32(conflictname);
					break;
				case 15:
					conflictVO.setC_33(conflictname);
					break;
				default:
					break;
				}
				//System.out.println(conflictname);
				
			}
			
		}
		
	}

	// 获得状态
	public String realtime() {
		/**
		 * trafficLigths[0-3]:表示一个红绿灯的各个方向依次为：0:东-》西,1:南-》北,2:西-》东,3:北-》南
		 * trafficLigths[0-3][0-4]：表示一个方向的5具体的灯： 0:左转灯,1: 直行灯 ,2:右转灯 ,3:人行道
		 * ,4:人行道 表示一个具体的灯： 3：红 2：黄 1：绿 0：灭 null：未知
		 */
		if (trafficlights != trafficlights_next) {
			trafficlights_next = trafficlights;
			String jsonString = "{\"success\":\"true\"" + ",\"l03\":\""
					+ trafficlights_next[0][3] + "\"" + // 东人行道
					",\"l23\":\"" + trafficlights_next[2][3] + "\"" + // 西人行道

					",\"l20\":\"" + trafficlights_next[2][0] + "\"" + // 西边左转灯
					",\"l21\":\"" + trafficlights_next[2][1] + "\"" + // 西边直行灯
					",\"l22\":\"" + trafficlights_next[2][2] + "\"" + // 西边右转灯

					",\"l00\":\"" + trafficlights_next[0][0] + "\"" + // 东边左转灯
					",\"l01\":\"" + trafficlights_next[0][1] + "\"" + // 东边直行灯
					",\"l02\":\"" + trafficlights_next[0][2] + "\"" + // 东边右转灯

					",\"l13\":\"" + trafficlights_next[1][3] + "\"" + // 南人行道
					",\"l33\":\"" + trafficlights_next[3][3] + "\"" + // 北人行道

					",\"l32\":\"" + trafficlights_next[3][2] + "\"" + // 北边右转灯
					",\"l31\":\"" + trafficlights_next[3][1] + "\"" + // 北边直行灯
					",\"l30\":\"" + trafficlights_next[3][0] + "\"" + // 北边左转灯

					",\"l11\":\"" + trafficlights_next[1][1] + "\"" + // 南边直行灯
					",\"l10\":\"" + trafficlights_next[1][0] + "\"" + // 南边左转灯
					",\"l12\":\"" + trafficlights_next[1][2] + "\"" + // 南边右转灯

					",\"dd\":\"" + trafficlights_next[0] + "\"" + // 东边倒计时
					",\"nd\":\"" + trafficlights_next[1] + "\"" + // 南边倒计时
					",\"xd\":\"" + trafficlights_next[2] + "\"" + // 西边倒计时
					",\"bd\":\"" + trafficlights_next[3] + "\"" + // 北边倒计时

					"}";
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(jsonString);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	/**
	 * 按指定相位运行
	 * @return
	 */
	private String sigNumber;
	public String runByPharse()
	{
		//0_2_3:3,0_0_3:3,0_3_3:*,*_*_*:*,*_*_*:3,0_0_0:3,0_3_2:*,*_*_*:*,*_*_*:3,0_1_0:3,0_1_1:3,0_1_2:3,0_2_0:3,0_2_1:3,0_2_2:3,0_1_3:3,
		//0
		//0
		//3
		/**
		 * data数组元素解释说明
		 * 4_0_3:1[解释 id_方向_灯: 灯色]
		 * 4  [解释 id(步序id都是偶数位)]
		 * 0  [方向(0:东-》西,1:南-》北,2:西-》东,3:北-》南]
		 * 3  [0:左转灯,1: 直行灯 ,2:右转灯 ,3:人行道]
		 * : 
		 * 1  [3：红 2：黄 1：绿 0：灭 null：未知]
		 */
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		IoSession currrenSession=this.getCurrrenSession(sigNumber);
		if(currrenSession==null)
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
		System.out.println(dates);
		System.out.println(gltime);//绿灯持续时间
		System.out.println(yltime);//黄灯持续时间
		System.out.println(rltime);//红灯持续时间
		
		byte send_byte[] = new byte[27+8+4];
		send_byte[0] = (byte) 0xff;
		send_byte[1] = (byte) 0xff;
		send_byte[2] = (byte) 0xff;
		send_byte[3] = (byte) 0xff;
		send_byte[4] = (byte) 0x01;
		send_byte[5] = (byte) 0xF0;
		send_byte[6] = (byte) 0xA2;
		send_byte[7] = (byte) 0x06;
		send_byte[8] = (byte) 0x00;
		send_byte[9] = (byte) 0x23;
		String[] solus = dates.split(",");
		for (int i = 0; i < solus.length; i++) {
			int stepid= Integer.parseInt(solus[i].substring(0, solus[i].indexOf("_")));
			int roadtype = Integer.parseInt(solus[i].substring(solus[i].indexOf("_")+1, solus[i].lastIndexOf("_")));
			int dengtype = Integer.parseInt(solus[i].substring(solus[i].lastIndexOf("_")+1, solus[i].indexOf(":")));
			String dengtypestr="";
			switch (dengtype) {
			case 0:
				dengtypestr = "leftcolor";
				break;
			case 1:
				dengtypestr = "linecolor";
				break;
			case 2:
				dengtypestr = "rightcolor";
				break;
			case 3:
				dengtypestr = "rxcolor";
				break;
			default:
				break;
			}
			int deng = Integer.parseInt(solus[i].substring(solus[i].indexOf(":")+1));
			System.out.println("stepid="+stepid+",fangxiang="+roadtype+",dengtype="+dengtype+",deng="+deng);
			
				
				switch (roadtype) {
				case 0:
					if(dengtype == 0){
						if(deng ==1){
							send_byte[10] = (byte) (send_byte[10]|0x20);
							send_byte[19] =  (byte) (send_byte[19]|0x40);
							
						}else if(deng == 2){
							send_byte[10] = (byte) (send_byte[10]|0x40);
						}else if(deng == 3){
							send_byte[10] = (byte) (send_byte[10]|0x80);
						}
						send_byte[28] =  (byte) (send_byte[28]|0x80);
					}else if(dengtype == 1){
						if(deng ==1){
							send_byte[10] = (byte) (send_byte[10]|0x04);
							send_byte[19] =  (byte) (send_byte[19]|0x08);
							
						}else if(deng == 2){
							send_byte[10] = (byte) (send_byte[10]|0x08);
						}else if(deng == 3){
							send_byte[10] = (byte) (send_byte[10]|0x10);
						}
						send_byte[28] =  (byte) (send_byte[28]|0x10);
					}else if(dengtype == 2){
						if(deng ==1){
							send_byte[11] = (byte) (send_byte[11]|0x20);
							send_byte[20] =  (byte) (send_byte[20]|0x40);
							
						}else if(deng == 2){
							send_byte[11] = (byte) (send_byte[11]|0x40);
						}else if(deng == 3){
							send_byte[11] = (byte) (send_byte[11]|0x80);
						}
						send_byte[29] =  (byte) (send_byte[29]|0x80);
					}else if(dengtype == 3){
						if(deng ==1){
							send_byte[11] = (byte) (send_byte[11]|0x0A);
							send_byte[20] =  (byte) (send_byte[20]|0x14);
							
						}else if(deng == 2){
						}else if(deng == 3){
							send_byte[11] = (byte) (send_byte[11]|0x14);
						}
						send_byte[29] =  (byte) (send_byte[29]|0x14);
					}
					break;
				case 1:
					if(dengtype == 0){
						if(deng ==1){
							send_byte[12] = (byte) (send_byte[12]|0x20);
							send_byte[21] =  (byte) (send_byte[21]|0x40);
						}else if(deng == 2){
							send_byte[12] = (byte) (send_byte[12]|0x40);
						}else if(deng == 3){
							send_byte[12] = (byte) (send_byte[12]|0x80);
						}
						send_byte[30] =  (byte) (send_byte[30]|0x80);
					}else if(dengtype == 1){
						if(deng ==1){
							send_byte[12] = (byte) (send_byte[12]|0x04);
							send_byte[21] =  (byte) (send_byte[21]|0x08);
							
						}else if(deng == 2){
							send_byte[12] = (byte) (send_byte[12]|0x08);
						}else if(deng == 3){
							send_byte[12] = (byte) (send_byte[12]|0x10);
						}
						send_byte[30] =  (byte) (send_byte[30]|0x10);
					}else if(dengtype == 2){
						if(deng ==1){
							send_byte[13] = (byte) (send_byte[13]|0x20);
							send_byte[22] =  (byte) (send_byte[22]|0x40);
							
						}else if(deng == 2){
							send_byte[13] = (byte) (send_byte[13]|0x40);
						}else if(deng == 3){
							send_byte[13] = (byte) (send_byte[13]|0x80);
						}
						send_byte[31] =  (byte) (send_byte[31]|0x80);
					}else if(dengtype == 3){
						if(deng ==1){
							send_byte[13] = (byte) (send_byte[13]|0x0A);
							send_byte[22] =  (byte) (send_byte[22]|0x14);
							
						}else if(deng == 2){
						}else if(deng == 3){
							send_byte[13] = (byte) (send_byte[13]|0x14);
						}
						send_byte[31] =  (byte) (send_byte[31]|0x14);
					}
					break;
				case 2:
					if(dengtype == 0){
						if(deng ==1){
							send_byte[14] = (byte) (send_byte[14]|0x20);
							send_byte[23] =  (byte) (send_byte[23]|0x40);
						}else if(deng == 2){
							send_byte[14] = (byte) (send_byte[14]|0x40);
						}else if(deng == 3){
							send_byte[14] = (byte) (send_byte[14]|0x80);
						}
						send_byte[32] =  (byte) (send_byte[32]|0x80);
					}else if(dengtype == 1){
						if(deng ==1){
							send_byte[14] = (byte) (send_byte[14]|0x04);
							send_byte[23] =  (byte) (send_byte[23]|0x08);
							
						}else if(deng == 2){
							send_byte[14] = (byte) (send_byte[14]|0x08);
						}else if(deng == 3){
							send_byte[14] = (byte) (send_byte[14]|0x10);
						}
						send_byte[32] =  (byte) (send_byte[32]|0x10);
					}else if(dengtype == 2){
						if(deng ==1){
							send_byte[15] = (byte) (send_byte[15]|0x20);
							send_byte[24] =  (byte) (send_byte[24]|0x40);
							
						}else if(deng == 2){
							send_byte[15] = (byte) (send_byte[15]|0x40);
						}else if(deng == 3){
							send_byte[15] = (byte) (send_byte[15]|0x80);
						}
						send_byte[33] =  (byte) (send_byte[33]|0x80);
					}else if(dengtype == 3){
						if(deng ==1){
							send_byte[15] = (byte) (send_byte[15]|0x0A);
							send_byte[24] =  (byte) (send_byte[24]|0x14);
							
						}else if(deng == 2){
						}else if(deng == 3){
							send_byte[15] = (byte) (send_byte[15]|0x14);
						}
						send_byte[33] =  (byte) (send_byte[33]|0x14);
					}
					break;
				case 3:
					if(dengtype == 0){
						if(deng ==1){
							send_byte[16] = (byte) (send_byte[16]|0x20);
							send_byte[25] =  (byte) (send_byte[25]|0x40);
						}else if(deng == 2){
							send_byte[16] = (byte) (send_byte[16]|0x40);
						}else if(deng == 3){
							send_byte[16] = (byte) (send_byte[16]|0x80);
						}
						send_byte[34] =  (byte) (send_byte[34]|0x80);
					}else if(dengtype == 1){
						if(deng ==1){
							send_byte[16] = (byte) (send_byte[16]|0x04);
							send_byte[25] =  (byte) (send_byte[25]|0x08);
							
						}else if(deng == 2){
							send_byte[16] = (byte) (send_byte[16]|0x08);
						}else if(deng == 3){
							send_byte[16] = (byte) (send_byte[16]|0x10);
						}
						send_byte[34] =  (byte) (send_byte[34]|0x10);
					}else if(dengtype == 2){
						if(deng ==1){
							send_byte[17] = (byte) (send_byte[17]|0x20);
							send_byte[26] =  (byte) (send_byte[26]|0x40);
							
						}else if(deng == 2){
							send_byte[17] = (byte) (send_byte[17]|0x40);
						}else if(deng == 3){
							send_byte[17] = (byte) (send_byte[17]|0x80);
						}
						send_byte[35] =  (byte) (send_byte[35]|0x80);
					}else if(dengtype == 3){
						if(deng ==1){
							send_byte[17] = (byte) (send_byte[17]|0x0A);
							send_byte[26] =  (byte) (send_byte[26]|0x14);
							
						}else if(deng == 2){
						}else if(deng == 3){
							send_byte[17] = (byte) (send_byte[17]|0x14);
						}
						send_byte[35] =  (byte) (send_byte[35]|0x14);
					}
					break;
				default:
					break;
				}
			
		}
		
		
		
	
		
		send_byte[18] = (byte) (gltime&0xff);
		send_byte[27] = (byte) (yltime&0xff);
		send_byte[36] = (byte) (rltime&0xff);
		
		 int k = 0;
		 for( int i1 = 4; i1 < send_byte.length-2; i1++){
			 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
			//System.out.println();
		  k += send_byte[i1]&0xFF;
		 }
		 
	       for (int i1 = 0; i1 < 2; i1++) {  
	    	   send_byte[send_byte.length-i1-1]  = (byte) (k >>> (i1 * 8));  
	       }  
		
	   	System.out.println("=======================下发按指定相位运行命令========================================");
		
		for (int i3 = 0; i3 < send_byte.length; i3++) {
			System.out.print(send_byte[i3]+" ");
		}
		System.out.println("");
		System.out.println("========================下发按指定相位运行命令=======================================");
		
		currrenSession.write(send_byte);
		
		return NONE;
	}
	
	public String clearErrorcode()
	{
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		IoSession currrenSession=this.getCurrrenSession(sigNumber);
		if(currrenSession==null)
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
		Sig sig = sigService.querySigByNumber(sigNumber);
		if(sig!=null){
			int iserror = 1;
			devlogService.deleteAllBySigid(iserror,sig.getId());
		}
		
		return NONE;
	}
	 
	public String clearFlow()
	{
		sigNumber = (String) session.get("sigNumber");
		if(sigNumber==null){
			String errorMsg="会话失效,请刷新页面重新进入信号机主界面";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		IoSession currrenSession=this.getCurrrenSession(sigNumber);
		if(currrenSession==null)
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
		Sig sig = sigService.querySigByNumber(sigNumber);
		if(sig!=null){
			flowService.deleteAllBySigid(sig.getId());
		}
		return NONE;
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
		return SUCCESS;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ISigService getSigService() {
		return sigService;
	}

	@Resource
	public void setSigService(ISigService sigService) {
		this.sigService = sigService;
	}

	public SigPara getSigParas() {
		return sigParas;
	}

	public void setSigParas(SigPara sigParas) {
		this.sigParas = sigParas;
	}

	public int getCurruntCommandId() {
		return curruntCommandId;
	}

	public void setCurruntCommandId(int curruntCommandId) {
		this.curruntCommandId = curruntCommandId;
	}

	public Long getMkid() {
		return mkid;
	}

	public void setMkid(Long mkid) {
		this.mkid = mkid;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public int getGltime() {
		return gltime;
	}

	public void setGltime(int gltime) {
		this.gltime = gltime;
	}

	public int getRltime() {
		return rltime;
	}

	public void setRltime(int rltime) {
		this.rltime = rltime;
	}

	public int getYltime() {
		return yltime;
	}

	public void setYltime(int yltime) {
		this.yltime = yltime;
	}

	public List<Greenconflict> getGreens() {
		return greens;
	}

	public void setGreens(List<Greenconflict> greens) {
		this.greens = greens;
	}

	public ConflictVO getConflictVO() {
		return conflictVO;
	}

	public void setConflictVO(ConflictVO conflictVO) {
		this.conflictVO = conflictVO;
	}

	public IGreenconflictService getGreenService() {
		return greenService;
	}
	@Resource
	public void setGreenService(IGreenconflictService greenService) {
		this.greenService = greenService;
	}

	public static String getCurruntSigNumber() {
		return curruntSigNumber;
	}

	public static void setCurruntSigNumber(String curruntSigNumber) {
		SigAction.curruntSigNumber = curruntSigNumber;
	}

	public String getSigNumber() {
		return sigNumber;
	}

	public void setSigNumber(String sigNumber) {
		this.sigNumber = sigNumber;
	}

	public IDevlogService getDevlogService() {
		return devlogService;
	}

	@Resource
	public void setDevlogService(IDevlogService devlogService) {
		this.devlogService = devlogService;
	}

	public IFlowService getFlowService() {
		return flowService;
	}
	@Resource
	public void setFlowService(IFlowService flowService) {
		this.flowService = flowService;
	}
	
	

}
