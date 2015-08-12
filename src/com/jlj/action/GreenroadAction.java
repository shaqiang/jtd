package com.jlj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mina.DataConvertor;
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

import com.jlj.model.Commontime;
import com.jlj.model.Greenconflict;
import com.jlj.model.Greenroad;
import com.jlj.model.Issuedcommand;
import com.jlj.model.Road;
import com.jlj.model.Sig;
import com.jlj.model.Solution;
import com.jlj.model.Step;
import com.jlj.model.Tqsig;
import com.jlj.service.ICommontimeService;
import com.jlj.service.IGreenconflictService;
import com.jlj.service.IGreenroadService;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.IRoadService;
import com.jlj.service.ISigService;
import com.jlj.service.ISolutionService;
import com.jlj.service.IStepService;
import com.jlj.service.ITqsigService;
import com.jlj.util.Commands;
import com.jlj.vo.AjaxMsgVO;
import com.jlj.vo.ConflictVO;
import com.jlj.vo.GreenroadVO;
import com.jlj.vo.PharseVO;
import com.jlj.vo.SigGreenRoadVO;
import com.jlj.vo.UsefulPhaseVO;
import com.opensymphony.xwork2.ActionSupport;

@Component("greenroadAction")
@Scope("prototype")
public class GreenroadAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	private List<Greenroad> greenroads;
	private List<SigGreenRoadVO> sigVOs;
	private List<UsefulPhaseVO> phaseVOs;
	private List<Road> roads;
	private List<Sig> sigs;

	private IGreenroadService greenroadService;
	private ICommontimeService commontimeService;
	private IIssuedcommandService issuedcommandService;
	private ISolutionService solutionService;
	private ISigService sigService;
	private IStepService stepService;
	private IRoadService roadService;
	private ITqsigService tqService;

	private Greenroad greenroad;
	private Commontime commontime;
	private Solution solution;
	private Step step;
	private Sig sig;
	private SigGreenRoadVO sigVO;
	private Tqsig tqsig;
	private int id;
	

	
	private String begintime;
	/*
	 * 绿冲突
	 */
	private List<Greenconflict> greens;
	private List<ConflictVO>  conflictVOs;
	private List<GreenroadVO> greenroadVOs;
	private ConflictVO conflictVO;
	private IGreenconflictService greenService;
	
	
	/*
	 * 绿波带地图
	 */
	private String sids;
	private Long mklid;
	private int areaid;
	
	
	/*
	 * 绿波带页面
	 */
	private int orderid;
	private int timetype;
	private int maxCircleTime;
	
	/*
	 * json messsage
	 */
	private String dates;
	private String gtime;
	private String rtime;
	private String ytime;
	private String tqname;
	private Long marklineid;
	

	/*
	 * 指定相位对象列表
	 */
	private List<PharseVO> pharseVOS;
	
	/**
	 * 地图加载绿波带
	 * 
	 * @throws Exception
	 */
	public String loadLines() throws Exception {

		greenroads = greenroadService.getAllGreenroads(0);
		if (greenroads != null && greenroads.size() > 0) {
			greenroadVOs = getGreenroadVOs(greenroads);
			JSONArray jsonArr = JSONArray.fromObject(greenroadVOs);
			System.out.println(jsonArr);
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(jsonArr.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;

	}
	
	
	/**
	 * 地图加载特勤控制
	 * 
	 * @throws Exception
	 */
	public String loadTqLines() throws Exception {

		
		greenroads = greenroadService.getAllGreenroads(1);
		if (greenroads != null && greenroads.size() > 0) {
			greenroadVOs = getGreenroadVOs(greenroads);
			JSONArray jsonArr = JSONArray.fromObject(greenroadVOs);
			System.out.println(jsonArr);
			PrintWriter out;
			try {
				response.setContentType("text/html;charset=UTF-8");
				out = response.getWriter();
				out.print(jsonArr.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return NONE;

	}
	
	private List<GreenroadVO> getGreenroadVOs(List<Greenroad> greenroads)
	{
		greenroadVOs = new ArrayList<GreenroadVO>();
		for(Greenroad greenroad:greenroads)
		{
			GreenroadVO greenroadVO = new GreenroadVO();
			greenroadVO.setId(greenroad.getId());
			greenroadVO.setMarklineid(greenroad.getMarklineid());
			greenroadVO.setName(greenroad.getName());
			greenroadVO.setSigmids(greenroad.getSigmids());
			greenroadVO.setType(greenroad.getType());
			greenroadVOs.add(greenroadVO);
		}
		return greenroadVOs;
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
	 * 绿波带地图页面新增 绿波带
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addOrUpdateLine() throws Exception {
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			// update
		} else {
			greenroad = new Greenroad();
			greenroad.setMarklineid(mklid);
			greenroad.setSigmids(sids);
			greenroad.setType(0);
			greenroadService.add(greenroad);
		}
		return NONE;
	}
	
	
	/**
	 * 绿波带地图页面新增 特勤控制
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addOrUpdateTqLine() throws Exception {
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			// update
		} else {
			greenroad = new Greenroad();
			greenroad.setMarklineid(mklid);
			greenroad.setSigmids(sids);
			greenroad.setType(1);
			greenroadService.add(greenroad);
		}
		return NONE;
	}
	
	/**
	 * 跳转至绿波带设置时距图 页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lbd() throws Exception {
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			setSigVOS(greenroad);
			maxCircleTime = setMaxCircle(sigVOs);
			return "lbd";
		} else {
			String errorMsg="没有获得相应绿波带信息,请确保数据不为空.";
			request.put("errorMsg", errorMsg);
			return "index";
		}

	}
	
	/**
	 * 跳转至特勤控制设置时间页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tq() throws Exception {
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			setTqSigVOS(greenroad);
			setGreenConflict(sigVOs);
			return "tq";
		} else {
			String errorMsg="没有获得相应特勤控制信息,请确保数据不为空.";
			request.put("errorMsg", errorMsg);
			return "index";
		}

	}
	
	/*
	 * 设置特勤控制信号机vo
	 */
	private void setTqSigVOS(Greenroad greenroad) {
		sigVOs = new ArrayList<SigGreenRoadVO>();
		String[] mkids = greenroad.getSigmids().split(",");
		for (int i = 0; i < mkids.length; i++) {
			sig = sigService.loadByMkid(Long.parseLong(mkids[i]));
			if (sig != null) {
				sigVO = new SigGreenRoadVO();
				sigVO.setId(sig.getId());
				sigVO.setNumber(sig.getNumber());
				sigVO.setName(sig.getName());
				sigVOs.add(sigVO);
			}
		}
	}


	/*
	 * 获得绿冲突对象列表
	 */
	private void setGreenConflict(List<SigGreenRoadVO> sigVOs) {
		
		conflictVOs = new ArrayList<ConflictVO>();
		for(int j=0;j<sigVOs.size();j++)
		{
			greens = greenService.loadBySid(sigVOs.get(j).getId());
			if(greens!=null&&greens.size()==16)
			{
				System.out.println("信号机："+sigVOs.get(j).getNumber());
				conflictVO = new ConflictVO();
				conflictVO.setSid(sigVOs.get(j).getId());
				conflictVO.setNumber(sigVOs.get(j).getNumber());
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
				}
				conflictVOs.add(conflictVO);
			}
			
			
		}
		
		
	}
	
	
	/**
	 * 时距图设置电缆联动和相位开始时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setPharseTime() throws Exception {
		System.out.println(dates+" "+begintime+" "+orderid+" "+timetype);
		int hour = 0;
		int minute = 0;
		int seconds = 0;
		int workingway =3;
		/**
		 * map数组元素解释说明 1:0[解释 sid信号机id: 开始时间]
		 * 开始时间是秒 90 120 或其他 
		 * 需处理 commontime :hour minute seconds  workingway 0表示普通控制方式，1表示黄闪，2表示关灯，3表示协调控制（绿波带），4表示感应控制，5表示中心控制，6未定义;
		 * 
		 */
		String[] sigctimes = dates.split(",");
		for (int i = 0; i < sigctimes.length; i++) {
			String[] sig_time = sigctimes[i].split(":");
			int sid = Integer.parseInt(sig_time[0]);
			int add_second = Integer.parseInt(sig_time[1]);
			commontime = commontimeService.loadByOrderIdAndTimetype(timetype, orderid, sid);
			if(!begintime.equals("0")&&!begintime.equals("")&&begintime.length()>0&&begintime.indexOf(":")!=-1)
			{
				hour = Integer.parseInt(begintime.split(":")[0]);
				minute =  Integer.parseInt(begintime.split(":")[1]);
			}else
			{
				hour = commontime.getHour();
				minute = commontime.getMinute();
			}
			if(add_second>3600)
			{
				hour = hour + add_second/3600;
				minute = minute + add_second%60;
				seconds = seconds + add_second%3600;
			}else
			{
				minute = minute + add_second/60;
				seconds = seconds + add_second%60;
			}
			commontimeService.updateCommontime(hour,minute,seconds,workingway,orderid,timetype,sid);
			// 下发信号机 时间段参数
			Sig sig = sigService.loadById(sid);
			if(sig!=null){
				String sigNumber = sig.getNumber();
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
				this.updateCommonTimeBytes(sig,this.getCurrrenSession(sigNumber));
				//System.out.println("setPharseTime-调阅新命令和新数据，更新数据库--------------------------------");
				Commands.executeCommand(6,this.getCurrrenSession(sigNumber));//commontime 编号6
				Thread.sleep(100);
				Commands.executeCommand(7,this.getCurrrenSession(sigNumber));//commontime 编号7
				Thread.sleep(100);
			}
		}
		
		return NONE;
	}
	
	
	
	/**
	 * 保存特勤控制时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveControl() throws Exception {
		//System.out.println(tqname);
		//System.out.println(marklineid);
		//System.out.println(gtime);//绿灯持续时间
		//System.out.println(ytime);//黄灯持续时间
		//System.out.println(rtime);//红灯持续时间
		
		greenroad = greenroadService.loadByMkid(marklineid);//修改名称方案
		if(greenroad!=null)
		{
			greenroad.setName(tqname);
			greenroadService.update(greenroad);
		}
	    setSpecifiedPharseVO(dates,gtime,ytime,rtime);
        for (int j = 0;j < pharseVOS.size(); j++) {
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
        			String[] solus = pharseVOS.get(j).getDates().split(",");
        			
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
        			
        			send_byte[18] = (byte) (pharseVOS.get(j).getGltime()&0xff);
        			send_byte[27] = (byte) (pharseVOS.get(j).getYltime()&0xff);
        			send_byte[36] = (byte) (pharseVOS.get(j).getRltime()&0xff);
        			
        			 int k = 0;
        			 for( int i1 = 4; i1 < send_byte.length-2; i1++){
        				 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
        				//System.out.println();
        			  k += send_byte[i1]&0xFF;
        			 }
        			 
        		       for (int i1 = 0; i1 < 2; i1++) {  
        		    	   send_byte[send_byte.length-i1-1]  = (byte) (k >>> (i1 * 8));  
        		       }  
        			
        			for (int i3 = 0; i3 < send_byte.length; i3++) {
        				System.out.print(send_byte[i3]);
        			}
        			String number = pharseVOS.get(j).getNumber();
        			tqsig = tqService.queryByNumber(greenroad.getId(),number);
        			if(tqsig==null)
        			{
        				Tqsig tqsig = new Tqsig();
            			tqsig.setGreenroad(greenroad);
            			String datastr = DataConvertor.toHexString(send_byte);
            			tqsig.setTqdatastr(datastr);
            			tqsig.setTqstatus(0);//信号机自动控制
            			tqsig.setNumber(number);
            			tqService.add(tqsig);
        			}else
        			{
        				String datastr = DataConvertor.toHexString(send_byte);
            			tqsig.setTqdatastr(datastr);
            			tqService.update(tqsig);
        			}
        			
        		}
			return NONE;
	}
	
	/*
	 * 设置指定相位VO对象
	 */
	private void setSpecifiedPharseVO(String dates, String gtime,
			String ytime, String rtime) {
		// TODO Auto-generated method stub
		String[] ytimes = ytime.split(",");
		String[] rtimes = rtime.split(",");
		String[] gtimes = gtime.split(",");
		
		String[] dateses = dates.split(",");
		 
	    pharseVOS= new ArrayList<PharseVO>();
		for (int j = 0; j < gtimes.length; j++) {
			PharseVO pharseVO = new PharseVO();
			String param = gtimes[j];
			String number = param.substring(0, param.indexOf("_"));
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVO.setNumber(number);
			pharseVO.setGltime(time);
			pharseVOS.add(pharseVO);
		}
		
		for (int j = 0; j < ytimes.length; j++) {
			String param = ytimes[j];
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVOS.get(j).setYltime(time);
		}
		
		for (int j = 0; j < rtimes.length; j++) {
			String param = rtimes[j];
			int time = Integer.parseInt(param.substring(param.indexOf("_")+1, param.length()));
			pharseVOS.get(j).setRltime(time);
		}
		
		
		
		  int splitSize = pharseVOS.size();//分割的块大小
		    
		  List<String> subAry = splitAry(dateses, splitSize);//分割后的子块数组
		
		  for (int j = 0; j < pharseVOS.size(); j++) {
			  pharseVOS.get(j).setDates(subAry.get(j));
			  System.out.println(pharseVOS.get(j).getNumber());
			  System.out.println(pharseVOS.get(j).getGltime());
			  System.out.println(pharseVOS.get(j).getYltime());
			  System.out.println(pharseVOS.get(j).getRltime());
			  System.out.println(pharseVOS.get(j).getDates());
			}
		
		
	}
	
	/*
	 * 将大数组拆分为等量小数组
	 */
	 private static List<String> splitAry(String[] ary, int subSize) {  
         int count = 16;  
         List<String> subAryList = new ArrayList<String>();  
 
         for (int i = 0; i < subSize; i++) {
       	  
       	  String dates = "";
       	  
       	  for (int j = 0; j < count; j++) {
       		  	
       		  dates = dates + ary[j+i*count]+",";
       	  }
       	  subAryList.add(dates);
       	  
         }
           
         return subAryList;  
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
	
	private void updateCommonTimeBytes(Sig sig1,IoSession currrenSession) {
		//下发信号机  commontime参数
		if(commontime!=null){
			System.out.println("updateCommonTimeBytes commontime datas 1 datas================"+commontime.getId());
			//0-获取新数据
			int i = commontime.getOrderid();//修改的循环当中的序号head是0-7;tail是8-15
			int hour = commontime.getHour();//(int)data[10+i*40]
			int minute = commontime.getMinute();//(int)data[11+i*40]
			int seconds = commontime.getSeconds();//(int)data[12+i*40]
			int workingway = commontime.getWorkingway();//(int)data[13+i*40]
			int workingprogram = commontime.getWorkingprogram();//(int)data[14+i*40]
			int lstime = commontime.getLstime();//(int)data[15+i*40]
			int hdtime = commontime.getHdtime();//(int)data[16+i*40]
			int qchdtime = commontime.getQchdtime();//(int)data[17+i*40]
			Integer[] worktime = commontime.getTimes();//worktime[]
			
			//1-获取数据库中保存的命令
			if(sig1==null){
				return;
			}
			String datastr1 ="";
			if(i<8){
				Issuedcommand issued1 = issuedcommandService.loadBySigidAndNumber(sig1.getId(),6);//根据sigid和number确定唯一命令
				System.out.println("updateCommonTimeBytes commontime datas 1 datas================"+issued1.getDatas());
				if(issued1!=null){
					datastr1 = issued1.getDatas();
				}
			}else if(i>7&&i<16){
				Issuedcommand issued2 = issuedcommandService.loadBySigidAndNumber(sig1.getId(),7);
				System.out.println("updateCommonTimeBytes commontime datas 2 datas================"+issued2.getDatas());
				if(issued2!=null){
					datastr1 = issued2.getDatas();
				}
			}
			System.out.println("datastr2="+datastr1);
			byte[] msendDatas = DataConvertor.decode(datastr1,332);
			
			switch(commontime.getTimetype()){
				case 1:
					msendDatas[6] = (byte)0x83 ;
					break;
				case 2:
					msendDatas[6] = (byte)0x84;
					break;
				case 3:
					msendDatas[6] = (byte)0x85;
					break;
			}
			
			
			msendDatas[7]=i<8?(byte) (0x00):(byte) (0x01);
			
			for (int j = 0; j < 8; j++) {
				msendDatas[10+i*40] = (byte) hour;
				msendDatas[11+i*40] = (byte) minute;
				msendDatas[12+i*40] = (byte) seconds;
				msendDatas[13+i*40] = (byte) workingway;
				msendDatas[14+i*40] = (byte) workingprogram;
				msendDatas[15+i*40] = (byte) lstime;
				msendDatas[16+i*40] = (byte) hdtime;
				msendDatas[17+i*40] = (byte) qchdtime;
				
				for (int j2 = 0; j2 < 32; j2++) {
					msendDatas[18+j2+i*40] = worktime[i].byteValue();
				}
			}
			
			 int k = 0;
			 for( int i1 = 4; i1 < msendDatas.length-2; i1++){
				 //System.out.println((msendDatas[i]&0xFF)+"对应"+msendDatas[i]);
				//System.out.println();
			  k += msendDatas[i1]&0xFF;
			 }
			 
		 
	         
		       for (int i2 = 0; i2 < 2; i2++) {  
		    	   msendDatas[msendDatas.length-i2-1]  = (byte) (k >>> (i2 * 8));  
		       }  
			
			System.out.println("=======================时间段参数下发========================================");
			
			for (int i3 = 0; i3 < msendDatas.length; i3++) {
				System.out.print(msendDatas[i3]);
			}
			System.out.println("");
			System.out.println("========================时间段参数下发=======================================");
			
			currrenSession.write(msendDatas);
		
		}
	}
	
	/**
	 * 设置最大周期
	 * @param sigVOs2
	 * @return
	 */
	private int setMaxCircle(List<SigGreenRoadVO> sigs) {
		// TODO Auto-generated method stub
		List<Integer> circles = new ArrayList<Integer>();
		int max = 0;
		if(sigs!=null&&sigs.size()>0)
		{
			for(SigGreenRoadVO sig:sigs)
			{
				circles.add(sig.getCircleTime());
			}
			max = Collections.max(circles);
		}
		return max;
	}

	/**
	 * 设置sigVOs 运输类
	 * @param greenroad
	 */
	private void setSigVOS(Greenroad greenroad) {
		sigVOs = new ArrayList<SigGreenRoadVO>();
		String[] mkids = greenroad.getSigmids().split(",");
		for (int i = 0; i < mkids.length; i++) {
			sig = sigService.loadByMkid(Long.parseLong(mkids[i]));
			if (sig != null) {
				sigVO = new SigGreenRoadVO();
				sigVO.setId(sig.getId());
				sigVO.setNumber(sig.getNumber());
				sigVO.setName(sig.getName());
				commontime = commontimeService.loadByOrderIdAndTimetype(
						timetype, orderid, sig.getId());
				if (commontime != null) {
					sigVO = setCurrentSigVOParam(sigVO);//设置 单个sigVO的复杂属性
				}
				sigVOs.add(sigVO);
			}
		}
	}

	/**
	 * 设置sigVO对象
	 * 包括（周期:circleRealTime,可用相位:phaseVOs,可用相位所占比例:pharsePros）
	 * @param sigVO
	 * @return
	 */
	private SigGreenRoadVO setCurrentSigVOParam(SigGreenRoadVO sigVO) {
		// TODO Auto-generated method stub
		List<UsefulPhaseVO> phaseVOs = new ArrayList<UsefulPhaseVO>();//设置sigVO对象中的可用相位
		List<String> pharsePros = new ArrayList<String>();//设置可用相位相位比例 (注：为了前台显示时距图时所有比例都要使用到)
		int circleRealTime = 0;
		
		String phaseString = commontime.getT0() + "," + commontime.getT1()
				+ "," + commontime.getT2() + "," + commontime.getT3() + ","
				+ commontime.getT4() + "," + commontime.getT5() + ","
				+ commontime.getT6() + "," + commontime.getT7() + ","
				+ commontime.getT8() + "," + commontime.getT9() + ","
				+ commontime.getT10() + "," + commontime.getT11() + ","
				+ commontime.getT12() + "," + commontime.getT13() + ","
				+ commontime.getT14() + "," + commontime.getT15() + ","
				+ commontime.getT16() + "," + commontime.getT17() + ","
				+ commontime.getT18() + "," + commontime.getT19() + ","
				+ commontime.getT20() + "," + commontime.getT21() + ","
				+ commontime.getT22() + "," + commontime.getT23() + ","
				+ commontime.getT24() + "," + commontime.getT25() + ","
				+ commontime.getT26() + "," + commontime.getT27() + ","
				+ commontime.getT28() + "," + commontime.getT29() + ","
				+ commontime.getT30() + "," + commontime.getT31();

		if (phaseString.substring(0, 1) != null
				&& !phaseString.substring(0, 1).equals("0")) {
			int firstIndex = phaseString.indexOf(",0");
			if (firstIndex != -1) {
				phaseString = phaseString.substring(0, firstIndex);
			}
			String[] usefulPhase = phaseString.split(",");

			circleRealTime = calculateCircleTime(commontime, usefulPhase.length);// 当前的相位周期

			for (int i = 0; i < usefulPhase.length; i++) {
				UsefulPhaseVO phaseVO = new UsefulPhaseVO();
				DecimalFormat df = new DecimalFormat("######0.00");
				double pro = getNowPro(i, circleRealTime, commontime);
				System.out.println("所占比例为：" + df.format(pro));
				pharsePros.add(df.format(pro));
				phaseVO.setCirclePro(getNowPro(i, circleRealTime, commontime));
				phaseVO.setSigid_usefulpid(sigVO.getId() + "_" + i);//相位都是从0相位开始的
				phaseVO.setName("相位" + i);
				phaseVOs.add(phaseVO);
			}
		}
		sigVO.setCircleTime(circleRealTime);
		sigVO.setUsefulPhases(phaseVOs);
		sigVO.setPharsePros(pharsePros);
		return sigVO;
	}

	

	
	/**
	 * 获得当前相位所占比例
	 * @param i
	 * @param circleRealTime
	 * @param commontime
	 * @return
	 */
	private double getNowPro(int i, int circleRealTime, Commontime commontime) {
		double pharseTime = commontime.getHdtime() + commontime.getQchdtime();
		switch (i) {
		case 0:
			pharseTime = pharseTime + commontime.getT0();
			break;
		case 1:
			pharseTime = pharseTime + commontime.getT1();
			break;
		case 2:
			pharseTime = pharseTime + commontime.getT2();
			break;
		case 3:
			pharseTime = pharseTime + commontime.getT3();
			break;
		case 4:
			pharseTime = pharseTime + commontime.getT4();
			break;
		case 5:
			pharseTime = pharseTime + commontime.getT5();
			break;
		case 6:
			pharseTime = pharseTime + commontime.getT6();
			break;
		case 7:
			pharseTime = pharseTime + commontime.getT7();
			break;
		case 8:
			pharseTime = pharseTime + commontime.getT8();
			break;
		case 9:
			pharseTime = pharseTime + commontime.getT9();
			break;
		case 10:
			pharseTime = pharseTime + commontime.getT10();
			break;
		case 11:
			pharseTime = pharseTime + commontime.getT11();
			break;
		case 12:
			pharseTime = pharseTime + commontime.getT12();
			break;
		case 13:
			pharseTime = pharseTime + commontime.getT13();
			break;
		case 14:
			pharseTime = pharseTime + commontime.getT14();
			break;
		case 15:
			pharseTime = pharseTime + commontime.getT15();
			break;
		case 16:
			pharseTime = pharseTime + commontime.getT16();
			break;
		case 17:
			pharseTime = pharseTime + commontime.getT17();
			break;
		case 18:
			pharseTime = pharseTime + commontime.getT18();
			break;
		case 19:
			pharseTime = pharseTime + commontime.getT19();
			break;
		case 20:
			pharseTime = pharseTime + commontime.getT20();
			break;
		case 21:
			pharseTime = pharseTime + commontime.getT21();
			break;
		case 22:
			pharseTime = pharseTime + commontime.getT22();
			break;
		case 23:
			pharseTime = pharseTime + commontime.getT23();
			break;
		case 24:
			pharseTime = pharseTime + commontime.getT24();
			break;
		case 25:
			pharseTime = pharseTime + commontime.getT25();
			break;
		case 26:
			pharseTime = pharseTime + commontime.getT26();
			break;
		case 27:
			pharseTime = pharseTime + commontime.getT27();
			break;
		case 28:
			pharseTime = pharseTime + commontime.getT28();
			break;
		case 29:
			pharseTime = pharseTime + commontime.getT29();
			break;
		case 30:
			pharseTime = pharseTime + commontime.getT30();
			break;
		case 31:
			pharseTime = pharseTime + commontime.getT31();
			break;
		default:
			break;
		}
		return pharseTime / circleRealTime;
	}

	/**
	 * 计算当前信号机在当前时间段的周期
	 * @param commontime
	 * @param length
	 * @return
	 */
	private int calculateCircleTime(Commontime commontime, int length) {

		int circleTime = commontime.getHdtime() * length
				+ commontime.getQchdtime() * length;
		switch (length) {
		case 1:
			circleTime = circleTime + commontime.getT0();
			break;
		case 2:
			circleTime = circleTime + commontime.getT0() + commontime.getT1();
			break;
		case 3:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2();
			break;
		case 4:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3();
			break;
		case 5:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4();
			break;
		case 6:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5();
			break;
		case 7:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6();
			break;
		case 8:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7();
			break;
		case 9:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8();
			break;
		case 10:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9();
			break;
		case 11:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10();
			break;
		case 12:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11();
			break;
		case 13:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12();
			break;
		case 14:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13();
			break;
		case 15:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14();
			break;
		case 16:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15();
			break;
		case 17:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16();
			break;
		case 18:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17();
			break;
		case 19:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18();
			break;
		case 20:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19();
			break;
		case 21:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20();
			break;
		case 22:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21();
			break;
		case 23:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22();
			break;
		case 24:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23();
			break;
		case 25:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24();
			break;
		case 26:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25();
			break;
		case 27:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26();
			break;
		case 28:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26() + commontime.getT27();
			break;
		case 29:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26() + commontime.getT27()
					+ commontime.getT28();
			break;
		case 30:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26() + commontime.getT27()
					+ commontime.getT28() + commontime.getT29();
			break;
		case 31:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26() + commontime.getT27()
					+ commontime.getT28() + commontime.getT29()
					+ commontime.getT30();
			break;
		case 32:
			circleTime = circleTime + commontime.getT0() + commontime.getT1()
					+ commontime.getT2() + commontime.getT3()
					+ commontime.getT4() + commontime.getT5()
					+ commontime.getT6() + commontime.getT7()
					+ commontime.getT8() + commontime.getT9()
					+ commontime.getT10() + commontime.getT11()
					+ commontime.getT12() + commontime.getT13()
					+ commontime.getT14() + commontime.getT15()
					+ commontime.getT16() + commontime.getT17()
					+ commontime.getT18() + commontime.getT19()
					+ commontime.getT20() + commontime.getT21()
					+ commontime.getT22() + commontime.getT23()
					+ commontime.getT24() + commontime.getT25()
					+ commontime.getT26() + commontime.getT27()
					+ commontime.getT28() + commontime.getT29()
					+ commontime.getT30() + commontime.getT31();
			break;

		default:
			break;
		}

		return circleTime;
	}

	
	/**
	 * 设置时距图完成后跳转至 时距图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String greenroad() throws Exception {
		return "greenroad";
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

	/**
	 * 处理请求参数
	 * 
	 */
	private void setURLParameter() {
		if (req.getParameter("mklid") != null) {
			mklid = Long.parseLong(req.getParameter("mklid"));
		}
		if (req.getParameter("timetype") != null) {
			timetype = Integer.parseInt(req.getParameter("timetype"));// 获得前台的时间类型
		}
		if (req.getParameter("orderid") != null) {
			orderid = Integer.parseInt(req.getParameter("orderid"));// 获得前台的时间段id
		}
		if (req.getParameter("begintime") != null) {
			begintime = req.getParameter("begintime");// 获得前台sid
		}
	}

	/**
	 * 删除绿波带对象
	 * 
	 */
	public String deleteLine()
	{
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			greenroadService.delete(greenroad);
		} 
		return NONE;
	}
	
	/**
	 * 删除特勤控制对象
	 * 
	 */
	public String deleteTqLine()
	{
		greenroad = greenroadService.loadByMkid(mklid);
		if (greenroad != null) {
			greenroadService.delete(greenroad);
		} 
		return NONE;
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

	public Greenroad getGreenroad() {
		return greenroad;
	}

	public void setGreenroad(Greenroad greenroad) {
		this.greenroad = greenroad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IGreenroadService getGreenroadService() {
		return greenroadService;
	}

	@Resource
	public void setGreenroadService(IGreenroadService greenroadService) {
		this.greenroadService = greenroadService;
	}

	public Long getMklid() {
		return mklid;
	}

	public void setMklid(Long mklid) {
		this.mklid = mklid;
	}


	public List<Greenroad> getGreenroads() {
		return greenroads;
	}

	public void setGreenroads(List<Greenroad> greenroads) {
		this.greenroads = greenroads;
	}

	public ICommontimeService getCommontimeService() {
		return commontimeService;
	}

	@Resource
	public void setCommontimeService(ICommontimeService commontimeService) {
		this.commontimeService = commontimeService;
	}

	public Commontime getCommontime() {
		return commontime;
	}

	public void setCommontime(Commontime commontime) {
		this.commontime = commontime;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getTimetype() {
		return timetype;
	}

	public void setTimetype(int timetype) {
		this.timetype = timetype;
	}

	public List<SigGreenRoadVO> getSigVOs() {
		return sigVOs;
	}

	public void setSigVOs(List<SigGreenRoadVO> sigVOs) {
		this.sigVOs = sigVOs;
	}

	public List<UsefulPhaseVO> getPhaseVOs() {
		return phaseVOs;
	}

	public void setPhaseVOs(List<UsefulPhaseVO> phaseVOs) {
		this.phaseVOs = phaseVOs;
	}

	public ISigService getSigService() {
		return sigService;
	}

	@Resource
	public void setSigService(ISigService sigService) {
		this.sigService = sigService;
	}

	public Sig getSig() {
		return sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	public SigGreenRoadVO getSigVO() {
		return sigVO;
	}

	public void setSigVO(SigGreenRoadVO sigVO) {
		this.sigVO = sigVO;
	}

	public int getMaxCircleTime() {
		return maxCircleTime;
	}

	public void setMaxCircleTime(int maxCircleTime) {
		this.maxCircleTime = maxCircleTime;
	}


	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public IIssuedcommandService getIssuedcommandService() {
		return issuedcommandService;
	}

	@Resource
	public void setIssuedcommandService(IIssuedcommandService issuedcommandService) {
		this.issuedcommandService = issuedcommandService;
	}

	public String getSids() {
		return sids;
	}

	public void setSids(String sids) {
		this.sids = sids;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}


	public String getDates() {
		return dates;
	}


	public void setDates(String dates) {
		this.dates = dates;
	}


	public ISolutionService getSolutionService() {
		return solutionService;
	}

	@Resource
	public void setSolutionService(ISolutionService solutionService) {
		this.solutionService = solutionService;
	}


	public Solution getSolution() {
		return solution;
	}


	public void setSolution(Solution solution) {
		this.solution = solution;
	}


	public IStepService getStepService() {
		return stepService;
	}

	@Resource
	public void setStepService(IStepService stepService) {
		this.stepService = stepService;
	}


	public Step getStep() {
		return step;
	}


	public void setStep(Step step) {
		this.step = step;
	}


	public List<Road> getRoads() {
		return roads;
	}


	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}


	public IRoadService getRoadService() {
		return roadService;
	}

	@Resource
	public void setRoadService(IRoadService roadService) {
		this.roadService = roadService;
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


	public List<Sig> getSigs() {
		return sigs;
	}


	public void setSigs(List<Sig> sigs) {
		this.sigs = sigs;
	}


	public List<ConflictVO> getConflictVOs() {
		return conflictVOs;
	}


	public void setConflictVOs(List<ConflictVO> conflictVOs) {
		this.conflictVOs = conflictVOs;
	}


	public String getGtime() {
		return gtime;
	}


	public void setGtime(String gtime) {
		this.gtime = gtime;
	}


	public String getRtime() {
		return rtime;
	}


	public void setRtime(String rtime) {
		this.rtime = rtime;
	}


	public String getYtime() {
		return ytime;
	}


	public void setYtime(String ytime) {
		this.ytime = ytime;
	}


	public List<PharseVO> getPharseVOS() {
		return pharseVOS;
	}


	public void setPharseVOS(List<PharseVO> pharseVOS) {
		this.pharseVOS = pharseVOS;
	}


	public String getTqname() {
		return tqname;
	}


	public void setTqname(String tqname) {
		this.tqname = tqname;
	}


	public Long getMarklineid() {
		return marklineid;
	}


	public void setMarklineid(Long marklineid) {
		this.marklineid = marklineid;
	}


	public ITqsigService getTqService() {
		return tqService;
	}

	@Resource
	public void setTqService(ITqsigService tqService) {
		this.tqService = tqService;
	}


	public List<GreenroadVO> getGreenroadVOs() {
		return greenroadVOs;
	}


	public void setGreenroadVOs(List<GreenroadVO> greenroadVOs) {
		this.greenroadVOs = greenroadVOs;
	}

	
	
	
	

}
