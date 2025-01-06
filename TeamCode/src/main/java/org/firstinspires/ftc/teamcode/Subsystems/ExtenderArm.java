package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class ExtenderArm extends SubsystemBase {
    private DcMotor extenderArm;

    private PIDController pidController;
    public static double kP = 0.2;
    public static double kI = 0;
    public static double kD = 0;

    private final double RPM = 537.7;

    public ExtenderArm(HardwareMap hardwareMap) {
        register();

        extenderArm = hardwareMap.get(DcMotor.class, "extenderArm");
        extenderArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extenderArm.setDirection(DcMotorSimple.Direction.REVERSE);

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(0.2);
    }

    public void setPower(double power) {
        extenderArm.setPower(power);
    }

    public double getLength() {
        return (extenderArm.getCurrentPosition() / RPM) * 12;
    }

    public PIDController getPidController() {
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("target", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("currentPos", getLength());
        FtcDashboard.getInstance().getTelemetry().addData("motor power", extenderArm.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}