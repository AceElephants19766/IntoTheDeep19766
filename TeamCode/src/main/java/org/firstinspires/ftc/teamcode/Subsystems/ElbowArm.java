package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Config
public class ElbowArm extends SubsystemBase {
    private DcMotor elbowArm;
    private PIDController pidController;
    public static double kP = 0.04;
    public static double kI = 0;
    public static double kD = 0;
    public static double TOL = 2;

    private final double TPR = 537.7;

    public ElbowArm(HardwareMap hardwareMap) {
        elbowArm = hardwareMap.get(DcMotor.class, "elbow");
        elbowArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(TOL);

        register();
    }

    public void setPower(double power) {
        elbowArm.setPower(power);
    }

    public double getAngle() {
        return ((elbowArm.getCurrentPosition() / TPR)/4) * 360;
    }

    public PIDController getPidController() {
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("elbowTarget", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("ElbowCurrentPos", getAngle());
        FtcDashboard.getInstance().getTelemetry().addData("elbow power", elbowArm.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }

}