package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;

public class ClawUpDownToggleCommand extends CommandBase {

    private ClawUpDown clawUpDown;
    private double pos;

    public ClawUpDownToggleCommand(ClawUpDown clawUpDown, double pos){
        this.clawUpDown = clawUpDown;
        this.pos = pos;
        addRequirements(clawUpDown);
    }

    @Override
    public void initialize() {
        clawUpDown.setPos(pos);
    }

    @Override
    public void end(boolean interrupted) {
        clawUpDown.setPos(ClawUpDown.SCORING);
    }
}
