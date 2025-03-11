package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderArmCommand extends CommandBase {
    private ExtenderArm extenderArm;
    private ElbowArm elbowArm;
    private int targetInCm;


    public ExtenderArmCommand (ExtenderArm extenderArm,ElbowArm elbowArm, int targetInCm){
        this.extenderArm = extenderArm;
        this.elbowArm = elbowArm;
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
                ) +  ExtenderArm.getFeedForward(extenderArm.getLength(),elbowArm.getDeg())
        );
    }

    @Override
    public boolean isFinished() {
        return extenderArm.getPidController().atSetPoint();
    }

    @Override
    public void end(boolean interrupted) {
        extenderArm.setPower(ExtenderArm.getFeedForward(extenderArm.getLength(),elbowArm.getDeg()));
    }
}
