package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

import java.util.Calendar;

public class RightElbowSetPower extends CommandBase {

    public RightElbow rightElbow;
    long startTime;

    public RightElbowSetPower (RightElbow rightElbow){
        this.rightElbow = rightElbow;
    }

    @Override
    public void initialize() {
        rightElbow.setPower(0.5);
        startTime = Calendar.getInstance().getTimeInMillis();
    }


    @Override
    public boolean isFinished() {
        double time = (Calendar.getInstance().getTimeInMillis()-startTime);
        return time>=1000;
    }

    @Override
    public void end(boolean interrupted) {
        rightElbow.stop();
    }
}
