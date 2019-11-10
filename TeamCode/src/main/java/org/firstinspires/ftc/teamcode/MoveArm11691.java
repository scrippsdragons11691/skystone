
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//The Arm is for moving the skystone to different postions/angles
public class MoveArm11691{
        HardwareMap11691 theHardwareMap;
        
public MoveArm11691(HardwareMap11691 HMap){
        theHardwareMap= HMap;
        }
        public void MoveArm (double ArmPos){
        theHardwareMap.MoveArm.setPosition(ArmPos);
        }
        
        
}
