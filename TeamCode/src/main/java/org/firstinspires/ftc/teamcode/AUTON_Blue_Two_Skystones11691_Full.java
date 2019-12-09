package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Two Skystones Full", group="Autons")

public class AUTON_Blue_Two_Skystones11691_Full extends BaseAuton {

    int blockCount = 0;

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.BLUE, SKYSTONE_FULL.YES,false);

            waitStep(0.05);
            turn_HighPowerAtEnd(0,0.65,4);

            driveBackward(2, 1, 10, false, true, false,true);
            DriveByBumperSwitches(0.25, 2);

            foundationDN();

            this.SK_Grab_Right.goToClawOpenPosition();
            this.SK_Grab_Left.goToClawOpenPosition();
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();

            waitStep(0.4);
            turn_aroundRearRightWheel(13, 1,  5.0);
            driveForward(15, 1, 10,false,false,false, false);
            turn_aroundRearRightWheel(90, 1,  5.0);
            driveForward(25, 1, 10,false,false,false, true);
            foundationUP();

            driveForward(GlobalSettings11691.OneTileLength_inch *3.4, 1, 10,true,true,true,true);

            driveBackward  (2,1,1.0);
            turn_HighPowerAtEnd(0,0.75,3);

            foundationDN();
            run2ndPartOfSkystone(COMPETITION_SIDE.BLUE, SKYSTONE_FULL.YES,true);

            driveForward(GlobalSettings11691.OneTileLength_inch *1.8, 1, 10);

            uninitialize();
            sleep(200000);
        }
    }
}
