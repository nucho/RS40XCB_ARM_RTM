// -*- Java -*-
/*!
 * @file  RS40XCB_ARM_TESTImpl.java
 * @brief RS40XCB_ARM_TEST
 * @date  $Date$
 *
 * $Id$
 */

import RTC.TimedLongSeq;
import RTC.TimedBooleanSeq;
import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import RTC.ReturnCode_t;
import java.io.*;

/*!
 * @class RS40XCB_ARM_TESTImpl
 * @brief RS40XCB_ARM_TEST
 *
 */
public class RS40XCB_ARM_TESTImpl extends DataFlowComponentBase {

  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public RS40XCB_ARM_TESTImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_position_in_val = new TimedLongSeq();
        m_position_in = new DataRef<TimedLongSeq>(m_position_in_val);
        m_PositionInIn = new InPort<TimedLongSeq>("PositionIn", m_position_in);
        m_load_in_val = new TimedLongSeq();
        m_load_in = new DataRef<TimedLongSeq>(m_load_in_val);
        m_LoadInIn = new InPort<TimedLongSeq>("LoadIn", m_load_in);
        m_speed_in_val = new TimedLongSeq();
        m_speed_in = new DataRef<TimedLongSeq>(m_speed_in_val);
        m_SpeedInIn = new InPort<TimedLongSeq>("SpeedIn", m_speed_in);
        m_temperature_in_val = new TimedLongSeq();
        m_temperature_in = new DataRef<TimedLongSeq>(m_temperature_in_val);
        m_TemperatureInIn = new InPort<TimedLongSeq>("TemperatureIn", m_temperature_in);
        m_voltage_in_val = new TimedLongSeq();
        m_voltage_in = new DataRef<TimedLongSeq>(m_voltage_in_val);
        m_VoltageInIn = new InPort<TimedLongSeq>("VoltageIn", m_voltage_in);
        m_torque_out_val = new TimedBooleanSeq();
        m_torque_out = new DataRef<TimedBooleanSeq>(m_torque_out_val);
        m_TorqueOutOut = new OutPort<TimedBooleanSeq>("TorqueOut", m_torque_out);
        m_position_out_val = new TimedLongSeq();
        m_position_out = new DataRef<TimedLongSeq>(m_position_out_val);
        m_PositionOutOut = new OutPort<TimedLongSeq>("PositionOut", m_position_out);
        m_time_out_val = new TimedLongSeq();
        m_time_out = new DataRef<TimedLongSeq>(m_time_out_val);
        m_TimeOutOut = new OutPort<TimedLongSeq>("TimeOut", m_time_out);
        // </rtc-template>

    }

    /**
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
        addInPort("PositionIn", m_PositionInIn);
        addInPort("LoadIn", m_LoadInIn);
        addInPort("SpeedIn", m_SpeedInIn);
        addInPort("TemperatureIn", m_TemperatureInIn);
        addInPort("VoltageIn", m_VoltageInIn);
        
        // Set OutPort buffer
        addOutPort("TorqueOut", m_TorqueOutOut);
        addOutPort("PositionOut", m_PositionOutOut);
        addOutPort("TimeOut", m_TimeOutOut);
        // </rtc-template>
        
		m_torque_out.v.tm = new RTC.Time(0, 0);
		m_position_out.v.tm = new RTC.Time(0, 0);
		m_time_out.v.tm = new RTC.Time(0, 0);
        
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
    	System.out.println("ARM Test");
    	
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
		System.out.println("trq:Send Torque Data");
		System.out.println("pos:Send Position Data");
		System.out.println("time:Send Time Data");

		System.out.println("r:Receive ARM Data");

		System.out.print("command>>");
		String line = getKeyboard();
		if (line.equals("trq")) {
			int[] torque_data;
			System.out.print("input torque>>");

			line = getKeyboard();
			torque_data = divideData(line);
			m_torque_out.v.data = new boolean[torque_data.length];
			for (int i = 0; i < torque_data.length; i++) {
				if (torque_data[i] == 1) {
					m_torque_out.v.data[i] = true;
				} else {
					m_torque_out.v.data[i] = false;
				}
			}
			m_TorqueOutOut.write();
		} else if (line.equals("pos")) {
			System.out.print("input Position>>");

			line = getKeyboard();
			m_position_out.v.data = divideData(line);

			m_PositionOutOut.write();
		} else if (line.equals("time")) {
			System.out.print("input Time>>");

			line = getKeyboard();
			m_time_out.v.data = divideData(line);

			m_TimeOutOut.write();
		} else if (line.equals("r")) {
			
			// read
			if (m_PositionInIn.isNew()) {
				m_PositionInIn.read();
			}

			if (m_LoadInIn.isNew()) {
				m_LoadInIn.read();
			}

			if (m_SpeedInIn.isNew()) {
				m_SpeedInIn.read();
			}

			if (m_VoltageInIn.isNew()) {
				m_VoltageInIn.read();
			}

			if (m_TemperatureInIn.isNew()) {
				m_TemperatureInIn.read();
			}
			
			System.out.print("Angle:");
			displayData(m_position_in.v.data);
			
			System.out.print("Load:");
			displayData(m_load_in.v.data);
			
			System.out.print("Speed:");
			displayData(m_speed_in.v.data);
			
			System.out.print("Temperature:");
			displayData(m_temperature_in.v.data);
			
			System.out.print("Voltage:");
			displayData(m_voltage_in.v.data);
		} else {
			System.out.println("Command not found");
		}

		System.out.println();
    	
        return super.onExecute(ec_id);
    }

	private void displayData(int[] data)
	{
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i] + "\t");
			}
		}
		System.out.println();
	}
	
	private int[] divideData(String line) {
		int[] input_data = new int[line.split(",").length];
		for (int i = 0; i < input_data.length; i++) {
			input_data[i] = Integer.parseInt(line.split(",")[i]);
		}

		return input_data;
	}

	private String getKeyboard() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String line = "";

		try {
			line = reader.readLine();
			line = line.trim();
		} catch (IOException ioex) {
			System.out.println("Excepiton occured in onExecute.");
		}

		return line;
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
    // DataInPort declaration
    // <rtc-template block="inport_declare">
    protected TimedLongSeq m_position_in_val;
    protected DataRef<TimedLongSeq> m_position_in;
    /*!
     */
    protected InPort<TimedLongSeq> m_PositionInIn;

    protected TimedLongSeq m_load_in_val;
    protected DataRef<TimedLongSeq> m_load_in;
    /*!
     */
    protected InPort<TimedLongSeq> m_LoadInIn;

    protected TimedLongSeq m_speed_in_val;
    protected DataRef<TimedLongSeq> m_speed_in;
    /*!
     */
    protected InPort<TimedLongSeq> m_SpeedInIn;

    protected TimedLongSeq m_temperature_in_val;
    protected DataRef<TimedLongSeq> m_temperature_in;
    /*!
     */
    protected InPort<TimedLongSeq> m_TemperatureInIn;

    protected TimedLongSeq m_voltage_in_val;
    protected DataRef<TimedLongSeq> m_voltage_in;
    /*!
     */
    protected InPort<TimedLongSeq> m_VoltageInIn;

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedBooleanSeq m_torque_out_val;
    protected DataRef<TimedBooleanSeq> m_torque_out;
    /*!
     */
    protected OutPort<TimedBooleanSeq> m_TorqueOutOut;

    protected TimedLongSeq m_position_out_val;
    protected DataRef<TimedLongSeq> m_position_out;
    /*!
     */
    protected OutPort<TimedLongSeq> m_PositionOutOut;

    protected TimedLongSeq m_time_out_val;
    protected DataRef<TimedLongSeq> m_time_out;
    /*!
     */
    protected OutPort<TimedLongSeq> m_TimeOutOut;

    
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
