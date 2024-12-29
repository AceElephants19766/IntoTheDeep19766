package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class ClawCommand  extends CommandBase {
    public Claw claw;

    public ClawCommand (Claw claw ){
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.SetPose(0.3);
    }

    @Override
    public void end(boolean interrupted) {
        claw.SetPose(0);
    }
}
