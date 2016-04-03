package sml;

/**
 * This class ....
 *
 * @author someone
 */

public class OutInstruction extends Instruction {

    private int result;
    private int registerData;

    public OutInstruction(String label, String op) {
        super(label, op);
    }

    public OutInstruction(String label, int result) {
        this(label, "out");
        this.result = result;

    }

    @Override
    public void execute(Machine m) {
        this.registerData = m.getRegisters().getRegister(result);

        // Print the contents of the register to java console
        System.out.println(" " + registerData);
    }

    @Override
    public String toString() {
        return super.toString() + " " + result;
    }
}