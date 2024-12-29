package org.firstinspires.ftc.teamcode.Teleop;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.ClawCommand;
import org.firstinspires.ftc.teamcode.Commands.ClawRotateCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.HangArmCommand;
import org.firstinspires.ftc.teamcode.Commands.ResetImu;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrainMecanum;
import org.firstinspires.ftc.teamcode.Test.CommandTests.RightElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawRotat;
import org.firstinspires.ftc.teamcode.Subsystems.HangArm;
import org.firstinspires.ftc.teamcode.Test.CommandTests.SubsystemTests.RightElbow;

@TeleOp
public class CollectingArmTest extends CommandOpMode {
    //Subsystem
    public Claw claw;
    public ClawRotat clawRotat;
    public HangArm hangArm;
    public RightElbow rightElbow;
    public DriveTrainMecanum driveTrainMecanum;

    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;

    @Override
    public void initialize() {

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        //Subsystems
        claw = new Claw(hardwareMap);
        clawRotat = new ClawRotat(hardwareMap);
        hangArm = new HangArm(hardwareMap);
        rightElbow = new RightElbow(hardwareMap);

        driveTrainMecanum = new DriveTrainMecanum(hardwareMap);

        //mecanum
        driveTrainMecanum.setDefaultCommand(
                new DriveCommand(
                        driveTrainMecanum,
                        gamepadEx2
                )
        );

        gamepadEx2.getGamepadButton(GamepadKeys.Button.START).whenPressed(
                new ResetImu(driveTrainMecanum)
        );


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
