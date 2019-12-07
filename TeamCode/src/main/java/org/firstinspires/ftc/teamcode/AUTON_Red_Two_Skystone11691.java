package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Two Skystones", group="Autons")

public class AUTON_Red_Two_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            double distance = runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.NO,true);

           // waitStep(0.8);
           // turn_HighPowerAtEnd(-90,0.75,0,1.5);

            driveForward(3 + distance + GlobalSettings11691.stoneLength * 3,1,10);

            waitStep(0.8);
            turn_HighPowerAtEnd(0,0.5,3);

            run2ndPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.NO,true);

            driveForward(40,1,5.5);

            uninitialize();
            sleep(200000);
        }
    }

}    
