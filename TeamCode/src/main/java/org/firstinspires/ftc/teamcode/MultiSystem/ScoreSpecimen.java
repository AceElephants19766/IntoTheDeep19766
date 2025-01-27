package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ScoreSpecimen extends SequentialCommandGroup {
    public ScoreSpecimen(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate, Claw claw) {
        addCommands(
                new ElbowArmCommand(elbowArm, ElbowArm.AFTER_COLLECT_SPECIMEN),
                new WaitCommand(400),
                new ExtenderArmCommand(extenderArm,ExtenderArm.SCORINGSPACIMEN)
        );
        addRequirements(
                extenderArm,
                clawUpDown,
                clawRollRotate
        );
    }
}
