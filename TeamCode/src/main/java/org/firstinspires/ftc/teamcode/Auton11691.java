package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basic: autontest", group="Linear Opmode")
//@Disabled
public class Auton11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            
            driveForward  (6,1.0,10, telemetry);
            turn     (180,0.5,10, telemetry);
            
            if(true)
            {
                //turn (-90,0.75,5, telemetry);
                /*
                straff (15,0.5,10, telemetry);
                waitStep(1);
                turn (-90,0.5,5, telemetry);
                waitStep(1);
                turn (-180,0.5,5, telemetry);
                waitStep(1);
                turn (-270,0.5,5, telemetry);
                waitStep(1);
                turn (0,0.5,5, telemetry);
                
                drive     (-29.5,0.5,10, telemetry); 
                foundationDN();
                waitStep(2);
                
                drive     (28,0.5,10, telemetry);
                foundationUP();
                waitStep(1);
    
                straff      (-20,1,10, telemetry);   
    
                turn      (45,1,5, telemetry);*/        
            }
            /*else if(false)
            {
                
              intakeIN();
              waitStep(5);
              intakeOUT();
                
            }else{
                
                for(int i = 0; i < 2; i++){
                pusherHOME();
                waitStep(5);
                pusherPUSH();
                waitStep(2);
                }
            }*/
            delay(20);
            sleep(200000);
        }
    }
}
