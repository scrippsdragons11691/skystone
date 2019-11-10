package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Pusher11691 {

    // todo: write your code here
HardwareMap11691 theHardwareMap11691;
        
public Pusher11691(HardwareMap11691 HMap){
        theHardwareMap11691= HMap; 
        }
        public void Pusher(double PushPos){
        theHardwareMap11691.Pusher.setPosition(PushPos);
        }
}
