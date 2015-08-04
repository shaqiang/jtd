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

import com.jlj.model.Commontime;
import com.jlj.model.Issuedcommand;
import com.jlj.model.Sig;
import com.jlj.model.Solution;
import com.jlj.model.Step;
import com.jlj.service.ICommontimeService;
import com.jlj.service.IIssuedcommandService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.service.ISolutionService;
import com.jlj.service.IStepService;

public class CommonTimeCmdFactory extends CmdFactoryBase implements ICmdParser{

	final static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	final static ISignpublicparamService signpublicparamService = (ISignpublicparamService)ac.getBean("signpublicparamService");
	final static ISigService sigService = (ISigService)ac.getBean("sigService");
	final static ICommontimeService commontimeService = (ICommontimeService)ac.getBean("commontimeService");
	final static ISolutionService solutionService = (ISolutionService)ac.getBean("solutionService");
	final static IStepService stepService = (IStepService)ac.getBean("stepService");
	final static IIssuedcommandService issuedcommandService = (IIssuedcommandService)ac.getBean("issuedcommandService");
	private Solution solution;
	private Step step;
	private List<Step> steps;
	public CommonTimeCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.MONITOR_CMD_COMMON_TIME;
	}
	
	@Override
	public void Process(IoSession session, CommandBase cmd){
		System.out.println("cmd.getCmdType() is "+cmd.getCmdType() +"this.expected_cmd is "+this.expected_cmd);
		if(cmd.getCmdType() == this.expected_cmd)
		{
				
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
		//----------------------数据库-------------------------
		//1-查询公共参数表，然后把这些commontime的列表与父表对应
		

	
//		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
//		Sig sig = sigService.querySigByIpAddress(clientIP);
		String number = (String)session.getAttribute("number");
		Sig sig = sigService.querySigByNumber(number);
		
		if(this.m_oData[7]==0){
			Upload_CommonTimeHead(session,this.m_oData,sig);
		}else if(this.m_oData[7]==1){
			Upload_CommonTimeTail(session,this.m_oData,sig);
		}
		return false;
	}

	

	private void Upload_CommonTimeHead(IoSession session, byte[] data,Sig sig) {
		// TODO Auto-generated method stub
//		ArrayList<Commontime> commtimelist = new ArrayList<Commontime>();
		//获取session中的IP
//		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		//保存信号机的公共参数下发命令的数据-start-from jlj
		String datastr = DataConvertor.toHexString(data);
		System.out.println("普通日参数0-7长度是--------------------="+data.length);
			//根据ip查出信号机
			if(sig!=null){
				Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig.getId(),6);
				if(issuedcommand==null){
					issuedcommand = new Issuedcommand();
					issuedcommand.setName("普通日参数1");
					issuedcommand.setDatas(datastr);
					issuedcommand.setNumber(6);//编号6
					issuedcommand.setSig(sig);
					try {
						issuedcommandService.add(issuedcommand);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					issuedcommandService.updateObjectById(datastr,issuedcommand.getId());
				}
				
			
			}
			
		//保存信号机的公共参数下发命令的数据-end-from jlj
		if(sig!=null){
			List<Commontime> commontimes = commontimeService.getCommontimesBySigid(sig.getId());
			//查询是否数据库中普通参数的公共参数为空，如果为空，新插入；如果不为空，更新所有数据；
			if(commontimes==null||commontimes.size()==0){
				for(int i = 0;i<8;i++){
					Commontime commontime = new Commontime();
					commontime.setHour((int)data[10+i*40]);
					commontime.setMinute((int)data[11+i*40]);		
					commontime.setSeconds((int)data[12+i*40]);;
					commontime.setWorkingway((int)data[13+i*40]);
					commontime.setWorkingprogram((int)data[14+i*40]);
					commontime.setLstime((int)data[15+i*40]);
					commontime.setHdtime((int)data[16+i*40]);
					commontime.setOrderid(i);
					commontime.setQchdtime((int)data[17+i*40]) ;
					commontime.setTimetype(0);//普通参数0；周日1；特殊2
					commontime.setSig(sig);
					int worktime[] = new int[32];

					for(int j=0;j<32;j++){
						worktime[j] = data[18+j+i*40];
					}
					commontime.setT0(worktime[0]);
					commontime.setT1(worktime[1]);
					commontime.setT2(worktime[2]);
					commontime.setT3(worktime[3]);
					commontime.setT4(worktime[4]);
					commontime.setT5(worktime[5]);
					commontime.setT6(worktime[6]);
					commontime.setT7(worktime[7]);
					commontime.setT8(worktime[8]);
					commontime.setT9(worktime[9]);
					commontime.setT10(worktime[10]);
					commontime.setT11(worktime[11]);
					commontime.setT12(worktime[12]);
					commontime.setT13(worktime[13]);
					commontime.setT14(worktime[14]);
					commontime.setT15(worktime[15]);
					commontime.setT16(worktime[16]);
					commontime.setT17(worktime[17]);
					commontime.setT18(worktime[18]);
					commontime.setT19(worktime[19]);
					commontime.setT20(worktime[20]);
					commontime.setT21(worktime[21]);
					commontime.setT22(worktime[22]);
					commontime.setT23(worktime[23]);
					commontime.setT24(worktime[24]);
					commontime.setT25(worktime[25]);
					commontime.setT26(worktime[26]);
					commontime.setT27(worktime[27]);
					commontime.setT28(worktime[28]);
					commontime.setT29(worktime[29]);
					commontime.setT30(worktime[30]);
					commontime.setT31(worktime[31]);
					//向当前时间段中的相位方案中的每个相位设置时间
//					solution = solutionService.loadById(commontime.getWorkingprogram()+1);// 相位方案0对应数据库中的id=1
//					if(solution!=null){
//						steps = stepService.loadBySoId(solution.getId());
//						if(steps!=null&&steps.size()>=8)
//						{
//							for(int j=0;j<8;j++){
//								steps.get(i).setSecond((int)data[18+j+i*40]);
//								stepService.update(steps.get(i));//修改步序对象
//							}
//						}
//					}

					try {
						commontimeService.add(commontime);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				for(int i = 0;i<8;i++){
					int hour = (int)data[10+i*40];
					int minute = (int)data[11+i*40];
					int seconds = (int)data[12+i*40];
					int workingway = (int)data[13+i*40];
					int workingprogram = (int)data[14+i*40];
					int lstime = (int)data[15+i*40];
					int hdtime = (int)data[16+i*40];
					int qchdtime = (int)data[17+i*40];
					int orderid = i;
					commontimeService.updateByConditionOrdinaryid(hour,minute,seconds,workingway,workingprogram,lstime,hdtime,qchdtime,orderid,sig.getId());
					//需要修改每个t0-t31-from jlj
				}
			}
		}
	}

	private void Upload_CommonTimeTail(IoSession session, byte[] data,Sig sig) {
		// TODO Auto-generated method stub
//		ArrayList<Commontime> commtimelist = new ArrayList<Commontime>();
		//获取session中的IP
//		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		//保存信号机的公共参数下发命令的数据-start-from jlj
		String datastr = DataConvertor.toHexString(data);
		System.out.println("普通日参数8-15长度是--------------------="+data.length);
			//根据ip查出信号机
			if(sig!=null){
				Issuedcommand issuedcommand = issuedcommandService.loadBySigidAndNumber(sig.getId(),7);
				if(issuedcommand==null){
					issuedcommand = new Issuedcommand();
					issuedcommand.setName("普通日参数2");
					issuedcommand.setDatas(datastr);
					issuedcommand.setNumber(7);//编号7
					issuedcommand.setSig(sig);
					try {
						issuedcommandService.add(issuedcommand);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					issuedcommandService.updateObjectById(datastr,issuedcommand.getId());
				}
				
			
			}
			
		//保存信号机的公共参数下发命令的数据-end-from jlj
			
		if(sig!=null){
			List<Commontime> commontimes = commontimeService.getCommontimesBySigid(sig.getId());
			//查询是否数据库中该sig的公共参数为空，如果为空，新插入；如果不为空，更新所有数据；
			if(commontimes.size()==8){
				for(int i = 0;i<8;i++){
					Commontime commontime = new Commontime();
					commontime.setHour((int)data[10+i*40]);
					commontime.setMinute((int)data[11+i*40]);		
					commontime.setSeconds((int)data[12+i*40]);;
					commontime.setWorkingway((int)data[13+i*40]);
					commontime.setWorkingprogram((int)data[14+i*40]);
					commontime.setLstime((int)data[15+i*40]);
					commontime.setHdtime((int)data[16+i*40]);
					commontime.setQchdtime((int)data[17+i*40]) ;
					commontime.setOrderid(i+8);
					commontime.setTimetype(0);//普通参数0；周日1；特殊2
					commontime.setSig(sig);
					int worktime[] = new int[32];
					

					commontime.setT0(worktime[0]);
					commontime.setT1(worktime[1]);
					commontime.setT2(worktime[2]);
					commontime.setT3(worktime[3]);
					commontime.setT4(worktime[4]);
					commontime.setT5(worktime[5]);
					commontime.setT6(worktime[6]);
					commontime.setT7(worktime[7]);
					commontime.setT8(worktime[8]);
					commontime.setT9(worktime[9]);
					commontime.setT10(worktime[10]);
					commontime.setT11(worktime[11]);
					commontime.setT12(worktime[12]);
					commontime.setT13(worktime[13]);
					commontime.setT14(worktime[14]);
					commontime.setT15(worktime[15]);
					commontime.setT16(worktime[16]);
					commontime.setT17(worktime[17]);
					commontime.setT18(worktime[18]);
					commontime.setT19(worktime[19]);
					commontime.setT20(worktime[20]);
					commontime.setT21(worktime[21]);
					commontime.setT22(worktime[22]);
					commontime.setT23(worktime[23]);
					commontime.setT24(worktime[24]);
					commontime.setT25(worktime[25]);
					commontime.setT26(worktime[26]);
					commontime.setT27(worktime[27]);
					commontime.setT28(worktime[28]);
					commontime.setT29(worktime[29]);
					commontime.setT30(worktime[30]);
					commontime.setT31(worktime[31]);
					try {
						commontimeService.add(commontime);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				for(int i = 0;i<8;i++){
					int hour = (int)data[10+i*40];
					int minute = (int)data[11+i*40];
					int seconds = (int)data[12+i*40];
					int workingway = (int)data[13+i*40];
					int workingprogram = (int)data[14+i*40];
					int lstime = (int)data[15+i*40];
					int hdtime = (int)data[16+i*40];
					int qchdtime = (int)data[17+i*40];
					int orderid = i+8;
					commontimeService.updateByConditionOrdinaryid(hour,minute,seconds,workingway,workingprogram,lstime,hdtime,qchdtime,orderid,sig.getId());
					//需要修改每个t0-t31-from jlj
				}
			}
		}
		
		
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

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	
}
	

