package com.jlj.service;

import java.util.List;

import com.jlj.model.Signpublicparam;

public interface ISignpublicparamService {

	//添加对象
	public abstract void add(Signpublicparam signpublicparam) throws Exception;

	//删除对象
	public abstract void delete(Signpublicparam signpublicparam);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Signpublicparam signpublicparam);

	//获取所有对象
	public abstract List<Signpublicparam> getSignpublicparams();

	//加载一个id的对象
	public abstract Signpublicparam loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Signpublicparam> queryList(int con, String convalue,
			int page, int size);

	public abstract Signpublicparam loadByMkid(long mkid);

	public abstract Signpublicparam getPublicparamByIp(String clientIP);

	public abstract void updateByPublicid(int red_Clearance_Time,
			int yellow_Flash_Time, String number, String comparam,
			int checkflow, int innermark, int workingset, int SigSunTime0, int SigSunTime1, int SigSunTime2,
			int SigSunTime3, int SigSunTime4, int SigSunTime5, int SigSunTime6, int gmintime, int gmaxtime,
			int zdbctime, int countdownmode, int xrfxtime, int cycle, int xyxr,
			int SigSpecialTime00,int SigSpecialTime01,int SigSpecialTime10,int SigSpecialTime11,
			int SigSpecialTime20,int SigSpecialTime21,int SigSpecialTime30,int SigSpecialTime31,
			int SigSpecialTime40,int SigSpecialTime41,int SigSpecialTime50,int SigSpecialTime51,
			int SigSpecialTime60,int SigSpecialTime61,int SigSpecialTime70,int SigSpecialTime71,
			int SigSpecialTime80,int SigSpecialTime81,int SigSpecialTime90,int SigSpecialTime91,
			int SigSpecialTime100,int SigSpecialTime101,int SigSpecialTime110,int SigSpecialTime111,
			int SigSpecialTime120,int SigSpecialTime121,int SigSpecialTime130,int SigSpecialTime131,
			int SigSpecialTime140,int SigSpecialTime141,int SigSpecialTime150,int SigSpecialTime151,
			int SigSpecialTime160,int SigSpecialTime161,int SigSpecialTime170,int SigSpecialTime171,
			int SigSpecialTime180,int SigSpecialTime181,int SigSpecialTime190,int SigSpecialTime191,
			int SigSpecialTime200,int SigSpecialTime201,int SigSpecialTime210,int SigSpecialTime211,
			int SigSpecialTime220,int SigSpecialTime221,int SigSpecialTime230,int SigSpecialTime231,String local_ip,String center_ip,String center_port, int publicid);

	public abstract Signpublicparam loadBySid(int sid);

	public abstract Signpublicparam loadBySigIp(String sigIp);

	public abstract Signpublicparam getPublicparamByNumber(String number2);

	public abstract void deleteBySigid(int sigid);


}