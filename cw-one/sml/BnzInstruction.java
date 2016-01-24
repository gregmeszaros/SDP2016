package sml;

/**
 * This class ....
 *
 * @author someone
 */

public class BnzInstruction extends Instruction {
    private int register;
    private String labelToJump;

    public BnzInstruction(String label, String opcode) {
        super(label, opcode);
    }

    public BnzInstruction(String label, int register, String labelToJump) {
        super(label, "bnz");
        this.register = register;
        this.labelToJump = labelToJump;

    }

    @Override
    public void execute(Machine m) {

        // If the contents of specified register is not zero, then jump to specified value
        if (m.getRegisters().getRegister(register) != 0) {

            // Get the index from the label and jump to that index
            int index = m.getLabels().indexOf(labelToJump);
            m.setPc(index);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " register " + register + " jump to " + labelToJump;
    }
}
