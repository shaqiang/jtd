package com.jlj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.model.Greenroad;
import com.jlj.model.Sig;
import com.jlj.model.Solution;
import com.jlj.model.Userarea;
import com.jlj.model.Usero;
import com.jlj.service.ICommontimeService;
import com.jlj.service.IDevlogService;
import com.jlj.service.IFlowService;
import com.jlj.service.IGreenconflictService;
import com.jlj.service.IGreenroadService;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.service.ISigsystimeService;
import com.jlj.service.ISolutionService;
import com.jlj.service.IUserareaService;
import com.jlj.vo.AjaxMsgVO;
import com.jlj.vo.MarkerVO;
import com.jlj.vo.UserareaVO;
import com.opensymphony.xwork2.ActionSupport;

@Component("mapAction")
@Scope("prototype")
public class MapAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	
	private ISigService sigService;
	private IUserareaService userareaService;
	private IGreenroadService greenroadService;
	
	
	//子信息删除用
	private IDevlogService devlogService;
	private ISigsystimeService sigsystimeService;
	private ISignpublicparamService signpublicparamService;
	private IIssuedcommandService issuedcommandService;
	private ISolutionService solutionService;
	private ICommontimeService commontimeService;
	private IGreenconflictService greenconflictService;
	private IFlowService flowService;

	private List<MarkerVO> initMarkers = new ArrayList<MarkerVO>();
	private List<Sig> sigs;
	private List<Userarea> userareas;
	private List<UserareaVO> userareaVOs;
	private List<Greenroad> greenroads;
	
	private Greenroad greenroad;
	private Sig sig;
	//private String sigIp;//2015-6-24 修改项目 改为使用signumber（信号机编号）来标识信号机唯一性
	private String sigNumber;
	private Usero usero;
	private Userarea userarea;
	/*
	 * 区域
	 */
	private int areaid;
	private String areaname;
	
	
	/*
	 * 信号机 map
	 */
	private String name;
	private int id;
	private Long mkid;
	private String address;
	//private String number;
	private String number;
	private String lng;
	private String lat;
	
	/*
	 * 用户
	 */
	
	
	/**
	 * 加载当前地图的区域
	 * @return
	 */
	public String loadArea()
	{
		usero = (Usero) session.get("usero");
		if(usero==null){
			return null;
		}else
		{
			if(usero.getUlimit()==0)
			{
				userareas = userareaService.getUserareas();
			}else
			{
				userareas = userareaService.queryList(usero.getId());
			}
			if(userareas.size()>0)
			{
				userarea = getCurrentUserarea(usero,areaid);
				UserareaVO areaVO = new UserareaVO();
				areaVO.setAreaname(userarea.getUareaname());
				areaVO.setId(userarea.getId());
				areaVO.setLat(userarea.getLat());
				areaVO.setLng(userarea.getLng());
				areaVO.setSize(userarea.getSize());
				areaVO.setUlimit(usero.getUlimit());
				JSONObject jsonObj = JSONObject.fromObject(areaVO);
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
			return null;
		}
	}
	
	/**
	 * load加载地图
	 * 
	 * @return
	 * @throws Exception
	 */
	public String load() throws Exception {
		usero = (Usero) session.get("usero");
		if(usero==null){
			//通过 ajax msg的长度就可以判断出当前的信息为错误提示还是正常信息 undefined
			AjaxMsgVO msgVO = new AjaxMsgVO();
			String message = "连接超时,请刷新页面并重新登入.";
			msgVO.setMessage(message);
			JSONObject jsonObj = JSONObject.fromObject(msgVO);
			PrintWriter errorout;
			try {
				response.setContentType("text/html;charset=UTF-8");
				errorout = response.getWriter();
				errorout.print(jsonObj.toString());
				errorout.flush();
				errorout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		sigs = new ArrayList<Sig>();
		if(areaid!=0)
		{
			//areaid 不为0表示前台有区域传入后台，则显示当前区域下的所有有效信号机
			userarea = getCurrentUserarea(usero,areaid);
			if(userarea!=null)
			{
				/*
				 * sigs_userarea 当前区域下的信号机
				 */
				List<Sig> sigs_userarea = getCurrentSigs(userarea);
				if(sigs_userarea!=null&&sigs_userarea.size()>0)
				{
					sigs.addAll(sigs_userarea);
				}
			}
			/*
			 * 不属于任何区域的信号机
			 */
			List<Sig> allsigs = sigService.getSigs();//所有信号机
			List<Sig> sigs_nullarea = new ArrayList<Sig>();//区域为null的信号机
			for(int i=0;i<allsigs.size();i++)
			{
				if(allsigs.get(i).getUserarea()==null)
				{
					sigs_nullarea.add(allsigs.get(i));
				}
			}
			if(sigs_nullarea!=null&&sigs_nullarea.size()>0)
			{
				sigs.addAll(sigs_nullarea);
			}
		}else
		{
			//areaid 为0表示前台有无区域传入后台，则显示所有区域下的所有有效信号机
			sigs = sigService.getNotNullSigs();
		}
		if(sigs!=null&&sigs.size()>0)
		{
			for (Sig sig : sigs) {
				MarkerVO markervo = new MarkerVO();
				markervo.setId(sig.getMkid());
				markervo.setAddress(sig.getAddress());
				markervo.setNumber(sig.getNumber());
				markervo.setLat(sig.getLat());
				markervo.setLng(sig.getLng());
				markervo.setName(sig.getName());
				initMarkers.add(markervo);
			}
			JSONArray jsonArr = JSONArray.fromObject(initMarkers);
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
	 * 加载当前用户的所有的区域
	 * @return
	 */
	public String loadAreas()
	{
		usero = (Usero) session.get("usero");
		if(usero==null){
			return null;
		}else
		{
			if(usero.getUlimit()==0)
			{
				userareas = userareaService.getUserareas();
			}else
			{
				userareas = userareaService.queryList(usero.getId());
			}
			if(userareas.size()>0)
			{
				userareaVOs = new ArrayList<UserareaVO>();
				for(Userarea userarea:userareas)
				{
					UserareaVO areaVO = new UserareaVO();
					areaVO.setAreaname(userarea.getUareaname());
					areaVO.setId(userarea.getId());
					userareaVOs.add(areaVO);
				}
				JSONArray jsonArr = JSONArray.fromObject(userareaVOs);
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
			return null;
		}
		
	}
	
	/**
	 * 查询当前区域内的信号机
	 * @param userarea
	 */
	private List<Sig> getCurrentSigs(Userarea userarea) {
		// TODO Auto-generated method stub
		return  sigService.querySigsByUserarea(userarea.getId());
	}


	/**
	 * 
	 * @param usero2
	 * @return
	 */
	private Userarea getCurrentUserarea(Usero usero,int areaid) {
		// TODO Auto-generated method stub
		if(usero.getUlimit()==0)
		{
			userareas = userareaService.getUserareas();
		}else
		{
			userareas = userareaService.queryList(usero.getId());
		}
		if(userareas!=null&&userareas.size()>0)
		{
			if(areaid==0)
			{
				userarea = userareas.get(0);
			}else
			{
				for(Userarea area:userareas)
				{
					if(area.getId()==areaid)
					{
						userarea = area;
						break;
					}
				}
				if(userarea==null)
				{
					userarea = userareas.get(0);
				}
			}
			return userarea;
		}else
		{
			return null;
		}
	}

	/**
	 * 添加
	 * @return
	 * @throws Exception信号机
	 */
	public String addOrUpdate() throws Exception {
		Sig sig1 = sigService.querySigByNumber(number);
		userarea = userareaService.loadById(areaid);
		if(sig1==null){
			return NONE;
		}else
		{
			//System.out.println("coming.......");
			if(userarea!=null)
			{
				sig1.setUserarea(userarea);
			}
			
			//第一次插入32 33相位
			Solution solution32 = solutionService.getSolutionBySignidAndOrderid(sig1.getId(),32);
			if(solution32==null){
			//新增两条相位方案
  				Solution solution1 = new Solution();
  				solution1.setOrderid(32);
  				solution1.setSoluname("相位方案32");
  				solution1.setSig(sig1);
  				solutionService.add(solution1);
  				
  				Solution solution2 = new Solution();
  				solution2.setOrderid(33);
  				solution2.setSoluname("相位方案33");
  				solution2.setSig(sig1);
  				solutionService.add(solution2);
			}
  				
			
			sig1.setMkid(mkid);
			sig1.setAddress(address);
			sig1.setName(name);
			sig1.setLat(lat);
			sig1.setLng(lng);
			sig1.setIserror(0);
//			sig1.setTqstatus(0);//0:信号机自动运行 1：特勤控制运行
			sigService.update(sig1);
		}
		return NONE;
	}
	

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String deleteMarker() {
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
		sig = sigService.loadByMkid(mkid);
		
		deletGreenroad(sig.getMkid());//删除信号机时，同时删除与信号机相关联的的绿波带及特勤控制
		sig.setUserarea(null);
		sig.setMkid(null);
		sig.setAddress(null);
		sig.setName(null);
		sig.setLat(null);
		sig.setLng(null);
		sig.setIp(null);
		sig.setIserror(null);
		sig.setErrorcode(null);
//		sig.setTqdatastr(null);
//		sig.setTqstatus(null);
		sigService.update(sig);
		//删除该信号机的所有子信息
		int sigid = sig.getId();
		devlogService.deleteAllBySigid2(sigid);
		sigsystimeService.deleteBySigid(sigid);
		signpublicparamService.deleteBySigid(sigid);
		issuedcommandService.deleteBySigid(sigid);
		solutionService.deleteBySigid(sigid);
		commontimeService.deleteBySigid(sigid);
		greenconflictService.deleteBySigid(sigid);
		flowService.deleteAllBySigid(sigid);
		return NONE;
	}
	/*
	 * 删除信号机相关联动
	 */
	private void deletGreenroad(Long mkid) {
		// TODO Auto-generated method stub
		greenroads = greenroadService.getGreenroads();
		for(Greenroad gd : greenroads)
		{
			if(gd.getSigmids().contains(mkid.toString()))
			{
				greenroadService.delete(gd);
			}
		}
	}

	public String checkMarkerName()
	{
		//System.out.println("checkMarkerName");
		sig = sigService.querySigByName(name);
		if(sig!=null)
		{
			MarkerVO markerVO = new MarkerVO();
			markerVO.setMessage("该信号机名称已经存在,请重新输入.");
			JSONObject jsonObj = JSONObject.fromObject(markerVO);
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
		return  null;
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

	public List<Sig> getSigs() {
		return sigs;
	}

	public void setSigs(List<Sig> sigs) {
		this.sigs = sigs;
	}

	@Resource
	public void setSigService(ISigService sigService) {
		this.sigService = sigService;
	}

	public ISigService getSigService() {
		return sigService;
	}
	
	public List<MarkerVO> getInitMarkers() {
		return initMarkers;
	}

	public void setInitMarkers(List<MarkerVO> initMarkers) {
		this.initMarkers = initMarkers;
	}

	public Sig getSig() {
		return sig;
	}

	public void setSig(Sig sig) {
		this.sig = sig;
	}

	/*public String getSigIp() {
		return sigIp;
	}

	public void setSigIp(String sigIp) {
		this.sigIp = sigIp;
	}*/

	public Usero getUsero() {
		return usero;
	}

	public void setUsero(Usero usero) {
		this.usero = usero;
	}



	public List<Userarea> getUserareas() {
		return userareas;
	}



	public void setUserareas(List<Userarea> userareas) {
		this.userareas = userareas;
	}


	public IUserareaService getUserareaService() {
		return userareaService;
	}

	@Resource
	public void setUserareaService(IUserareaService userareaService) {
		this.userareaService = userareaService;
	}


	public int getAreaid() {
		return areaid;
	}


	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}


	public Userarea getUserarea() {
		return userarea;
	}


	public void setUserarea(Userarea userarea) {
		this.userarea = userarea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getMkid() {
		return mkid;
	}

	public void setMkid(Long mkid) {
		this.mkid = mkid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}*/

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public List<UserareaVO> getUserareaVOs() {
		return userareaVOs;
	}

	public void setUserareaVOs(List<UserareaVO> userareaVOs) {
		this.userareaVOs = userareaVOs;
	}

	public String getSigNumber() {
		return sigNumber;
	}

	public void setSigNumber(String sigNumber) {
		this.sigNumber = sigNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public IGreenroadService getGreenroadService() {
		return greenroadService;
	}
	@Resource
	public void setGreenroadService(IGreenroadService greenroadService) {
		this.greenroadService = greenroadService;
	}

	public List<Greenroad> getGreenroads() {
		return greenroads;
	}

	public void setGreenroads(List<Greenroad> greenroads) {
		this.greenroads = greenroads;
	}

	public Greenroad getGreenroad() {
		return greenroad;
	}

	public void setGreenroad(Greenroad greenroad) {
		this.greenroad = greenroad;
	}

	public IDevlogService getDevlogService() {
		return devlogService;
	}

	@Resource
	public void setDevlogService(IDevlogService devlogService) {
		this.devlogService = devlogService;
	}

	public ISigsystimeService getSigsystimeService() {
		return sigsystimeService;
	}

	@Resource
	public void setSigsystimeService(ISigsystimeService sigsystimeService) {
		this.sigsystimeService = sigsystimeService;
	}

	public ISignpublicparamService getSignpublicparamService() {
		return signpublicparamService;
	}

	@Resource
	public void setSignpublicparamService(
			ISignpublicparamService signpublicparamService) {
		this.signpublicparamService = signpublicparamService;
	}

	public IIssuedcommandService getIssuedcommandService() {
		return issuedcommandService;
	}

	@Resource
	public void setIssuedcommandService(IIssuedcommandService issuedcommandService) {
		this.issuedcommandService = issuedcommandService;
	}

	public ISolutionService getSolutionService() {
		return solutionService;
	}

	@Resource
	public void setSolutionService(ISolutionService solutionService) {
		this.solutionService = solutionService;
	}

	public ICommontimeService getCommontimeService() {
		return commontimeService;
	}

	@Resource
	public void setCommontimeService(ICommontimeService commontimeService) {
		this.commontimeService = commontimeService;
	}

	public IGreenconflictService getGreenconflictService() {
		return greenconflictService;
	}

	@Resource
	public void setGreenconflictService(IGreenconflictService greenconflictService) {
		this.greenconflictService = greenconflictService;
	}

	public IFlowService getFlowService() {
		return flowService;
	}

	@Resource
	public void setFlowService(IFlowService flowService) {
		this.flowService = flowService;
	}

	

	
}
