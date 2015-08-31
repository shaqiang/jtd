package protocpl;

import org.apache.mina.core.session.IoSession;

import mina.CmdFactoryBase;
import mina.CommandBase;
import mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class Xml1CmdFactory extends CmdFactoryBase {

	public Xml1CmdFactory(byte[] data) {
		super(data);
		// TODO Auto-generated constructor stub
		this.expected_cmd = MONITOR_CMD_TYPE.MONITOR_CMD_XML1;
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

	@Override
	public boolean OnAfter_Ack(IoSession session, CommandBase cmd)
			throws Exception {
		// TODO Auto-generated method stub
		
		//写在这里
		
		return false;
	}
	
}
