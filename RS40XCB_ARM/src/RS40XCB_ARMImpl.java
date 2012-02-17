// -*- Java -*-
/*!
 * @file  RS40XCB_ARMImpl.java
 * @brief This RTC is robot arm using the RS40XCB.
 * @date  $Date$
 *
 * $Id$
 */

import RS40XCB_Java.RS40XCB;
import RTC.TimedBooleanSeq;
import RTC.TimedLongSeq;
import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import jp.go.aist.rtm.RTC.util.StringHolder;
import jp.go.aist.rtm.RTC.util.IntegerHolder;
import RTC.ReturnCode_t;
import RS40XCB_Java.RS40XCB;

/*!
 * @class RS40XCB_ARMImpl
 * @brief This RTC is robot arm using the RS40XCB.
 *
 */
public class RS40XCB_ARMImpl extends DataFlowComponentBase {
	RS40XCB servo;
  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public RS40XCB_ARMImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_torque_in_val = new TimedBooleanSeq();
        m_torque_in = new DataRef<TimedBooleanSeq>(m_torque_in_val);
        m_TorqueInIn = new InPort<TimedBooleanSeq>("TorqueIn", m_torque_in);
        m_position_in_val = new TimedLongSeq();
        m_position_in = new DataRef<TimedLongSeq>(m_position_in_val);
        m_PositionInIn = new InPort<TimedLongSeq>("PositionIn", m_position_in);
        m_time_in_val = new TimedLongSeq();
        m_time_in = new DataRef<TimedLongSeq>(m_time_in_val);
        m_TimeInIn = new InPort<TimedLongSeq>("TimeIn", m_time_in);
        m_position_out_val = new TimedLongSeq();
        m_position_out = new DataRef<TimedLongSeq>(m_position_out_val);
        m_PositionOutOut = new OutPort<TimedLongSeq>("PositionOut", m_position_out);
        m_load_out_val = new TimedLongSeq();
        m_load_out = new DataRef<TimedLongSeq>(m_load_out_val);
        m_LoadOutOut = new OutPort<TimedLongSeq>("LoadOut", m_load_out);
        m_speed_out_val = new TimedLongSeq();
        m_speed_out = new DataRef<TimedLongSeq>(m_speed_out_val);
        m_SpeedOutOut = new OutPort<TimedLongSeq>("SpeedOut", m_speed_out);
        m_temperature_out_val = new TimedLongSeq();
        m_temperature_out = new DataRef<TimedLongSeq>(m_temperature_out_val);
        m_TemperatureOutOut = new OutPort<TimedLongSeq>("TemperatureOut", m_temperature_out);
        m_voltage_out_val = new TimedLongSeq();
        m_voltage_out = new DataRef<TimedLongSeq>(m_voltage_out_val);
        m_VoltageOutOut = new OutPort<TimedLongSeq>("VoltageOut", m_voltage_out);
        // </rtc-template>

    }

    /*!
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        // Set InPort buffers
        addInPort("TorqueIn", m_TorqueInIn);
        addInPort("PositionIn", m_PositionInIn);
        addInPort("TimeIn", m_TimeInIn);
        
        // Set OutPort buffer
        addOutPort("PositionOut", m_PositionOutOut);
        addOutPort("LoadOut", m_LoadOutOut);
        addOutPort("SpeedOut", m_SpeedOutOut);
        addOutPort("TemperatureOut", m_TemperatureOutOut);
        addOutPort("VoltageOut", m_VoltageOutOut);
        // </rtc-template>
        bindParameter("portname", m_portname, "/dev/ttyUSB0");
        bindParameter("baudrate", m_baudrate, "115200");
        bindParameter("startID", m_startID, "1");
        bindParameter("endID", m_endID, "7");
        return super.onInitialize();
    }

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) {
    	
		servo = new RS40XCB(m_portname.value, m_baudrate.value);


		int servoNum = m_endID.value - m_startID.value + 1;
		
		m_position_out.v.tm = new RTC.Time(0, 0);
		m_position_out.v.data = new int[servoNum];

		m_load_out.v.tm = new RTC.Time(0, 0);
		m_load_out.v.data = new int[servoNum];

		m_speed_out.v.tm = new RTC.Time(0, 0);
		m_speed_out.v.data = new int[servoNum];

		m_temperature_out.v.tm = new RTC.Time(0, 0);
		m_temperature_out.v.data = new int[servoNum];

		m_time_in.v.tm = new RTC.Time(0, 0);
		m_time_in.v.data = new int[servoNum];
		for (int i = 0; i < m_time_in.v.data.length; i++) {
			m_time_in.v.data[i] = 0;
		}

		m_voltage_out.v.tm = new RTC.Time(0, 0);
		m_voltage_out.v.data = new int[servoNum];
		
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
    	servo.close();
    	
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
		if (m_TorqueInIn.isNew()) {
			m_TorqueInIn.read();
			for (int i = 0; i < m_torque_in.v.data.length; i++) {
				servo.torque(m_startID.value + i, m_torque_in.v.data[i]);
			}

		}

		if (m_TimeInIn.isNew()) {
			m_TimeInIn.read();
		}

		if (m_PositionInIn.isNew()) {
			m_PositionInIn.read();
			for (int i = 0; i < m_position_in.v.data.length; i++) {
				servo.move(m_startID.value + i, m_position_in.v.data[i],
						m_time_in.v.data[i]);
			}
		}

		for (int i = m_startID.value; i <= m_endID.value; i++) {
			servo.getParam(i);
			
			m_position_out.v.data[i - m_startID.value] = servo.getAngle();
			m_load_out.v.data[i - m_startID.value] = servo.getLoad();
			m_speed_out.v.data[i - m_startID.value] = servo.getSpeed();
			m_temperature_out.v.data[i - m_startID.value] = servo.getTemperature();
			m_voltage_out.v.data[i - m_startID.value] = servo.getVoltage();
			
		}
		
		m_PositionOutOut.write();
		m_LoadOutOut.write();
		m_SpeedOutOut.write();
		m_TemperatureOutOut.write();
		m_VoltageOutOut.write();

    	
        return super.onExecute(ec_id);
    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
	// Configuration variable declaration
	// <rtc-template block="config_declare">
    /*!
     * 
     * - Name:  portname
     * - DefaultValue: /dev/ttyUSB0
     */
    protected StringHolder m_portname = new StringHolder();
    /*!
     * 
     * - Name:  baudrate
     * - DefaultValue: 115200
     */
    protected IntegerHolder m_baudrate = new IntegerHolder();
    /*!
     * 
     * - Name:  startID
     * - DefaultValue: 1
     */
    protected IntegerHolder m_startID = new IntegerHolder();
    /*!
     * 
     * - Name:  endID
     * - DefaultValue: 7
     */
    protected IntegerHolder m_endID = new IntegerHolder();
	// </rtc-template>

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    protected TimedBooleanSeq m_torque_in_val;
    protected DataRef<TimedBooleanSeq> m_torque_in;
    /*!
     * This function flip of a switch of torque of servo.
     */
    protected InPort<TimedBooleanSeq> m_TorqueInIn;

    protected TimedLongSeq m_position_in_val;
    protected DataRef<TimedLongSeq> m_position_in;
    /*!
     * Move the servo to the specified angle When you watch servo from a top, a 
     * clockwise rotation is a positive value and a counterclockwise rotation is a nega
     * tive value.
     */
    protected InPort<TimedLongSeq> m_PositionInIn;

    protected TimedLongSeq m_time_in_val;
    protected DataRef<TimedLongSeq> m_time_in;
    /*!
     * when the servo to move,take this value. This value is unit of 10msec.
     */
    protected InPort<TimedLongSeq> m_TimeInIn;

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedLongSeq m_position_out_val;
    protected DataRef<TimedLongSeq> m_position_out;
    /*!
     * This function can get current angle of unit of 0.1 degrees. If movable ra
     * nge of center is 0, clockwise rotation range is 150 degrees and counterclockwise
     *  rotation range is -150 degrees. 
     */
    protected OutPort<TimedLongSeq> m_PositionOutOut;

    protected TimedLongSeq m_load_out_val;
    protected DataRef<TimedLongSeq> m_load_out;
    /*!
     * This function can get load of unit of mA. 
     */
    protected OutPort<TimedLongSeq> m_LoadOutOut;

    protected TimedLongSeq m_speed_out_val;
    protected DataRef<TimedLongSeq> m_speed_out;
    /*!
     * This function can get current speed of unit of deg/sec. This speed is the
     *  speed of the moment. 
     */
    protected OutPort<TimedLongSeq> m_SpeedOutOut;

    protected TimedLongSeq m_temperature_out_val;
    protected DataRef<TimedLongSeq> m_temperature_out;
    /*!
     * This function can get of temperature of basal plate. The temperature sens
     * or has a degree of error of + -3 due to individual differences. Protective funct
     * ion is activated by temperature and need to reset the servo. 
     */
    protected OutPort<TimedLongSeq> m_TemperatureOutOut;

    protected TimedLongSeq m_voltage_out_val;
    protected DataRef<TimedLongSeq> m_voltage_out;
    /*!
     * This function can get supply voltage of unit of 10mV.. There is an error 
     * of about + - 0.3V. 
     */
    protected OutPort<TimedLongSeq> m_VoltageOutOut;

    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
