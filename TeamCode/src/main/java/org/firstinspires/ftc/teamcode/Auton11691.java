package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Test: Playground", group="Linear Opmode")
//@Disabled
public class Auton11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

         driveForward  (GlobalSettings11691.OneTileLength_inch *3,0.5,10);
        //driveBackward  (40,0.5,5);

        sleep(5000);
    }
}
