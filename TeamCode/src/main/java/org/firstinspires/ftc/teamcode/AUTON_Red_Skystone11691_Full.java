package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Skystone Full", group="Autons")

public class AUTON_Red_Skystone11691_Full extends BaseAuton {

    int blockCount = 0;

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            driveBackward    (30,.75,6, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SkyStone      (20, telemetry);
            waitStep(0.8);
            //driveForward      (5,1,3, telemetry);
            waitStep(0.1);
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.25,0.35,3,telemetry);
            waitStep(0.2);
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.25,0,3,telemetry);
            waitStep(0.1);
            straff(-4, 0.5, 2,telemetry);
            driveBackward       (60 + totalDistanceMoved,1,5.5, telemetry);
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();
            driveBackward       (35,1,5.5, telemetry);
            autonTurn.AutonTurn_HighPowerAtEnd(0,0.75,0,4,telemetry);
            //turnLeft(0,0.75,4,telemetry);
            waitStep(0.1);
            autonTurn.AutonTurn_HighPowerAtEnd(0,0.25,0,0.5,telemetry);
//            turnLeft(0,0.75,0.5,telemetry);

            //go to foundation

            autonDrive.DriveByBumperSwitches(0.25, 2);


            foundationDN();
            driveBackward  (6,1,1.0, telemetry);
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.5,0.25,3,telemetry);
            driveBackward  (30,0.75,1.5, telemetry);

            // Drive under the bridge
            foundationUP();
            waitStep(0.8);
            straff(-10,0.75,1,telemetry);
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.75,0,1.5,telemetry);

//            turnLeft(-90,0.75,4,telemetry);
            driveForward(45,0.75,1.5,telemetry);


                sleep(200000);
            }

        }

    }
