

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;



public class HardwareMap11691 {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor RR;
    public DcMotor LR;
    public DcMotor RF;
    public DcMotor LF;
    public DcMotor IntakeL;
    public DcMotor IntakeR;
    public DcMotor LIFT;
    public DcMotor TapeM;

    public Servo MoveArm = null;
    public Servo Grabber = null;
    public Servo Foundation = null;
    public Servo SK_BlockLeft = null;
    public Servo SK_BlockRight = null;
    public Servo Claw_Right = null;
    public Servo Claw_Left = null;
    public AnalogInput pot;
    public Servo Pusher;
    public TouchSensor Lift_TS;
    public TouchSensor RHFoundBumper;
    public TouchSensor LHFoundBumper;

    public DistanceSensor Sensor2M = null;
    public DistanceSensor left2MSensor = null;
    public DistanceSensor right2MSensor = null;
    public ModernRoboticsI2cRangeSensor rangeSensor = null;
    public DistanceSensor autonSensorDLH = null;
    public DistanceSensor autonSensorDRH = null;
    public DistanceSensor clrSensorD = null;

    public ColorSensor leftColorSensor = null;
    public ColorSensor rightColorSensor = null;
    //public ColorSensor autonSensorCLH = null;
    //public ColorSensor autonSensorCRH = null;
    public ColorSensor clrSensorC = null;
    public BNO055IMU imu;          // The IMU sensor object



    public HardwareMap11691(HardwareMap hMap, LinearOpMode theOpMode){
        RR              = (DcMotor)hMap.get("RR");
        LR              = (DcMotor)hMap.get("LR");
        RF              = (DcMotor)hMap.get("RF");
        LF              = (DcMotor)hMap.get("LF");
        LIFT            = (DcMotor)hMap.get("LIFT");
        IntakeL         = (DcMotor)hMap.get("IntakeL");
        IntakeR         = (DcMotor)hMap.get("IntakeR");
        TapeM           = (DcMotor)hMap.get("tapeM");
        Foundation      = hMap.get(Servo.class,"Found");
        MoveArm         = hMap.get(Servo.class,"Arm");
        pot             = (AnalogInput) hMap.get ("Lpot");
        Pusher          = hMap.get(Servo.class,"Push");
        Grabber         = hMap.get(Servo.class,"Grab");
        autonSensorDLH    = (DistanceSensor)hMap.get("autonSensorLH");
        leftColorSensor    = (ColorSensor)hMap.get("autonSensorLH");
        autonSensorDRH    = (DistanceSensor)hMap.get("autonSensorRH");
        rightColorSensor    = (ColorSensor)hMap.get("autonSensorRH");
        clrSensorC      = (ColorSensor)hMap.get("ClrSensor");
        clrSensorD      = (DistanceSensor)hMap.get("ClrSensor");
        Lift_TS         = (TouchSensor)hMap.get("Lift_TS");
        RHFoundBumper   = (TouchSensor)hMap.get("LHFoundSW");
        LHFoundBumper   = (TouchSensor)hMap.get("RHFoundSW");
        Claw_Right = hMap.get(Servo.class,"RightSK");
        Claw_Left = hMap.get(Servo.class,"LeftSK");
        SK_BlockLeft = hMap.get(Servo.class,"sk_servoL");
        SK_BlockRight    = hMap.get(Servo.class,"sk_servoR");
        imu             = hMap.get(BNO055IMU.class, "imu");

        final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu.initialize(parameters);

        //todo check that we should not exit
        // make sure the imu gyro is calibrated before continuing.
        // while (!isStopRequested() && !imu.isGyroCalibrated())
        while (!imu.isGyroCalibrated()
                && theOpMode.opModeIsActive()
                && !theOpMode.isStopRequested())
        {
            //sleep(50);
            //idle();
        }

        Grabber.setPosition(0);
        Pusher.setPosition(GlobalSettings11691.pushBlock);
        Foundation.setPosition(0.70);
        MoveArm.setPosition(0.05);

        SK_BlockLeft.setPosition(GlobalSettings11691.LeftSKGrabber_InitPosition);
        SK_BlockRight.setPosition(GlobalSettings11691.RightSKGrabber_InitPosition);

        Claw_Left.setPosition(GlobalSettings11691.LeftSKGrabberClaw_InitPosition);
        Claw_Right.setPosition(GlobalSettings11691.RightSKGrabberClaw_InitPosition);
    }

}
