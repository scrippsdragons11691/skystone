package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


public class AutonColorSensor11691 {

    public double AlphaValue;
    public double RedValue;
    public double GreenValue;
    public double BlueValue;
    public double HueValue;
    public boolean SkyStone;
    public boolean Stone;
    
    HardwareMap11691 theHardwareMap11691;
    
    public AutonColorSensor11691 (HardwareMap11691 HMap){
        float hsvValues[] = {0F, 0F, 0F};
        theHardwareMap11691 = HMap;
        AlphaValue = theHardwareMap11691.autonSensorC.alpha();
        RedValue = theHardwareMap11691.autonSensorC.red();
        GreenValue = theHardwareMap11691.autonSensorC.green();
        BlueValue = theHardwareMap11691.autonSensorC.blue();
        
        Color.RGBToHSV((int) (RedValue * 255),
                    (int) (GreenValue * 255),
                    (int) (BlueValue * 255),
                    hsvValues);
        HueValue = hsvValues[0]; 
        

    }   
        //checks for stone/skystone
    public boolean StoneCheck(Telemetry tele){
        float hsvValues[] = {0F, 0F, 0F};
        AlphaValue = theHardwareMap11691.autonSensorC.alpha();
        RedValue = theHardwareMap11691.autonSensorC.red();
        GreenValue = theHardwareMap11691.autonSensorC.green();
        BlueValue = theHardwareMap11691.autonSensorC.blue();
        
        Color.RGBToHSV((int) (RedValue * 255),
                    (int) (GreenValue * 255),
                    (int) (BlueValue * 255),
                    hsvValues);
        HueValue = hsvValues[0]; 
        tele.addData("Alphavalue", "%.2f",AlphaValue);
        tele.addData("Huevalue", "%.2f",HueValue);
        tele.addData("RedValue", "%.2f",RedValue);
        tele.addData("BlueValue", "%.2f",BlueValue);
        tele.addData("GreenValue", "%.2f",GreenValue);
        tele.update();
        if(HueValue >= 29){
            SkyStone = true;
            }else if(HueValue <= 29){
                Stone = true;
            }else {
                SkyStone = false;
                Stone = false;
            }
            return SkyStone;
        }
        
    
    
}
