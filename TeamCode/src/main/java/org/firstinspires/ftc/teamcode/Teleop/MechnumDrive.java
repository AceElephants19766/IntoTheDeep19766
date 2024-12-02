package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;

@TeleOp
public class MechnumDrive extends CommandOpMode
{
    public DriveTrainMecanum mecanumDrive;
    public Arm arm;

    DcMotor left_up;
    DcMotor left_elevator;

    @Override
    public void initialize() {
        mecanumDrive = new DriveTrainMecanum(hardwareMap);
        arm = new Arm(hardwareMap);

        left_up = hardwareMap.get(DcMotor.class, "left_up");
        left_elevator = hardwareMap.get(DcMotor.class, "left_elevator");
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

        double[] powers1  = {
                -gamepad1.right_stick_y,
                gamepad1.right_stick_x,
                0.0,
                0.0
        };
        arm.setPower(powers1);


        //elevators up
       // right_up.setPower(-gamepad2.right_stick_y);
        left_up.setPower(gamepad2.left_stick_y);
        //claw.setPosition(gamepad2.right_stick_x);

//        if (gamepad2.dpad_up)
//            right_elevator.setPower(0.9);
//        else if (gamepad2.dpad_down)
//            right_elevator.setPower(-0.9);
//        else
//            right_elevator.setPower(0.0);

        if (gamepad2.dpad_right)
            left_elevator.setPower(0.9);
        else if (gamepad2.dpad_left)
            left_elevator.setPower(-0.9);
        else
            left_elevator.setPower(0.0);

    }
}
