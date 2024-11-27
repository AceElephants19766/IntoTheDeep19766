package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Subsystems.Roller;
@TeleOp
public class MechnumDrive extends CommandOpMode
{
    public DriveTrainMecanum mecanumDrive;

    CRServo first_servo;
    CRServo second_servo;
    Servo third_servo;

    DcMotor right_up;
    DcMotor right_elevator;
    DcMotor left_up;
    DcMotor left_elevator;

    @Override
    public void initialize() {
        mecanumDrive = new DriveTrainMecanum(hardwareMap);

        right_up = hardwareMap.get(DcMotor.class, "right_up");
        right_elevator = hardwareMap.get(DcMotor.class, "right_elevator");
        left_up = hardwareMap.get(DcMotor.class, "left_up");
        left_elevator = hardwareMap.get(DcMotor.class, "left_elevator");

        first_servo = hardwareMap.get(CRServo.class, "first_servo");
        second_servo = hardwareMap.get(CRServo.class, "second_servo");
        third_servo= hardwareMap.get(Servo.class, "third_servo");
    }

    @Override
    public void run() {
        super.run();
        //mecanum

        double[] powers = {
                -gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x,
                -gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x,
                -gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x,
                -gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x
        };

        mecanumDrive.setPower(powers);

        //elevators up
        right_up.setPower(-gamepad2.right_stick_y);
        left_up.setPower(gamepad2.left_stick_y);
        third_servo.setPosition(gamepad2.right_stick_x);


        if (gamepad2.dpad_up)
            right_elevator.setPower(0.9);
        else if (gamepad2.dpad_down)
            right_elevator.setPower(-0.9);
        else
            right_elevator.setPower(0.0);

        if (gamepad2.dpad_right)
            left_elevator.setPower(0.9);
        else if (gamepad2.dpad_left)
            left_elevator.setPower(-0.9);
        else
            left_elevator.setPower(0.0);

        if (gamepad2.y)
            first_servo.setPower(0.5);
        else if (gamepad2.a)
            first_servo.setPower(-0.5);
        else
            first_servo.setPower(0.0);

        if (gamepad2.x)
            second_servo.setPower(0.5);
        else if (gamepad2.b)
            second_servo.setPower(-0.5);
        else
            second_servo.setPower(0.0);



    }
}
