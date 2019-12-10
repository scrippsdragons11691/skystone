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
           // dataTracing.stop();
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
           // dataTracing.Start();
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
        autonDrive.encoderDriveAutonNew (dist, power,timeouta, this, true, true, false, true);
    }
    protected void driveForward (double dist, double power, double timeouta, boolean rampDown, boolean brakeAtEnd, boolean incremental, boolean nearest90){
        autonDrive.encoderDriveAutonNew (dist, power,timeouta, this, rampDown, brakeAtEnd, incremental, nearest90);
    }

    protected void driveBackward (double dist, double power, double timeouta){
        autonDrive.encoderDriveAutonNew (dist * -1, power,timeouta, this, true,true, false, true);
    }

    protected void driveBackward (double dist, double power, double timeouta, boolean rampDown, boolean brakeAtEnd, boolean incremental, boolean nearest90){
        autonDrive.encoderDriveAutonNew (dist * -1, power,timeouta, this, rampDown, brakeAtEnd, incremental, nearest90);
    }

    protected void turn_HighPowerAtEnd (double angle, double powerturn, double timeoutc){
        autonTurn.AutonTurn_HighPowerAtEnd (angle, powerturn, timeoutc, this);
    }

    protected void turn_aroundRearRightWheel (double angle, double powerturn, double timeoutc){
        autonTurn.AutonTurn_aroundRearRightWheel (angle, powerturn, timeoutc, this);
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


    double get_SkyStone (double timeout, Telemetry tele, boolean forceSKarm, SK_Block11691.SKYSTONE_ARM_LOCATION whichArm){
        double distanceToNextStone = GlobalSettings11691.StoneLength_inch;
        double totalDistanceMoved = 0;

        usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Right;

        //todo how can we grab the 2 blocks with different arms so that we we deliver on to the foundation, the stones do not fall off or try to stack the blocks
        leftColorSensor.StoneCheck();
        if ((leftColorSensor.StoneCheck() && (forceSKarm == false)) || ((forceSKarm) && (whichArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left))){
            SK_Grab_Left.GrabSkystone();
            usedSkystoneArm = SK_Block11691.SKYSTONE_ARM_LOCATION.Left;
            waitStep(0.5);
            SK_Grab_Left.goToClawGrabPosition();
            waitStep(0.8);
            SK_Grab_Left.carryStone();
            SK_Grab_Right.goToHomePosition();
        }
        else {
            if((rightColorSensor.StoneCheck() && (forceSKarm == false)) || ((forceSKarm) && (whichArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left))){
            }
            else {
                straff(distanceToNextStone,0.5,1);
                waitStep(0.2);
                totalDistanceMoved += distanceToNextStone;
            }

            SK_Grab_Right.GrabSkystone();
            waitStep(.5);
            SK_Grab_Right.goToClawGrabPosition();
            waitStep(0.8);
            SK_Grab_Right.carryStone();
            SK_Grab_Left.goToHomePosition();
        }

        return totalDistanceMoved * -1;
    }

    protected double run2ndPartOfSkystone(COMPETITION_SIDE competitionSide, SKYSTONE_FULL isFull, boolean dropStoneAtEnd) {
        double turnAngle;
        double backupDistance;

        if( competitionSide == COMPETITION_SIDE.RED)
        {
            turnAngle = -85;
            backupDistance = 5;
        }
        else
        {
            turnAngle = 85;
            backupDistance = 8;
        }

        this.SK_Grab_Left.goToClawOpenPosition();
        this.SK_Grab_Right.goToClawOpenPosition();
        SK_Grab_Left.goToApproachPosition();
        SK_Grab_Right.goToApproachPosition();
        waitStep(0.4); //todo can this be reduced?

        DriveByDistanceSensors( 0.25, 3.5, 10);
        waitStep(0.2); //todo can this be reduced?
        double totalDistanceMoved = get_SkyStone(20, telemetry, false, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);
        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left) {
            waitStep(.3); //todo can this be reduced?
        }
        else {
            waitStep(0.3); //todo can this be reduced?
        }

        driveForward(backupDistance, 1, 2);
//        waitStep(0.2);

        turn_HighPowerAtEnd(turnAngle, 1,  5.0);
//        waitStep(0.2);

        if( competitionSide == COMPETITION_SIDE.BLUE)
            totalDistanceMoved *= -1;

        double returnDistance;
        if( isFull == SKYSTONE_FULL.YES) {
            returnDistance = totalDistanceMoved+ GlobalSettings11691.OneTileLength_inch *4.6;
            driveBackward(returnDistance, 1, 5.5);
        }
        else {
            returnDistance = totalDistanceMoved+ GlobalSettings11691.OneTileLength_inch *4.5;
            driveBackward(returnDistance, 1, 5.5);
        }
        if(dropStoneAtEnd) {
            SK_Grab_Right.GrabSkystone();
            SK_Grab_Left.GrabSkystone();
            waitStep(0.2);
            this.SK_Grab_Right.goToClawOpenPosition();
            this.SK_Grab_Left.goToClawOpenPosition();
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();
            waitStep(0.2);
        }

        return returnDistance;
    }

    protected double runFirstPartOfSkystone(COMPETITION_SIDE competitionSide, SKYSTONE_FULL isFull, boolean dropStoneAtEnd, boolean includeApproach, boolean forceSKarm, SK_Block11691.SKYSTONE_ARM_LOCATION useThisArm) {
        double turnAngle;
        double backupDistance;

        if( competitionSide == COMPETITION_SIDE.RED)
        {
            turnAngle = -85;
            backupDistance = 8;
        }
        else
        {
            turnAngle = 85;
            backupDistance = 8;
        }

        // ========== Drive towards stones and at the same time position the stone arms and claws
        this.SK_Grab_Left.goToClawOpenPosition();
        this.SK_Grab_Right.goToClawOpenPosition();
        SK_Grab_Left.goToApproachPosition();
        SK_Grab_Right.goToApproachPosition();

        // Deduct 6 inches just for safety. The last inch or two will be driven using the distance sensors
        if(includeApproach) {
            double approachDistance = (2 * GlobalSettings11691.OneTileLength_inch) - GlobalSettings11691.RobotLength_inch - 6;
            driveBackward(approachDistance, 1, 3, true, false, false, true);
        }
        DriveByDistanceSensors( 0.25, 3.5, 10);

        waitStep(0.0); //todo why is this so long?
        double totalDistanceMoved = get_SkyStone(20, telemetry, forceSKarm, useThisArm);

        // If the robot straffs, we have to compensate because it does not straff square :-(
        if(Math.abs(totalDistanceMoved) > 0.0001)
            backupDistance += 2;

        if(usedSkystoneArm == SK_Block11691.SKYSTONE_ARM_LOCATION.Left) {
            waitStep(.3); //todo check if this can be shortened
        }
        else {
            waitStep(0.3); //todo check if this can be shortened
        }

        driveForward(backupDistance, 1, 2); //todo Remove rampdown and perhaps decrease the backupdistance in order to compensate
        waitStep(0.0);

        turn_HighPowerAtEnd(turnAngle, 1,  5.0); //todo turn at power = 1. Compensate for overshoot by decreasing the turnAngle
        waitStep(0.0);

        if( competitionSide == COMPETITION_SIDE.BLUE)
            totalDistanceMoved *= -1;

        double returnDistance;
        if( isFull == SKYSTONE_FULL.YES) {
            returnDistance = 3.4 *GlobalSettings11691.OneTileLength_inch + totalDistanceMoved;
            driveBackward(returnDistance, 1, 5.5 );
        }
        else {
            returnDistance = 3.5 *GlobalSettings11691.OneTileLength_inch + totalDistanceMoved;
            driveBackward(returnDistance, 1, 5.5);
        }
        if(dropStoneAtEnd) {
            SK_Grab_Right.GrabSkystone();
            SK_Grab_Left.GrabSkystone();
            waitStep(0.2);
            SK_Grab_Right.goToClawOpenPosition();
            SK_Grab_Left.goToClawOpenPosition();
            SK_Grab_Left.goToHomePosition();
            SK_Grab_Right.goToHomePosition();
            waitStep(0.2);
        }

        return returnDistance;
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
            beforeParkingStraff = 5;
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

        straff(initialStraff, 0.5, 2);
        waitStep(0.2);

       // turn_HighPowerAtEnd(0,0.25,0.5);
       // waitStep(0.2);

        
        driveBackward  (22/*28.1*/,0.8,3.0);
        DriveByBumperSwitches(0.25, 3);

        foundationDN();
      //  driveBackward  (4,1,0.5);
        waitStep(0.8);
        turn_HighPowerAtEnd(foundationPullEndAngle, foundationPullSPeed, 0.25, 3);
        driveBackward  (18,1,2);

        foundationUP();
        waitStep(1.4);

        straff(beforeParkingStraff,0.75,2);

        waitStep(0.8);
        //turn_HighPowerAtEnd(foundationPullEndAngle, 0.5, 0, 3);
        //waitStep(0.8);

        driveForward(45,0.8,4);
    }

    protected void runTwoSkystonesFull(COMPETITION_SIDE competition_side)
    {
        runFirstPartOfSkystone(competition_side, SKYSTONE_FULL.YES,false, true, false, SK_Block11691.SKYSTONE_ARM_LOCATION.Left);

        waitStep(0.05);
        turn_HighPowerAtEnd(0,0.65,4);

        driveBackward(2, 1, 10, false, true, false,true);
        DriveByBumperSwitches(0.25, 2);

        foundationDN();

        SK_Grab_Right.GrabSkystone();
        SK_Grab_Left.GrabSkystone();
        waitStep(0.3);
        SK_Grab_Right.goToClawOpenPosition();
        SK_Grab_Left.goToClawOpenPosition();
        SK_Grab_Left.goToHomePosition();
        SK_Grab_Right.goToHomePosition();
        double headingSign = (competition_side == COMPETITION_SIDE.RED) ? -1 : 1;
        double backupDistance = (competition_side == COMPETITION_SIDE.RED) ? 5 : 15;

        waitStep(0.2);
        turn_aroundRearRightWheel(headingSign * 13, 1,  5.0);
        driveForward(backupDistance, 1, 10,false,false,false, false);



        turn_aroundRearRightWheel(headingSign * 90, 1,  5.0);


        driveForward(25, 1, 10,false,false,false, true);
        foundationUP();

        double driveMultiplier = (competition_side == COMPETITION_SIDE.RED) ? 3.2 : 3.4;
        driveForward(GlobalSettings11691.OneTileLength_inch * driveMultiplier, 1, 10,true,true,true,true);

        driveBackward  (2,1,1.0);
        turn_HighPowerAtEnd(0,0.75,3);

        foundationDN();
        run2ndPartOfSkystone(competition_side, SKYSTONE_FULL.YES,true);

        driveForward(GlobalSettings11691.OneTileLength_inch *1.8, 1, 10);
    }
}
