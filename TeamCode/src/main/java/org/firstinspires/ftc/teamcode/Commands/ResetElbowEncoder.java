package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;

public class ResetElbowEncoder extends InstantCommand {
    public ResetElbowEncoder (ElbowArm elbowArm){
        super(
                () -> elbowArm.resetEncoder(),
                elbowArm
        );
    }
}
