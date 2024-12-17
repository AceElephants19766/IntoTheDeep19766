package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrainMecanum extends SubsystemBase {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    public DriveTrainMecanum(HardwareMap hardwareMap) {
        register();

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public double[] joystickToPower(double x, double y, double rx) {

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double[] powers = {
                (y + x + rx)/denominator,
                (y - x + rx)/denominator,
                (y - x - rx)/denominator,
                (y + x - rx)/denominator
        };
        return powers;
    }

    public void setPower(double[] powers) {
        leftFront.setPower(powers[0]);
        leftBack.setPower(powers[1]);
        rightFront.setPower(powers[2]);
        rightBack.setPower(powers[3]);
    }
    public void arcadeDrive (double x, double y,double rx){
        setPower(joystickToPower(x,y,rx));
    }
}
