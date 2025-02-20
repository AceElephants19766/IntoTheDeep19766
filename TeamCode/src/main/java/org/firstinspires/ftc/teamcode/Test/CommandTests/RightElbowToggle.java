package org.firstinspires.ftc.teamcode.Test.CommandTests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Test.SubsystemTests.RightElbow;

public class RightElbowToggle extends CommandBase {

    private RightElbow rightElbow;

    public RightElbowToggle (RightElbow rightElbow){
        this.rightElbow = rightElbow;
        addRequirements(rightElbow);
    }

    @Override
    public void initialize() {
        rightElbow.setPower(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        rightElbow.stop();
    }
}
