package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue 3 Skystones", group="Autons")

public class AUTON_Blue_Three_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runSkystones(COMPETITION_SIDE.BLUE, true);

            uninitialize();
            sleep(200000);
        }
    }


}    
