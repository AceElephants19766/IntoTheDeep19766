package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.DoubleSupplier;

@Config
public class ElbowArm extends SubsystemBase {

    private DcMotor elbowArmLeft;
    private DcMotor elbowArmRight;

    private PIDController pidController;

    public static double kP = 0.03;
    public static double kI = 0.0001; //0.1
    public static double kD = 0.002;
    public static double TOL = 1;

    public static double kGMin = 0.045;
    public static double kGMax = 0.16;
    public static double FUDGE_FACTOR = 1.5;

    private static final double ELBOW_STARTING_ANG = 37;
    private final double TPR = 537.7;
    private double offset = 0;

    public static final int DEFAULT = 20;

    public static final int COLLECT_SAMPLE = 20;
    public static final int SCORING_SAMPLE = 120;

    public static final int SPECIMEN_COLLECT = 20;
    public static final int  SCORING_SPECIMEN = 110;

    public static final int AUTO_SCORING_SPECIMEN = 100;

    public static final int AFTER_COLLECT_SPECIMEN = 60;

    public ElbowArm(HardwareMap hardwareMap) {
        elbowArmLeft = hardwareMap.get(DcMotor.class, "elbowLeft");
        elbowArmRight = hardwareMap.get(DcMotor.class, "elbowRight");

        elbowArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbowArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elbowArmRight.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoder();

        pidController = new PIDController(kP, kI, kD);
        pidController.setIntegrationBounds(0, 50);
        pidController.setTolerance(TOL);

        register();
    }

    public void setPower(double power) {
        elbowArmLeft.setPower(power);
        elbowArmRight.setPower(power);
    }

    public double getTicks () {
        return (elbowArmLeft.getCurrentPosition() + offset);
    }

    public void resetEncoder(){
        offset = -elbowArmLeft.getCurrentPosition();
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

    public static double getFeedForward(double extenderLength,double elbowAng) {
        return ((kGMax-kGMin) * (extenderLength/ExtenderArm.MAX_OPEN) + kGMin) * FUDGE_FACTOR
                * Math.cos(Math.toRadians(elbowAng-ELBOW_STARTING_ANG)
        );
    }


    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("elbowTarget", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("ElbowCurrentPos", getAngle().getAsDouble());
        FtcDashboard.getInstance().getTelemetry().addData("elbow power", elbowArmLeft.getPower());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}