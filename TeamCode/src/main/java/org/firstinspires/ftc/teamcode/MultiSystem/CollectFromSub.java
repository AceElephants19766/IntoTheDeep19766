package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawUpDown;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class CollectFromSub extends ParallelCommandGroup {
    public  CollectFromSub(ElbowArm elbowArm, ExtenderArm extenderArm, ClawUpDown clawUpDown, Claw claw, DoubleSupplier rightTriggerSupplier){

        addCommands(
                new InstantCommand(()->claw.SetPose(Claw.OPEN)),
                new InstantCommand(()-> clawUpDown.setPos(ClawUpDown.COLLECT)),
                new InstantCommand(()->extenderArm.getPidController().setSetPoint((int)(rightTriggerSupplier.getAsDouble()*26))),
                new InstantCommand(()-> {
                    int ang = (int)((Math.toDegrees(Math.acos((20.0/(38+(rightTriggerSupplier.getAsDouble()*26)))))) -37);
                        elbowArm.getPidController().setSetPoint(ang);
                })
        );

        addRequirements(
                claw,
                clawUpDown
        );
    }
}
