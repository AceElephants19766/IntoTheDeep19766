package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ElbowKeepPos extends CommandBase {
    public ElbowArm elbowArm;
    public ExtenderArm extenderArm;

    public ElbowKeepPos (ElbowArm elbowArm, ExtenderArm extenderArm){
        this.elbowArm = elbowArm;
        this.extenderArm = extenderArm;
        addRequirements(elbowArm);
    }

    @Override
    public void initialize() {
        elbowArm.getPidController().setSetPoint(ElbowArm.DEFAULT);
    }

    @Override
    public void execute() {
        elbowArm.setPower(
                elbowArm.getPidController().calculate(
                        elbowArm.getAngle().getAsDouble()
                ) + ElbowArm.getFeedForward(extenderArm.getLength(),elbowArm.getDeg())
        );
    }
}
