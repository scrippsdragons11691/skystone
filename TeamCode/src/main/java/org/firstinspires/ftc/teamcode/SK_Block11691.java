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
        ARM_LOCATION theArm;
        double homePosition;
        double skystoneLatchPosition;

        public enum ARM_LOCATION {Left,Right}

        public SK_Block11691(HardwareMap11691 HMap, ARM_LOCATION arm){
                theHardwareMap= HMap;
                theArm = arm;
                if(arm == ARM_LOCATION.Right)
                {
                        homePosition = 1;
                        skystoneLatchPosition = 0.75;
                }
                else
                {
                        homePosition = 0.5;
                        skystoneLatchPosition = 0.65;
                }
        }

        public void goToHomePosition()
        {
                goToPosition(homePosition);
        }

        public void GrabSkystone()
        {
                goToPosition(skystoneLatchPosition);
        }

        void goToPosition(double pos)
        {
                if(theArm == ARM_LOCATION.Left)
                        theHardwareMap.SK_BlockRight.setPosition(pos);
                else
                        theHardwareMap.SK_Block.setPosition(pos);

        }

        public void SK_ARM (double ArmPos){
        theHardwareMap.SK_Block.setPosition(ArmPos);
        }
        public void SK_ARM_Right (double ArmPos){ theHardwareMap.SK_BlockRight.setPosition(ArmPos);
        }
}
