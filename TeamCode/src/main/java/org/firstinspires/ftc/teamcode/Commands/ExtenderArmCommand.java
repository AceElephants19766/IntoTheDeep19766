package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderArmCommand extends CommandBase {
    private ExtenderArm extenderArm;
    private int targetInCm;


    public ExtenderArmCommand (ExtenderArm extenderArm, int targetInCm){
        this.extenderArm = extenderArm;
        this.targetInCm = targetInCm;
        addRequirements(extenderArm);
    }

    @Override
    public void initialize() {
        extenderArm.getPidController().setSetPoint(targetInCm);
    }

    @Override
    public void execute() {
        extenderArm.setPower(
                extenderArm.getPidController().calculate(
                        extenderArm.getLength()
                )
        );
    }

    @Override
    public boolean isFinished() {
        return extenderArm.getPidController().atSetPoint();
    }

    @Override
    public void end(boolean interrupted) {
        extenderArm.setPower(0);
    }

}
