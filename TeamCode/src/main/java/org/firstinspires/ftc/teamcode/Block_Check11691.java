package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


public class Block_Check11691 {

    public double AlphaValue;
    public double RedValue;
    public double GreenValue;
    public double BlueValue;
    public double HueValue;
    public boolean stone_present;
    public boolean Stone;
    
    HardwareMap11691 theHardwareMap11691;
    
    public Block_Check11691 (HardwareMap11691 HMap){
        float hsvValues[] = {0F, 0F, 0F};
        theHardwareMap11691 = HMap;
        AlphaValue = theHardwareMap11691.clrSensorC.alpha();
        RedValue = theHardwareMap11691.clrSensorC.red();
        GreenValue = theHardwareMap11691.clrSensorC.green();
        BlueValue = theHardwareMap11691.clrSensorC.blue();
        HueValue = hsvValues[0];     
        
        Color.RGBToHSV((int) (RedValue * 255),
                    (int) (GreenValue * 255),
                    (int) (BlueValue * 255),
                    hsvValues);
        

    }   
        //checks for stone/skystone
    public boolean StonePresent(){
        if(HueValue >= 22){
            stone_present = true;
            }else if(HueValue <= 22){
                Stone = true;
            }else {
                stone_present  = false;
                Stone = false;
            }
            return stone_present;
        }
        
    
    
}
