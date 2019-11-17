/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */


public class ColorSensor11691  {

 public ColorSensor theSensor;

 public enum SKYSTONE_COLOR_SENSOR_LOCATION {Left,Right}

 public ColorSensor11691  (HardwareMap11691 HMap, SKYSTONE_COLOR_SENSOR_LOCATION sensor){

  if(sensor == SKYSTONE_COLOR_SENSOR_LOCATION.Right)
  {
   theSensor = HMap.rightColorSensor;
  }
  else
  {
   theSensor = HMap.leftColorSensor;
  }
 }

 public boolean StoneCheck()
 {
  //double AlphaValue;
  double RedValue;
  double GreenValue;
  double BlueValue;
  double HueValue;
  double SaturationValue;
  boolean stone_present;
  boolean Stone;

  float hsvValues[] = {0F, 0F, 0F};
  //AlphaValue = theSensor.alpha();
  RedValue = theSensor.red();
  GreenValue = theSensor.green();
  BlueValue = theSensor.blue();


  Color.RGBToHSV((int) (RedValue * 255),
          (int) (GreenValue * 255),
          (int) (BlueValue * 255),
          hsvValues);
  HueValue = hsvValues[0];
  SaturationValue = hsvValues[1];

  if(SaturationValue <= GlobalSettings11691.skystoneSaturationValue){
   return true;
  }else {
   return false;
  }

 }

}

