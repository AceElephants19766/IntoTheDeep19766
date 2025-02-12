package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class Default extends SequentialCommandGroup {
    public Default(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw,ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new InstantCommand(() -> claw.SetPose(Claw.OPEN)),
                new InstantCommand(()->clawRollRotate.setPose(ClawRollRotate.DEFAULT)),
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.COLLECT)),
                new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT),
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT)
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}
