package org.firstinspires.ftc.teamcode.MultiSystem;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.ElbowArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ExtenderArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.ExtenderArm;

import java.util.function.DoubleSupplier;

public class CollectFromSub extends ParallelCommandGroup {
    public  CollectFromSub(ElbowArm elbowArm, ExtenderArm extenderArm, DoubleSupplier rightTriggerSupplier){
        addCommands(
                new ExtenderArmCommand(extenderArm,(int)(rightTriggerSupplier.getAsDouble()*30))
//                new ElbowArmCommand(elbowArm,Math.acos((int)(extenderArm.getLength())/35)
        );
    }
}
