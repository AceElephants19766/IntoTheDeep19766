package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Commands.RightElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRotat;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;
import org.firstinspires.ftc.teamcode.Subsystems.RightElbow;

@TeleOp
public class CollectingArmTest extends CommandOpMode {
    //Subsystem
    public Claw claw;
    public ClawRotat clawRotat;
    public HangArm hangArm;
    public RightElbow rightElbow;

    public GamepadEx gamepadEx1;

    @Override
    public void initialize() {

        gamepadEx1 = new GamepadEx(gamepad1);

        //Subsystems
        claw = new Claw(hardwareMap);
        clawRotat = new ClawRotat(hardwareMap);
        hangArm = new HangArm(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenPressed(
                new ClawCommand(claw)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X).toggleWhenPressed(
                new ClawRotateCommand(clawRotat)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileActiveOnce(
                new HangArmCommand(hangArm,-0.5)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whileActiveOnce(
                new HangArmCommand(hangArm,0.5)
        );

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).toggleWhenPressed(
                new RightElbowArm(rightElbow,0.5)
        );
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).toggleWhenPressed(
                new RightElbowArm(rightElbow,-0.5)
        );
    }
}
