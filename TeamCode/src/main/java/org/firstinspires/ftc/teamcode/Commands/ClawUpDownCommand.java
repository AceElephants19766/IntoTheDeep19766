package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;

public class ClawUpDownCommand extends CommandBase {

    public ClawUpDown clawUpDown;
    public double pos;

    public ClawUpDownCommand(ClawUpDown clawUpDown, double pos){
        this.clawUpDown = clawUpDown;
        this.pos = pos;
        addRequirements(clawUpDown);
    }

    @Override
    public void initialize() {
        clawUpDown.SetPos(pos);
    }

    @Override
    public void end(boolean interrupted) {
        clawUpDown.SetPos(ClawUpDown.SCORING);
    }
}
