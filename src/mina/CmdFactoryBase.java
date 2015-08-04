package mina;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import protocpl.CommandCommonTime;
import protocpl.CommandDiaoyue;
import protocpl.CommandParam;
import protocpl.CommandPhase;
import protocpl.CommandSpecialTime;
import protocpl.CommandSunTime;
import protocpl.CommandUpLoad;
import protocpl.CommonTimeCmdFactory;
import protocpl.ParametersCmdFactory;
import protocpl.DiaoYueCmdFactory;
import protocpl.PhaseCmdFactory;
import protocpl.SpecialTimeFactory;
import protocpl.SunTimeCmdFactory;
import protocpl.UploadCmdFactory;

import mina.ICmdParser;




public class CmdFactoryBase implements ICmdParser {
	private static final Logger log = LoggerFactory.getLogger(CmdFactoryBase.class);
	public static final String SESSION_PARAM_CMD_FACTORY = "cmd_factory";
	public enum MONITOR_CMD_TYPE{
		UNKNOWN_CMD(-1),
		MONITOR_CMD_DIAOYUE(0),
		MONITOR_CMD_UPLOAD(1),
		MONITOR_CMD_COMMON_PARAMETERS(2),
		MONITOR_CMD_COMMON_TIME(3),
		MONITOR_CMD_SUN_TIME(4),
		MONITOR_CMD_SPECIAL_TIME(5),
		MONITOR_CMD_PHASE(6);

		
		
		private final int _val;
		private MONITOR_CMD_TYPE(int val)
		{
			_val = val;
		}
		
		public int getValue()
		{
			return this._val;
		}
		
		
		
		public static MONITOR_CMD_TYPE valueOf(int val){
			switch(val)
			{
			case 0:
				return MONITOR_CMD_DIAOYUE;
			case 1:
				return MONITOR_CMD_UPLOAD;
			case 2:
				return MONITOR_CMD_COMMON_PARAMETERS;
			case 3:
				return MONITOR_CMD_COMMON_TIME;
			case 4:
				return MONITOR_CMD_SUN_TIME;
			case 5:
				return MONITOR_CMD_SPECIAL_TIME;
			case 6:
				return MONITOR_CMD_PHASE;

			default:
				return UNKNOWN_CMD;
				
			}
		}
	}
	
	
	public static MONITOR_CMD_TYPE getCommandType(byte[] data) {
		
		//int command = data[7] & 0xFF;
		
		
		int sum = 0;
		int flag_ff = 0;
		for(int i = 0;i<4;i++){
			if(data[i] == -1)
				sum++;
		}
		
		for (int i = 0; i < data.length; i++) {
			if(data[i] == -1){
				
			}
		}
		
		if(sum == 4){
			int command = data[6];
			return MONITOR_CMD_TYPE.valueOf(command);
		}else{
			return MONITOR_CMD_TYPE.valueOf(-1);
		}
		
	}
	
	public static CmdFactoryBase SelectCmdFactory(IoSession session, Object message)
	{
		CmdFactoryBase factory = null;
		byte[] data = DataConvertor.toByteArray(message);
		MONITOR_CMD_TYPE eCmdType = getCommandType(data);
		if(MONITOR_CMD_TYPE.UNKNOWN_CMD == eCmdType){
			
				//log.debug("Not expected first cmd type");
				return null;
		}
		switch(eCmdType){
		case MONITOR_CMD_DIAOYUE:
			//log.debug("login factory");
			factory = new DiaoYueCmdFactory(data); 
			break;	
		case MONITOR_CMD_UPLOAD:
			factory = new UploadCmdFactory(data);
			break;
			
		case 	MONITOR_CMD_COMMON_PARAMETERS:
			factory = new ParametersCmdFactory(data); 
			break;
		case MONITOR_CMD_COMMON_TIME:
			//log.debug("switch factory");
			factory = new CommonTimeCmdFactory(data);
			break;
			
		case MONITOR_CMD_SUN_TIME:
			//log.debug("heart beat factory");
			factory = new SunTimeCmdFactory(data);
			break;
			
		case MONITOR_CMD_SPECIAL_TIME:
			//log.debug("msg push ack factory");
			factory = new SpecialTimeFactory(data);
			break;
			
		case MONITOR_CMD_PHASE:
			//log.debug("img upload factory");
			factory = new PhaseCmdFactory(data);
			break;

		}

		
		
		return factory;
	}
	
	public CmdFactoryBase(byte[] data)
	{
		m_oData = data;
	}
	
	protected byte[] m_oData = null;
	protected MONITOR_CMD_TYPE expected_cmd;
	
	public void Process(IoSession session, CommandBase cmd) throws Exception{
//		cmd.Parse(session, m_oData);
	}
	
	public  CommandBase CreateCommand(IoSession session, Object message)
	{

		m_oData = DataConvertor.toByteArray(message);
		CommandBase cmd = null;
				
			MONITOR_CMD_TYPE eCmdType = getCommandType(m_oData);
			
			switch(eCmdType){
			case MONITOR_CMD_DIAOYUE:
				log.debug("CMD_LOGIN");
				cmd = new CommandDiaoyue(this, m_oData);
				break;	
			case MONITOR_CMD_UPLOAD:
				log.debug("CMD_BYE");
				cmd = new CommandUpLoad(this, m_oData);
				break;	
			case MONITOR_CMD_COMMON_PARAMETERS:
				cmd = new CommandParam(this, m_oData);
				break;
			case MONITOR_CMD_COMMON_TIME:
				cmd = new CommandCommonTime(this, m_oData);
				break;
			case MONITOR_CMD_SUN_TIME:
				cmd = new CommandSunTime(this, m_oData);
				break;
			case MONITOR_CMD_SPECIAL_TIME:
				cmd = new CommandSpecialTime(this, m_oData);
				break;
			case 	MONITOR_CMD_PHASE:
				cmd = new CommandPhase(this, m_oData);
				break;
			}
		return cmd;
	}

	public int GetByeAckFlag(CommandBase cmd) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean OnAfter_Ack(IoSession session, CommandBase cmd) throws Exception {
		// TODO Auto-generated method stub
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

	public void TaskDispose() {
		// TODO Auto-generated method stub
		
	}


	
	
	
}
