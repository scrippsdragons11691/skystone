package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

//The Arm is for grabbing the moving the skystone to different postions/angles
public class SK_Block11691{

        private double homePosition;
        private double skystoneGrabPosition;
        private Servo theServo;

        public enum SKYSTONE_ARM_LOCATION {Left,Right}

        public SK_Block11691(HardwareMap11691 HMap, SKYSTONE_ARM_LOCATION arm){

                if(arm == SKYSTONE_ARM_LOCATION.Right)
                {
                        homePosition = GlobalSettings11691.RightSKGrabber_homePosition;
                        skystoneGrabPosition = GlobalSettings11691.RightSKGrabber_GrabPosition;
                        theServo = HMap.SK_BlockRight;
                }
                else
                {
                        homePosition = GlobalSettings11691.LeftSKGrabber_homePosition;
                        skystoneGrabPosition = GlobalSettings11691.LeftSKGrabber_GrabPosition;
                        theServo = HMap.SK_BlockLeft;
                }
        }

        public void goToHomePosition()
        {
                theServo.setPosition(homePosition);
        }

        public void GrabSkystone()
        {
                theServo.setPosition(skystoneGrabPosition);
        }
}
