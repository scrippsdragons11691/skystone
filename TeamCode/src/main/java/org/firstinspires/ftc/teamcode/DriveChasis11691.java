
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class DriveChasis11691 {
    
    HardwareMap11691 theHardwareMap11691;

    double leftPower=0;
    double rightPower=0;

    public DriveChasis11691(HardwareMap11691 HMap){
        theHardwareMap11691 = HMap;
        theHardwareMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        theHardwareMap11691.RF.setDirection(DcMotor.Direction.FORWARD);
         
    }
    
    public void drive (double drive, double turn){
        
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);
        theHardwareMap11691.LR.setPower(leftPower);
        theHardwareMap11691.LF.setPower(leftPower);
        theHardwareMap11691.RR.setPower(rightPower);
        theHardwareMap11691.RF.setPower(rightPower);
    }
    public void straff(double pow){
        
         theHardwareMap11691.LR.setPower(pow);
         theHardwareMap11691.LF.setPower(-pow);
         theHardwareMap11691.RR.setPower(-pow);
         theHardwareMap11691.RF.setPower(pow);
         
    }
    
}
     
