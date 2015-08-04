package com.jlj.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.model.Oplog;
import com.jlj.model.Sig;
import com.jlj.model.Usero;
import com.jlj.service.IOplogService;
import com.jlj.service.ISigService;
import com.jlj.service.IUseroService;
import com.opensymphony.xwork2.ActionSupport;

@Component("oplogAction")
@Scope("prototype")
public class OplogAction extends ActionSupport implements RequestAware,
SessionAware,ServletResponseAware,ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private IOplogService oplogService;
	private IUseroService useroService;
	Map<String,Object> request;
	Map<String,Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	private int id;
	private Oplog oplog;
	//分页显示
	private String[] arg=new String[2];
	private List<Oplog> oplogs;
	private int page;
	private final int size=10;
	private int pageCount;
	private int totalCount;
	private int status;//按状态
	private int pid;//按用户id
	//条件
	private int con;
	private String convalue;
	
	private int uid;
	private int logtype;
	private String opevent;
	private String startdate;
	private String enddate;
	private List<Usero> useros;
	
	
	private ISigService sigService;
	private int sigid;
	private String devevent;
	private String startdate2;
	private String enddate2;
	private List<Sig> sigs;
	
	public String plist(){
		/*	String ipAddress = (String)session.get("sigIp");
		if(ipAddress==null||ipAddress.equals("")){
			return NONE;
		}*/
		String number = (String)session.get("sigNumber");
		if(number==null||number.equals("")){
			String errorMsg="会话失效,请重新登陆";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		
		if(page<1){
			page=1;
		}
			//总记录数
			totalCount=oplogService.getSigidTotalCount(number);
			//总页数
			pageCount=oplogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			oplogs=oplogService.querySigidList(number,page,size);
//			System.out.println(oplogs.get(0).getSigip());
			
		
		return "siglist";
	}
	
	
	/**
	 * 日志管理
	 */
	public String list() throws Exception{
		//----------------------------------查询用户日志-------------------------------------
		Usero usero = (Usero)session.get("usero");
		if(usero==null){
			String errorMsg="会话失效,请重新登陆";
			request.put("errorMsg", errorMsg);
			return "index";
		}
		if(opevent!=null&&!opevent.equals("")){
			opevent=URLDecoder.decode(opevent, "utf-8");
		}
		if(uid==0){
			uid=usero.getId();
			opevent = "";
		}
		useros=useroService.getUsers();
		if(page<1){
			page=1;
		}
		
		//总记录数
		if(uid!=0||(opevent!=null&&!opevent.trim().equals(""))||(startdate!=null&&!startdate.trim().equals(""))||(enddate!=null&&!enddate.trim().equals(""))){
			
			totalCount=oplogService.getConditionTotalCount(uid,opevent,startdate,enddate);
			//总页数
			pageCount=oplogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			oplogs=oplogService.queryConditionList(uid,opevent,startdate,enddate,page,size);
		}else{
			totalCount=oplogService.getTotalCount();
			//总页数
			pageCount=oplogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			oplogs=oplogService.queryList(page,size);
		}
		//----------------------------------查询设备日志-------------------------------------
//		sigs = sigService.getSigs();
//		if(page<1){
//			page=1;
//		}
//		//总记录数
//		if(sigid!=0||(devevent!=null&&!devevent.trim().equals(""))||(startdate2!=null&&!startdate2.trim().equals(""))||(enddate2!=null&&!enddate2.trim().equals(""))){
//			
//			totalCount2=devlogService.getConditionTotalCount(sigid,devevent,startdate2,enddate2);
//			//总页数
//			pageCount2=devlogService.getPageCount(totalCount2,size);
//			if(pageCount2!=0&&page2>pageCount2){
//				page2=pageCount2;
//			}
//			//所有当前页记录对象
//			devlogs=devlogService.queryConditionList(sigid,devevent,startdate2,enddate2,page2,size);
//		}else{
//			totalCount2=devlogService.getTotalCount();
//			//总页数
//			pageCount2=devlogService.getPageCount(totalCount2,size);
//			if(pageCount2!=0&&page2>pageCount2){
//				page2=pageCount2;
//			}
//			//所有当前页记录对象
//			devlogs=devlogService.queryList(page2,size);
//		}
		return "list";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	private String outinfo;
	public String add() throws Exception{
		oplogService.add(oplog);
		outinfo="恭喜您，添加用户成功！";
		return this.list();
	}
	/**
	 * 删除
	 * @return
	 * @throws Exception 
	 */
	public String delete() throws Exception{
		oplogService.deleteById(id);
		outinfo="恭喜您，删除用户成功！";
		return this.list();
	}
	/**
	 * 修改
	 * @return
	 */
	public String update() throws Exception{
		oplogService.update(oplog);
		outinfo="恭喜您，修改用户成功！";
		return this.list();
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String load() throws Exception{
		oplog=oplogService.loadById(id);
		return "load";
	}
		
	
	// 获得HttpServletResponse对象
    public void setServletResponse(HttpServletResponse response)
    {
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
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	public String getConvalue() {
		return convalue;
	}
	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String[] getArg() {
		return arg;
	}
	public void setArg(String[] arg) {
		this.arg = arg;
	}
	

	
	//get、set-------------------------------------------
	
	public IOplogService getOplogService() {
		return oplogService;
	}
	
	@Resource
	public void setOplogService(IOplogService oplogService) {
		this.oplogService = oplogService;
	}
	public Oplog getOplog() {
		return oplog;
	}
	public void setOplog(Oplog oplog) {
		this.oplog = oplog;
	}
	public List<Oplog> getOplogs() {
		return oplogs;
	}
	public void setOplogs(List<Oplog> oplogs) {
		this.oplogs = oplogs;
	}
	public String getOutinfo() {
		return outinfo;
	}
	public void setOutinfo(String outinfo) {
		this.outinfo = outinfo;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getLogtype() {
		return logtype;
	}
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public IUseroService getUseroService() {
		return useroService;
	}
	@Resource
	public void setUseroService(IUseroService useroService) {
		this.useroService = useroService;
	}
	public List<Usero> getUseros() {
		return useros;
	}
	public void setUseros(List<Usero> useros) {
		this.useros = useros;
	}
	public List<Sig> getSigs() {
		return sigs;
	}
	public void setSigs(List<Sig> sigs) {
		this.sigs = sigs;
	}
	public ISigService getSigService() {
		return sigService;
	}
	@Resource
	public void setSigService(ISigService sigService) {
		this.sigService = sigService;
	}


	public String getOpevent() {
		return opevent;
	}


	public void setOpevent(String opevent) {
		this.opevent = opevent;
	}


	public int getSigid() {
		return sigid;
	}


	public void setSigid(int sigid) {
		this.sigid = sigid;
	}


	public String getDevevent() {
		return devevent;
	}


	public void setDevevent(String devevent) {
		this.devevent = devevent;
	}


	public String getStartdate2() {
		return startdate2;
	}


	public void setStartdate2(String startdate2) {
		this.startdate2 = startdate2;
	}


	public String getEnddate2() {
		return enddate2;
	}


	public void setEnddate2(String enddate2) {
		this.enddate2 = enddate2;
	}
	
	
}
