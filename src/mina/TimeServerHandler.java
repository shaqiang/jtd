package mina;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jlj.action.SigAction;
import com.jlj.model.Devlog;
import com.jlj.model.Sig;
import com.jlj.model.Userarea;
import com.jlj.service.IDevlogService;
import com.jlj.service.ISigService;
import com.jlj.service.IUserareaService;


  
public class TimeServerHandler  implements IoHandler {
	public static List<IoSession> iosessions = new ArrayList<IoSession>();
	final static int[] cmd_diaoyue = new int[]{0xFF ,0xFF ,0xFF ,0xFF ,0x01 ,0xF0 ,0x90 ,0x00 ,0x00 ,0x08 ,0x01 ,0x89};
	final static String cmd_diaoyue1 = "FF FF FF FF 01 F0 90 00 00 08 01 89";
	final static String cmd_canshu = "FF FF FF FF 01 F0 92 00 00 08 01 8B";
	
	final static String cmd_test = "74 65 73 74 73 65 6E 64";
	public final static ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
	public final static ISigService sigService = (ISigService)ac.getBean("sigService");
	public final static IUserareaService userareaService = (IUserareaService)ac.getBean("userareaService");
	final static IDevlogService devlogService = (IDevlogService)ac.getBean("devlogService");
	private static int index;
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		arg1.printStackTrace();  
		
	}

	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.close(true);
		
	}
	protected byte[] m_oData = null;
	
	public void messageReceived(IoSession session, Object msg) throws Exception {

		CmdFactoryBase cmdFactory = CmdFactoryBase.SelectCmdFactory(session, msg);

		//System.out.println("enter messageReceived"+DataConvertor.bytesToHexString(DataConvertor.toByteArray(msg)));

			if(cmdFactory != null){
				CommandBase cmd = cmdFactory.CreateCommand(session, msg);
				if(null != cmd){
					cmdFactory.Process(session, cmd);
				}
			}
			
	}
	

	
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		 //System.out.println("发送信息:"+arg1.toString()+"到"+arg0.getRemoteAddress().toString()); 
	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IP:"+arg0.getRemoteAddress().toString()+"断开连接"); 
		CmdFactoryBase cmdFactory = (CmdFactoryBase)arg0.getAttribute(CmdFactoryBase.SESSION_PARAM_CMD_FACTORY);
		if(cmdFactory != null)
		{
			cmdFactory.TaskDispose();
		}
		index--;
		//根据sigNumber获取是哪个信号机sig
		String number = (String) arg0.getAttribute("number");
		if(number!=null&&!number.equals("")){
			Sig sig = sigService.querySigByNumber(number);
			if(sig!=null){
				//插入设备连接日志
				Devlog devlog = new Devlog();
				devlog.setSig(sig);
				devlog.setDevevent("信号机["+number+"]与中心软件断开连接");
				devlog.setDevtime(new Date());
				devlog.setIserror(0);
				devlogService.add(devlog);
			}
		}
		
		iosessions.remove(arg0);
		
	}

	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("IP:"+session.getRemoteAddress().toString());
		SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();   
       // cfg.setReceiveBufferSize(2 * 1024 * 1024);   
       // cfg.setReadBufferSize(2 * 1024 * 1024);   
        cfg.setKeepAlive(true);   
        cfg.setSoLinger(0); //这个是根本解决问题的设置   
		
//		//获取session中的IP地址，匹配数据库，发现sig表中无该ip，添加数据；发现sig表中有ip，则不插入-from jlj
//		String ipAddress= ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
//		Sig sig = sigService.querySigByIpAddress(ipAddress);
//		if(sig==null){
//			sig = new Sig();
//			sig.setIserror(0);
//			sig.setIp(ipAddress);
//			Userarea userarea = userareaService.loadById(1);//load未知区域
//			if(userarea==null){
//				System.out.println("userarea=null--------------------");
//			}
//			sig.setUserarea(userarea);
//			sigService.add(sig);
//		}
	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println( "IDLE " + arg0.getIdleCount( arg1 ));  
		if(arg0.getIdleCount( arg1 ) == 3){
			arg0.close(true);
		}
	}

	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=====================================================新增sessIon的次数:"+index++);
		System.out.println( "opened " +session.getRemoteAddress().toString());  
		iosessions.add(session);
//			String[] cmds = cmd_diaoyue1.split(" ");
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
//	        
	}
	 
}
