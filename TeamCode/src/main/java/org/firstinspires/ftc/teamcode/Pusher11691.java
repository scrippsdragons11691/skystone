package org.firstinspires.ftc.teamcode;

public class Pusher11691 {

        HardwareMap11691 theHardwareMap11691;

        public Pusher11691(HardwareMap11691 HMap){
                theHardwareMap11691= HMap;
        }
        public void Pusher(double PushPos){
                theHardwareMap11691.Pusher.setPosition(PushPos);
        }
}
