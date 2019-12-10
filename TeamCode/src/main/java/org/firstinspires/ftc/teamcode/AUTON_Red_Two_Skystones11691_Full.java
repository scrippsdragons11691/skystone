package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red 2 Skystones Full", group="Autons")

public class AUTON_Red_Two_Skystones11691_Full extends BaseAuton {

    int blockCount = 0;

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        COMPETITION_SIDE competition_side = COMPETITION_SIDE.RED;

        while (opModeIsActive()) {

            runTwoSkystonesFull(COMPETITION_SIDE.RED);

            uninitialize();
            sleep(200000);
        }
    }
}
