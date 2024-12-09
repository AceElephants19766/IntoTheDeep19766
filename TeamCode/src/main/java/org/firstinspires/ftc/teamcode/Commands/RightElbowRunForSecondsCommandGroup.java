package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

public class RightElbowRunForSecondsCommandGroup extends SequentialCommandGroup {

    public RightElbowRunForSecondsCommandGroup (RightElbow rightElbow,double power,long millis){
        addCommands(
                new RightElbowSetPower(rightElbow, power),
                new WaitCommand(millis),
                new RightElbowSetPower(rightElbow,0)
        );
        addRequirements(rightElbow);
    }
}
