package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class ServoTest extends CommandOpMode {

    Servo leftHand;
    DcMotor motor;
    TouchSensor digitalTouch;

    public void initialize() {
        leftHand = hardwareMap.get(Servo.class, "left_hand");
        motor = hardwareMap.get(DcMotor.class, "left_drive");
        digitalTouch = hardwareMap.get(TouchSensor.class, "touch");

        leftHand.setPosition(0.6);
    }

    @Override
    public void run() {
        super.run();
        leftHand.setPosition(gamepad1.right_trigger);
        motor.setPower(gamepad1.left_stick_y);
        telemetry.addData("touch:", digitalTouch.isPressed());
        telemetry.update();
    }

    @Override
    public void reset() {
        super.reset();
        // Signal done;
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}


