package org.firstinspires.ftc.teamcode.Test.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Test.CommandTests.PIDCommandTest;
import org.firstinspires.ftc.teamcode.Test.SubsystemTests.MotorControllTest;

@Disabled
public class PIDTest extends CommandOpMode {

    public MotorControllTest motorControllTest;

    public GamepadEx gamepadEx1;

    @Override
    public void initialize() {
        gamepadEx1 = new GamepadEx(gamepad1);

        motorControllTest = new MotorControllTest(hardwareMap);

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new PIDCommandTest(motorControllTest,70)
        );


        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new PIDCommandTest(motorControllTest,-56)
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("motor revs", motorControllTest.getRevs());
        telemetry.update();
    }
}
