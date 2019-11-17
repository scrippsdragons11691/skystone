package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Basic: autontest", group="Linear Opmode")
//@Disabled
public class Auton11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();



        while (opModeIsActive()) {
            driveForward  (60,1,5, telemetry);
            driveBackward  (60,1,5, telemetry);

/*            driveBackward  (1,1,0.5, telemetry);
            waitStep(0.2);

            straff(16,0.75,2,telemetry);
            waitStep(0.2);

            autonTurn.AutonTurn (0, 0.25, 0.5, telemetry);
            waitStep(0.2);


            driveBackward  (27,1,2.5, telemetry);
            autonDrive.DriveByBumperSwitches(0.25, 1);


            foundationDN();
            driveBackward  (4,1,0.5, telemetry);
            //waitStep(0.8);
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.5,0.25,3,telemetry);
            driveBackward  (18,1,2, telemetry);
            //turnLeft(0,0.5, 1, telemetry);
*/


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
