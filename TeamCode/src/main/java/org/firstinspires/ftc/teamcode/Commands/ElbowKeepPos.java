package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;

public class ElbowKeepPos extends CommandBase {
    public ElbowArm elbowArm;

    public ElbowKeepPos (ElbowArm elbowArm){
        this.elbowArm = elbowArm;
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
                ) + ElbowArm.getkG()/**Math.signum(elbowArm.getPidController().getPositionError())*/
        );
    }
}
