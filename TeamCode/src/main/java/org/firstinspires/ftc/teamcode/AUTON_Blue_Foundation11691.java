package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Foundation", group="Autons")

public class AUTON_Blue_Foundation11691 extends BaseAuton {

    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFoundationRoutine( COMPETITION_SIDE.BLUE, PARK_POSITION.NEXT_TO_WALL);

            uninitialize();
            sleep(200000);
        }
    }


}

