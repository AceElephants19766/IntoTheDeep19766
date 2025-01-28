package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class PreaperForScoreSampleAuto extends SequentialCommandGroup {
    public PreaperForScoreSampleAuto(ElbowArm elbowArm, ExtenderArm extenderArm) {
        addCommands(
                new ElbowArmCommand(elbowArm, ElbowArm.SCORING_SAMPLE),
                new WaitUntilCommand(() -> elbowArm.getPidController().getPositionError() < 20),
                new WaitCommand(1000),
                new ExtenderArmCommand(extenderArm, ExtenderArm.SCORE).withTimeout(2000)
        );
        addRequirements(
                extenderArm
        );
    }
}
