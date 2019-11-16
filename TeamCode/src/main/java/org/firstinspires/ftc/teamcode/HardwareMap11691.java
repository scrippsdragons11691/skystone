

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
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
    public Servo SK_Block = null;
    public AnalogInput pot;
    public Servo Pusher;
    public TouchSensor Lift_TS;

    
    public DistanceSensor Sensor2M = null;
    public DistanceSensor left2MSensor = null;
    public DistanceSensor right2MSensor = null;
    public ModernRoboticsI2cRangeSensor rangeSensor = null;
    public DistanceSensor autonSensorD = null;
    public DistanceSensor clrSensorD = null;
    
    public ColorSensor leftColorSensor = null;
    public ColorSensor rightColorSensor = null;
    public ColorSensor autonSensorC = null;
    public ColorSensor clrSensorC = null;
    static public BNO055IMU imu;          // The IMU sensor object
   


    public HardwareMap11691(HardwareMap hMap){
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
        autonSensorD    = (DistanceSensor)hMap.get("autonSensorLH");
        autonSensorC    = (ColorSensor)hMap.get("autonSensorLH");
        clrSensorC      = (ColorSensor)hMap.get("ClrSensor");
        clrSensorD      = (DistanceSensor)hMap.get("ClrSensor");
        Lift_TS         = (TouchSensor)hMap.get("Lift_TS");
        SK_Block        = hMap.get(Servo.class,"sk_servoL");
        imu             = hMap.get(BNO055IMU.class, "imu");


        // Initializing Servos
        Grabber.setPosition(0);
        Pusher.setPosition(.75);
        Foundation.setPosition(0.70);
        MoveArm.setPosition(0.05);
        SK_Block.setPosition(0);

        // IMU parameters Initialization
        final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        //parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        imu.initialize(parameters);




        //telemetry.addData("Mode", "calibrating...");
        //telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        //while (!isStopRequested() && !imu.isGyroCalibrated())
        while (!imu.isGyroCalibrated())
        {
            //sleep(50);
            //idle();
        }
        }
        
}
