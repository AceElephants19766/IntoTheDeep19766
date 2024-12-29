package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmElbowPID;
import org.firstinspires.ftc.teamcode.Subsystems.CollectingArmPID;

import java.util.Calendar;

public class CollectingArmElbowCommnad extends CommandBase {

    public CollectingArmElbowPID collectingArmElbowPID;
    public double target;
    public long startTime;

    public CollectingArmElbowCommnad(CollectingArmElbowPID collectingArmElbowPID, double target) {
        this.collectingArmElbowPID = collectingArmElbowPID;
        this.target = target;
        addRequirements(collectingArmElbowPID);
    }

    @Override
    public void initialize() {
        collectingArmElbowPID.getPidController().setSetPoint(target);
    }

    @Override
    public void execute() {
        collectingArmElbowPID.setPower(
                collectingArmElbowPID.getPidController().calculate(
                        collectingArmElbowPID.getRevs()
                )
        );
    }

    @Override
    public boolean isFinished() {
        if (!collectingArmElbowPID.getPidController().atSetPoint()) {
            startTime = Calendar.getInstance().getTimeInMillis();
        }
        return Calendar.getInstance().getTimeInMillis() - startTime > 500;
    }

    @Override
    public void end(boolean interrupted) {
        //if the motor cannot finish, we will put a small amount of power at the "end"
        collectingArmElbowPID.setPower(0.1);
    }
}