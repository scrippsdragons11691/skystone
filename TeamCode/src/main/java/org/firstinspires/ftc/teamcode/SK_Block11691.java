package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//The Arm is for grabbing the moving the skystone to different postions/angles
public class SK_Block11691{
        HardwareMap11691 theHardwareMap;
        
public SK_Block11691(HardwareMap11691 HMap){
        theHardwareMap= HMap;
        }
        public void SK_ARM (double ArmPos){
        theHardwareMap.SK_Block.setPosition(ArmPos);
        }
        public void SK_ARM_Right (double ArmPos){
                theHardwareMap.SK_BlockRight.setPosition(ArmPos);
        }
}
