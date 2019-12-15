package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red 2 Skystones", group="Autons")

public class AUTON_Red_2_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            waitStep(4);
            runSkystones(COMPETITION_SIDE.RED, false);

            uninitialize();
            sleep(200000);
        }
    }

}    
