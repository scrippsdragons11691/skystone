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

            straff(20,0.75,2);
            straff(-20,0.75,2);

            sleep(200000);
        }
    }
}
