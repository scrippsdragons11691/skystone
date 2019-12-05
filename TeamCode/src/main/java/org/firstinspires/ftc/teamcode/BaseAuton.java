package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class BaseAuton extends LinearOpMode{

    public enum PARK_POSITION {NEXT_TO_WALL, NEXT_TO_CENTER_BRIDGE}
    public enum COMPETITION_SIDE {RED, BLUE}
    public enum SKYSTONE_FULL {YES, NO}

    @Override
    public void runOpMode() throws InterruptedException {

    }

    public static dataTracingSocket dataTracing = null;
    HardwareMap11691        hMap;
    AutonDrive11691         autonDrive;
    AutonTurn11691          autonTurn;
    ElapsedTime runtime;
    Intake_11691            intake;
    MoveArm11691            movearm;
    GrabBlock11691          grab;
    Foundation11691         autonFoundation;
    LIFT11691               lift;
    Pusher11691             pusher;
    public SK_Block11691           SK_Grab_Right;
    public SK_Block11691           SK_Grab_Left;
    ColorSensor11691   leftColorSensor;
    ColorSensor11691   rightColorSensor;
    TapeMeasure11691        TapeMea;

    public SK_Block11691.SKYSTONE_ARM_LOCATION  usedSkystoneArm;

    public void BaseAuton() {
    }

    protected void uninitialize()
    {
        telemetry.addData("Data server", "Stopping");
        telemetry.update();
        try {
            dataTracing.stop();
            telemetry.addData("Data server", "Stop completed");
            telemetry.update();
        }
        catch (Exception ex) {
            telemetry.addData("exception", ex.toString());
            telemetry.update();
        }
    }
    protected void initialize(){
        if(dataTracing == null)
            dataTracing     = new dataTracingSocket();

        dataTracing.theAuton = this;

        try {
            dataTracing.Start();
            telemetry.addData("Data server", "... Connected");
            telemetry.update();
        }
        catch (Exception ex) {
            telemetry.addData("exception", ex.toString());
            telemetry.update();
        }

        runtime         = new ElapsedTime();
        hMap            = new HardwareMap11691(hardwareMap, this);
        autonDrive      = new AutonDrive11691(hMap);
        autonTurn       = new AutonTurn11691(hMap);
        intake          = new Intake_11691(hMap);
        movearm         = new MoveArm11691(hMap);
        grab            = new GrabBlock11691 (hMap);
        lift            = new LIFT11691(hMap);
        pusher          = new Pusher11691(hMap);
        SK_Grab_Right   = new SK_Block11691(hMap, SK_Block11691.SKYSTONE_ARM_LOCATION.Right);
        SK_Grab_Left    = new SK_Block11691(hMap, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);
        leftColorSensor     = new ColorSensor11691(hMap, ColorSensor11691.SKYSTONE_COLOR_SENSOR_LOCATION.Left);
        rightColorSensor     = new ColorSensor11691(hMap, ColorSensor11691.SKYSTONE_COLOR_SENSOR_LOCATION.Right);
        autonFoundation = new Foundation11691(hMap);
        TapeMea         = new TapeMeasure11691(hMap);
        //AutonSKDetect   = new AutonColorSensor11691(hMap);
    }

    protected void delay (double timeoutc) {
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

    protected void waitStep(double wait_in_sec)
    {
        double timer = runtime.time();
        while (opModeIsActive() && !isStopRequested() && (runtime.time() - timer < wait_in_sec))
        {
        }
    }

    protected void DriveByBumperSwitches(double power, double timeout)
    {
        autonDrive.DriveByBumperSwitches( power, timeout, this);
    }

    protected void DriveByDistanceSensors(double power, double distance, double timeout)
    {
        autonDrive.DriveByDistanceSensors( power, distance, timeout, this);
    }

    protected void driveForward (double dist, double power, double timeouta){
        autonDrive.encoderDriveAutonNew (dist, power,timeouta, this);
    }

    protected void driveBackward (double dist, double power, double timeouta){
        autonDrive.encoderDriveAutonNew (dist * -1, power,timeouta, this);
    }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double timeoutc){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, timeoutc, this);
    }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double deltaPower, double timeoutc){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, deltaPower, timeoutc, this);
    }

    protected void straff (double dist, double power, double timeoutb){
        autonDrive.Auton_Straff (dist,power,timeoutb, this);
    }

    protected void Move_Tape (int target, double timeout, Telemetry tele){
        TapeMea.AutonTape (target, timeout, tele);
    }

    protected void foundationUP()     { autonFoundation.Foundation (GlobalSettings11691.foundationHome); }
    protected void foundationDN()     { autonFoundation.Foundation (GlobalSettings11691.foundationHook); }
    protected void grabOPEN()         { grab.Grab         (GlobalSettings11691.grabRelease);   }
    protected void grabCLOSE()        { grab.Grab         (GlobalSettings11691.grabBlock);  }
    protected void intakeIN()         { intake.intake     (GlobalSettings11691.intakeInpos);}
    protected void intakeOUT()        { intake.intake     (GlobalSettings11691.intakeOutpos);}
    protected void movearmHOME()      { movearm.MoveArm   (GlobalSettings11691.armHome);}
    protected void movearmNINETY()    { movearm.MoveArm   (GlobalSettings11691.armNinety);}
    protected void movearmONEEIGHTY() { movearm.MoveArm   (GlobalSettings11691.armOneEighty);}
    protected void pusherHOME()       { pusher.Pusher     (GlobalSettings11691.pushHome);}
    protected void pusherPUSH()       { pusher.Pusher     (GlobalSettings11691.pushBlock);}


    double get_SkyStone (double timeout,Telemetry tele){
        double distanceToNextStone = 7;
        double totalDistanceMoved = 0;

        usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Right;

        leftColorSensor.StoneCheck();
        if (leftColorSensor.StoneCheck()){
            SK_Grab_Left.GrabSkystone();
            usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Left;
            waitStep(0.8);
            SK_Grab_Left.goToClawGrabPosition();
            waitStep(0.8);
            SK_Grab_Left.carryStone();
            SK_Grab_Right.goToHomePosition();
        }
        else {
            if(rightColorSensor.StoneCheck()){
            }
            else {
                straff(distanceToNextStone,0.5,1);
                waitStep(0.5);
                totalDistanceMoved += distanceToNextStone;
            }

            SK_Grab_Right.GrabSkystone();
            waitStep(1.3);
            SK_Grab_Right.goToClawGrabPosition();
            waitStep(1);
            SK_Grab_Right.carryStone();
            SK_Grab_Left.goToHomePosition();
        }

        return totalDistanceMoved * -1;
    }


    protected void runFirstPartOfSkystone(COMPETITION_SIDE competitionSide, SKYSTONE_FULL isFull) {
        double turnAngle;
        double speed;
        double straffLeft;
        double straffRight;
        if( competitionSide == COMPETITION_SIDE.RED)
        {
            turnAngle = 90;
            speed = 0.5;
            straffLeft = -4;
            straffRight = -8;
        }
        else
        {
            turnAngle = 90;
            speed = 0.5;
            straffLeft = -5;
            straffRight = 4;
        }

        this.SK_Grab_Left.goToClawOpenPosition();
        this.SK_Grab_Right.goToClawOpenPosition();
        SK_Grab_Left.goToApproachPosition();
        SK_Grab_Right.goToApproachPosition();
        waitStep(1);

        driveBackward(22, 1, 3);
       // waitStep(2);
        DriveByDistanceSensors( 0.25, 3.5, 5);
        waitStep(1);
        double totalDistanceMoved = get_SkyStone(20, telemetry);
        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left) {
            waitStep(1.4);
        }
        else {
            waitStep(0.8);
        }

        driveForward(5, 1, 2);
        waitStep(0.2);

        turn_HighPowerAtEnd(turnAngle, speed,  5.0);
        waitStep(0.2);

        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Right) {
            straff(straffLeft, 0.65, 2);
        }
        else
        {
            straff(straffRight, 0.65, 2);
        }

        if( competitionSide == COMPETITION_SIDE.BLUE)
            totalDistanceMoved *= -1;
                
        if( isFull == SKYSTONE_FULL.YES) {
            driveBackward(70 + totalDistanceMoved, 1, 5.5);
        }
        else {
            driveBackward(70 + totalDistanceMoved, 1, 5.5);
        }
        this.SK_Grab_Right.goToClawOpenPosition();
        this.SK_Grab_Left.goToClawOpenPosition();
        SK_Grab_Left.goToHomePosition();
        SK_Grab_Right.goToHomePosition();
    }

    protected void runFoundationRoutine(COMPETITION_SIDE competitionSide, PARK_POSITION parkPosition)
    {
        /*====================================== Settings section start */
        double initialStraffDistance = 16;
        double initialStraffDirection = 1;
        double foundationPullEndAngle = 90;
        double foundationPullSPeed = 0.5;
        double beforeParkingStraff = 0;

        if(competitionSide == COMPETITION_SIDE.RED) {
            initialStraffDirection = 1;
            foundationPullEndAngle = -90;
            foundationPullSPeed = 0.5;
        }
        else {
            initialStraffDirection = -1;
            foundationPullEndAngle = 90;
            foundationPullSPeed = -0.5;
            beforeParkingStraff = 20;
        }

        if(competitionSide == COMPETITION_SIDE.RED) {
        if(parkPosition == PARK_POSITION.NEXT_TO_CENTER_BRIDGE){
                beforeParkingStraff = -12;
        } else {
            beforeParkingStraff = 10;
            }
        }
        else
        {
            if(parkPosition == PARK_POSITION.NEXT_TO_CENTER_BRIDGE){
                beforeParkingStraff = 18;
            } else {
                beforeParkingStraff = -10;
            }
        }
        /*====================================== Settings section end */

        double initialStraff = initialStraffDistance * initialStraffDirection;

        driveBackward  (1,1,0.5);
        waitStep(0.2);

        straff(initialStraff, 0.75, 2);
        waitStep(0.2);

       // turn_HighPowerAtEnd(0,0.25,0.5);
       // waitStep(0.2);

        driveBackward  (24,1,2.5);
        DriveByBumperSwitches(0.25, 1);

        foundationDN();
        driveBackward  (4,1,0.5);
        //waitStep(0.8);
        turn_HighPowerAtEnd(foundationPullEndAngle, foundationPullSPeed, 0.25, 3);
        driveBackward  (18,1,2);

        foundationUP();
        waitStep(0.8);

        straff(beforeParkingStraff,0.75,2);

        waitStep(0.8);
        turn_HighPowerAtEnd(foundationPullEndAngle, 0.5, 0, 3);
        waitStep(0.8);

        driveForward(45,0.8,4);
    }

}
