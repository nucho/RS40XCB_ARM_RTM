// -*- Java -*-
/*!
 * @file  RS40XCB_ARMImpl.java
 * @brief This RTC is robot arm using the RS40XCB.
 * @date  $Date$
 *
 * $Id$
 */

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

/*!
 * @class RS40XCB_ARMImpl
 * @brief This RTC is robot arm using the RS40XCB.
 *
 */
public class RS40XCB_ARMImpl extends DataFlowComponentBase {

  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public RS40XCB_ARMImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_torque_val = new TimedBooleanSeq();
        m_torque = new DataRef<TimedBooleanSeq>(m_torque_val);
        m_TorqueIn = new InPort<TimedBooleanSeq>("Torque", m_torque);
        m_move_val = new TimedLongSeq();
        m_move = new DataRef<TimedLongSeq>(m_move_val);
        m_MoveIn = new InPort<TimedLongSeq>("Move", m_move);
        m_angle_val = new TimedLongSeq();
        m_angle = new DataRef<TimedLongSeq>(m_angle_val);
        m_AngleOut = new OutPort<TimedLongSeq>("Angle", m_angle);
        m_load_val = new TimedLongSeq();
        m_load = new DataRef<TimedLongSeq>(m_load_val);
        m_LoadOut = new OutPort<TimedLongSeq>("Load", m_load);
        m_speed_val = new TimedLongSeq();
        m_speed = new DataRef<TimedLongSeq>(m_speed_val);
        m_SpeedOut = new OutPort<TimedLongSeq>("Speed", m_speed);
        m_temperature_val = new TimedLongSeq();
        m_temperature = new DataRef<TimedLongSeq>(m_temperature_val);
        m_TemperatureOut = new OutPort<TimedLongSeq>("Temperature", m_temperature);
        m_time_val = new TimedLongSeq();
        m_time = new DataRef<TimedLongSeq>(m_time_val);
        m_TimeOut = new OutPort<TimedLongSeq>("Time", m_time);
        m_voltage_val = new TimedLongSeq();
        m_voltage = new DataRef<TimedLongSeq>(m_voltage_val);
        m_VoltageOut = new OutPort<TimedLongSeq>("Voltage", m_voltage);
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
        addInPort("Torque", m_TorqueIn);
        addInPort("Move", m_MoveIn);
        
        // Set OutPort buffer
        addOutPort("Angle", m_AngleOut);
        addOutPort("Load", m_LoadOut);
        addOutPort("Speed", m_SpeedOut);
        addOutPort("Temperature", m_TemperatureOut);
        addOutPort("Time", m_TimeOut);
        addOutPort("Voltage", m_VoltageOut);
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
    protected TimedBooleanSeq m_torque_val;
    protected DataRef<TimedBooleanSeq> m_torque;
    /*!
     * This function flip of a switch of torque of servo.
     */
    protected InPort<TimedBooleanSeq> m_TorqueIn;

    protected TimedLongSeq m_move_val;
    protected DataRef<TimedLongSeq> m_move;
    /*!
     * Move the servo to the specified angle When you watch servo from a top, a 
     * clockwise rotation is a positive value and a counterclockwise rotation is a nega
     * tive value.
     */
    protected InPort<TimedLongSeq> m_MoveIn;

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedLongSeq m_angle_val;
    protected DataRef<TimedLongSeq> m_angle;
    /*!
     * This function can get current angle of unit of 0.1 degrees. If movable ra
     * nge of center is 0, clockwise rotation range is 150 degrees and counterclockwise
     *  rotation range is -150 degrees. 
     */
    protected OutPort<TimedLongSeq> m_AngleOut;

    protected TimedLongSeq m_load_val;
    protected DataRef<TimedLongSeq> m_load;
    /*!
     * This function can get load of unit of mA. 
     */
    protected OutPort<TimedLongSeq> m_LoadOut;

    protected TimedLongSeq m_speed_val;
    protected DataRef<TimedLongSeq> m_speed;
    /*!
     * This function can get current speed of unit of deg/sec. This speed is the
     *  speed of the moment. 
     */
    protected OutPort<TimedLongSeq> m_SpeedOut;

    protected TimedLongSeq m_temperature_val;
    protected DataRef<TimedLongSeq> m_temperature;
    /*!
     * This function can get of temperature of basal plate. The temperature sens
     * or has a degree of error of + -3 due to individual differences. Protective funct
     * ion is activated by temperature and need to reset the servo. 
     */
    protected OutPort<TimedLongSeq> m_TemperatureOut;

    protected TimedLongSeq m_time_val;
    protected DataRef<TimedLongSeq> m_time;
    /*!
     * The lapsed time after starting movement is obtained in the unit for 10 ms
     * . The last time will be held if movement is completed. 
     */
    protected OutPort<TimedLongSeq> m_TimeOut;

    protected TimedLongSeq m_voltage_val;
    protected DataRef<TimedLongSeq> m_voltage;
    /*!
     * This function can get supply voltage of unit of 10mV.. There is an error 
     * of about + - 0.3V. 
     */
    protected OutPort<TimedLongSeq> m_VoltageOut;

    
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
