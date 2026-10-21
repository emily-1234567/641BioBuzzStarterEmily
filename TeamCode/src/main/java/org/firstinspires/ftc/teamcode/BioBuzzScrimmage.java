package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOpBiobuzzScrimmage")
public class BioBuzzScrimmage extends OpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor firstIntake;
    private DcMotor secondIntake;
    //private DcMotor shooter;
    private
    double intakePower1 = 0.5;
    private
    double intakePower2 = 0.5;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        firstIntake = hardwareMap.get(DcMotor.class, "firstMotor");
        secondIntake = hardwareMap.get(DcMotor.class, "secondMotor");
        //shooter = hardwareMap.get(DcMotor.class, "shooter");

        // Usually needed so robot drives straight
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {

        double y = gamepad1.left_stick_y;   // forward/back
        double x = -gamepad1.left_stick_x;

        double rx = gamepad1.right_stick_x;

        double frontLeftPower  = y + x + rx;
        double frontRightPower = y - x - rx;
        double backLeftPower   = y - x + rx;
        double backRightPower  = y + x - rx;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

        if (gamepad1.a) {
            firstIntake.setPower(intakePower1);
            secondIntake.setPower(intakePower2);
        }

        if (gamepad1.b) {
            firstIntake.setPower(0);
            secondIntake.setPower(0);
        }

        if (gamepad1.x) {
            firstIntake.setPower(1);
            secondIntake.setPower(1);
        }

        if (gamepad1.y) {
            firstIntake.setPower(-1);
            secondIntake.setPower(-1);
        }
//         if (gamepad1.left_bumper) {
//            shooter.setPower(1);
//        }
//
//        if (gamepad1.right_bumper) {
//            shooter.setPower(0);
//        }

        if (gamepad1.dpad_up) {
            intakePower1 += 0.5;
            firstIntake.setPower(intakePower1);
        }

        if (gamepad1.dpad_down) {
            intakePower1 -= 0.5;
            firstIntake.setPower(intakePower1);
        }

        if (gamepad1.dpad_right) {
            intakePower2 += 0.5;
            secondIntake.setPower(intakePower2);
        }

        if (gamepad1.dpad_left) {
            intakePower2 -= 0.5;
            secondIntake.setPower(intakePower2);
        }

        telemetry.addData("Y", y);
        telemetry.addData("X", -x);
        telemetry.addData("RX", rx);
        telemetry.addData("intake power 1", intakePower1);
        telemetry.addData("intake power 2", intakePower2);
        telemetry.update();

    }
}