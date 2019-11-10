package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//The Arm is for grabbing the moving the skystone to different postions/angles
public class TapeMeasure11691{
        HardwareMap11691        theHMap;
        ElapsedTime             runtime;
        
        public TapeMeasure11691(HardwareMap11691 HMap){
                theHMap= HMap;
                runtime = new ElapsedTime();
        }
        
        
        public void TapeM(double PowerTM){
                theHMap.TapeM.setPower(PowerTM);
        }

        public void AutonTape (int target, double timeout, Telemetry tele) {
                theHMap.TapeM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); 
                theHMap.TapeM.setTargetPosition(target);
                      runtime.reset();
                while ((runtime.seconds() < timeout) && (theHMap.TapeM.isBusy())){
                        tele.addData("is_moving drive", theHMap.TapeM.isBusy());
                        tele.addData("Tape Measure encoder","position= %d", theHMap.TapeM.getCurrentPosition());
                        tele.addData("Runtime",runtime.time()); 
                        tele.addData("Timeout", timeout);
                        tele.update();   
            
                }
        
                theHMap.TapeM.setPower(0);
                        
        }
}

        
