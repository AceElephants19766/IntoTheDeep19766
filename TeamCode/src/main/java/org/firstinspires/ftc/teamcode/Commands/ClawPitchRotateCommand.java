package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ClawPitchRotate;

public class ClawPitchRotateCommand extends CommandBase {

    public ClawPitchRotate clawPitchRotate;
    public double power;

    public ClawPitchRotateCommand(ClawPitchRotate clawPitchRotate, double power){
        this.clawPitchRotate = clawPitchRotate;
        this.power = power;
        addRequirements(clawPitchRotate);
    }

    @Override
    public void initialize() {
        clawPitchRotate.setPos(power);
    }

    @Override
    public void end(boolean interrupted) {
        clawPitchRotate.setPos(0);
    }
}
