package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class CollectingArmPID extends SubsystemBase {

    public DcMotor collectingArm;

    private final double RPM = 537.7;

    private PIDController pidController;
    public static double kP = 0.8;
    public static double kI = 0;
    public static double kD = 0;

    public CollectingArmPID(HardwareMap hardwareMap) {
        register();

        collectingArm = hardwareMap.get(DcMotor.class, "collectingArm");
        collectingArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(-0.2);
    }

    public void setPower(double power) {
        collectingArm.setPower(power);
    }

    public double getRevs() {
        return collectingArm.getCurrentPosition() / RPM;
    }

    public PIDController getPidController() {
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("Elevator target", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("Elevator currentPos", getRevs());
        FtcDashboard.getInstance().getTelemetry().addData("Elevator power", collectingArm.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}


