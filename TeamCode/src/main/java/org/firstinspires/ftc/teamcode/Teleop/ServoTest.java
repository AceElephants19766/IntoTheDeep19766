package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class ServoTest extends CommandOpMode
{
    CRServo first_servo;
    CRServo second_servo;

    DcMotor right_up;
    DcMotor right_elevator;
    DcMotor left_up;
    DcMotor left_elevator;

    public void initialize()
    {
        right_up = hardwareMap.get(DcMotor.class, "right_up");
        right_elevator = hardwareMap.get(DcMotor.class, "right_elevator");
        left_up = hardwareMap.get(DcMotor.class, "left_up");
        left_elevator = hardwareMap.get(DcMotor.class, "left_elevator");

        first_servo = hardwareMap.get(CRServo.class, "first_servo");
        second_servo = hardwareMap.get(CRServo.class, "second_servo");

    }

    @Override
    public void run()
    {
        super.run();
        right_up.setPower(gamepad1.left_stick_y);

        if (gamepad1.dpad_right)
            right_elevator.setPower(0.9);
        else if (gamepad1.dpad_left)
            right_elevator.setPower(-0.9);
        else
            right_elevator.setPower(0.0);

        left_up.setPower(gamepad2.right_stick_y*-1);


        if (gamepad2.dpad_right)
            left_elevator.setPower(0.9);
        else if (gamepad2.dpad_left)
            left_elevator.setPower(-0.9);
        else
            left_elevator.setPower(0.0);


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


