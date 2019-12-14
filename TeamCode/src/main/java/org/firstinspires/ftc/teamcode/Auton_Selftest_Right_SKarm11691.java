package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Right SK Arm", group="Tests")
//@Disabled
public class Auton_Selftest_Right_SKarm11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        SK_Grab_Left.goToHomePosition();
        SK_Grab_Right.goToHomePosition();
        waitStep(5);
        SK_Grab_Right.goToApproachPosition();
        waitStep(5);
        SK_Grab_Left.goToClawPreGrabPosition();
        waitStep(0.2);
        SK_Grab_Right.GrabSkystone();
        waitStep(5);
        SK_Grab_Right.goToClawGrabPosition();
        waitStep(5);
        SK_Grab_Right.carryStone();
        waitStep(5);
        SK_Grab_Left.goToHomePosition();
        waitStep(5);
        SK_Grab_Right.goToHomePosition();

        uninitialize();
        sleep(5000);
    }
}
