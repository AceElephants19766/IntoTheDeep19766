package org.firstinspires.ftc.teamcode.Test.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;

@Disabled
public class ServoTest1 extends CommandOpMode {


    public HangArm hangArm;
    public GamepadEx gamepadEx1;

    @Override
    public void initialize() {

        gamepadEx1 = new GamepadEx(gamepad1);

        hangArm = new HangArm(hardwareMap);
//        collectingArmServo = new CollectingArmServo(hardwareMap);
//
//        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).toggleWhenPressed(
//                new CollectingElbowServoCommand(collectingArmServo, 0.7)
//        );
//
//        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).toggleWhenPressed(
//                new CollectingElbowServoCommand(collectingArmServo, 1)
//        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm, -0.5)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whileActiveOnce(
                new HangArmCommand(hangArm, 0.5)
        );
    }

}
