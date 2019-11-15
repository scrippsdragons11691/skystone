package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation", group="Autons")

public class AUTON_Red_Foundation11691 extends BaseAuton {

    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
                //get off wall
                
                drive     (-30,.75,3, telemetry);
                
                foundationDN();
                waitStep(0.5);
                
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                waitStep(0.5);
                
                
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                straff    (24,1,3, telemetry);
                
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                drive     (-18,1,3, telemetry);
                foundationUP();
                turn      (-90,1,5, telemetry);
                waitStep(0.5);
                drive     (2,0.5,3, telemetry);
                turn      (-90,1,5, telemetry);
                straff    (25,0.5,3, telemetry);
                waitStep(0.5);
                turn      (-90,0.25,2, telemetry);
                waitStep(8.5);
                drive     (38,1,3.5, telemetry);
                
                sleep(200000);
        }
    }

    
   /* void get_SK (double timeout){
        boolean found = ColorSensor.StoneCheck();
        time = runtime.time();
        while (opModeIsActive() && !found && (runtime.time()-time < timeout)) {
            autonDrive.basicDrive(0.5);
        }
        SK.SK_ARM(GlobalSettings11691.skdown);
    }*/
}    
