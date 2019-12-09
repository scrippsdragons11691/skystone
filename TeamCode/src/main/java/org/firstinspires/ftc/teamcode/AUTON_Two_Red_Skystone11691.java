package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Two Red Skystone", group="Autons")
@Disabled
public class AUTON_Two_Red_Skystone11691 extends BaseAuton {


    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            runFirstPartOfSkystone(COMPETITION_SIDE.RED, SKYSTONE_FULL.NO,true);

            waitStep(0.5);
            turn_HighPowerAtEnd(-90,0.75,0,1.5);

            driveForward(87,1,4);
            turn_HighPowerAtEnd(0,0.35,0,2);
            waitStep(0.5);
            turn_HighPowerAtEnd(0,0.25,0,1.5);
            straff(2, 0.5, 2);
            waitStep(0.5);
            turn_HighPowerAtEnd(0,0.25,0,1);
            
            driveBackward(6,0.25,2);
            double totalDistanceMoved = get_SkyStone(20, telemetry);
            //straff(2, 0.5, 2, telemetry);
            waitStep(0.5);
            driveForward(8,1,4);
            
            turn_HighPowerAtEnd(-90,0.5,0,1);
            waitStep(0.5);
            turn_HighPowerAtEnd(-90,0.25,0,1);
            driveBackward(80,1,4);
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();
            waitStep(0.8);
            
            driveForward(16,1,4);

            uninitialize();
            sleep(200000);
        }
    }

}    
