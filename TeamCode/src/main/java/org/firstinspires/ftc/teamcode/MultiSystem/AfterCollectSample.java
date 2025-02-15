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

public class AfterCollectSample extends SequentialCommandGroup {
    public AfterCollectSample(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate){
        addCommands(
                new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT),
                new InstantCommand(()->clawRollRotate.setPose(ClawRollRotate.DEFAULT)),
                new InstantCommand(()->clawUpDown.setPos(ClawUpDown.PREAPER_SCORING_BACKWARD_SPECIMEN )),
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
