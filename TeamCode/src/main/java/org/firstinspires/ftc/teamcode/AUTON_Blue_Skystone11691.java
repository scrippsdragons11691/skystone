package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends BaseAuton {

    @Override
    public void runOpMode() {

        initialize();
        waitForStart();

        while (opModeIsActive()) {

            driveBackward       (7,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turnLeft        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SkyStone      (20, telemetry);
            waitStep(0.8);
            straff      (-8.5,0.5,2, telemetry);
            turnLeft      (90,0.5,4, telemetry);
            turnLeft      (90,0.5,4, telemetry);
            straff       (44 + totalDistanceMoved,1,4, telemetry);
            //SK.SK_ARM(GlobalSettings11691.skhome);
            
            waitStep(0.8);
            turnLeft (90,0.5,4, telemetry);
            straff      (-18,1,3, telemetry);
            

              sleep(200000);
        }
    }


}    
