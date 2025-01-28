package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ExtenderArmJoystickCommandIn extends CommandBase {
    public ExtenderArm extenderArm;
    public ElbowArm elbowArm;
    public double power;
    public ExtenderArmJoystickCommandIn(ExtenderArm extenderArm, ElbowArm elbowArm, double power){
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
    public void end(boolean interrupted) {
        extenderArm.setPower(0);
    }
}