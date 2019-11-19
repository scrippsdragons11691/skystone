package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Foundation FWD", group="Autons")
public class Auton_Blue_Foundation_FWD_11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFoundationRoutine( COMPETITION_SIDE.BLUE, PARK_POSITION.NEXT_TO_CENTER_BRIDGE);

            sleep(200000);
        }
    }
}    
