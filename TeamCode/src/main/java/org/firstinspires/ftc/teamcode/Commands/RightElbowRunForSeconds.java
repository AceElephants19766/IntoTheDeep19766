package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

import java.util.Calendar;

public class RightElbowRunForSeconds extends CommandBase {

    public RightElbow rightElbow;
    long startTime;
    double seconds;

    public RightElbowRunForSeconds(RightElbow rightElbow, double seconds){
        this.rightElbow = rightElbow;
        this.seconds = seconds;
        addRequirements(rightElbow);
    }

    @Override
    public void initialize() {
        rightElbow.setPower(0.5);
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    @Override
    public boolean isFinished() {
        double time = (Calendar.getInstance().getTimeInMillis()-startTime);
        return time>=seconds;
    }

    @Override
    public void end(boolean interrupted) {
        rightElbow.stop();
    }
}
