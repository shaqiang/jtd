package com.jlj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

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

import protocpl.ParametersCmdFactory;

import com.jlj.model.Greenconflict;
import com.jlj.model.Issuedcommand;
import com.jlj.model.Road;
import com.jlj.model.Sig;
import com.jlj.model.Signpublicparam;
import com.jlj.model.Solution;
import com.jlj.model.Step;
import com.jlj.service.IGreenconflictService;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.IRoadService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.service.ISolutionService;
import com.jlj.service.IStepService;
import com.jlj.util.Commands;
import com.jlj.vo.AjaxMsgVO;
import com.jlj.vo.ConflictVO;
import com.opensymphony.xwork2.ActionSupport;

@Component("solutionAction")
@Scope("prototype")
public class SolutionAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	private ISigService sigService;
	private ISignpublicparamService sigpubparamService;
	private ISolutionService solutionService;
	private IStepService stepService;
	private IRoadService roadService;
	private IGreenconflictService greenService;
	private IIssuedcommandService issuedcommandService;
	
	private List<Solution> solutions;
	private List<Step> steps;
	private List<Greenconflict> greens;
	
	private Solution solution;
	private Signpublicparam sigpubparam;
	
	private Sig sig;
	private ConflictVO conflictVO;
	//private String sigIp;//2015-6-24 修改项目 改为使用signumber（信号机编号）来标识信号机唯一性
	private String sigNumber;
	
	/*
	 * url param
	 */
	private int soid;
	private String dates;
	
	public String solutions()
	{
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
			sig = sigService.querySigByNumber(sigNumber);
			if(sig!=null)
			{
				solutions = solutionService.loadByPubid(sig.getId());
			}
			if(solutions!=null&&solutions.size()>0)
			{
				if(soid==0)
				{
					solution = solutions.get(0);
				}else
				{
					solution = solutionService.loadById(soid);
				}
				steps = stepService.loadBySoId(solution.getId());//获得相位方案的相位（相位为步序是偶数位的步序,service层已做处理）
				setGreenConflict(sig.getId());
				session.put("sigNumber", sigNumber);//从地图中进入信号机，将信号机id传入session
				return "cssz-fa";
			}else
			{
				String errorMsg="未获得相位方案信息,请确保数据不为空.";
				request.put("errorMsg", errorMsg);
				return "index";
			}
	}
	
	//获得绿冲突对象
	private void setGreenConflict(Integer sigid) {
		greens = greenService.loadBySid(sigid);
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
	public String updateSolution() throws Exception {
		//System.out.println("1-获取界面数据，更新数据库--------------------------------");
		//需要插入数据库 解析 map-from jlj
		System.out.println(dates);
		/**
		 * map数组元素解释说明
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
			roadService.updateByCondition(deng,dengtypestr, roadtype, stepid);
		}
		System.out.println("2-获取数据库数据，下发命令--------------------------------");
		//下发命令
	
		this.updateSolutionBytes(this.getCurrrenSession(sigNumber));
		Thread.sleep(2000);//线程等待2秒让信号机进行调阅
		System.out.println("3-调阅新命令和新数据，更新数据库--------------------------------");
		int commandId = solutionService.loadById(soid).getOrderid()+12;
		
		Commands.executeCommand(commandId,this.getCurrrenSession(sigNumber));
		
		return NONE;
	}
	
	private void updateSolutionBytes(IoSession currrenSession) {
		//0-获取所有数据库中保存的新数据
		Solution solution = solutionService.loadById(soid);
		System.out.println("updateSolutionBytes获取所有新数据================"+solution.getSoluname());
		if(solution==null){
			System.out.println("updateSolutionBytes solution================ null");
			return ;
		}
		int orderid = solution.getOrderid();//(int)data[7]
		byte[] msendDatas = new byte[524];
		//1-获取数据库中保存的命令
		Sig sig1 = sigService.querySigByNumber(sigNumber);
		if(sig1==null){
			System.out.println("updateSolutionBytes sig1================ null");
			return ;
		}
		
		Issuedcommand issued1 = issuedcommandService.loadBySigidAndNumber(sig1.getId(),12+orderid);//根据sigip和number确定唯一命令
		//System.out.println("updateSolutionBytes solution datas================"+issued1.getDatas());
		String datastr1 ="";
		if(issued1!=null){
			datastr1 = issued1.getDatas();
			msendDatas = DataConvertor.decode(datastr1,524);
			msendDatas[6] = (byte) 0x86;
			msendDatas[7] = (byte) orderid;
		}else{
			System.out.println("updateSolutionBytes issued1================ null");
			return ;
		}
		//获取数据库中所有步序，若list长度为64，则取出所有属性
		List<Step> steps = stepService.loadBySoIdStep(soid);
		if(steps!=null&&steps.size()==64){
			for (int k = 0; k < steps.size(); k++) {
				Step step1 = steps.get(k);
				if(step1!=null){
					List<Road> roads = roadService.loadByStepid(step1.getId());
					for (int i = 0; i < roads.size(); i++) {
						Road road1 = roads.get(i);
						int leftcolor = road1.getLeftcolor();//locatelist.get(k)[a][0]
						int linecolor = road1.getLinecolor();//locatelist.get(k)[a][1]
						int rightcolor = road1.getRightcolor();//locatelist.get(k)[a][2]
						int rxcolor = road1.getRxcolor();//locatelist.get(k)[a][3]
						msendDatas[i*2+10+k*8] = 0;
						msendDatas[i*2+11+k*8] = 0;
						
//						System.out.println("updateSolutionBytes================步序是"+k+"方位"+i+"左直右人是"+leftcolor+linecolor+rightcolor+rxcolor);
						
						switch(leftcolor){
							case 0:
									
								break;
							case 1:
								msendDatas[i*2+10+k*8] |= 1<<5;
								break;
							case 2:
								msendDatas[i*2+10+k*8] |= 1<<6;
								break;
							case 3:
								msendDatas[i*2+10+k*8] |= 1<<7;
								break;
								
							}
						
						switch(linecolor){
						case 0:
								
							break;
						case 1:
							msendDatas[i*2+10+k*8] |= 1<<2;
							break;
						case 2:
							msendDatas[i*2+10+k*8] |= 1<<3;
							break;
						case 3:
							msendDatas[i*2+10+k*8] |= 1<<4;
							break;
							
						}
						
						switch(rightcolor){
						case 0:
								
							break;
						case 1:
							msendDatas[i*2+11+k*8] |= 1<<5;
							break;
						case 2:
							msendDatas[i*2+11+k*8] |= 1<<6;
							break;
						case 3:
							msendDatas[i*2+11+k*8] |= 1<<7;
							break;
							
						}
						
						switch(rxcolor){
						case 0:
								
							break;
						case 1:
							msendDatas[i*2+11+k*8] |= 1<<3;
							break;
						case 2:
						
							break;
						case 3:
							msendDatas[i*2+11+k*8] |= 1<<4;
							break;
							
						}
						
						switch(rxcolor){
						case 0:
								
							break;
						case 1:
							msendDatas[i*2+11+k*8] |= 1<<1;
							break;
						case 2:
						
							break;
						case 3:
							msendDatas[i*2+11+k*8] |= 1<<2;
							break;
							
						}
						
					//	System.out.println("the msendDatas"+i*2+10+k*8+" is "+msendDatas[i*2+10+k*8]+"the msendDatas"+i*2+11+k*8+" is "+msendDatas[i*2+11+k*8]);
					}
					
				}
			}
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
		
		System.out.println("updateSolutionBytes================相位方案下发============================================");
		//包装成新命令
		//for (int i = 0; i < msendDatas.length; i++) {
			System.out.print("updateSolutionBytes newDatas================"+DataConvertor.toHexString(msendDatas));
		
		System.out.println();
		System.out.println("updateSolutionBytes================相位方案下发============================================");
		//2-命令下发-from sl
		currrenSession.write(IoBuffer.wrap(msendDatas));
		
	}
	
	/**
	 * 2015-6-24 修改项目 改为使用signumber（信号机编号）来标识信号机唯一性
	 * @return
	public IoSession getCurrrenSession(String sigIp)
	{
		for(IoSession session : TimeServerHandler.iosessions)
		{
			if(((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress().equals(sigIp))
			{
				return session;
			}
		}
		return null;
	}
	*/
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

	
	public int getSoid() {
		return soid;
	}

	public void setSoid(int soid) {
		this.soid = soid;
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
	public List<Solution> getSolutions() {
		return solutions;
	}
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	
	public IStepService getStepService() {
		return stepService;
	}

	@Resource
	public void setStepService(IStepService stepService) {
		this.stepService = stepService;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}


	public IGreenconflictService getGreenService() {
		return greenService;
	}
	@Resource
	public void setGreenService(IGreenconflictService greenService) {
		this.greenService = greenService;
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


	public IRoadService getRoadService() {
		return roadService;
	}

	@Resource
	public void setRoadService(IRoadService roadService) {
		this.roadService = roadService;
	}

/*	public String getSigIp() {
		return sigIp;
	}

	public void setSigIp(String sigIp) {
		this.sigIp = sigIp;
	}*/
	
	

	public IIssuedcommandService getIssuedcommandService() {
		return issuedcommandService;
	}

	@Resource
	public void setIssuedcommandService(IIssuedcommandService issuedcommandService) {
		this.issuedcommandService = issuedcommandService;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getSigNumber() {
		return sigNumber;
	}

	public void setSigNumber(String sigNumber) {
		this.sigNumber = sigNumber;
	}

	
	
	
}
