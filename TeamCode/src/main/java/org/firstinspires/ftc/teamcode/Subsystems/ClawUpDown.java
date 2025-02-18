package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ClawUpDown extends SubsystemBase {
    private Servo clawUpDown;

    public static final double COLLECT = 1;
    public static final double SCORING = 0.73;

    public static final double P_F_COLLECT_SPECIMEN = 0.67;
    public static final double PREAPER_SCORING_BACKWARD_SPECIMEN = 0.42;
    public static final double SCORE_SPECIMEN = 0;

    public ClawUpDown(HardwareMap hardwareMap){
        clawUpDown = hardwareMap.get(Servo.class , "clawUpDown");
        clawUpDown.setPosition(ClawUpDown.SCORE_SPECIMEN);
    }
    public void setPos(double pos){
        clawUpDown.setPosition(pos);
    }

    @Override
    public void periodic() {
        FtcDashboard.getInstance().getTelemetry().addData("claw Up Down pos", clawUpDown.getPosition());
        FtcDashboard.getInstance().getTelemetry().update();
    }
}