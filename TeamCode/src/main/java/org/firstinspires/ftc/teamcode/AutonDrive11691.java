package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutonDrive11691 extends MyIMU11691 {
    //Rev Hex HD Motor 2240 counts per rotation
    static final double COUNTS_PER_MOTOR_REV =      1120; //20 - 537.6, 40 - 1120, 60- 1680 (cpr) Gear Neverest Motor Encoder
    static final double DRIVE_GEAR_REDUTION =       2.2; // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES =     3.0; // For figuring circumference
    static final double COUNTS_PER_INCH =(COUNTS_PER_MOTOR_REV / DRIVE_GEAR_REDUTION)/(WHEEL_DIAMETER_INCHES*3.1415);
    boolean is_moving = false;

    HardwareMap11691 theHardwareMap11691;
    ElapsedTime runtime     = new ElapsedTime();
    
    // Constructor
    public AutonDrive11691(HardwareMap11691 HMap){

        // Initializing the base class
        super(HMap);

        theHardwareMap11691 = HMap;
        
      
      //set direction for each motor
        theHardwareMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        theHardwareMap11691.RF.setDirection(DcMotor.Direction.FORWARD);
        
        setPositionTolerance(20);
     }
    
    // Straffing Auton 
    public void Auton_Straff (double dist_Straff_In, double speed_Straff, double timeoutS, Telemetry tele)
    {

        int newStraffLeftFTarget;
        int newStraffRightFTarget;
        int newStraffLeftBTarget;
        int newStraffRightBTarget;
        
        // Reset the encoders
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        //Determine new target postition, and pass to motor controller
        newStraffLeftFTarget  = (int)(-1*dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffRightFTarget = (int)(dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffLeftBTarget  = (int)(dist_Straff_In * COUNTS_PER_INCH / 0.707);
        newStraffRightBTarget = (int)(-1*dist_Straff_In * COUNTS_PER_INCH / 0.707);
        
        //Send the target position to the REV module
        theHardwareMap11691.LF.setTargetPosition(newStraffLeftFTarget);
        theHardwareMap11691.RF.setTargetPosition(newStraffRightFTarget);
        theHardwareMap11691.LR.setTargetPosition(newStraffLeftBTarget);
        theHardwareMap11691.RR.setTargetPosition(newStraffRightBTarget);
        

        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //Set the motor speed
        runtime.reset();
        theHardwareMap11691.LF.setPower(speed_Straff);
        theHardwareMap11691.RF.setPower(speed_Straff);
        theHardwareMap11691.LR.setPower(speed_Straff);
        theHardwareMap11691.RR.setPower(speed_Straff); 
        
        runtime.reset();
        double DrivingAngle = globalAngle;

        while (runtime.seconds() < timeoutS && theHardwareMap11691.LF.isBusy() &&
                theHardwareMap11691.LR.isBusy() &&
                theHardwareMap11691.RF.isBusy() &&
                theHardwareMap11691.RR.isBusy())
        {

            // Use gyro to drive in a straight line.
            correction = checkDirectionStraff(DrivingAngle);

            //Set the motor speed
            runtime.reset();
            theHardwareMap11691.LF.setPower(speed_Straff + correction);
            theHardwareMap11691.LR.setPower(speed_Straff + correction);
            theHardwareMap11691.RF.setPower(speed_Straff - correction);
            theHardwareMap11691.RR.setPower(speed_Straff - correction);

            tele.addData("is_moving drive", is_moving);
            tele.addData("LF encoder","position= %d", theHardwareMap11691.LF.getCurrentPosition());
            tele.addData("RF encoder","position= %d", theHardwareMap11691.RF.getCurrentPosition());
            tele.addData("LR encoder","position= %d", theHardwareMap11691.LR.getCurrentPosition());
            tele.addData("RR encoder","position= %d", theHardwareMap11691.RR.getCurrentPosition());
            tele.addData("Runtime",runtime.time()); 
            tele.addData("Timeout", timeoutS);
            tele.update();   
            
         }
        
        theHardwareMap11691.LF.setPower(0);
        theHardwareMap11691.RF.setPower(0);
        theHardwareMap11691.LR.setPower(0);
        theHardwareMap11691.RR.setPower(0); 
        
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    // Drive Auton
    public void encoderDriveAuton(double distanceInches, double speed, double timeoutT, Telemetry tele){
        
        double leftInches   = distanceInches;
        double rightInches  = distanceInches;
        double motorspeed = speed;
        double timeoutS;
        
        int newLeftFTarget;
        int newRightFTarget;
        int newLeftBTarget;
        int newRightBTarget;
        
        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        // Reset the encoders
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        //Determine new target postition, and pass to motor controller
        newLeftFTarget  = (int)(leftInches * COUNTS_PER_INCH);
        newRightFTarget = (int)(rightInches * COUNTS_PER_INCH);
        newLeftBTarget  = (int)(leftInches * COUNTS_PER_INCH);
        newRightBTarget = (int)(rightInches * COUNTS_PER_INCH);

        //Set the motors to run to encoder mode
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //Send the target position to the REV module
        theHardwareMap11691.LF.setTargetPosition(newLeftFTarget);
        theHardwareMap11691.RF.setTargetPosition(newRightFTarget);
        theHardwareMap11691.LR.setTargetPosition(newLeftBTarget);
        theHardwareMap11691.RR.setTargetPosition(newRightBTarget);
        
        //Set the motor speed
        theHardwareMap11691.LF.setPower(motorspeed);
        theHardwareMap11691.RF.setPower(motorspeed);
        theHardwareMap11691.LR.setPower(motorspeed);
        theHardwareMap11691.RR.setPower(motorspeed); 
        
        runtime.reset();
        double DrivingAngle = globalAngle;


        while (runtime.seconds() < timeoutT &&
                theHardwareMap11691.LF.isBusy() &&
                theHardwareMap11691.LR.isBusy() &&
                theHardwareMap11691.RF.isBusy() &&
                theHardwareMap11691.RR.isBusy())
        {

            // Use gyro to drive in a straight line.
            correction = checkDirection(DrivingAngle);

            tele.addData("1 imu heading", lastAngles.firstAngle);
            tele.addData("2 global heading", globalAngle);
            tele.addData("3 correction", correction);
            tele.update();

            //Set the motor speed
            theHardwareMap11691.LF.setPower(motorspeed + correction);
            theHardwareMap11691.LR.setPower(motorspeed + correction);
            theHardwareMap11691.RF.setPower(motorspeed - correction);
            theHardwareMap11691.RR.setPower(motorspeed - correction);


            tele.addData("1 imu heading", lastAngles.firstAngle);
            tele.addData("2 global heading", globalAngle);
            tele.addData("3 correction", correction);


            tele.addData("is_moving drive", is_moving);
            tele.addData("LF encoder","position= %d", theHardwareMap11691.LF.getCurrentPosition());
            tele.addData("RF encoder","position= %d", theHardwareMap11691.RF.getCurrentPosition());
            tele.addData("LR encoder","position= %d", theHardwareMap11691.LR.getCurrentPosition());
            tele.addData("RR encoder","position= %d", theHardwareMap11691.RR.getCurrentPosition());
            tele.addData("Runtime",runtime.time()); 
            tele.addData("Timeout", timeoutT);
            tele.update();   
            
             }
        theHardwareMap11691.LF.setPower(0);
        theHardwareMap11691.RF.setPower(0);
        theHardwareMap11691.LR.setPower(0);
        theHardwareMap11691.RR.setPower(0);
        
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);

    }
    
    // Basic Drive - turns motors to a set speed
    public void basicDrive (double speeda)    {
        
        theHardwareMap11691.LF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.LR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        theHardwareMap11691.RR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        
        theHardwareMap11691.LF.setPower(speeda);
        theHardwareMap11691.RF.setPower(speeda);
        theHardwareMap11691.LR.setPower(speeda);
        theHardwareMap11691.RR.setPower(speeda); 
        }
        

public void displayPositionTolerance(Telemetry tele)
    {
        int tol = ((DcMotorEx)(theHardwareMap11691.LF)).getTargetPositionTolerance();
        tele.addData("LF ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.RF)).getTargetPositionTolerance();
        tele.addData("RF ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.LR)).getTargetPositionTolerance();
        tele.addData("LR ","position tol= %d", tol);

        tol = ((DcMotorEx)(theHardwareMap11691.RR)).getTargetPositionTolerance();
        tele.addData("RR ","position tol= %d", tol);
         tele.update();
    }
    
    public void setPositionTolerance(int tolerance)
    {
        ((DcMotorEx)(theHardwareMap11691.LF)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.RF)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.LR)).setTargetPositionTolerance(tolerance);
        ((DcMotorEx)(theHardwareMap11691.RR)).setTargetPositionTolerance(tolerance);
    }

    private double checkDirection(double angle)
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, gain = .075;

        angle = angle - getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

    private double checkDirectionStraff(double angle)
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, gain = .075;

        angle = angle - getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }

}
