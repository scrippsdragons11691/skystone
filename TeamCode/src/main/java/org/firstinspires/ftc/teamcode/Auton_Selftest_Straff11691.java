package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Straff Straight", group="Tests")
//@Disabled
public class Auton_Selftest_Straff11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            straff(GlobalSettings11691.OneTileLength_inch,0.75,2);
            waitStep(5);
            straff(-1*GlobalSettings11691.OneTileLength_inch,0.75,2);

            uninitialize();
            sleep(200000);
        }
    }
}
