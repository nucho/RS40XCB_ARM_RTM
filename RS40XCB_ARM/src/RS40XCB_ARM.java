// -*- Java -*-
/*!
 * @file RS40XCB_ARM.java
 * @date $Date$
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;

/*!
 * @class RS40XCB_ARM
 * @brief This RTC is robot arm using the RS40XCB.
 */
public class RS40XCB_ARM implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "RS40XCB_ARM",
    	    "type_name",         "RS40XCB_ARM",
    	    "description",       "This RTC is robot arm using the RS40XCB.",
    	    "version",           "1.0.0",
    	    "vendor",            "javatea",
    	    "category",          "ARM",
    	    "activity_type",     "STATIC",
    	    "max_instance",      "0",
    	    "language",          "Java",
    	    "lang_type",         "compile",
            // Configuration variables
            "conf.default.portname", "/dev/ttyUSB0",
            "conf.default.baudrate", "115200",
            "conf.default.startID", "1",
            "conf.default.endID", "7",
            // Widget
            "conf.__widget__.portname", "text",
            "conf.__widget__.baudrate", "text",
            "conf.__widget__.startID", "text",
            "conf.__widget__.endID", "text",
            // Constraints
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new RS40XCB_ARMImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new RS40XCB_ARM(), new RS40XCB_ARM());
    }
}
