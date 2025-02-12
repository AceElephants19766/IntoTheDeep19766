package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

@Config
public class ElbowArm extends SubsystemBase {
    private DcMotor elbowArm;
    private PIDController pidController;

    public static double kP = 0.045;
    public static double kI = 0.2;
    public static double kD = 0.003;
    public static double TOL = 1;

    private final double TPR = 537.7;
    private double offset = 0;

    public static final int DEFAULT = 20;

    public static final int COLLECT_SAMPLE = 20;
    public static final int SCORING_SAMPLE = 130;

    public static final int SPECIMEN_COLLECT = 20;
    public static final int  SCORING_SPECIMEN = 110;

    public static final int AUTO_SCORING_SPECIMEN = 100;

    public static final int AFTER_COLLECT_SPECIMEN = 60;

    public ElbowArm(HardwareMap hardwareMap) {
        elbowArm = hardwareMap.get(DcMotor.class, "elbow");
        elbowArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoder();

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(TOL);

        register();
    }

    public void setPower(double power) {
        elbowArm.setPower(power);
    }

    public double getTicks () {
        return (elbowArm.getCurrentPosition() + offset);
    }

    public void resetEncoder(){
        offset = -elbowArm.getCurrentPosition();
    }

    public DoubleSupplier getAngle() {
        return ()->((getTicks() / TPR)/4) * 360;
    }
    public double getDeg(){
        return ((getTicks() / TPR)/4)*360;
    }


    public PIDController getPidController() {
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("elbowTarget", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("ElbowCurrentPos", getAngle().getAsDouble());
        FtcDashboard.getInstance().getTelemetry().addData("elbow power", elbowArm.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }

}