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
    public double AlphaValue2;
    public double RedValue2;
    public double GreenValue2;
    public double BlueValue2;
    public double HueValue2;
    public boolean SkyStone;
    public boolean Stone;
    public boolean SkyStone2;
    public boolean Stone2;
    
    HardwareMap11691 theHardwareMap11691;
    
    public AutonColorSensor11691 (HardwareMap11691 HMap){
        float hsvValues[] = {0F, 0F, 0F};
        float hsvValues2[] = {0F, 0F, 0F};
        theHardwareMap11691 = HMap;
        AlphaValue = theHardwareMap11691.autonSensorCLH.alpha();
        RedValue = theHardwareMap11691.autonSensorCLH.red();
        GreenValue = theHardwareMap11691.autonSensorCLH.green();
        BlueValue = theHardwareMap11691.autonSensorCLH.blue();
        AlphaValue2 = theHardwareMap11691.autonSensorCRH.alpha();
        RedValue2 = theHardwareMap11691.autonSensorCRH.red();
        GreenValue2 = theHardwareMap11691.autonSensorCRH.green();
        BlueValue2 = theHardwareMap11691.autonSensorCRH.blue();
        //Left Color Sensor Hue Value
        Color.RGBToHSV((int) (RedValue * 255),
                    (int) (GreenValue * 255),
                    (int) (BlueValue * 255),
                    hsvValues);
        HueValue = hsvValues[0];
        //Right Color Sensor Hue Value
        Color.RGBToHSV((int) (RedValue2 * 255),
                (int) (GreenValue2 * 255),
                (int) (BlueValue2 * 255),
                hsvValues2);
        HueValue2 = hsvValues2[0];
        

    }   
        //checks for stone/skystone
    public boolean StoneCheck(Telemetry tele){
        float hsvValues[] = {0F, 0F, 0F};
        AlphaValue = theHardwareMap11691.autonSensorCLH.alpha();
        RedValue = theHardwareMap11691.autonSensorCLH.red();
        GreenValue = theHardwareMap11691.autonSensorCLH.green();
        BlueValue = theHardwareMap11691.autonSensorCLH.blue();
        
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
        tele.addData("Alphavalue2", "%.2f",AlphaValue2);
        tele.addData("Huevalue2", "%.2f",HueValue2);
        tele.addData("RedValue2", "%.2f",RedValue2);
        tele.addData("BlueValue2", "%.2f",BlueValue2);
        tele.addData("GreenValue2", "%.2f",GreenValue2);
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

        /*if(HueValue2 >= 29){
            SkyStone2 = true;
        }else if(HueValue2 <= 29){
            Stone2 = true;
        }else {
            SkyStone2 = false;
            Stone2 = false;
        }
        return SkyStone2;*/

        }
        
    
    
}
