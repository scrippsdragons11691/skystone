package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

//The Arm is for grabbing the moving the skystone to different postions/angles
public class SK_Block11691{

        private double homePosition;
        private double skystoneGrabPosition;
        private double approachPosition;
        private double carryPosition;
        private double openClawPosition;
        private double grabClawPosition;
        private Servo theServo;
        private Servo clawServo;

        public enum SKYSTONE_ARM_LOCATION {Left,Right}

        public SK_Block11691(HardwareMap11691 HMap, SKYSTONE_ARM_LOCATION arm){

                if(arm == SKYSTONE_ARM_LOCATION.Right)
                {
                        homePosition = GlobalSettings11691.RightSKGrabber_homePosition;
                        skystoneGrabPosition = GlobalSettings11691.RightSKGrabber_GrabPosition;
                        carryPosition = GlobalSettings11691.RightSKGrabber_CarryPosition;
                        approachPosition = GlobalSettings11691.RightSKGrabber_ApproachPosition;
                        openClawPosition = GlobalSettings11691.RightSKGrabberClaw_OpenPosition;
                        grabClawPosition = GlobalSettings11691.RightSKGrabberClaw_GrabPosition;
                        theServo = HMap.SK_BlockRight;
                        clawServo = HMap.Claw_Right;
                }
                else
                {
                        homePosition = GlobalSettings11691.LeftSKGrabber_homePosition;
                        skystoneGrabPosition = GlobalSettings11691.LeftSKGrabber_GrabPosition;
                        carryPosition = GlobalSettings11691.LeftSKGrabber_CarryPosition;
                        approachPosition = GlobalSettings11691.LeftSKGrabber_ApproachPosition;
                        openClawPosition = GlobalSettings11691.LeftSKGrabberClaw_OpenPosition;
                        grabClawPosition = GlobalSettings11691.LeftSKGrabberClaw_GrabPosition;
                        theServo = HMap.SK_BlockLeft;
                        clawServo = HMap.Claw_Left;
                }
        }

        public void goToHomePosition()
        {
                theServo.setPosition(homePosition);
        }

        public void goToApproachPosition()
        {
                theServo.setPosition(approachPosition);
        }

        public void GrabSkystone()     { theServo.setPosition(skystoneGrabPosition);  }

        public void carryStone() {theServo.setPosition((carryPosition));}

        public void goToClawOpenPosition() {clawServo.setPosition(openClawPosition);}

        public void goToClawGrabPosition() {clawServo.setPosition(grabClawPosition);}
}
