package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="SkyStonePro2", group="Linear Opmode")

public class TeleOp11691 extends LinearOpMode{

    // Declare OpMode members.
    HardwareMap11691 TeleOp_HM;
    DriveChasis11691 TeleOp_DC;
    Intake_11691  TeleOp_InT;
    MoveArm11691 movearm;
    GrabBlock11691  grabpos;
    Foundation11691 TeleOpfou;
    LIFT11691 lift;
    Pusher11691 Spush;
    SK_Block11691 SK;
    //AutonColorSensor11691 ColorSensor;
    TapeMeasure11691 TapeMeasure;
    Block_Check11691 BC;
    ElapsedTime runtime;

    //Switching grabRelease to FoundationHook

    @Override
    public void runOpMode() {

        runtime                 = new ElapsedTime();
        TeleOp_HM               = new HardwareMap11691(hardwareMap);
        TeleOp_DC               = new  DriveChasis11691(TeleOp_HM);
        movearm                 = new MoveArm11691(TeleOp_HM);
        TeleOpfou               = new Foundation11691(TeleOp_HM);
        lift                    = new LIFT11691(TeleOp_HM);
        TeleOp_InT              = new Intake_11691(TeleOp_HM);
        Spush                   = new Pusher11691(TeleOp_HM);
        grabpos                 = new GrabBlock11691(TeleOp_HM);
        SK                      = new SK_Block11691(TeleOp_HM, SK_Block11691.SKYSTONE_ARM_LOCATION.Right);
        //ColorSensor             = new AutonColorSensor11691(TeleOp_HM);
        TapeMeasure             = new TapeMeasure11691(TeleOp_HM);
        BC                      = new Block_Check11691(TeleOp_HM);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double drive = 0;
        double turn = 0;
        double straff_power=0;
        double lift_power=0;
        double aLastTime=0;
        double yLastTime=0;               //last time gamepad 2 b button was pressed
        double xLastTime=0;               //last time gamepad 2 x button was pressed
        double dpadUpLastTime=0;
        boolean Apush = false;
        boolean Afound = true;
        boolean Agrab = true;
        boolean SKgrab = true;
        boolean FoundHome = true;
        boolean buttonb = false;
        boolean Color = false;
        double slow = 1;

        // Wait for the game to start (driver presses PLAY)

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if(gamepad2.right_stick_y > 0.5)    { movearm.MoveArm(GlobalSettings11691.armOneEighty); }          //move arm  to 180
            if(gamepad2.right_stick_y < -0.5)   { movearm.MoveArm(GlobalSettings11691.armHome);}                //move arm to home
            if(gamepad2.right_stick_x > 0.5)    { movearm.MoveArm(GlobalSettings11691.armNinety); }             //movearm to 90
            if(gamepad2.dpad_right)             { TapeMeasure.TapeM(1.0); } else {TapeMeasure.TapeM(0);}//tapemeasure out
            if(gamepad2.dpad_down)              { TapeMeasure.TapeM(-1.0); } else {TapeMeasure.TapeM(0);} //tapemeasure in
            if(gamepad2.dpad_left)              { Spush.Pusher(GlobalSettings11691.pushEject);}                 //eject position is 0
            if(gamepad2.right_bumper)           { TeleOp_InT.intake(GlobalSettings11691.intakeInpos);  }        //Intake In Position
            if(gamepad2.left_bumper)            { TeleOp_InT.intake(GlobalSettings11691.intakeOutpos); }        //Intake Out Position
            if(!gamepad2.right_bumper && !gamepad2.left_bumper ) { TeleOp_InT.intake(0); }                   //Intake off
            if (gamepad2.left_stick_y> 0.5)     { lift.rotate(); }                           //Lift to third heght
            if (gamepad2.left_stick_y<-0.5)     { lift.down();  }                           //down - almost to home position
            if (gamepad2.left_stick_x<-0.5)     { lift.LiftLevel(-1); /*level-1*/}                          //left - ready to rotate
            if (gamepad2.left_stick_x>0.5)      { lift.LiftLevel(1);  /*level+1*/}                         //right - movs almost up
            if(gamepad1.left_bumper)            { slow  = 0.4;} else { slow = 1;}        //Intake In Position

            if(gamepad2.dpad_up)
                if((runtime.time() - dpadUpLastTime) > 0.5){
                    if(FoundHome){
                        FoundHome = false;
                        TeleOpfou.Foundation(GlobalSettings11691.foundationHook);//foundation to hook
                        dpadUpLastTime = runtime.time();
                    } else {
                        FoundHome = true;
                        TeleOpfou.Foundation(GlobalSettings11691.foundationHome);//foundation to home
                        dpadUpLastTime = runtime.time();
                    }
                }

            if(gamepad2.y)  {
                if((runtime.time() - yLastTime) > 0.5)
                {
                    if(Apush) {
                        Apush = false;
                        Spush.Pusher(GlobalSettings11691.pushBlock); //push block into holder
                        yLastTime = runtime.time();
                    }
                    else {
                        Apush = true;
                        Spush.Pusher(GlobalSettings11691.pushHome); //push arm to home
                        yLastTime = runtime.time();
                    }
                }

            }

            if (gamepad2.x){
                if((runtime.time() - xLastTime) > 0.5) {
                    if(Agrab){
                        Agrab = false;
                        grabpos.Grab(GlobalSettings11691.grabBlock);   // Grabber to release position
                        xLastTime = runtime.time();
                    }
                    else {
                        Agrab = true;
                        grabpos.Grab(GlobalSettings11691.grabRelease);   // Grabber to grab home position
                        xLastTime = runtime.time();
                    }
                }
            }

            //todo which buttons will we use for the skystone grabber?
            if (gamepad2.a){
                if((runtime.time() - aLastTime) > 0.5)
                {
                    if(SKgrab){
                        SKgrab = false;
                        SK.GrabSkystone();
                        aLastTime = runtime.time();
                    }
                    else {
                        SKgrab = true;
                        SK.goToHomePosition();
                        aLastTime = runtime.time();
                    }
                }
            }


            // Preset-to get ready for intake:   Pusher = home, grabber = released; lift = down, foundation = home.

            if (gamepad2.b)
            {
                Spush.Pusher(GlobalSettings11691.pushHome);
                grabpos.Grab(GlobalSettings11691.grabRelease);
                movearm.MoveArm(GlobalSettings11691.armHome);
                TeleOpfou.Foundation(GlobalSettings11691.foundationHome);
            }

            /*
            if (gamepad1.left_bumper)  //auto grab while block is in
            {
                Spush.Pusher(GlobalSettings11691.pushBlock);
                grabpos.Grab(GlobalSettings11691.grabBlock);
            }

            if (gamepad1.a)   // auto reset
            {
                Spush.Pusher(GlobalSettings11691.pushHome);
                grabpos.Grab(GlobalSettings11691.grabHome);
                TeleOpfou.Foundation(GlobalSettings11691.foundationHome);
                TeleOpfou.Arm.GlobalSettings11691.armNinety();

                }

            if (gamepad1.b)   //auto grab
            {
                TeleOp_InT.intake(GlobalSettings11691.intakeInpos);
                if (BC.StonePresent()){
                    Spush.Pusher(GlobalSettings11691.pushBlock);
                    wait_Step(.5);
                    grabpos.Grab(GlobalSettings11691.grabBlock);
                }
            }
            if (gamepad1.y){
                Color = true;
            }
            */

            //manual DR4b control Ltrigger up, Rtrigger down

            if (gamepad2.left_trigger > 0.1)
            {
                lift_power = gamepad2.left_trigger; // Up
                lift.move(lift_power);
            }
            else if (gamepad2.right_trigger > 0.1)  // Down
            {
                lift_power = -gamepad2.right_trigger;
                lift.move(lift_power * 0.90);
            }
            else { lift.move(0.1);}

            // Drive control
            drive = Math.pow(-gamepad1.left_stick_y,3) * slow;
            turn  = Math.pow(gamepad1.right_stick_x,3) * slow;
            if((Math.abs(drive)>0.0001) || (Math.abs(turn)>0.0001)) {
                TeleOp_DC.drive(drive, turn);
            }
            else {
                // straff control
                straff_power = Math.pow(-gamepad1.left_stick_x, 3) * slow;
                TeleOp_DC.straff(straff_power);
            }
            //Telemetry
            telemetry.addData("Distance","%.2f",TeleOp_HM.autonSensorDLH.getDistance(DistanceUnit.INCH));
            telemetry.addData("DR4BPOT","Voltage= %.2f",TeleOp_HM.pot.getVoltage());
            telemetry.addData("target position","Voltage= %.2f",lift.targetPosition);
            telemetry.addData("lift power","Voltage= %.2f", lift_power);
            telemetry.addData("lift set power","Voltage= %.2f", lift.driveSpeedSetPoint);
            telemetry.addData("lift set power","Voltage= %.2f", lift.lift_Move);
            telemetry.addData("gamepad2.b", gamepad2.b);
            telemetry.addData("Runtime",runtime.time());
            telemetry.addData("yLastTime", yLastTime);
            telemetry.addData("xLastTime", xLastTime);
            telemetry.addData("Color", Color);
            telemetry.update();

        }
    }
    void wait_Step(double wait_in_sec) {
        double timer = runtime.time();
        while (opModeIsActive() && (runtime.time() - timer < wait_in_sec)) {  }
    }
}
