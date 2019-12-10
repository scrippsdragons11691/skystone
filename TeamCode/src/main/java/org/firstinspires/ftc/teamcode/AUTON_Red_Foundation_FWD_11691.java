package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation FWD", group="Foundation Autons")

public class AUTON_Red_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFoundationRoutine( COMPETITION_SIDE.RED, PARK_POSITION.NEXT_TO_CENTER_BRIDGE);

            uninitialize();
            sleep(200000);
        }
    }

}    
