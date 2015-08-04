package protocpl;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jlj.model.Devlog;
import com.jlj.model.Sig;
import com.jlj.service.IDevlogService;
import com.jlj.service.ISigService;
import com.jlj.service.ISignpublicparamService;
import com.jlj.util.Commands;

import mina.CmdFactoryBase;
import mina.CommandBase;
import mina.DataConvertor;
import mina.ICmdParser;
import mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class UploadCmdFactory extends CmdFactoryBase implements ICmdParser{

	final static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	final static ISigService sigService = (ISigService)ac.getBean("sigService");
	final static IDevlogService devlogService = (IDevlogService)ac.getBean("devlogService");
	public UploadCmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.MONITOR_CMD_UPLOAD;
	}

	@Override
	public void Process(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub
		super.Process(session, cmd);
		if(cmd.getCmdType() == this.expected_cmd)
		{
			OnAfter_Ack(session, cmd);
		}
	}
	
	private void send_ack(IoSession session){
		String Reply_cmd = "FF FF FF FF 01 F0 9F 00 00 08 01 98";
		String[] cmds = Reply_cmd.split(" ");
        byte[] aaa = new byte[cmds.length];
        int i = 0;
        for (String b : cmds) {
            if (b.equals("FF")) {
                aaa[i++] = -1;
            } else {
                aaa[i++] = Integer.valueOf(b, 16).byteValue();;
            }
        }
        session.write(IoBuffer.wrap(aaa));
	}
	
	public int GetByeAckFlag(CommandBase cmd) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd) {
		// TODO Auto-generated method stub
		//this.m_oData
		
		if(this.m_oData[7]==1){
			Upload_time(session,this.m_oData);
		}else if(this.m_oData[7]==2){
			Upload_fault(session,this.m_oData);
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
	
	private void Upload_time(IoSession session,byte[] data){
		int year;
		int month;
		int day;
		int weak;
		int hour;
		int minute;
		int second;
		
		// 对应data[10]开始 7个字节
		
		
		
	}
	
	private void Upload_fault(IoSession session,byte[] data){
		int error = data[10]>>7;        //如果大于0 发生故障   等于0 排除故障
        if(error>0){
        	int year = (data[10]&0x7F);     //  年
	        int mounth = data[11];
	        int day = data[12];
	        int hour = data[13];
	        int minute = data[14];
	        int secound = data[15];	        

	        int error_code = data[16];  
	        
	        System.out.println("故障代码是"+(error_code&0x7f));
        

        	String time = year+"-"+mounth+"-"+day+" "+hour+":"+minute+":"+secound;
        	//获取session中的IP
//    		String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
//    		Sig sig = sigService.querySigByIpAddress(clientIP);
        	String number = (String)session.getAttribute("number");
			Sig sig = sigService.querySigByNumber(number);
        	
    		if(sig!=null){
    			//若当前状态为正常状态，录入故障信息并插入数据库
    			if(sig.getIserror()!=null&&sig.getIserror()==0){
    				try {
                		//录入故障日志
                    	Devlog devlog = new Devlog();
                    	devlog.setSig(sig);
                    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    					Date errorDate = sdf.parse(time);
    					devlog.setDevtime(errorDate);
    					String errormessage = Commands.error_codes[error_code];
    					devlog.setIserror(1);
    					devlog.setDevevent(errormessage);
    					devlogService.add(devlog);
    					
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                	//修改当前信号机故障状态以及故障代码
        			int sigStatus = 1;
        			sigService.updateSigStatus(sigStatus,error_code,sig.getId());
    			}
            	
    		}
        }
	}
}
