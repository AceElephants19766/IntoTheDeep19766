package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ServoTest extends CommandOpMode {

    DcMotor right_forward;
    DcMotor left_forward;
    DcMotor right_backward;
    DcMotor left_backward;

    CRServo first_servo;
    CRServo second_servo;

    DcMotor right_up;
    DcMotor right_elevator;
    DcMotor left_up;
    DcMotor left_elevator;

    public void initialize() {
        right_forward = hardwareMap.get(DcMotor.class, "right_forward");
        left_forward = hardwareMap.get(DcMotor.class, "left_forward");
        right_backward = hardwareMap.get(DcMotor.class, "right_backward");
        left_backward = hardwareMap.get(DcMotor.class, "left_backward");

        right_up = hardwareMap.get(DcMotor.class, "right_up");
        right_elevator = hardwareMap.get(DcMotor.class, "right_elevator");
        left_up = hardwareMap.get(DcMotor.class, "left_up");
        left_elevator = hardwareMap.get(DcMotor.class, "left_elevator");

        first_servo = hardwareMap.get(CRServo.class, "first_servo");
        second_servo = hardwareMap.get(CRServo.class, "second_servo");

    }

    @Override
    public void run() {
        super.run();

        if (gamepad1.right_stick_y > 0){
            right_forward.setPower(-gamepad1.right_stick_y);
            right_backward.setPower(-gamepad1.right_stick_y);
            left_forward.setPower(gamepad1.right_stick_y);
            left_backward.setPower(gamepad1.right_stick_y);
        }
        else if (gamepad1.right_stick_y < 0) {
            right_forward.setPower(-gamepad1.right_stick_y);
            right_backward.setPower(-gamepad1.right_stick_y);
            left_forward.setPower(gamepad1.right_stick_y);
            left_backward.setPower(gamepad1.right_stick_y);
        }
        else {
            right_forward.setPower(0);
            left_forward.setPower(0);
            right_backward.setPower(0);
            left_backward.setPower(0);
        }

        if (gamepad1.right_stick_x > 0){
            right_forward.setPower(gamepad1.right_stick_x);
            right_backward.setPower(gamepad1.right_stick_x);
            left_forward.setPower(gamepad1.right_stick_x);
            left_backward.setPower(gamepad1.right_stick_x);
        }
        else if (gamepad1.right_stick_x < 0) {
            right_forward.setPower(gamepad1.right_stick_x);
            right_backward.setPower(gamepad1.right_stick_x);
            left_forward.setPower(gamepad1.right_stick_x);
            left_backward.setPower(gamepad1.right_stick_x);
        }
        else {
            right_forward.setPower(0);
            left_forward.setPower(0);
            right_backward.setPower(0);
            left_backward.setPower(0);
        }


        right_up.setPower(gamepad1.left_stick_y);
        right_elevator.setPower(gamepad1.left_stick_x);
        left_up.setPower(gamepad1.left_trigger);
        left_elevator.setPower(gamepad1.right_trigger);

        if (gamepad1.y)
            first_servo.setPower(0.5);
        else if (gamepad1.a)
            first_servo.setPower(-0.5);
        else
            first_servo.setPower(0.0);

        if (gamepad1.x)
            second_servo.setPower(0.5);
        else if (gamepad1.b)
            second_servo.setPower(-0.5);
        else
            second_servo.setPower(0.0);




    }

    @Override
    public void reset() {
        super.reset();
    }
}


