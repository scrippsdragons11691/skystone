package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Test: Playground", group="Tests")
//@Disabled
public class Auton11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

       // turn_HighPowerAtEnd(-90, 0.5,  5.0);
        /*driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
        DriveByDistanceSensors( 0.25, 2.5, 20);

        for(int i=0; i<0; i++) {
            driveBackward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
        }*/

        for(int i=0; i<2; i++) {
            driveForward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(-90, 0.5,  5.0);
            waitStep(0.1);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.1);

            driveBackward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(90, 0.5,  5.0);
            waitStep(0.1);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.1);

          //  turn_HighPowerAtEnd(0, 0.75,  5.0);
          //  waitStep(0.5);
            //driveForward(GlobalSettings11691.OneTileLength_inch * 3, 1, 10);
        }

        uninitialize();
        sleep(5000);
    }
}
