package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


public class TestColorSensor {

    public double AlphaValue;
    public double RedValue;
    public double GreenValue;
    public double BlueValue;
    public double HueValue;
    public boolean Svalue;
    public boolean value;
    
    HardwareMap11691 theHardwareMap11691;
    
    public TestColorSensor (HardwareMap11691 HMap){
        float hsvValues[] = {0F, 0F, 0F};
        theHardwareMap11691 = HMap;
        AlphaValue = theHardwareMap11691.autonSensorCLH.alpha();
        RedValue = theHardwareMap11691.autonSensorCLH.red();
        GreenValue = theHardwareMap11691.autonSensorCLH.green();
        BlueValue = theHardwareMap11691.autonSensorCLH.blue();
        HueValue = hsvValues[0];     
        
        Color.RGBToHSV((int) (RedValue * 255),
                    (int) (GreenValue * 255),
                    (int) (BlueValue * 255),
                    hsvValues);
        

    }   
        //checks for stone/skystone
    public boolean check(){
        if((AlphaValue >= 121 && AlphaValue <= 130 )&& HueValue >= 45){
            Svalue = true;
            }else if(HueValue <= 45){
                value = true;
            }else {
                Svalue = false;
                value = false;
            }
            return Svalue;
        }
        
}

