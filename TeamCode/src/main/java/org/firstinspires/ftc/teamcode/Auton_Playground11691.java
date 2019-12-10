package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Test: Playground", group="Tests")
//@Disabled
public class Auton_Playground11691 extends BaseAuton {


    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        foundationDN();
        waitStep(1);
        turn_aroundRearRightWheel(-13, 1,  5.0);
        driveForward(2.5, 1, 10,false,false,false, false);
     //   turn_aroundRearRightWheel(-30, 1,  5.0);
     //   driveForward(2.5, 1, 10,false,false,false, false);
        turn_aroundRearRightWheel(-90, 1,  5.0);
        driveForward(30, 1, 10,false,false,false, true);
        foundationUP();
        driveForward(15, 1, 10,true,true,true,true);
        driveBackward(GlobalSettings11691.OneTileLength_inch *2.5, 1, 10,true,true,true,true);


        /*double distanceSensorRH;
        double distanceSensorLH;
        do{
            distanceSensorRH = hMap.autonSensorDRH.getDistance(DistanceUnit.INCH);
            distanceSensorLH = hMap.autonSensorDLH.getDistance(DistanceUnit.INCH);

            if( GlobalSettings11691.SendTelemetry) {
                telemetry.addData("RH distance", "%.3f", distanceSensorRH);
                telemetry.addData("LH distance", "%.3f", distanceSensorLH);

                telemetry.update();
            }
        }while( !isStopRequested() && opModeIsActive());
        */
        //DriveByDistanceSensors (0.25, 3.5, 10);

        /*this.SK_Grab_Left.goToClawOpenPosition();
        waitStep(5);
        this.SK_Grab_Left.goToClawGrabPosition();
        waitStep(3);
        this.SK_Grab_Right.goToClawOpenPosition();
        waitStep(3);
        this.SK_Grab_Right.goToClawGrabPosition();
        waitStep(3);
        this.SK_Grab_Left.carryStone();
        this.SK_Grab_Right.carryStone();
*/

        //turn_HighPowerAtEnd(-90, 0.5,  5.0);
        /*driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
        DriveByDistanceSensors( 0.25, 2.5, 20);

        for(int i=0; i<0; i++) {
            driveBackward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
        }*/

        for(int i=0; i<0; i++) {
            driveForward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(-90, 0.5,  5.0);
            waitStep(0.1);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);
            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.1);

            driveBackward(GlobalSettings11691.OneTileLength_inch * 1, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(90, 0.5,  5.0);
            waitStep(0.1);

            driveForward(GlobalSettings11691.OneTileLength_inch * 2, 1, 10);
            waitStep(0.1);

            turn_HighPowerAtEnd(0, 0.5,  5.0);
            waitStep(0.1);

          //  turn_HighPowerAtEnd(0, 0.75,  5.0);
          //  waitStep(0.5);
            //driveForward(GlobalSettings11691.OneTileLength_inch * 3, 1, 10);
        }

        uninitialize();
        sleep(5000);
    }
}
