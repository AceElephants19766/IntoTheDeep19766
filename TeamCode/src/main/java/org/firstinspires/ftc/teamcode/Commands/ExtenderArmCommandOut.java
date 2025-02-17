package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderArmCommandOut extends CommandBase {
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;
    public double power;
    public ExtenderArmCommandOut(ExtenderArm extenderArm, ElbowArm elbowArm, double power){
        this.extenderArm = extenderArm;
        this.elbowArm = elbowArm;
        this.power = power;
        addRequirements(extenderArm);
    }
    @Override
    public void execute() {
        extenderArm.setPower(power);
    }

    @Override
    public boolean isFinished() {
        if (elbowArm.getAngle().getAsDouble() <90 && extenderArm.getLength() > 44.5){
            return true;
        } else {
            return  false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        extenderArm.setPower(0);
    }
}