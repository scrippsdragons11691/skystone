package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation FWD", group="Autons")

public class Auton_Red_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            
                driveBackward     (30,.75,3, telemetry);
                
                foundationDN();
                waitStep(0.5);
                
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                waitStep(0.5);
                straff    (20,1,3, telemetry);
                turn_HighPowerAtEnd      (-90,1,5, telemetry);
                driveBackward     (18,1,3, telemetry);
                foundationUP();
                turn      (-90,1,5, telemetry);
                waitStep(0.5);
                
                driveForward     (38,1,3.5, telemetry);
                
                sleep(200000);             
        }
    }

}    
