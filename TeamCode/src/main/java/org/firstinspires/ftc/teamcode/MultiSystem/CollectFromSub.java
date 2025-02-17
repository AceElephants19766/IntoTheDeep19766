package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class CollectFromSub extends ParallelCommandGroup {
    public  CollectFromSub(ElbowArm elbowArm, ExtenderArm extenderArm, DoubleSupplier rightTriggerSupplier){
        addCommands(
                new InstantCommand(()->extenderArm.getPidController().setSetPoint((int)(rightTriggerSupplier.getAsDouble()*30))),
                new InstantCommand(()-> {
                    int ang = (int)(Math.toDegrees(Math.acos((15.0/(38+(rightTriggerSupplier.getAsDouble()*30)))))) -53;
                    if (ang>5){
                        elbowArm.getPidController().setSetPoint(ang);
                    }
                })
        );
    }
}