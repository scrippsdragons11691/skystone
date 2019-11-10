package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;




public class Intake_11691{

HardwareMap11691 theHardwareMap11691;

//DcMotor IntakeL;
//DcMotor IntakeR;


public Intake_11691(HardwareMap11691 HMap){
    theHardwareMap11691 = HMap;
    theHardwareMap11691.IntakeL.setDirection(DcMotor.Direction.REVERSE);
    theHardwareMap11691.IntakeR.setDirection(DcMotor.Direction.FORWARD);
}

public void intake(double pow){
    
theHardwareMap11691.IntakeL.setPower(pow);
theHardwareMap11691.IntakeR.setPower(pow);
    
    }
}

