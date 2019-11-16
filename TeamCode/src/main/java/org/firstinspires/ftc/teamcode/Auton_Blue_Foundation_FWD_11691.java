package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Foundation FWD", group="Autons")
public class Auton_Blue_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

                //get off wall
                driveBackward     (30,0.75,3, telemetry);
                foundationDN();
                waitStep(0.5);
                
                turn_HighPowerAtEnd      (90,1,3, telemetry);
                straff    (-14,0.75,3, telemetry);
                turn_HighPowerAtEnd      (90,1,3, telemetry);
                turn_HighPowerAtEnd      (90,1,1, telemetry);
                driveBackward     (18,0.75,2, telemetry);
                foundationUP();
                waitStep(0.5);
                turn      (90,.25,5, telemetry);
                driveForward     (40,1,3, telemetry);

            sleep(200000);
        }
    }
}    
