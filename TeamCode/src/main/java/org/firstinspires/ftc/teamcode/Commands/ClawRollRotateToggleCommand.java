package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;

public class ClawRollRotateToggleCommand extends CommandBase {
    private ClawRollRotate clawRollRotat;
    private double pos;

    public ClawRollRotateToggleCommand(ClawRollRotate clawRotat, double pos){
        this.clawRollRotat = clawRotat;
        this.pos = pos;
        addRequirements(clawRotat);
    }

    @Override
    public void initialize() {
        clawRollRotat.setPose(pos);
    }

    @Override
    public void end(boolean interrupted) {
        clawRollRotat.setPose(ClawRollRotate.DEFAULT);
    }
}
