package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Left SK Arm", group="Tests")
//@Disabled
public class Auton_Selftest_Left_SKarm11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        SK_Grab_Right.goToHomePosition();
        SK_Grab_Left.goToHomePosition();
        waitStep(2);
        SK_Grab_Left.goToApproachPosition();
        waitStep(3);
        SK_Grab_Left.goToClawPreGrabPosition();
        waitStep(0.2);
        SK_Grab_Left.GrabSkystone();
        waitStep(3);
        SK_Grab_Left.goToClawGrabPosition();
        waitStep(3);
        SK_Grab_Left.carryStone();
        waitStep(3);
        SK_Grab_Left.goToClawOpenPosition();
        SK_Grab_Left.goToHomePosition();

        uninitialize();
        sleep(5000);
    }
}
