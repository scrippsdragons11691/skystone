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

            runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.YES);

            driveBackward       (35,1,5.5);

            turn_HighPowerAtEnd(0,0.75,0,4);

            waitStep(0.1);
            turn_HighPowerAtEnd(0,0.25,0,0.5);
            waitStep(0.1);

            DriveByBumperSwitches(0.25, 2);

            foundationDN();
            driveBackward  (6,1,1.0);
            turn_HighPowerAtEnd(-90,0.5,0.25,3);
            driveBackward  (30,0.75,1.5);

            // Drive under the bridge
            foundationUP();
            waitStep(0.8);
            straff(-10,0.75,1);
            turn_HighPowerAtEnd(-90,0.75,0,1.5);

            driveForward(45,0.75,1.5);

            sleep(200000);
        }
    }
}
