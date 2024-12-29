package org.firstinspires.ftc.teamcode.Test.CommandTests;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests.RightElbow;

public class RightElbowRunForSecondsCommandGroup extends SequentialCommandGroup {

    public RightElbowRunForSecondsCommandGroup (RightElbow rightElbow,double power,long seconds){
        addCommands(
                new RightElbowSetPower(rightElbow, power),
                new WaitCommand(seconds*1000),
                new RightElbowSetPower(rightElbow,0)
        );
        addRequirements(rightElbow);
    }
}
