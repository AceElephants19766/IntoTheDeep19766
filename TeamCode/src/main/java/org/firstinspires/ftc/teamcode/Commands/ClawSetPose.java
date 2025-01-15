package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class ClawSetPose extends InstantCommand {

    public double pos;
    public ClawSetPose (Claw claw, double pos){
        super(
                () -> claw.SetPose(pos),
                claw
        );
    }
}