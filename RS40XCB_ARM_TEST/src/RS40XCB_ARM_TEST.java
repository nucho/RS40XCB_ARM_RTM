// -*- Java -*-
/*!
 * @file RS40XCB_ARM_TEST.java
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
 * @class RS40XCB_ARM_TEST
 * @brief RS40XCB_ARM_TEST
 */
public class RS40XCB_ARM_TEST implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "RS40XCB_ARM_TEST",
    	    "type_name",         "RS40XCB_ARM_TEST",
    	    "description",       "RS40XCB_ARM_TEST",
    	    "version",           "1.0.0",
    	    "vendor",            "javatea",
    	    "category",          "test",
    	    "activity_type",     "STATIC",
    	    "max_instance",      "0",
    	    "language",          "Java",
    	    "lang_type",         "compile",
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new RS40XCB_ARM_TESTImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new RS40XCB_ARM_TEST(), new RS40XCB_ARM_TEST());
    }
}
