package org.firstinspires.ftc.teamcode;


public class GrabBlock11691 {

    HardwareMap11691 theHardwareMap11691;
        
public GrabBlock11691 (HardwareMap11691 HMap){
        theHardwareMap11691= HMap;
        }
public void Grab (double GrabPos){
        theHardwareMap11691.Grabber.setPosition(GrabPos);
        }

}
