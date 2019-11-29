package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Test: Playground", group="Linear Opmode")
//@Disabled
public class Auton11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        for(int i=0; i<2; i++) {
            driveForward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.25);

            turn_HighPowerAtEnd(-90, 0.5,  5.0);
            waitStep(0.25);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.25);
            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.25);

            driveBackward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.25);

            turn_HighPowerAtEnd(90, 0.5,  5.0);
            waitStep(0.25);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.25);

            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.25);

          //  turn_HighPowerAtEnd(0, 0.75,  5.0);
          //  waitStep(0.5);
            //driveForward(GlobalSettings11691.OneTileLength_inch * 3, 1, 10);
        }
        sleep(5000);
    }
}
