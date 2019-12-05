package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="SelfTest: Turn", group="Tests")
//@Disabled
public class Auton_Selftest_Turn11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        turn_HighPowerAtEnd(-90, 0.5,  5.0);
        waitStep(5);
        turn_HighPowerAtEnd(0, 0.5,  5.0);
        waitStep(5);
        turn_HighPowerAtEnd(90, 0.5,  5.0);
        waitStep(5);
        turn_HighPowerAtEnd(0, 0.5,  5.0);

        uninitialize();
        sleep(5000);
    }
}
