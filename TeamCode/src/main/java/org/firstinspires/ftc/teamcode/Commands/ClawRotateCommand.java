package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawRotat;

public class ClawRotateCommand extends CommandBase {
    public ClawRotat clawRotat;
    public ClawRotateCommand (ClawRotat clawRotat){
        this.clawRotat = clawRotat;
        addRequirements(clawRotat);
    }

    @Override
    public void initialize() {
        clawRotat.SetPose(0.28);
    }

    @Override
    public void end(boolean interrupted) {
        clawRotat.SetPose(0);
    }
}
