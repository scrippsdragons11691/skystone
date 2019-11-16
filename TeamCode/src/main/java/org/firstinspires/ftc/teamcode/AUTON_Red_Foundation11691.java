package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Foundation", group="Autons")

public class AUTON_Red_Foundation11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {
            //get off wall

            driveBackward(28, 0.35, 3, telemetry);

            foundationDN();
            waitStep(0.8);

            turn_HighPowerAtEnd(-90, 0.5, 5, telemetry);
            waitStep(0.5);


            turn_HighPowerAtEnd(-90, 0.5, 5, telemetry);
            straff(30, 0.5, 3, telemetry);

            turn_HighPowerAtEnd(-90, 0.5, 5, telemetry);
            driveBackward(18, 0.5, 3, telemetry);
            foundationUP();
            turnRight(90, 0.5, 5, telemetry);
            waitStep(0.5);
            driveForward(2, 0.5, 3, telemetry);
            turnRight(90, 0.5, 5, telemetry);
            straff(25, 0.5, 3, telemetry);
            waitStep(0.5);
            turnRight(90, 0.25, 2, telemetry);
            waitStep(0.5);
            driveForward(38, 0.5, 3.5, telemetry);

            sleep(200000);
        }
    }
}
