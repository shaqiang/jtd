package protocpl;

import mina.CommandBase;
import mina.ICmdParser;
import mina.CmdFactoryBase.MONITOR_CMD_TYPE;

public class CommandSpecialTime extends CommandBase {

	public CommandSpecialTime(ICmdParser parser, byte[] data) {
		super(parser, data);
		// TODO Auto-generated constructor stub
		m_eCmdType = MONITOR_CMD_TYPE.MONITOR_CMD_SPECIAL_TIME;
	}

}
