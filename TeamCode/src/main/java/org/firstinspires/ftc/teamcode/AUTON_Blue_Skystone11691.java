package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.BLUE, SKYSTONE_FULL.NO);

            waitStep(0.8);
            turn_HighPowerAtEnd(90,0.25,0,3);

            driveForward(40,1,5.5);

            uninitialize();
            sleep(200000);
        }
    }


}    
