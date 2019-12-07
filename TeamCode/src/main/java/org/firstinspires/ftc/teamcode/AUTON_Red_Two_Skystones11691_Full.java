package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Two Skystones Full", group="Autons")

public class AUTON_Red_Two_Skystones11691_Full extends BaseAuton {

    int blockCount = 0;

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.YES,false);

            driveBackward       (11,1,5.5);

            turn_HighPowerAtEnd(0,0.75,0,4);

            waitStep(0.1);
            turn_HighPowerAtEnd(0,0.25,0,0.5);
            waitStep(0.1);

            DriveByBumperSwitches(0.25, 2);

            foundationDN();
            this.SK_Grab_Right.goToClawOpenPosition();
            this.SK_Grab_Left.goToClawOpenPosition();
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();
            driveBackward  (6,1,1.0);
            turn_HighPowerAtEnd(-90,0.5,0.25,3);
            driveBackward  (30,1,1.5);

            // Drive under the bridge
            foundationUP();
            waitStep(0.8);
            //driveForward(10,1,1.5);
            straff(-8,0.75,1);
           // turn_HighPowerAtEnd(-90,0.75,0,1.5);

            driveForward(105,1,5);
            turn_HighPowerAtEnd(0,0.5,1.5);

            run2ndPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.YES,true);

            uninitialize();
            sleep(200000);
        }
    }
}
