package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation", group="Foundation Autons")

public class AUTON_Red_Foundation11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFoundationRoutine( COMPETITION_SIDE.RED, PARK_POSITION.NEXT_TO_WALL);

            uninitialize();
            sleep(200000);
        }
    }
}
