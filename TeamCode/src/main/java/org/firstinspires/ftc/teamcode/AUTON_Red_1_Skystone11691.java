package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red 1 Skystone", group="Autons")

public class AUTON_Red_1_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.NO,true, true, false, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);

            driveForward(30,1,2);

            uninitialize();
            sleep(200000);
        }
    }

}    
