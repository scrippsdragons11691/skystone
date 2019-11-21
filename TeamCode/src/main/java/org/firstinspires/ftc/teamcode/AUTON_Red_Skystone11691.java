package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Skystone", group="Autons")

public class AUTON_Red_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.NO);

            waitStep(0.8);
            turn_HighPowerAtEnd(-90,0.75,0,1.5);

            driveForward(17,0.75,1.5);
            
            sleep(200000);
        }
    }

}    
