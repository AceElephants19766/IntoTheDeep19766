package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.Commands.ClawSetPose;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class ScoringBasketAutonomuos extends SequentialCommandGroup {
    public ScoringBasketAutonomuos(ElbowArm elbowArm, ExtenderArm extenderArm,ClawUpDown clawUpDown,Claw claw){
        addCommands(
                        new PreaperForScoreSampleAuto(elbowArm, extenderArm),
                        new WaitCommand(500),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.SCORINGBACKWARD)),
                        new WaitCommand(500),
                        new ClawSetPose(claw, Claw.OPEN),
                        new WaitCommand(600),
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.COLLECT))

        );
        addRequirements(
                extenderArm,
                elbowArm,
                claw,
                clawUpDown
        );

    }
}
