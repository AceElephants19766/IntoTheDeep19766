package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MotorControllTest;

import java.util.Calendar;

public class PIDCommandTest extends CommandBase {

    public MotorControllTest motorControllTest;
    public int target;
    public long startTime;

    public PIDCommandTest(MotorControllTest motorControllTest, int target){
        this.motorControllTest = motorControllTest;
        this.target = target;
        addRequirements(motorControllTest);
    }

    @Override
    public void initialize() {
        motorControllTest.getPidController().setSetPoint(target);
    }

    @Override
    public void execute() {
        motorControllTest.setPower(
                motorControllTest.getPidController().calculate(
                        motorControllTest.getRevs()
                )
        );
    }

    @Override
    public boolean isFinished() {
        if (!motorControllTest.getPidController().atSetPoint()){
            startTime = Calendar.getInstance().getTimeInMillis();
        }
        return Calendar.getInstance().getTimeInMillis()-startTime > 500;
    }

    @Override
    public void end(boolean interrupted) {
        //if the motor cannot finish, we will put a small amount of power at the "end"
        motorControllTest.setPower(0);
    }
}
