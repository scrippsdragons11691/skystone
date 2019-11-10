package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Blue Skystone", group="Autons")

public class AUTON_Blue_Skystone11691 extends LinearOpMode {

    HardwareMap11691        hMap;
    AutonDrive11691         autonDrive;
    AutonTurn11691          autonTurn;
    ElapsedTime             runtime;
    Intake_11691            intake;
    MoveArm11691            movearm;
    GrabBlock11691          grab;
    Foundation11691         autonFoundation;
    LIFT11691               lift;
    Pusher11691             pusher;
    SK_Block11691           SK;
    AutonColorSensor11691   ColorSensor;
    TapeMeasure11691        TapeMea;
    AutonColorSensor11691   AutonSKDetect;
    int blockCount = 0;
    
    @Override
    public void runOpMode() {

        hMap            = new HardwareMap11691(hardwareMap);
        autonDrive      = new AutonDrive11691(hMap);
        autonTurn       = new AutonTurn11691(hMap);
        runtime         = new ElapsedTime();
        intake          = new Intake_11691(hMap);
        movearm         = new MoveArm11691(hMap);
        grab            = new GrabBlock11691 (hMap);
        lift            = new LIFT11691(hMap);
        pusher          = new Pusher11691(hMap);
        SK              = new SK_Block11691(hMap);
        ColorSensor     = new AutonColorSensor11691(hMap);
        autonFoundation = new Foundation11691(hMap);
        TapeMea         = new TapeMeasure11691(hMap);
        AutonSKDetect   = new AutonColorSensor11691(hMap);
        
        waitForStart();

        while (opModeIsActive()) {
            
            /*
            core functions
                passed variables
                    drive - distance, power, timeout, telemetry pass
                    straff - distance, power, timeout,telemetry pass(left = postitive, right = negative)
                    delay - timeout - used to display information to phone
                    foundationUP - foundation servo up
                    foundationDN - foundation servo down - grabs the foundation
                    grabOPEN - graber to open position
                    grabCLOSE - grabber to close position
                    TapeMeasure - encoder count target, telemetry pass - extends tapemeasure.
                    get_SK - timeout - boolean that will change to true when SK is detected.
                    wait_step - time in seconds
                no passed variables
                    intakeIN - pull blocks in
                    intakeOUT - push blocks out
                    movearmHOME - moves arm to home posiiton
                    movearmNINETY - moves arm to 90 deg position
                    movearmONEEIGHTY - moves arm to 180 position
                    pusherHOME - moves arm to HOME position
                    pusherPUSH - moves arm to block in position.
                    
            */  
            drive       (-7,0.5,2,telemetry);
            straff      (23,0.5,3, telemetry);
            turn        (0,0.5,4, telemetry);
            waitStep(0.1);
            double totalDistanceMoved = get_SK      (20, telemetry);
            waitStep(0.8);
            straff      (-8.5,0.5,2, telemetry);
            turn      (90,0.5,4, telemetry);
            turn      (90,0.5,4, telemetry);
            straff       (44 + totalDistanceMoved,1,4, telemetry);
            SK.SK_ARM(GlobalSettings11691.skhome);
            
            waitStep(0.8);
            turn (90,0.5,4, telemetry);
            straff      (-18,1,3, telemetry);
            

              sleep(200000);
        }
    }
    
    void delay (double timeoutc) {
        double timer = runtime.time();
        while (opModeIsActive() && runtime.time() - timer < timeoutc){
            telemetry.addData("actual angle","degrees= %.2f", hMap.imu.getAngularOrientation(AxesReference.INTRINSIC , AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);
            telemetry.addData("error", autonTurn.error);
            telemetry.addData("angle", autonTurn.targetAngle);
            telemetry.addData("is_moving turn", autonTurn.is_moving);
            telemetry.addData("is_moving drive", autonDrive.is_moving);
            telemetry.addData("actualangle", autonTurn.actualangle);
            telemetry.addData("LR encoder","positione=", hMap.LR.getCurrentPosition());
            telemetry.addData("Runtime",runtime.time()); 
            telemetry.addData("Timeout", timeoutc);
            telemetry.update();
        }
    }
    
    void waitStep(double wait_in_sec)
    {
        double timer = runtime.time();
        while (opModeIsActive() && (runtime.time() - timer < wait_in_sec))
        {
        }
    }
    
    void turn (double angle, double powerturn, double timeoutc, Telemetry tele){
        autonTurn.AutonTurn (angle, powerturn, timeoutc, tele);
    }
    
    void drive (double dist, double power, double timeouta, Telemetry tele){
        autonDrive.encoderDriveAuton (dist, power,timeouta, tele);
    }
    
    void straff (double dist, double power, double timeoutb,Telemetry tele){
        autonDrive.Auton_Straff (dist,power,timeoutb, tele);
    }
    
    void Move_Tape (int target, double timeout, Telemetry tele){
        TapeMea.AutonTape (target, timeout, tele);
    }
    
    void foundationUP()     { autonFoundation.Foundation (GlobalSettings11691.foundationHome); }    
    void foundationDN()     { autonFoundation.Foundation (GlobalSettings11691.foundationHook); }
    void grabOPEN()         { grab.Grab         (GlobalSettings11691.grabRelease);   }
    void grabCLOSE()        { grab.Grab         (GlobalSettings11691.grabBlock);  }
    void intakeIN()         { intake.intake     (GlobalSettings11691.intakeInpos);}
    void intakeOUT()        { intake.intake     (GlobalSettings11691.intakeOutpos);}
    void movearmHOME()      { movearm.MoveArm   (GlobalSettings11691.armHome);}
    void movearmNINETY()    { movearm.MoveArm   (GlobalSettings11691.armNinety);}
    void movearmONEEIGHTY() { movearm.MoveArm   (GlobalSettings11691.armOneEighty);}
    void pusherHOME()       { pusher.Pusher     (GlobalSettings11691.pushHome);}
    void pusherPUSH()       { pusher.Pusher     (GlobalSettings11691.pushBlock);}
    
    double get_SK (double timeout,Telemetry tele){
        boolean found = ColorSensor.StoneCheck(tele);
        double distanceToNextStone = 8;
        double distanceToCenter = 0;
        double totalDistanceMoved = 0;
        if (found) { 
         //SK.SK_ARM(GlobalSettings11691.skdown);
        }
        else {
            waitStep(0.2);
         drive (distanceToNextStone,0.5,10, telemetry);
         totalDistanceMoved += distanceToNextStone;
         found = ColorSensor.StoneCheck(tele);
         if (found){ 
           // SK.SK_ARM(GlobalSettings11691.skdown);
         }
         else {
             waitStep(0.2);
            drive (distanceToNextStone,0.5,10, telemetry);
            totalDistanceMoved += distanceToNextStone;
            //SK.SK_ARM(GlobalSettings11691.skdown);
         }
         }
        found = ColorSensor.StoneCheck(tele);
        drive (distanceToCenter * -1,0.5,10, telemetry);
        totalDistanceMoved += distanceToCenter;
        SK.SK_ARM(GlobalSettings11691.skdown);
        
        return totalDistanceMoved * 1;
    }
}    
