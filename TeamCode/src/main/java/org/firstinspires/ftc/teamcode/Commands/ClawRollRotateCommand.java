package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;

public class ClawRollRotateCommand extends CommandBase {
    private ClawRollRotate clawRollRotat;
    private double pos;

    public ClawRollRotateCommand(ClawRollRotate clawRotat, double pos){
        this.clawRollRotat = clawRotat;
        this.pos = pos;
        addRequirements(clawRotat);
    }

    @Override
    public void initialize() {
        clawRollRotat.SetPose(pos);
    }

    @Override
    public void end(boolean interrupted) {
        clawRollRotat.SetPose(0.71);
    }
}
