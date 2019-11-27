package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DataServer: Start", group="Linear Opmode")
//@Disabled
public class StartDataServer extends BaseAuton {


    @Override
    public void runOpMode()
    {
        initialize();
        waitForStart();

        telemetry.addData("Data server", "Starting");
        telemetry.update();

        try {
            dataTracing.Start();
            telemetry.addData("Data server", "Running");
            telemetry.update();
        }
        catch (Exception ex) {
                telemetry.addData("exception", ex.toString());
                telemetry.update();
        }

        sleep(5000);
    }
}
