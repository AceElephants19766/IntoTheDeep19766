package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderGetToZero;

@Config
public class ExtenderArm extends SubsystemBase {
    private DcMotor extenderArm;

    private TouchSensor touchSensor;

    
    private PIDController pidController;
    public static double kP = 0.3;
    public static double kI = 0;
    public static double kD = 0;
    public static double kG = 0.15;

    public static final double MAX_OPEN = 42;
    private static final double ELBOW_STARTING_ANG = 124;


    private final double TPR = 537.7;
    private double offset = 0;

    public static  final int COLLECT = 0;
    public static final int SCORE = 42;
    public static final int P_F_COLLECTSAMPLE = 16;

    private static final int HANG = 12;

    public static final int SCORINGSPACIMEN = 15;
    public ExtenderArm(HardwareMap hardwareMap) {
        register();

        extenderArm = hardwareMap.get(DcMotor.class, "extenderArm");
        extenderArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extenderArm.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoder();

        touchSensor = hardwareMap.get(TouchSensor.class,"extenderTouchSens");

        pidController = new PIDController(kP, kI, kD);
        pidController.setTolerance(2);
    }

    public void setPower(double power) {
        extenderArm.setPower(power);
    }

    public double getTicks () {
        return (extenderArm.getCurrentPosition() + offset);
    }

    public void resetEncoder(){
        offset = -extenderArm.getCurrentPosition();
    }

    public double getLength() {
        return (getTicks() / TPR) * 12;
    }

    public boolean isPressed(){
        return touchSensor.isPressed();
    }


    public PIDController getPidController() {
        return pidController;
    }

    public  static double getFeedForward(double extenderLength,double elbowAng){
        return (kG * (extenderLength/ExtenderArm.MAX_OPEN)
                * Math.cos(Math.toRadians(elbowAng-ELBOW_STARTING_ANG))
        );
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("extenderTarget", pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("extenderCurrentPos", getLength());
        FtcDashboard.getInstance().getTelemetry().addData("extender power", extenderArm.getPower());
        FtcDashboard.getInstance().getTelemetry().addData("extender is finished",getPidController().atSetPoint());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}