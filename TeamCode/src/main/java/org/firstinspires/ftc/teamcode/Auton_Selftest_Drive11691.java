package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Drive Straight", group="Linear Opmode")
//@Disabled
public class Auton_Selftest_Drive11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        driveForward  (60,1,5);
        driveBackward  (60,1,5);

        sleep(5000);
    }
}
