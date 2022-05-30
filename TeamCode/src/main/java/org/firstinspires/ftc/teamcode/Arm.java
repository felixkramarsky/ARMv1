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

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode scans a single servo back and forwards until Stop is pressed.
 * The code is structured as a LinearOpMode
 * INCREMENT sets how much to increase/decrease the servo position each cycle
 * CYCLE_MS sets the update period.
 *
 * This code assumes a Servo configured with the name "left_hand" as is found on a pushbot.
 *
 * NOTE: When any servo position is set, ALL attached servos are activated, so ensure that any other
 * connected servos are able to move freely before running this test.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@TeleOp(name = "Initial Test")
public class Arm extends LinearOpMode {

    // Define class members
    Servo base;
    Servo first;
    Servo middle;
    Servo last;
    Servo claw;


    @Override
    public void runOpMode() {

        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.

        //IDK
        base = hardwareMap.get(Servo.class, "base");
        //Forward is positive
        first = hardwareMap.get(Servo.class, "first");
        //Forward is negative
        middle = hardwareMap.get(Servo.class, "middle");
        //Forward is positive
        last = hardwareMap.get(Servo.class, "last");
        claw = hardwareMap.get(Servo.class, "claw");

        //initialize servos
        base.setPosition(0.5);
        first.setPosition(0.75);
        middle.setPosition(0.5);
        last.setPosition(0.8);
        // Wait for the start button
        //telemetry.addData(">", "Press Start to scan Servo." );
        telemetry.update();
        waitForStart();
        double rotation = 0.5;
        double lowServoPos = 0.75;
        double midServoPos = 0.5;
        double topServoPos = 0.8;
        // Scan servo till stop pressed.
        while(opModeIsActive()) {
            //base
            if(gamepad1.dpad_up){
                rotation-=0.075;
            }
            if(gamepad1.dpad_down){
                rotation+=0.075;
            }
            base.setPosition(rotation);
            //low servo
            if(gamepad1.left_stick_y < -0.3){
                lowServoPos += 0.075;
            }
            if(gamepad1.left_stick_y > 0.3){
                lowServoPos -= 0.075;
            }
            first.setPosition(lowServoPos);

            if(gamepad1.right_stick_y < -0.3){
                midServoPos += 0.075;
            }
            if(gamepad1.right_stick_y > 0.3){
                midServoPos -= 0.075;
            }
            middle.setPosition(midServoPos);

            if(gamepad1.left_bumper){
                topServoPos-=0.075;
            }
            if(gamepad1.right_bumper){
                topServoPos+=0.075;
            }
            last.setPosition(topServoPos);

            //claw
            if(gamepad1.a) {
                claw.setPosition(0.82);
            }
            if(gamepad1.b){
                claw.setPosition(0.43);
            }
            if(gamepad1.x) {
                claw.setPosition(0.645);
            }
            sleep(100);
            telemetry.addData(">Base: ", base.getPosition());
            telemetry.addData(">First: ", first.getPosition());
            telemetry.addData(">UHOH", lowServoPos);
            telemetry.addData(">Middle: ", middle.getPosition());
            telemetry.addData(">Last: ", last.getPosition());
            telemetry.addData(">Claw: ", claw.getPosition());
            telemetry.update();
            rotation = Range.clip(rotation,0,1);
            lowServoPos = Range.clip(lowServoPos,0,1);
            midServoPos = Range.clip(midServoPos,0,1);
            topServoPos = Range.clip(topServoPos,0,1);

        }
    }
}