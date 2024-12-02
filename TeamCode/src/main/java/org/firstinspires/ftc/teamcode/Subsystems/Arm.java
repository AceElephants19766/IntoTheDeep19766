package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm extends SubsystemBase {
    private DcMotor right_up;
    private DcMotor right_elevator;

    private Servo claw_rotation;
    private Servo claw;


    public Arm (HardwareMap hardwareMap) {

        right_up = hardwareMap.get(DcMotor.class, "right_up");
        right_elevator = hardwareMap.get(DcMotor.class, "right_elevator");

        claw_rotation = hardwareMap.get(Servo.class, "claw_rotation");
        claw = hardwareMap.get(Servo.class, "claw");

    }

    public void setPower (double[] powers)
    {
        right_up.setPower(powers[0]);
        right_elevator.setPower(powers[1]);

        claw_rotation.setPosition(powers[2]);
        claw.setPosition(powers[3]);
    }
}
