package protocpl;


import mina.CmdFactoryBase;
import mina.CommandBase;
import mina.ICmdParser;
import mina.CmdFactoryBase.MONITOR_CMD_TYPE;



public class CommandParam extends CommandBase {

	public CommandParam(ICmdParser parser,  byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
	
		m_eCmdType = MONITOR_CMD_TYPE.MONITOR_CMD_COMMON_PARAMETERS;
		
		
	}


}
