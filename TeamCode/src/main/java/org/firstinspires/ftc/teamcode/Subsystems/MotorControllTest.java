package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class MotorControllTest extends SubsystemBase {

    private DcMotor motor;

    private PIDController pidController;
    public static double kP = 0.5;
    public static double kI = 0;
    public static double kD = 0;

    private final double RPM = 537.7;

    public MotorControllTest (HardwareMap hardwareMap) {
        register();

        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pidController = new PIDController(kP,kI,kD);
        pidController.setTolerance(0.2);
    }
    public void setPower (double power){
        motor.setPower(power);
    }
    public double getRevs (){
        return motor.getCurrentPosition()/RPM;
    }

    public PIDController getPidController(){
        return pidController;
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("target",pidController.getSetPoint());
        FtcDashboard.getInstance().getTelemetry().addData("currentPos",getRevs());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}
