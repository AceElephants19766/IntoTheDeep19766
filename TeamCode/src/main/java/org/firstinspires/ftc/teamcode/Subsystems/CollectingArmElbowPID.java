package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class CollectingArmElbowPID extends SubsystemBase {

    public DcMotor collectingArmElbow;

    private final double RPM = 537.7;

    private PIDController pidController;
    public static double kP = 0.2;
    public static double kI = 0;
    public static double kD = 0;

    public CollectingArmElbowPID(HardwareMap hardwareMap) {
        register();

        collectingArmElbow = hardwareMap.get(DcMotor.class, "collectingArmElbow");
        collectingArmElbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(0.2);
    }

    public void setPower(double power) {
        collectingArmElbow.setPower(power);
    }

    public double getRevs() {
        return collectingArmElbow.getCurrentPosition() / RPM;
    }

    public PIDController getPidController() {
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("Elbow target", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("Elbow currentPos", getRevs());
        FtcDashboard.getInstance().getTelemetry().addData("Elbow power", collectingArmElbow.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}