package org.firstinspires.ftc.teamcode.AutoMultySystem;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRollRotate;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

public class AuotPreaperForCollect extends SequentialCommandGroup {
    public AuotPreaperForCollect(ElbowArm elbowArm, ExtenderArm extenderArm, Claw claw, ClawUpDown clawUpDown, ClawRollRotate clawRollRotate) {
        addCommands(
                new SequentialCommandGroup(
                        new InstantCommand(() -> clawUpDown.setPos(ClawUpDown.COLLECT)),
                        new ExtenderArmCommand(extenderArm, ExtenderArm.COLLECT),
                        new ElbowArmCommand(elbowArm, ElbowArm.DEFAULT),
                        new WaitCommand(500),
                        new ExtenderArmCommand(extenderArm, ExtenderArm.P_F_COLLECTSAMPLE)
                )
        );
        addRequirements(
                extenderArm,
                claw,
                clawUpDown,
                clawRollRotate
        );
    }
}
