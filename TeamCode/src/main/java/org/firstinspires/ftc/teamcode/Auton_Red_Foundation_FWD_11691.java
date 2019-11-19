package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation FWD", group="Autons")

public class Auton_Red_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            driveBackward  (1,1,0.5, telemetry);
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

            foundationUP();
            waitStep(0.8);
            straff(-14,0.75,2,telemetry);
            waitStep(0.8);
			//todo 0.25 should be 0 so that the robot just squares up
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.5,0.25,3,telemetry);
            waitStep(0.8);

            driveForward(45,0.8,4,telemetry);
                
                sleep(200000);             
        }
    }

}    
