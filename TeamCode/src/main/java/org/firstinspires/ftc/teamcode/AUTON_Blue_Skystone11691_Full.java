package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Skystone Full", group="Autons")

public class AUTON_Blue_Skystone11691_Full extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive())   {

            runFirstPartOfBlueSkystone();

            driveBackward       (35,1,5.5, telemetry);

            autonTurn.AutonTurn_HighPowerAtEnd(0,0.75,0,4,telemetry);
            waitStep(0.1);
            autonTurn.AutonTurn_HighPowerAtEnd(0,0.25,0,0.5,telemetry);


            autonDrive.DriveByBumperSwitches(0.25, 2);


            foundationDN();
/*            driveBackward  (6,1,1.0, telemetry);
            autonTurn.AutonTurn_HighPowerAtEnd(90,-0.5,0.25,3,telemetry);
            driveBackward  (30,0.75,1.5, telemetry);

            // Drive under the bridge
            foundationUP();
            waitStep(0.8);
            straff(10,0.75,1,telemetry);
            autonTurn.AutonTurn_HighPowerAtEnd(90,-0.75,0,1.5,telemetry);

            driveForward(45,0.75,1.5,telemetry);
*/
            sleep (200000);
        }
    }


}

