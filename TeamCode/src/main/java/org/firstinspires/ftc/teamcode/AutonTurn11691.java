package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class AutonTurn11691 extends BaseAutonIMU{

    static double   ANGLE_TOL           = 0.5;
    public boolean  is_moving           = false;
    private double  motorSetPoint       = 0;
    public double   actualangle;
    public double   error;
    ElapsedTime     runtime;
    public double initialangle;
    public double   targetAngle;
    public double   targetSpeed;
    public double   targetDeltaSpeed;
    public double   timeout;
    double  power = .30, correction;
    Telemetry telemetry;


    public AutonTurn11691(HardwareMap11691 HMap/*, LinearOpMode opMode*/){
        super(HMap);

        runtime         = new ElapsedTime();
        initialangle     = getAbsoluteHeading();
       // telemetry=opMode.telemetry;
        
        //set direction for each motor
        theHardwareMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        theHardwareMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        theHardwareMap11691.RF.setDirection(DcMotor.Direction.FORWARD);
     }
    
   public void AutonTurn (double Angle, double speed, double timeoute, Telemetry tele) {
        is_moving=true;
        targetAngle = Angle;
        targetSpeed = speed;
        timeout = timeoute;
        telemetry = tele;
        telemetry.addData("T_angle;",targetAngle);
        telemetry.update();
        gotoPosition(0.15);
   }
   public void AutonTurn_HighPowerAtEnd (double Angle, double speed, double timeoute, Telemetry tele) {
       AutonTurn_HighPowerAtEnd (Angle,  speed, 0,  timeoute,  tele);
   }

    public void AutonTurn_HighPowerAtEnd (double Angle, double speed, double deltaSpeed, double timeoute, Telemetry tele) {
        is_moving=true;
        targetAngle = Angle;
        targetSpeed = speed;
        targetDeltaSpeed = deltaSpeed;
        timeout = timeoute;
        telemetry = tele;
        telemetry.addData("T_angle;",targetAngle);
        telemetry.update();
        gotoPosition(0.25);
    }

    public void gotoPosition(double powerForFinalAdjust) {
        actualangle     = getAbsoluteHeading();

        error = targetAngle - actualangle;
        
        
        double time = runtime.time();
        while((Math.abs(error) > 3) && (runtime.time() - time < timeout)) 
        {
            actualangle = getAbsoluteHeading();
            error = targetAngle - actualangle;

            telemetry.addData("Target Angle","error= %.2f", error);
            telemetry.addData("Target Angle","value= %.2f", targetAngle);
            telemetry.addData("Actual Angle","value= %.2f", actualangle);
            telemetry.update();        

            if(error < 0) 
            {
                rotate( targetSpeed, targetDeltaSpeed ); //clockwise
            } else if(error > 0) 
            {
                rotate( -targetSpeed, targetDeltaSpeed); //counterclockwise
            }
             
        } 
        
        rotate(0,0);
        
        //waitStep(0.5);
        
        while((Math.abs(error) > ANGLE_TOL) && (runtime.time() - time < timeout)) 
        {
            actualangle = getAbsoluteHeading();
            error = targetAngle - actualangle;

            telemetry.addData("Target Angle","error= %.2f", error);
            telemetry.addData("Target Angle","value= %.2f", targetAngle);
            telemetry.addData("Actual Angle","value= %.2f", actualangle);
            telemetry.update();        

            if(error < 0) 
            {
                rotate(  powerForFinalAdjust, 0); //clockwise
            } else if(error > 0) 
            {
                rotate( -1 * powerForFinalAdjust, 0); //counterclockwise
            }
             
        } 

        rotate(0,0);

    }
    
        void waitStep(double wait_in_sec)
    {
        double timer = runtime.time();
        while (runtime.time() - timer < wait_in_sec)
        {
        }
    }
    
    public double getAbsoluteHeading() {

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    void rotate(double motorSetPoint, double deltaPower){
        if(deltaPower < 0.0001) {
            theHardwareMap11691.LF.setPower(motorSetPoint);
            theHardwareMap11691.LR.setPower(motorSetPoint);
            theHardwareMap11691.RF.setPower(-motorSetPoint);
            theHardwareMap11691.RR.setPower(-motorSetPoint);
        }
        else {
            theHardwareMap11691.LF.setPower(motorSetPoint + deltaPower);
            theHardwareMap11691.LR.setPower(motorSetPoint + deltaPower);
            theHardwareMap11691.RF.setPower(motorSetPoint - deltaPower);
            theHardwareMap11691.RR.setPower(motorSetPoint - deltaPower);
        }
    }
}
