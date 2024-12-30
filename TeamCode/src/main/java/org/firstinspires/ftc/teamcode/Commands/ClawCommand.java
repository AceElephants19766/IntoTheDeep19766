package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;

public class ClawCommand  extends CommandBase {

    public Claw claw;
    public double pos;

    public ClawCommand (Claw claw , double pos){
        this.claw = claw;
        this.pos = pos;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        claw.SetPose(pos);
    }

    @Override
    public void end(boolean interrupted) {
        claw.SetPose(0);
    }
}
