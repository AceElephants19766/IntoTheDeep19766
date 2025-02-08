package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class ClawToggleCommand extends CommandBase {
    private Claw claw;
    public ClawToggleCommand(Claw claw){
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.SetPose(Claw.OPEN);
    }

    @Override
    public void end(boolean interrupted) {
        claw.SetPose(Claw.CLOSE);
    }
}
