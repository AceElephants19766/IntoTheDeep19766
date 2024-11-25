package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Servo;

@TeleOp
public class TestServoTeleop extends CommandOpMode {
    public Servo servo;

    @Override
    public void initialize() {
        servo = new Servo(hardwareMap);
        servo.setPower(gamepad1.right_stick_x);
    }


    @Override
    public void run() {
        super.run();

    }
}
