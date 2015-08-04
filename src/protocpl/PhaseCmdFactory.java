package protocpl;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import mina.CmdFactoryBase;
import mina.CommandBase;
import mina.DataConvertor;
import mina.ICmdParser;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jlj.model.Issuedcommand;
import com.jlj.model.Road;
import com.jlj.model.Sig;
import com.jlj.model.Solution;
import com.jlj.model.Step;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.IRoadService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.service.ISolutionService;
import com.jlj.service.IStepService;

public class PhaseCmdFactory extends CmdFactoryBase implements ICmdParser{
	final static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	final static ISigService sigService = (ISigService)ac.getBean("sigService");
	final static ISignpublicparamService signpublicparamService = (ISignpublicparamService)ac.getBean("signpublicparamService");
	final static ISolutionService solutionService = (ISolutionService)ac.getBean("solutionService");
	final static IIssuedcommandService issuedcommandService = (IIssuedcommandService)ac.getBean("issuedcommandService");
	final static IStepService stepService = (IStepService)ac.getBean("stepService");
	final static IRoadService roadService = (IRoadService)ac.getBean("roadService");
	public PhaseCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.MONITOR_CMD_PHASE;
	}
	
	@Override
	public void Process(IoSession session, CommandBase cmd){
	//	System.out.println("cmd.getCmdType() is "+cmd.getCmdType() +"this.expected_cmd is "+this.expected_cmd);
		if(cmd.getCmdType() == this.expected_cmd)
		{
				
//
//			String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
//			String[] cmds = Reply_cmd.split(" ");
//	        byte[] aaa = new byte[cmds.length];
//	        int i = 0;
//	        for (String b : cmds) {
//	            if (b.equals("FF")) {
//	                aaa[i++] = -1;
//	            } else {
//	                aaa[i++] = Integer.valueOf(b, 16).byteValue();;
//	            }
//	        }
//	        session.write(IoBuffer.wrap(aaa));
			
			OnAfter_Ack(session, cmd);
			
		}
		
	}
	
	public int GetByeAckFlag(CommandBase cmd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd) {
		// TODO Auto-generated method stub
		//this.m_oData
		
		Upload_parameters(session,this.m_oData);
	
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
	
	private void Upload_parameters(IoSession session,byte[] data){
		
		//data[7]  相位方案序号
	
		System.out.println("相位方案"+data[7]+"长度是"+data.length);
		
		ArrayList<int[][]> locatelist = new ArrayList<int[][]>();
		
		for(int step_i = 0;step_i<64;step_i++){
			int locate[][] = new int[4][5];
			for(int i=0;i<4;i++){
		  		if((data[i*2+10+step_i*8]&0x80)>0){
		  			locate[i][0] = 3;              
		  		}else if((data[i*2+10+step_i*8]&0x40)>0){
		  			locate[i][0] = 2;
		  		}else if((data[i*2+10+step_i*8]&0x20)>0){
		  			locate[i][0] = 1;
		  		}else{
		  			locate[i][0] = 0;
		  		}
		  		
		  		if((data[i*2+10+step_i*8]&0x10)>0){
		  			locate[i][1] = 3;
		  		}else if((data[i*2+10+step_i*8]&0x08)>0){
		  			locate[i][1] = 2;
		  		}else if((data[i*2+10+step_i*8]&0x04)>0){
		  			locate[i][1] = 1;
		  		}else{
		  			locate[i][1] = 0;
		  		}
		  		
		  		if((data[i*2+11+step_i*8]&0x80)>0){
		  			locate[i][2] = 3;
		  		}else if((data[i*2+11+step_i*8]&0x40)>0){
		  			locate[i][2] = 2;
		  		}else if((data[i*2+11+step_i*8]&0x20)>0){
		  			locate[i][2] = 1;
		  		}else{
		  			locate[i][2] = 0;
		  		}
		  		
		  		if((data[i*2+11+step_i*8]&0x10)>0){
		  			locate[i][3] = 3;
		  		}else if((data[i*2+11+step_i*8]&0x08)>0){
		  			locate[i][3] = 1;
		  		}else{
		  			locate[i][3] = 0;
		  		}
		  		
		  		if((data[i*2+11+step_i*8]&0x04)>0){
		  			locate[i][4] = 3;
		  		}else if((data[i*2+11+step_i*8]&0x02)>0){
		  			locate[i][4] = 1;
		  		}else{
		  			locate[i][4] = 0;
		  		}
		  	}
			locatelist.add(locate);
		}
			//System.out.println("the locatelist is "+locatelist);
			
			//---------------------数据库----------------------------
			//1-根据ip获取对应的信号机
			//2-根据信号机id和相位编号orderid判断该相位是否保存
			//3-若无，保存相位方案以及步序和方向（东南西北、左直右人人）；若有，更新数据。
			//获取session中的IP
//			String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
			String number = (String)session.getAttribute("number");
			Sig sig = sigService.querySigByNumber(number);
			
				//保存信号机的相位方案下发命令的数据-start-from jlj
				String datastr = DataConvertor.toHexString(data);
				//根据ip查出信号机，检查是否为空
				if(sig!=null){
					Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig.getId(),12);
					if(issuedcommand==null){
						issuedcommand = new Issuedcommand();
						issuedcommand.setName("相位方案");
						issuedcommand.setDatas(datastr);
						issuedcommand.setNumber(12);//编号12
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
				//保存信号机的相位方案下发命令的数据-end-from jlj
			
			if(sig!=null){
				int soluorderid = (int)data[7];
				Solution thissolu = solutionService.getSolutionBySignidAndOrderid(sig.getId(),soluorderid);
				if(thissolu==null){
					if(locatelist.size()==64){
						Solution solution = new Solution();
						solution.setOrderid((int)data[7]);
						solution.setSig(sig);
						solution.setSoluname("相位方案"+data[7]);
						try {
							solutionService.add(solution);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
							//保存该相位方案的所有相位和步序
							for (int k = 0; k < 64; k++) {
								Step step = new Step();
								step.setOrderid(k);
								step.setPhasename("相位"+k/2);
								step.setStepname("步序"+k);
								step.setSolution(solution);
								try {
									stepService.add(step);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//保存该相位步序下的所有方向
								for (int a = 0; a < 4; a++) {
									Road road = new Road();
									road.setLeftcolor(locatelist.get(k)[a][0]);
									road.setLinecolor(locatelist.get(k)[a][1]);
									road.setRightcolor(locatelist.get(k)[a][2]);
									road.setRxcolor(locatelist.get(k)[a][3]);
									road.setRoadtype(a);
									road.setStep(step);
									try {
										roadService.add(road);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
						}
					}
				}else{
					if(locatelist.size()==64){
						//更新数据库
							String soluname="相位方案"+data[7];//?
							int soluid = thissolu.getId();
							solutionService.updateBySoluid(soluname,soluid);
							
							//保存该相位方案的所有相位步序
							for (int k = 0; k < 64; k++) {
								int orderid=k;
								String phasename="相位"+k/2;
								String stepname="步序"+k;
								int stepid=0;
								Step step = stepService.queryStepBySoluid(orderid,soluid);
								stepService.updateByStepid(phasename,stepname,step.getId());
								
								//保存该相位步序下的所有方向
								for (int a = 0; a < 4; a++) {
									int leftcolor=locatelist.get(k)[a][0];
									int linecolor=locatelist.get(k)[a][1];
									int rightcolor=locatelist.get(k)[a][2];
									int rxcolor=locatelist.get(k)[a][3];
									int roadtype=a;
									roadService.updateByRoadid(leftcolor,linecolor,rightcolor,rxcolor,roadtype,step.getId());
								}
							}
							
						}
				}
			}
//			for(int road_i = 0;road_i<4 ; road_i++){
//				Road road = new Road();
//				road.setRoadtype(road_i);
//				road.setLeftcolor(locate[road_i][0]);
//				road.setLinecolor(locate[road_i][1]);
//				road.setRightcolor(locate[road_i][2]);
//				road.setRxcolor(locate[road_i][3]);
//				
//			}
//		for(int step_i = 0;step_i<2;step_i++){
//			Step step = new Step();
//			step.setOrderid();
//			step.setStepname();
//		}
		
//		for( int phase_i = 0;phase_i<32;phase_i++){
//			Phase phase = new Phase();
//			phase.setOrderid(phase_i);
//			phase.setPhasename("相位"+phase_i);
//			
//			for(int step_i = 0;step_i<2;step_i++){
//				Step step = new Step();
//				step.setOrderid(phase_i*2+step_i);
//				step.setStepname("步序"+phase_i*2+step_i);
//			}
//			
//			phase.setSteps(steps)
//		}
		
		
	}
	
	private void Upload_fault(IoSession session,byte[] data){
		
	}
	
	
}
