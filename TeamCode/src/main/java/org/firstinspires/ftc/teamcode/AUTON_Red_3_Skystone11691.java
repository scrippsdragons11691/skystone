package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red 3 Skystones", group="Autons")

public class AUTON_Red_3_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runSkystones(COMPETITION_SIDE.RED, true);

            uninitialize();
            sleep(200000);
        }
    }

}    
