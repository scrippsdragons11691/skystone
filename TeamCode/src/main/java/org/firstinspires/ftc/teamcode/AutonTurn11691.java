package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class AutonTurn11691 {

    static double   ANGLE_TOL           = 0.5;
    public boolean  is_moving           = false;
    private double  motorSetPoint       = 0;
    public double   actualangle;
    public double   error;
    ElapsedTime     runtime;
    public double initialangle;
    public double   targetAngle;
    public double   targetSpeed;
    public double   timeout;
    double  globalAngle, power = .30, correction;
    Telemetry telemetry;
    Orientation             lastAngles = new Orientation();
    
    HardwareMap11691 HMap11691;
        
    public AutonTurn11691(HardwareMap11691 HMap/*, LinearOpMode opMode*/){
        HMap11691       = HMap;
        runtime         = new ElapsedTime();
        initialangle     = getAbsoluteHeading();
       // telemetry=opMode.telemetry;
        
        //set direction for each motor
        HMap11691.LR.setDirection(DcMotor.Direction.REVERSE);
        HMap11691.LF.setDirection(DcMotor.Direction.REVERSE);
        HMap11691.RR.setDirection(DcMotor.Direction.FORWARD);
        HMap11691.RF.setDirection(DcMotor.Direction.FORWARD);
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
        is_moving=true;
        targetAngle = Angle;
        targetSpeed = speed;
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
                rotate( targetSpeed ); //clockwise
            } else if(error > 0) 
            {
                rotate( -targetSpeed); //counterclockwise
            }
             
        } 
        
        rotate(0);
        
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
                rotate(  powerForFinalAdjust); //clockwise
            } else if(error > 0) 
            {
                rotate( -1 * powerForFinalAdjust); //counterclockwise
            }
             
        } 

        rotate(0);

    }
    
        void waitStep(double wait_in_sec)
    {
        double timer = runtime.time();
        while (runtime.time() - timer < wait_in_sec)
        {
        }
    }
    
    public double getAbsoluteHeading() {
       // return HMap11691.imu.getAngularOrientation(AxesReference.INTRINSIC , AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        
        Orientation angles = HMap11691.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    void rotate(double motorSetPoint){
            HMap11691.LF.setPower(motorSetPoint);
            HMap11691.RF.setPower(-motorSetPoint);
            HMap11691.LR.setPower(motorSetPoint);
            HMap11691.RR.setPower(-motorSetPoint);
    }
    
        private void resetAngle()
    {
        lastAngles = HMap11691.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }
}        
