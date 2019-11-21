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
            autonTurn.AutonTurn_HighPowerAtEnd(-90,0.75,0,1.5,telemetry);

            driveForward(17,0.75,1.5,telemetry);
            
            sleep(200000);
        }
    }

}    
