package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Two Skystones", group="Autons")

public class AUTON_Blue_Two_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            double distance = runFirstPartOfSkystone(COMPETITION_SIDE.BLUE, SKYSTONE_FULL.NO, true);

            waitStep(0.8);
            turn_HighPowerAtEnd(90,0.25,0,3);

            driveForward(3 + distance + GlobalSettings11691.stoneLength * 3,1,10);

            waitStep(0.8);
            turn_HighPowerAtEnd(0,0.5,3);

            run2ndPartOfSkystone(COMPETITION_SIDE.BLUE, SKYSTONE_FULL.NO,true);

            driveForward(40,1,5.5);

            uninitialize();
            sleep(200000);
        }
    }


}    
