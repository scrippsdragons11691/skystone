package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DataServer: Stop", group="Linear Opmode")
//@Disabled
public class StopDataServer extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        telemetry.addData("Data server", "Stopping");
        telemetry.update();
        try {
            dataTracing.stop();
            telemetry.addData("Data server", "Stopped");
            telemetry.update();
        }
        catch (Exception ex) {
            telemetry.addData("exception", ex.toString());
            telemetry.update();
        }

        sleep(5000);
    }
}
