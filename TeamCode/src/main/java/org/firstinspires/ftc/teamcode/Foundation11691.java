
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//The Foundation class is for moving the foundation in and out of the building site
public class Foundation11691{
    
    HardwareMap11691 theHardwareMap11691;
        
public Foundation11691 (HardwareMap11691 HMap){
        theHardwareMap11691= HMap;
        }
public void Foundation (double FoundPos){
        theHardwareMap11691.Foundation.setPosition(FoundPos);
        }

}
