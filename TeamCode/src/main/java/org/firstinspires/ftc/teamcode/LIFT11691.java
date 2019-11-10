package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import java.util.Set;
import com.qualcomm.robotcore.hardware.DcMotor;


public class LIFT11691 {

    //The variables for the program
        static final double LIFT_MOTOR_SPEED         = 0.3;
        static final double LIFT_HOLD_VOLTS          = 0.1;
        static final double POS_TOLERANCE            = 0.05;
        static final double DOWN_POSITION_VOLTS      = 1.6;
        static final double FIRST_POSITION_VOLTS     = 1.75;
        static final double SECOND_POSITION_VOLTS    = 1.35;//2.5;
        static final double THIRD_POSITION_VOLTS     = 1.5;//3.0;
        static final double FOURTH_POSITION_VOLTS    = 1.6;
        static final double FIFTH_POSITION_VOLTS     = 4;
        static final double SIXTH_POSITION_VOLTS     = 4.5;
        static final double SEVENTH_POSITION_VOLTS   = 5;
        static final double EIGTH_POSITION_VOLTS     = 6;
        static final double ROTATE_POSITION_VOLTS    = 2.0;
    
        HardwareMap11691 theHardwareMap11691;
        public double   driveSpeedSetPoint = 0.0;
        public boolean  is_moving = false;
        public double   targetPosition;
        public double   lift_Move = 0;
        public int liftLevel = 0;//starting level of DR4B
    
    //Constructor
    public LIFT11691(HardwareMap11691 HMap) {
        theHardwareMap11691 = HMap;
        }
        
    // Loop that controls the lift to go to a specific position in volts
    
    public void LiftLevel(int lvl){
        liftLevel += lvl;
        if (liftLevel == 1){first();   }
        if (liftLevel == 2){second();  }
        if (liftLevel == 3){third();   }
        if (liftLevel == 4){fourth();  }
        if (liftLevel == 5){fifth();   }
        if (liftLevel == 6){sixth();   }
        if (liftLevel == 7){seventh(); }
        if (liftLevel == 8){eigth();   }
    }
    void gotoPosition() {
        double error = targetPosition - theHardwareMap11691.pot.getVoltage();
        while(Math.abs(error) > POS_TOLERANCE) {
            error = targetPosition - theHardwareMap11691.pot.getVoltage();
            if(error < 0) {
                driveSpeedSetPoint = -LIFT_MOTOR_SPEED; //up
              //  driveLIFT();
                
            } else if(error > 0) {
                driveSpeedSetPoint = LIFT_MOTOR_SPEED; //down
            //    driveLIFT();
            }
            theHardwareMap11691.LIFT.setPower(driveSpeedSetPoint);
        } 
        
        is_moving = false;
        stop();
    
    }
    
    void moveOrHoldPosition() {
        if(is_moving)
            gotoPosition();
        else
            stop();
    }

    //move DR4B to the down position to get ready to pick up a block.
    
    void down() {
        if (!theHardwareMap11691.Lift_TS.isPressed()){
            theHardwareMap11691.LIFT.setPower(-0.4);
            }
        else {
           theHardwareMap11691.LIFT.setPower(LIFT_HOLD_VOLTS); 
        }
    }

    //positions of the Double Reverse Four Bar
    
    void first() {
        targetPosition = FIRST_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void second() {
        targetPosition = SECOND_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void third() {
        targetPosition = THIRD_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }
    
    void fourth() {
        targetPosition = FOURTH_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void fifth() {
        targetPosition = FIFTH_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void sixth() {
        targetPosition = SIXTH_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void seventh() {
        targetPosition = SEVENTH_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

    void eigth() {
        targetPosition = EIGTH_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }


    void rotate() {
        targetPosition = ROTATE_POSITION_VOLTS;
        is_moving = true;
        gotoPosition();
    }

  
    void driveLIFT() {
        theHardwareMap11691.LIFT.setPower(driveSpeedSetPoint);
        moveOrHoldPosition();
        is_moving = false;
    }
    
   void move(double speed){
        lift_Move = Math.pow(speed,3);  //Joystick Control of DR4B
        theHardwareMap11691.LIFT.setPower(lift_Move);
        is_moving = false;
    }
  
  void stop() {
        is_moving = false;
        theHardwareMap11691.LIFT.setPower(LIFT_HOLD_VOLTS);
        
    }
    
    
  
}
