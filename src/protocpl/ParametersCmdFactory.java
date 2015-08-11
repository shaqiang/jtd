package protocpl;

import java.net.InetSocketAddress;
import java.util.List;

import mina.CmdFactoryBase;
import mina.CommandBase;
import mina.DataConvertor;
import mina.ICmdParser;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jlj.model.Greenconflict;
import com.jlj.model.Issuedcommand;
import com.jlj.model.Sig;
import com.jlj.model.Signpublicparam;
import com.jlj.model.Userarea;
import com.jlj.service.IGreenconflictService;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.service.IUserareaService;



public class ParametersCmdFactory extends CmdFactoryBase implements ICmdParser{

	public static boolean setSuccess;
	final static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	final static ISigService sigService = (ISigService)ac.getBean("sigService");
	public final static IUserareaService userareaService = (IUserareaService)ac.getBean("userareaService");
	final static ISignpublicparamService signpublicparamService = (ISignpublicparamService)ac.getBean("signpublicparamService");
	final static IGreenconflictService greenconflictService = (IGreenconflictService)ac.getBean("greenconflictService");
	final static IIssuedcommandService issuedcommandService = (IIssuedcommandService)ac.getBean("issuedcommandService");
	public ParametersCmdFactory(byte[] data){
		super(data);
		this.expected_cmd = MONITOR_CMD_TYPE.MONITOR_CMD_COMMON_PARAMETERS;
		//DataCacheFactory.Init();
	}
	
	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception{
		System.out.println("cmd.getCmdType() is "+cmd.getCmdType() +"this.expected_cmd is "+this.expected_cmd);
		if(cmd.getCmdType() == this.expected_cmd)
		{
				
			OnAfter_Ack(session, cmd);
			
		}
		
	}
	
	public int GetByeAckFlag(CommandBase cmd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub
		//this.m_oData
		
	//	System.out.println("the 6 is"+this.m_oData[6]+"the 7 is"+this.m_oData[7]);
		

//		String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
//		String[] cmds = Reply_cmd.split(" ");
//        byte[] aaa = new byte[cmds.length];
//        int i = 0;
//        for (String b : cmds) {
//            if (b.equals("FF")) {
//                aaa[i++] = -1;
//            } else {
//                aaa[i++] = Integer.valueOf(b, 16).byteValue();;
//            }
//        }
//        session.write(IoBuffer.wrap(aaa));
		
		if(this.m_oData[7]==0){
			Upload_fault(session,this.m_oData);
		}else if(this.m_oData[7]==1){
			Upload_parameters(session,this.m_oData);
		}else if(this.m_oData[7]==4){
			Upload_conflict(session,this.m_oData);
		}
		return false;
	}

	

	public void UpdatePushTask() {
		// TODO Auto-generated method stub
		
	}

	public byte[] getSerialNum() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSerialNum(byte[] serial) {
		// TODO Auto-generated method stub
		
	}
	
	public void Upload_parameters(IoSession session,byte[] data) throws Exception{
		
	
		String number = (String)session.getAttribute("number");
		//获取session中的IP
//		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		//保存信号机的公共参数下发的命令-start-from jlj
		String datastr = DataConvertor.toHexString(data);
		System.out.println("公共参数命令长度是========================="+data.length);
			//根据ip查出信号机
			
			Sig sig2 = sigService.querySigByNumber(number);
			if(sig2!=null){
				Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig2.getId(),5);
				if(issuedcommand==null){
					issuedcommand = new Issuedcommand();
					issuedcommand.setName("公共参数");
					issuedcommand.setDatas(datastr);
					issuedcommand.setNumber(5);//编号5
					issuedcommand.setSig(sig2);
					issuedcommandService.add(issuedcommand);
				}else{
					issuedcommandService.updateObjectById(datastr,issuedcommand.getId());
				}
				
			
			}
			
		//保存信号机的公共参数下发命令的数据-end-from jlj
		
		//获取信号机中的数据
		int Red_Clearance_Time	 	= data[11];
		int Yellow_Flash_Time 		= data[12];
		
		int comparam				= data[15];
		int checkflow 				= data[16];
		int innermark				= data[17];
		int Workingset 				= data[18];
		System.out.println("data[18]----------------------------------------------"+data[18]);
		int SigSun 					= data[19];
		int SigSunTime[] 			= new int[7];
		int SigSunStatus[] = new int[7];
		for (int i = 0; i < SigSunTime.length; i++) {
			SigSunTime[6-i] 			= SigSun&((int)Math.pow(2,i));
			System.out.println("SigSunTime[i]:"+SigSunTime[6-i]);
			if(SigSunTime[6-i]>0){
				SigSunStatus[6-i]=1;
			}else{
				SigSunStatus[6-i]=0;
			}
		}
		
		
		String local_ip = String.format("%d.%d.%d.%d", data[31]&0xff,data[32]&0xff,data[33]&0xff,data[34]&0xff);
		String center_ip = String.format("%d.%d.%d.%d", data[37]&0xff,data[38]&0xff,data[39]&0xff,data[40]&0xff);
		
		int center_port = ((data[35]<<8)&0xff00)+(data[36]&0xff);
	
		System.out.println("local_ip is "+local_ip+"center port is"+center_port+"center is is"+center_ip);
		
		int gmintime 				= data[26];
		int gmaxtime 				= data[27];
		int zdbctime 				= data[28];
		int countdownmode			= data[29];
		int xrfxtime 				= data[42];
		int cycle 					= data[43];			
		int  xyxr 					= data[44];
		int SigSpecialTime[][] 		= new int[24][2];
		for( int j =0 ;j < 24;j++){
			SigSpecialTime[j][0] 	= data[58+j*2] ;
			SigSpecialTime[j][1] 	= data[58+j*2+1] ;
		}
		//-----------------------数据库---------------------------
		//检查公共参数表是否有该ip地址：若无，插入新数据；若有，修改原数据
//		Signpublicparam signpublicparam = signpublicparamService.getPublicparamByIp(clientIP);
		Signpublicparam signpublicparam = signpublicparamService.getPublicparamByNumber(number);
		if(signpublicparam==null){
			//System.out.println("-------------------------------signpublicparam add");
			signpublicparam = new Signpublicparam();
//			signpublicparam.setIp(clientIP);
			signpublicparam.setQchdtime(Red_Clearance_Time);//清场红灯
			signpublicparam.setKjhstime(Yellow_Flash_Time);//开机黄闪
			signpublicparam.setNumber(String.valueOf(number));
			signpublicparam.setComparam(String.valueOf(comparam));
			signpublicparam.setCheckflow(checkflow);
			signpublicparam.setInnermark(String.valueOf(innermark));
			signpublicparam.setWorkingset(Workingset);
			signpublicparam.setMon(SigSunStatus[0]);
			signpublicparam.setTue(SigSunStatus[1]);
			signpublicparam.setWes(SigSunStatus[2]);
			signpublicparam.setThir(SigSunStatus[3]);
			signpublicparam.setFra(SigSunStatus[4]);
			signpublicparam.setSata(SigSunStatus[5]);
			signpublicparam.setSun(SigSunStatus[6]);
			signpublicparam.setGmintime(gmintime);
			signpublicparam.setGmaxtime(gmaxtime);
			signpublicparam.setZdbctime(zdbctime);
			signpublicparam.setCountdownmode(countdownmode);//未插入
			signpublicparam.setXrfxtime(xrfxtime);
			signpublicparam.setCycle(cycle);
			signpublicparam.setXyxr(xyxr);
			signpublicparam.setSpecialmonth0(SigSpecialTime[0][0]);
			signpublicparam.setSpecialday0(SigSpecialTime[0][1]);
			signpublicparam.setSpecialmonth1(SigSpecialTime[1][0]);
			signpublicparam.setSpecialday1(SigSpecialTime[1][1]);
			signpublicparam.setSpecialmonth2(SigSpecialTime[2][0]);
			signpublicparam.setSpecialday2(SigSpecialTime[2][1]);
			signpublicparam.setSpecialmonth3(SigSpecialTime[3][0]);
			signpublicparam.setSpecialday3(SigSpecialTime[3][1]);
			signpublicparam.setSpecialmonth4(SigSpecialTime[4][0]);
			signpublicparam.setSpecialday4(SigSpecialTime[4][1]);
			signpublicparam.setSpecialmonth5(SigSpecialTime[5][0]);
			signpublicparam.setSpecialday5(SigSpecialTime[5][1]);
			signpublicparam.setSpecialmonth6(SigSpecialTime[6][0]);
			signpublicparam.setSpecialday6(SigSpecialTime[6][1]);
			signpublicparam.setSpecialmonth7(SigSpecialTime[7][0]);
			signpublicparam.setSpecialday7(SigSpecialTime[7][1]);
			signpublicparam.setSpecialmonth8(SigSpecialTime[8][0]);
			signpublicparam.setSpecialday8(SigSpecialTime[8][1]);
			signpublicparam.setSpecialmonth9(SigSpecialTime[9][0]);
			signpublicparam.setSpecialday9(SigSpecialTime[9][1]);
			signpublicparam.setSpecialmonth10(SigSpecialTime[10][0]);
			signpublicparam.setSpecialday10(SigSpecialTime[10][1]);
			signpublicparam.setSpecialmonth11(SigSpecialTime[11][0]);
			signpublicparam.setSpecialday11(SigSpecialTime[11][1]);
			signpublicparam.setSpecialmonth12(SigSpecialTime[12][0]);
			signpublicparam.setSpecialday12(SigSpecialTime[12][1]);
			signpublicparam.setSpecialmonth13(SigSpecialTime[13][0]);
			signpublicparam.setSpecialday13(SigSpecialTime[13][1]);
			signpublicparam.setSpecialmonth14(SigSpecialTime[14][0]);
			signpublicparam.setSpecialday14(SigSpecialTime[14][1]);
			signpublicparam.setSpecialmonth15(SigSpecialTime[15][0]);
			signpublicparam.setSpecialday15(SigSpecialTime[15][1]);
			signpublicparam.setSpecialmonth16(SigSpecialTime[16][0]);
			signpublicparam.setSpecialday16(SigSpecialTime[16][1]);
			signpublicparam.setSpecialmonth17(SigSpecialTime[17][0]);
			signpublicparam.setSpecialday17(SigSpecialTime[17][1]);
			signpublicparam.setSpecialmonth18(SigSpecialTime[18][0]);
			signpublicparam.setSpecialday18(SigSpecialTime[18][1]);
			signpublicparam.setSpecialmonth19(SigSpecialTime[19][0]);
			signpublicparam.setSpecialday19(SigSpecialTime[19][1]);
			signpublicparam.setSpecialmonth20(SigSpecialTime[20][0]);
			signpublicparam.setSpecialday20(SigSpecialTime[20][1]);
			signpublicparam.setSpecialmonth21(SigSpecialTime[21][0]);
			signpublicparam.setSpecialday21(SigSpecialTime[21][1]);
			signpublicparam.setSpecialmonth22(SigSpecialTime[22][0]);
			signpublicparam.setSpecialday22(SigSpecialTime[22][1]);
			signpublicparam.setSpecialmonth23(SigSpecialTime[23][0]);
			signpublicparam.setSpecialday23(SigSpecialTime[23][1]);
			signpublicparam.setIp(local_ip);
			signpublicparam.setCenterip(center_ip);
			signpublicparam.setCenterport(center_port+"");
			signpublicparamService.add(signpublicparam);//保存公共参数

			System.out.println("-------------------------------signpublicparam add success");
		}else{
			System.out.println("-------------------------------signpublicparam update");	
			signpublicparamService.updateByPublicid(Red_Clearance_Time,Yellow_Flash_Time,number+"",
					comparam+"",checkflow,innermark,Workingset,
					SigSunStatus[0],SigSunStatus[1],SigSunStatus[2],SigSunStatus[3],SigSunStatus[4],SigSunStatus[5],SigSunStatus[6],
					gmintime,gmaxtime,zdbctime,countdownmode,xrfxtime,cycle,xyxr,
					SigSpecialTime[0][0],SigSpecialTime[0][1],SigSpecialTime[1][0],SigSpecialTime[1][1],
					SigSpecialTime[2][0],SigSpecialTime[2][1],SigSpecialTime[3][0],SigSpecialTime[3][1],
					SigSpecialTime[4][0],SigSpecialTime[4][1],SigSpecialTime[5][0],SigSpecialTime[5][1],
					SigSpecialTime[6][0],SigSpecialTime[6][1],SigSpecialTime[7][0],SigSpecialTime[7][1],
					SigSpecialTime[8][0],SigSpecialTime[8][1],SigSpecialTime[9][0],SigSpecialTime[9][1],
					SigSpecialTime[10][0],SigSpecialTime[10][1],SigSpecialTime[11][0],SigSpecialTime[11][1],
					SigSpecialTime[12][0],SigSpecialTime[12][1],SigSpecialTime[13][0],SigSpecialTime[13][1],
					SigSpecialTime[14][0],SigSpecialTime[14][1],SigSpecialTime[15][0],SigSpecialTime[15][1],
					SigSpecialTime[16][0],SigSpecialTime[16][1],SigSpecialTime[17][0],SigSpecialTime[17][1],
					SigSpecialTime[18][0],SigSpecialTime[18][1],SigSpecialTime[19][0],SigSpecialTime[19][1],
					SigSpecialTime[20][0],SigSpecialTime[20][1],SigSpecialTime[21][0],SigSpecialTime[21][1],
					SigSpecialTime[22][0],SigSpecialTime[22][1],SigSpecialTime[23][0],SigSpecialTime[23][1],local_ip,center_ip,center_port+"",signpublicparam.getId());
			
			System.out.println("-------------------------------signpublicparam update success");		
		}
		
	}
	
	private void Upload_fault(IoSession session,byte[] data){
		
		int conflict[][] = new int[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				conflict[i][j] = data[10+j+i*16];
			}
		}
		
	}
	
	/**
	 * @param session
	 * @param data
	 */
	private void Upload_conflict(IoSession session, byte[] data) {
		// TODO Auto-generated method stub
		int conflict[][] = new int[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				conflict[i][j] = data[10+j+i*16];
			}
		}
		//-----------------------数据库---------------------------
		//获取session中的IP
//		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		
		//保存信号机的绿冲突下发命令的数据-start-from jlj
		String datastr = DataConvertor.toHexString(data);
		System.out.println("绿冲突--------------------datastr="+datastr);
			//根据ip查出信号机
			String number = (String)session.getAttribute("number");
			Sig sig = sigService.querySigByNumber(number);
			if(sig!=null){
				Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig.getId(),35);
				if(issuedcommand==null){
					issuedcommand = new Issuedcommand();
					issuedcommand.setName("绿冲突");
					issuedcommand.setDatas(datastr);
					issuedcommand.setNumber(35);//编号35
					issuedcommand.setSig(sig);
					try {
						issuedcommandService.add(issuedcommand);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					issuedcommandService.updateObjectById(datastr,issuedcommand.getId());
				}
				
			
			}
			
		//保存信号机的绿冲突下发命令的数据-end-from jlj
		
		//检查绿冲突表：若无，插入新数据；若有，修改原数据
		if(sig!=null){
			List<Greenconflict> greenconflicts = greenconflictService.loadBySid(sig.getId());
			if(greenconflicts==null||greenconflicts.size()==0){
				//新增数据
				for (int i = 0; i < 16; i++) {
					Greenconflict greenconflict = new Greenconflict();
					greenconflict.setSig(sig);
					
					switch (i) {
					case 0:
						greenconflict.setName("东左");
						break;
					case 1:
						greenconflict.setName("东直");
						break;
					case 2:
						greenconflict.setName("东右");
						break;
					case 3:
						greenconflict.setName("东人");
						break;
					case 4:
						greenconflict.setName("南左");
						break;
					case 5:
						greenconflict.setName("南直");
						break;
					case 6:
						greenconflict.setName("南右");
						break;
					case 7:
						greenconflict.setName("南人");
						break;
					case 8:
						greenconflict.setName("西左");
						break;
					case 9:
						greenconflict.setName("西直");
						break;
					case 10:
						greenconflict.setName("西右");
						break;
					case 11:
						greenconflict.setName("西人");
						break;
					case 12:
						greenconflict.setName("北左");
						break;
					case 13:
						greenconflict.setName("北直");
						break;
					case 14:
						greenconflict.setName("北右");
						break;
					case 15:
						greenconflict.setName("北人");
						break;
					
					default:
						break;
					}
					
					for (int j = 0; j < 16; j++) {
						switch (j) {
						case 0:
							greenconflict.setL00(conflict[i][j]);
							break;
						case 1:
							greenconflict.setL01(conflict[i][j]);
							break;
						case 2:
							greenconflict.setL02(conflict[i][j]);
							break;
						case 3:
							greenconflict.setL03(conflict[i][j]);
							break;
						case 4:
							greenconflict.setL10(conflict[i][j]);
							break;
						case 5:
							greenconflict.setL11(conflict[i][j]);
							break;
						case 6:
							greenconflict.setL12(conflict[i][j]);
							break;
						case 7:
							greenconflict.setL13(conflict[i][j]);
							break;
						case 8:
							greenconflict.setL20(conflict[i][j]);
							break;
						case 9:
							greenconflict.setL21(conflict[i][j]);
							break;
						case 10:
							greenconflict.setL22(conflict[i][j]);
							break;
						case 11:
							greenconflict.setL23(conflict[i][j]);
							break;
						case 12:
							greenconflict.setL30(conflict[i][j]);
							break;
						case 13:
							greenconflict.setL31(conflict[i][j]);
							break;
						case 14:
							greenconflict.setL32(conflict[i][j]);
							break;
						case 15:
							greenconflict.setL33(conflict[i][j]);
							break;
						
						default:
							break;
						}
					}
					try {
						greenconflictService.add(greenconflict);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}else{
				//若存在绿冲突，修改绿冲突
				for (int i = 0; i < 16; i++) {
					Greenconflict greenconflict = greenconflicts.get(i);
					switch (i) {
					case 0:
						greenconflict.setName("东左");
						break;
					case 1:
						greenconflict.setName("东直");
						break;
					case 2:
						greenconflict.setName("东右");
						break;
					case 3:
						greenconflict.setName("东人");
						break;
					case 4:
						greenconflict.setName("南左");
						break;
					case 5:
						greenconflict.setName("南直");
						break;
					case 6:
						greenconflict.setName("南右");
						break;
					case 7:
						greenconflict.setName("南人");
						break;
					case 8:
						greenconflict.setName("西左");
						break;
					case 9:
						greenconflict.setName("西直");
						break;
					case 10:
						greenconflict.setName("西右");
						break;
					case 11:
						greenconflict.setName("西人");
						break;
					case 12:
						greenconflict.setName("北左");
						break;
					case 13:
						greenconflict.setName("北直");
						break;
					case 14:
						greenconflict.setName("北右");
						break;
					case 15:
						greenconflict.setName("北人");
						break;
					
					default:
						break;
					}
					for (int j = 0; j < 16; j++) {
						
						switch (j) {
						case 0:
							greenconflict.setL00(conflict[i][j]);
							break;
						case 1:
							greenconflict.setL01(conflict[i][j]);
							break;
						case 2:
							greenconflict.setL02(conflict[i][j]);
							break;
						case 3:
							greenconflict.setL03(conflict[i][j]);
							break;
						case 4:
							greenconflict.setL10(conflict[i][j]);
							break;
						case 5:
							greenconflict.setL11(conflict[i][j]);
							break;
						case 6:
							greenconflict.setL12(conflict[i][j]);
							break;
						case 7:
							greenconflict.setL13(conflict[i][j]);
							break;
						case 8:
							greenconflict.setL20(conflict[i][j]);
							break;
						case 9:
							greenconflict.setL21(conflict[i][j]);
							break;
						case 10:
							greenconflict.setL22(conflict[i][j]);
							break;
						case 11:
							greenconflict.setL23(conflict[i][j]);
							break;
						case 12:
							greenconflict.setL30(conflict[i][j]);
							break;
						case 13:
							greenconflict.setL31(conflict[i][j]);
							break;
						case 14:
							greenconflict.setL32(conflict[i][j]);
							break;
						case 15:
							greenconflict.setL33(conflict[i][j]);
							break;
						
						default:
							break;
						}
						try {
							greenconflictService.update(greenconflict);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
}
