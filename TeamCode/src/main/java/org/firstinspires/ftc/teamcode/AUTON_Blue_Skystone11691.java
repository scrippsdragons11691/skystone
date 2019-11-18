package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfBlueSkystone();

            waitStep(0.8);
            autonTurn.AutonTurn_HighPowerAtEnd(90,-0.75,0,3,telemetry);

            driveForward(35,1,5.5, telemetry);

            sleep(200000);
        }
    }


}    
