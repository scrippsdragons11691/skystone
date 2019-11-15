package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Red Skystone Full", group="Autons")

public class AUTON_Red_Skystone11691_Full extends BaseAuton {

    int blockCount = 0;

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {


            

                drive(4, .5, 1, telemetry);
                straff(23, .75, 3, telemetry);
                turn(0, 0.3, 1, telemetry);
                waitStep(0.1);
                double totalDistanceMoved = get_SK(20, telemetry);
                waitStep(0.8);
                straff(-5, 0.7, 1.5, telemetry);
                turn(-90, 0.75, 1.5, telemetry);
                waitStep(0.2);
                turn(-90, 0.25, 1, telemetry);
                double totalDistToMove = 57 + totalDistanceMoved;
                straff(totalDistToMove, 0.85, 4.5, telemetry);
                SK.SK_ARM(GlobalSettings11691.skhome);
                turn(-90, 0.25, 1, telemetry);

                //grab foundation
                drive(-11.5, .5, 1, telemetry);

                foundationDN();
                waitStep(0.5);

                turn_HighPowerAtEnd(-180, 1, 3, telemetry);
                waitStep(0.5);
                straff(24, 1, 3, telemetry);
                turn_HighPowerAtEnd(-180, 1, 1, telemetry);
                drive(-18, 1, 1.5, telemetry);
                foundationUP();
                waitStep(0.5);

                // Drive under the bridge
                turn(-180, 0.25, 1, telemetry);
                straff(-6, 1, 2, telemetry);
                turn(-180, 0.25, 1, telemetry);
                waitStep(0.5);
                drive(38, 1, 3.5, telemetry);
                sleep(200000);
            }

        }
        double get_SK ( double timeout, Telemetry tele){
            boolean found = ColorSensor.StoneCheck(tele);
            double distanceToNextStone = -8;
            double distanceToCenter = -3;
            double totalDistanceMoved = 0;
            if (found) {
                //SK.SK_ARM(GlobalSettings11691.skdown);
            } else {
                waitStep(0.2);
                drive(distanceToNextStone, 0.5, 10, telemetry);
                totalDistanceMoved += distanceToNextStone;
                found = ColorSensor.StoneCheck(tele);
                if (found) {
                    // SK.SK_ARM(GlobalSettings11691.skdown);
                } else {
                    waitStep(0.2);
                    drive(distanceToNextStone, 0.5, 10, telemetry);
                    totalDistanceMoved += distanceToNextStone;
                    //SK.SK_ARM(GlobalSettings11691.skdown);
                }
            }
            found = ColorSensor.StoneCheck(tele);
            drive(distanceToCenter, 0.5, 10, telemetry);
            totalDistanceMoved += distanceToCenter;
            SK.SK_ARM(GlobalSettings11691.skdown);

            return totalDistanceMoved * -1;
        }
    }
