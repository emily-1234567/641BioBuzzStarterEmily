package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "shooter")
public class ShooterTest extends OpMode {
    private DcMotor shooter;
    double shooterPower = 0;

    @Override
    public void init() {
        shooter = hardwareMap.get(DcMotor.class, "thirdMotor");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            shooterPower = 1;
            shooter.setPower(shooterPower);
        }

        if (gamepad1.b) {
            shooterPower = -1;
            shooter.setPower(shooterPower);
        }
        if (gamepad1.x) {
            shooterPower = 0;
            shooter.setPower(shooterPower);
        }

        telemetry.addData("shooter", shooterPower);
    }
}
