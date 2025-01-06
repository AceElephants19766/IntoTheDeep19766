package org.firstinspires.ftc.teamcode.Test.TeleOpTests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowRunForSecondsCommandGroup;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowSetPower;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowToggle;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;
import org.firstinspires.ftc.teamcode.Test.SubsystemTests.RightElbow;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowRunForSeconds;


@Disabled
public class Test extends CommandOpMode {

    //subsystem
    public RightElbow rightElbow;
    public HangArm hangArm;

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    @Override
    public void initialize() {

        //GamePad
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //subsystem
        rightElbow = new RightElbow(hardwareMap);
        hangArm = new HangArm(hardwareMap);

        gamepadEx2.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                new RightElbowRunForSeconds(rightElbow, 5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).toggleWhenPressed(
                new RightElbowToggle(rightElbow)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm,-1)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whileActiveOnce(
                new HangArmCommand(hangArm,1)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).whenPressed(
                new SequentialCommandGroup(
                        new RightElbowSetPower(rightElbow, 0.5),
                        new WaitCommand(1000),
                        new RightElbowSetPower(rightElbow,0)
                )
        );
        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                new RightElbowRunForSecondsCommandGroup(rightElbow,1,5)
        );
    }
}
