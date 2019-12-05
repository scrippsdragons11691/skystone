package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Drive Straight", group="Tests")
//@Disabled
public class Auton_Selftest_Drive11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        driveBackward  (/*GlobalSettings11691.OneTileLength_inch *4*/60,1,5);
        waitStep(5);
        driveForward  (/*GlobalSettings11691.OneTileLength_inch *4*/60,1,5);

        uninitialize();
        sleep(5000);
    }
}

/* Testing:
1) Run the Linear drive test and record the highest wheel angular speed achieved -> set topWheelAngularVelocity_radPerSec
2) Rerun the linear drive test -> should stop at accurate locations
3) Run the Turn test. Expect that first it turns 90 deg, then go to 0, then -90, then back to 0 deg

 */