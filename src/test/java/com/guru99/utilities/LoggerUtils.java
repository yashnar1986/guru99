package com.guru99.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtils {
	
public static boolean exists = false;
    
    public static Logger getLogger(Class cls) {
        if(exists) {
            return Logger.getLogger(cls);
        }
            PropertyConfigurator.configure("log4j.properties");
            exists= true;
            return Logger.getLogger(cls);
            
            
            
        }

}
