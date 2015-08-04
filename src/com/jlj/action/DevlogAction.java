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

import com.jlj.model.Devlog;
import com.jlj.model.Sig;
import com.jlj.service.IDevlogService;
import com.jlj.service.ISigService;
import com.jlj.service.IUseroService;
import com.opensymphony.xwork2.ActionSupport;

@Component("devlogAction")
@Scope("prototype")
public class DevlogAction extends ActionSupport implements RequestAware,
SessionAware,ServletResponseAware,ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private IDevlogService devlogService;
	private IUseroService useroService;
	Map<String,Object> request;
	Map<String,Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	private int id;
	private Devlog devlog;
	//分页显示
	private List<Devlog> devlogs;
	private int page;
	private final int size=10;
	private int pageCount;
	private int totalCount;
	//条件
	
	private String startdate;
	private String enddate;
	private ISigService sigService;
	private int sigid;
	private String devevent;
	private List<Sig> sigs;
	private int iserror;
	public String plist() throws Exception{
		//----------------------------------查询设备日志-------------------------------------
	/*	String ipAddress = (String)session.get("sigIp");
		if(ipAddress==null||ipAddress.equals("")){
			return NONE;
		}*/
		String number = (String)session.get("sigNumber");
		if(number==null||number.equals("")){
			return NONE;
		}
		if(page<1){
			page=1;
		}
		//总记录数
		
			totalCount=devlogService.getSigTotalCount(number,iserror);
			//总页数
			pageCount=devlogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			devlogs=devlogService.querySigList(number,iserror,page,size);
		return "siglist";
	}
	
	/**
	 * 日志管理
	 */
	public String list() throws Exception{
		//----------------------------------查询设备日志-------------------------------------
		if(devevent!=null&&!devevent.equals("")){
			devevent=URLDecoder.decode(devevent, "utf-8");
		}
		sigs = sigService.getNotNullSigs();
		if(page<1){
			page=1;
		}
		if(devevent!=null&&!devevent.equals("")){
			devevent=URLDecoder.decode(devevent, "utf-8");
		}
		//总记录数
		if(sigid!=0||(devevent!=null&&!devevent.trim().equals(""))||(startdate!=null&&!startdate.trim().equals(""))||(enddate!=null&&!enddate.trim().equals(""))){
			
			totalCount=devlogService.getConditionTotalCount(sigid,devevent,startdate,enddate);
			//总页数
			pageCount=devlogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			devlogs=devlogService.queryConditionList(sigid,devevent,startdate,enddate,page,size);
		}else{
			totalCount=devlogService.getTotalCount();
			//总页数
			pageCount=devlogService.getPageCount(totalCount,size);
			if(pageCount!=0&&page>pageCount){
				page=pageCount;
			}
			//所有当前页记录对象
			devlogs=devlogService.queryList(page,size);
		}
		return "list";
	}
	
	private String outinfo;
	
	/**
	 * 删除
	 * @return
	 * @throws Exception 
	 */
	public String delete() throws Exception{
		devlogService.deleteById(id);
		outinfo="恭喜您，删除用户成功！";
		return this.list();
	}
	
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String load() throws Exception{
		devlog=devlogService.loadById(id);
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

	
	//get、set-------------------------------------------
	
	public IDevlogService getDevlogService() {
		return devlogService;
	}
	
	@Resource
	public void setDevlogService(IDevlogService devlogService) {
		this.devlogService = devlogService;
	}
	public Devlog getDevlog() {
		return devlog;
	}
	public void setDevlog(Devlog devlog) {
		this.devlog = devlog;
	}
	public List<Devlog> getDevlogs() {
		return devlogs;
	}
	public void setDevlogs(List<Devlog> devlogs) {
		this.devlogs = devlogs;
	}
	public String getOutinfo() {
		return outinfo;
	}
	public void setOutinfo(String outinfo) {
		this.outinfo = outinfo;
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

	public int getIserror() {
		return iserror;
	}

	public void setIserror(int iserror) {
		this.iserror = iserror;
	}
	
	
}
